package com.xmhx.cnlife.buzz.platform.dao;

import java.util.List;

import com.xmhx.cnlife.buzz.platform.model.MessagesDTO;



/**
 * 菜单
 * @author 吴进 by 20161007
 *
 */
public interface Webservices_messages_PropertyrightDAO {

	List<MessagesDTO> queryMessages(String replayType);

	
}
