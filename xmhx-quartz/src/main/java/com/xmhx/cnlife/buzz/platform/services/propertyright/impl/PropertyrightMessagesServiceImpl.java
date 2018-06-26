package com.xmhx.cnlife.buzz.platform.services.propertyright.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmhx.cnlife.buzz.platform.dao.Webservices_messages_PropertyrightDAO;
import com.xmhx.cnlife.buzz.platform.model.MessagesDTO;
import com.xmhx.cnlife.buzz.platform.services.jpush.JpushClassifies;
import com.xmhx.cnlife.buzz.platform.services.propertyright.PropertyrightMessagesService;
import com.xmhx.cnlife.buzz.utils.EnumUtils.MessageJpushType;
import com.xmhx.cnlife.buzz.utils.EnumUtils.MessageType;

@Service
public class PropertyrightMessagesServiceImpl implements
		PropertyrightMessagesService {

	@Autowired
	private Webservices_messages_PropertyrightDAO propertyrightDAO;
	@Autowired
	private JpushClassifies jpushClassifies;

	@Override
	public void propertyrightMessages() {
		
		// 1、获取待发消息数据
		List<MessagesDTO> messages = propertyrightDAO.queryMessages(MessagesDTO.REPLY_NOT);
		// 2、通过极光向移动端发送消息
		for (MessagesDTO messagesDTO : messages) {
			//组装扩展字段信息
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("hxuuid", messagesDTO.getHxuuid());
			extra.put("createTime", messagesDTO.getDate_created());
			extra.put("msgType", messagesDTO.getMsgType());
			extra.put("msgMobile", messagesDTO.getMsgMobile());//手机号码
			extra.put("msgTitle", messagesDTO.getMsgTitle());//内容标题
			//获取推送类型
			MessageType messageType = MessageType.getDescription(messagesDTO.getMsgType());
			String notifyall = MessageJpushType.NOTIFYALL.getPushType();//默认都是通知类型(整个平台)
			//调用推送类
			jpushClassifies.toJpush(messagesDTO.getMsgMobile(), messagesDTO.getMsgTitle(), messagesDTO.getMsgContent(), extra, messageType.getDescription(), notifyall);
		}
		
	}
	
}
