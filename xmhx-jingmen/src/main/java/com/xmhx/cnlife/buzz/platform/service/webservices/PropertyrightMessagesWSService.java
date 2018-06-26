package com.xmhx.cnlife.buzz.platform.service.webservices;

import com.xmhx.cnlife.buzz.platform.model.webservices.MessagesDTO;
import com.xmhx.cnlife.core.model.PageBean;

public interface PropertyrightMessagesWSService {

	void addPropertyOverdue(MessagesDTO messageDTO);

	void updateMessagesIfreply(String[] uuids, String replyState);

	MessagesDTO queryMessage(MessagesDTO messagesDTO);

	PageBean<MessagesDTO> getMessagesByPage(int page, int rows, String sort,
			String order, MessagesDTO message);

	void delMessages(String ids);

	void jpusMessages(String ids);

}
