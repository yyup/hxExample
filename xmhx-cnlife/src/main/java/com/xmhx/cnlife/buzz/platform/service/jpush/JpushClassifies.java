package com.xmhx.cnlife.buzz.platform.service.jpush;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.jpush.api.push.PushResult;

@Component
public class JpushClassifies {
	
	private static JpushClassifies jc = null;
	
	public static JpushClassifies getInstance(){
		if(jc==null){
			 jc = new JpushClassifies();
		}
		return jc;
	}
	
	private static final Logger logger = Logger.getLogger(JpushClassifies.class);
	
	@Resource
	private JPushAgent jPushAgent;
	
	
	/**
	  *@see
	  *@param mobiles 要推送的对象（手机号码），多个用“，”隔开
	  *@param notifyTitile 通知栏标题
	  *@param notifyContent 通知栏内容
	  *@param extra 手机端解析的参数内容,无参数为null(map格式的key，value)
	  *@param typeName 模块名称（例：租赁合同到期提醒）
	  *@param remindFlag 是否自定义消息 (1* 自定义消息   2*为 通知)
	 */
	public void toJpush(String mobiles, String notifyTitile, String notifyContent, Map<String, String> extra, String typeName, String remindFlag){
		try {
			logger.info(typeName + "消息推送信息为：手机号码  = "+ mobiles + ",通知栏标题 = " + notifyTitile + ",通知栏内容= " + notifyContent + ",是否自定义消息 = " + remindFlag);
			Long starttime = System.currentTimeMillis() / 1000;
//			ExecutorService es = Executors.newFixedThreadPool(10);//线程池
			
			if(null != mobiles){
				
//				JpushThread jThread = new JpushThread(mobiles, notifyTitile, notifyContent, extra, remindFlag);
//				Future<PushResult> future = es.submit(jThread);					
//				if (future.isDone()) 
//					logger.info(typeName + "，消息推送成功：手机号码  = "+mobiles);
//				else
//					logger.info(typeName + "，消息推送失败：手机号码  = "+mobiles);
				
				PushResult result = jPushAgent.pushMessages(mobiles, notifyTitile, notifyContent, extra, remindFlag);
				if(result.isResultOK()){
					logger.info("极光推送完成，手机号 = " + mobiles);
				} 
		    }
			Long endtime = System.currentTimeMillis() / 1000;
			logger.info(typeName + "消息推送结束：手机号码  = "+mobiles + 
					"，起始时间毫秒 = " + starttime + "，结束时间毫秒 = " + endtime + "，耗时 = " + (endtime - starttime));
		} catch (Exception e) {
			logger.error(typeName + "消息推送失败，原因为：" + e);
		}
	}

}
