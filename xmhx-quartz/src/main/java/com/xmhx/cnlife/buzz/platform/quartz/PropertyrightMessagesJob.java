package com.xmhx.cnlife.buzz.platform.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmhx.cnlife.buzz.platform.services.propertyright.PropertyrightMessagesService;

/**
 * 物业消息定时任务
 * @author 吴进 by 20161206
 * <p>
 */
public class PropertyrightMessagesJob {

	@Autowired
	private PropertyrightMessagesService propertyrightMessagesService;
	
	public void propertyrightmessagesquartz() {
		propertyrightMessagesService.propertyrightMessages();
	}
	
}
