package com.xmhx.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmhx.cnlife.buzz.platform.services.propertyright.PropertyrightMessagesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring*.xml" })
public class PropertyrightMessagesServiceTest {

	@Autowired
	private PropertyrightMessagesService propertyrightMessagesService;
	
	@Test
	public void testPropertyrightMessagesService() {
		
		propertyrightMessagesService.propertyrightMessages();
		
	}
	
}
