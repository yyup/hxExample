package com.xmhx.cnlife.buzz.platform.service.jpush;

import java.util.Map;

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

import com.xmhx.cnlife.buzz.utils.EnumUtils.MessageJpushType;


@Component
public class JPushAgent {
	public static Logger logger = Logger.getLogger(JPushAgent.class);
	
	private static String notifyall = MessageJpushType.NOTIFYALL.getPushType();//通知（20）
	private static String messageall = MessageJpushType.MESSAGEALL.getPushType();//自定义（10）
	
	private static JPushClient jpush = new JPushClient("dd61b5a42a045b3eedcbe518","956015e394cf399a9413d4d0", 3);
	
	public PushResult pushMessages(String mobiles, String notifyTitile, String notifyContent,  Map<String, String> extra, String remindFlag) {
		logger.info("极光推送开始，手机号 = " + mobiles + "，是否自定义消息 = " + remindFlag);
		PushResult pushResult = null;
		PushPayload payload = null;
			try{
				
				if (messageall.equals(remindFlag)) {//自定义消息（全平台）
					payload = all_message_alias(mobiles, notifyTitile, 
							notifyContent, extra);
				} else if (notifyall.equals(remindFlag)){//通知（全平台）
					payload = all_notification_alias(mobiles, notifyTitile, 
							notifyContent, extra);
				}
				pushResult = jpush.sendPush(payload);//调用极光接口，获取推送状态信息
				logger.info("Got result - " + pushResult);
				
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
	                .setPlatform(Platform.android_ios())//----------------------推送平台（Android、ios）
	                .setAudience(Audience.alias(mobiles.split(",")))//----------推送目标（使用别名）
	                .setNotification(Notification.newBuilder()
	                		.setAlert(notifyContent)//--------------------------各平台通知内容
	                		.addPlatformNotification(AndroidNotification.newBuilder()
	                				.setTitle(notifyTitile)//-------------------Android平台通知标题
	                				.addExtras(extra)//-------------------------Android平台扩展字段
	                				.build())
	                		.addPlatformNotification(IosNotification.newBuilder()
	                				.incrBadge(+1)   //-------------------------应用角标（+1： 没接收到一个推送+1）
	                				.setSound("happy")//------------------------声音设置
//	                				.setAlert(notifyTitile)
	                				.addExtras(extra).build())
	                		.build())
	                		.setMessage(Message.newBuilder()//------------------自定义消息（ios接收用）
		                        .setTitle(notifyTitile)//-----------------------自定义消息标题
		                        .setMsgContent(notifyContent)//-----------------自定义消息内容
		                        .addExtras(extra)//-----------------------------自定义消息扩展字段
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
		                 .setMessage(Message.newBuilder()
		                        .setTitle(notifyTitile)
		                        .setMsgContent(notifyContent)
		                        .addExtras(extra)
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

