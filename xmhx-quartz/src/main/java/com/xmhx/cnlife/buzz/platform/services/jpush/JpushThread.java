package com.xmhx.cnlife.buzz.platform.services.jpush;

import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

@Component
public class JpushThread implements Callable<PushResult> {
	
	public static Logger logger = Logger.getLogger(JpushThread.class);
	
	private static JPushClient jpush = new JPushClient("dd61b5a42a045b3eedcbe518","956015e394cf399a9413d4d0", 3);
	
	private String mobiles;
	private String notifyTitile;
	private String notifyContent;
	private Map<String, String> extra;
	private String remindFlag;

	public JpushThread() {
		
	}

	/**
	 * 根据别名发送推送
	 * @param timemillis 自增数字
	 * @param mobiles 手机号
	 * @param notifyTitile 通知标题
	 * @param notifyContent 通知内容  必填
	 * @param extra 附加参数
	 * @param remindFlag 是否自定义消息（1*无通知提示 2*有通知提示）
	 * @return
	 */
	public JpushThread(String mobiles, String notifyTitile, 
			String notifyContent, Map<String, String> extra, String remindFlag) {
		this.mobiles = mobiles;
		this.notifyTitile = notifyTitile;
		this.notifyContent = notifyContent;
		this.extra = extra;
		this.remindFlag = remindFlag;
	}
	
	@Override
	public PushResult call() throws Exception {
		logger.info("极光推送开始，手机号 = " + mobiles + "，是否自定义消息 = " + remindFlag);
		PushResult pushResult = null;
		PushPayload payload = null;
			try{
				
				if ("10".equals(remindFlag)) {//自定义消息（全平台）
					payload = all_message_alias(mobiles, notifyTitile, 
							notifyContent, extra);
				} else if ("20".equals(remindFlag)){//通知（全平台）
					payload = all_notification_alias(mobiles, notifyTitile, 
							notifyContent, extra);
				}
				pushResult = jpush.sendPush(payload);//调用极光接口，获取推送状态信息
				if(pushResult.isResultOK()){
					logger.info("极光推送成功，手机号 = " + mobiles);
				}
				
			} catch (APIConnectionException e) {  
				logger.error("Connection error. Should retry later. ", e);  
		    } catch (APIRequestException e) {  
		    	logger.error("极光推送异常，手机号 = " + mobiles +"，异常信息:"+ e.getErrorMessage());  
		    	logger.info("极光推送失败，手机号 = " + mobiles +"，HTTP Status: " + e.getStatus()+"Error Code: " + e.getErrorCode()+"Error Message: " + e.getErrorMessage()+"Msg ID: " + e.getMsgId());  
		    }
			
		return pushResult;
	}

	//----------------------------以下是通知的类型--------------------------------------------
	//推送整个平台
	public static PushPayload all_notification_alias(String mobiles, String notifyTitile, 
			String notifyContent, Map<String, String> extra) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(mobiles.split(",")))
                .setNotification(Notification.newBuilder()
                		.setAlert(notifyContent)
                		.addPlatformNotification(AndroidNotification.newBuilder()
                				.setTitle(notifyTitile)
                				.addExtras(extra)
                				.build())
                		.addPlatformNotification(IosNotification.newBuilder()
                				.incrBadge(+1)   //应用角标+1
//                				.setSound("happy");//声音
//                				.setAlert(notifyTitile)
                				.addExtras(extra).build())
                		.build())
                .setMessage(Message.newBuilder()
	                        .setTitle(notifyTitile)
	                        .setMsgContent(notifyContent)
	                        .addExtras(extra)
	                        .build())
                .build();
    }
	
	//推送安卓平台
	 public static PushPayload android_notification_alias(String mobiles, String notifyTitile, 
				String notifyContent, Map<String, String> extra) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android())
	                .setAudience(Audience.alias(mobiles.split(",")))
	                .setNotification(Notification.android(notifyContent, notifyTitile, extra))
	                .build();
	    }
	//推送ios平台
	 public static PushPayload ios_notification_alias(String mobiles, String notifyTitile, 
				String notifyContent, Map<String, String> extra) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.alias(mobiles.split(",")))
	                .setNotification(Notification.newBuilder()
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setAlert(notifyContent)
	                                .setBadge(+1)
	                                .setSound("happy")
	                                .addExtras(extra)
	                                .build())
	                        .build())
	                 .setOptions(Options.newBuilder()
	                         .setApnsProduction(true)
	                         .build())
	                 .build();
	    }
	 
	//----------------------------以下是自定义的类型--------------------------------------------
	 //推送全平台
	 public static PushPayload all_message_alias(String mobiles, String notifyTitile, 
				String notifyContent, Map<String, String> extra) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.alias(mobiles.split(",")))
	                .setMessage(Message.newBuilder()
	                        .setTitle(notifyTitile)
	                        .setMsgContent(notifyContent)
	                        .addExtras(extra)
	                        .build())
	                .build();
	    }
	 //推送android平台
	 public static PushPayload android_message_alias(String mobiles, String notifyTitile, 
				String notifyContent, Map<String, String> extra) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android())
	                .setAudience(Audience.alias(mobiles.split(",")))
	                .setMessage(Message.newBuilder()
	                        .setTitle(notifyTitile)
	                        .setMsgContent(notifyContent)
	                        .addExtras(extra)
	                        .build())
	                .build();
	    }
	//推送ios平台
	 public static PushPayload ios_message_alias(String mobiles, String notifyTitile, 
				String notifyContent, Map<String, String> extra) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.alias(mobiles.split(",")))
	                .setMessage(Message.newBuilder()
	                        .setTitle(notifyTitile)
	                        .setMsgContent(notifyContent)
	                        .addExtras(extra)
	                        .build())
	                .build();
	    }
}
