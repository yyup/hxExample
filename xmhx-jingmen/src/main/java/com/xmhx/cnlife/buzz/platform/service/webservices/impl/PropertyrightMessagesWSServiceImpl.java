package com.xmhx.cnlife.buzz.platform.service.webservices.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xmhx.cnlife.buzz.platform.dao.Webservices_messages_MessagesDAO;
import com.xmhx.cnlife.buzz.platform.model.webservices.MessagesDTO;
import com.xmhx.cnlife.buzz.platform.service.jpush.JpushClassifies;
import com.xmhx.cnlife.buzz.platform.service.webservices.PropertyrightMessagesWSService;
import com.xmhx.cnlife.buzz.utils.EnumUtils.MessageJpushType;
import com.xmhx.cnlife.buzz.utils.EnumUtils.MessageType;
import com.xmhx.cnlife.core.model.PageBean;
import com.xmhx.cnlife.core.model.PageEntity;
/**
 * 消息
 * @author yyp
 *
 */
@Service
public class PropertyrightMessagesWSServiceImpl implements PropertyrightMessagesWSService {
	
	@Resource
	private Webservices_messages_MessagesDAO  messagesDao;
	@Resource
	private JpushClassifies jpushClassifies;

	@Override
	public void addPropertyOverdue(MessagesDTO messageDTO) {
		messagesDao.addPropertyOverdue(messageDTO);
	}

	@Override
	public void updateMessagesIfreply(String[] uuids, String replyState) {
		messagesDao.updateMessagesIfreply(uuids, replyState);
	}

	@Override
	public MessagesDTO queryMessage(MessagesDTO messagesDTO) {
		return messagesDao.queryMessage(messagesDTO);
	}

	@Override
	public PageBean<MessagesDTO> getMessagesByPage(int page, int rows,
			String sort, String order, MessagesDTO message) {
		PageBean<MessagesDTO> pageBean = new PageBean<MessagesDTO>(page, rows);
		PageEntity<MessagesDTO> pageEntity = new PageEntity<MessagesDTO>(page, rows, sort, order, message, pageBean);
		
		int count = messagesDao.getMessageCount(pageEntity);
		List<MessagesDTO> list = messagesDao.getMessageList(pageEntity);
		
		pageBean.setDataList(list);
		pageBean.setPageCount(count);
		pageBean.setRecordCount(count);
		return pageBean;
	}

	@Override
	public void delMessages(String ids) {
		String[] idArray = ids.split(",");//转换成数组
		messagesDao.delMessages(idArray);
	}

	@Override
	public void jpusMessages(String ids) {
		try{
			//1、查询要推送的消息
			String[] idArray = ids.split(",");
			List<MessagesDTO> messages = messagesDao.queryMessagesByIds(idArray);
			//2、推送消息
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
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
