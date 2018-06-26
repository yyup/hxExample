package com.xmhx.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmhx.cnlife.buzz.platform.dao.Webservices_messages_PropertyrightDAO;
import com.xmhx.cnlife.buzz.platform.model.MessagesDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring*.xml" })
public class Webservices_messages_PropertyrightDAOTest {
	/* 事务
	 * 声明式、注解式
	 */
	
	@Autowired
	private Webservices_messages_PropertyrightDAO propertyrightDAO;

	@Test
	public void testQueryCorporationById() {
		
		List<MessagesDTO> messages = propertyrightDAO.queryMessages("1");
		System.out.println(messages.size());
	}
	
	

}
