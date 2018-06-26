package com.xmhx.cnlife.buzz.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmhx.cnlife.buzz.platform.model.webservices.MessagesDTO;
import com.xmhx.cnlife.core.model.PageEntity;

public interface Webservices_messages_MessagesDAO {

	void addPropertyOverdue(MessagesDTO messageDTO);

	void updateMessagesIfreply(@Param("uuids") String[] uuids, @Param("replyState") String replyState);

	MessagesDTO queryMessage(MessagesDTO messagesDTO);

	int getMessageCount(PageEntity<MessagesDTO> pageEntity);

	List<MessagesDTO> getMessageList(PageEntity<MessagesDTO> pageEntity);

	void delMessages(String[] ids);

	List<MessagesDTO> queryMessagesByIds(String[] idArray);

}
