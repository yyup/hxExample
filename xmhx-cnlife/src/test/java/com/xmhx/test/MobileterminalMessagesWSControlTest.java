package com.xmhx.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.xmhx.cnlife.buzz.utils.SSOUtils;

public class MobileterminalMessagesWSControlTest {

	//更新应答状态
	@Test
	public void testUpdateMessagesIfreply(){
		String url = "http://localhost:8071/xmhx-cnlife/ws/updateMessagesIfreply.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", "343c532afc0daf9e7bcfa93317df7e34");
		map.put("uuids", "1952241856655360,1952242462633984");
		
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	
	//查询消息 
	@Test
	public void testQueryMessageById(){
		String url = "http://localhost:8071/xmhx-cnlife/ws/queryMessageById.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", "5841982e0868bd52b1510fd15b038ecc");
		map.put("hxuuid", "1952241856655360");
		
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
}
