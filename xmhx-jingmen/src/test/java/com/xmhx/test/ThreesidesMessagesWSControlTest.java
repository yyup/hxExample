package com.xmhx.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.xmhx.cnlife.buzz.utils.SSOUtils;

public class ThreesidesMessagesWSControlTest {

	//物业合同租赁等逾期提醒接口
	// 物业逾期提醒:  String msgMobile, String msgTitle, String msgContent, String msgType, String costName, String costMoney,
	//* 					 String costCutoff, String costOverdue, String overdueFine, String sumMoney, String discription
	
	// 租赁合同即将到期提醒:  String msgMobile, String msgTitle, String msgContent, String msgType, String contractNo, String contractType,
	//* 					 String contractCutoff, String daysRemaining, String discription
	@Test
	public void testPropertyrightMessagesWSControl101(){
		String url = "http://192.168.21.109:8678/xmhx-cnlife/ws/threesidesMessages.hx";
//		String url = "http://localhost:8071/xmhx-cnlife/ws/threesidesMessages.hx";
		Map<String, String> map = new HashMap<String, String>();
		//物业：ffee98a5-ba89-11e6-9a0b-9ea20a09670a  集采：0b9c00d5-ba8a-11e6-9a0b-9ea20a09670a
		map.put("threesidesNo", "ffee98a5-ba89-11e6-9a0b-9ea20a09670a");
		map.put("msgMobile", "15859203465");
		map.put("msgTitle", "物业费逾期提醒");
		map.put("msgContent", "尊敬的云创智谷业主,您有逾期未缴费用,具体消息如下");
		
		//物业-逾期提醒
		map.put("msgType", "101");
		map.put("costName", "物业管理费");
		map.put("costMoney", "1000");
		map.put("costCutoff", "2016-10-15");
		map.put("costOverdue", "15天");
		map.put("overdueFine", "45元");
		map.put("sumMoney", "1045元");
		map.put("discription", "请尽快缴纳物业费,避免产生更多的滞纳金");
		
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	@Test
	public void testPropertyrightMessagesWSControl102(){
		String url = "http://192.168.21.109:8678/xmhx-cnlife/ws/threesidesMessages.hx";
//		String url = "http://localhost:8071/xmhx-cnlife/ws/threesidesMessages.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("threesidesNo", "ffee98a5-ba89-11e6-9a0b-9ea20a09670a");
		map.put("msgMobile", "15859203465");
		map.put("msgTitle", "租赁合同即将到期提醒");
		map.put("msgContent", "尊敬的云创智谷业主,您有合同即将到期, 请及明联系弘信物业管理处续订合同或其它操作");
		
		//物业-租赁合同即将到期提醒
		map.put("msgType", "102");
		map.put("contractNo", "ht7845621");
		map.put("contractType", "租赁合同");
		map.put("contractCutoff", "2016-10-15");
		map.put("daysRemaining", "15天");
		map.put("discription", "请联系弘信物业管理处合同管理人员杨怀玉,电话:0592-1234567,谢谢");
		
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	
	@Test
	public void testPropertyrightMessagesWSControl301(){
		String url = "http://192.168.21.109:8678/xmhx-cnlife/ws/threesidesMessages.hx";
//		String url = "http://localhost:8071/xmhx-cnlife/ws/threesidesMessages.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("threesidesNo", "0b9c00d5-ba8a-11e6-9a0b-9ea20a09670a");
		map.put("msgMobile", "15859203465");
		//集采
		map.put("msgTitle", "买家下单提醒-已下单");
		map.put("msgContent", "sj4050已下单(订单号:10998)");
		map.put("msgType", "301");
		map.put("discription", "sj4050已下单(订单号:10998)，请确认");
		
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	
	@Test
	public void testPropertyrightMessagesWSControl302(){
		String url = "http://192.168.21.109:8678/xmhx-cnlife/ws/threesidesMessages.hx";
//		String url = "http://localhost:8071/xmhx-cnlife/ws/threesidesMessages.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("threesidesNo", "0b9c00d5-ba8a-11e6-9a0b-9ea20a09670a");
		map.put("msgMobile", "15859203465");
		//集采
		map.put("msgTitle", "买家下单提醒-新订单");
		map.put("msgContent", "2016-12-02 17:30买家有提交新的订单");
		map.put("msgType", "302");
		map.put("discription", "2016-12-02 17:30买家有提交新的订单，请确认");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	
	@Test
	public void testPropertyrightMessagesWSControl303(){
		String url = "http://192.168.21.109:8678/xmhx-cnlife/ws/threesidesMessages.hx";
//		String url = "http://localhost:8071/xmhx-cnlife/ws/threesidesMessages.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("threesidesNo", "0b9c00d5-ba8a-11e6-9a0b-9ea20a09670a");
		map.put("msgMobile", "15859203465");
		
		//集采
		map.put("msgTitle", "已接单提醒");
		map.put("msgContent", "您的订单号：10998我们于 2016-12-02 19:30已接单");
		map.put("msgType", "303");
		map.put("discription", "您的订单号：10998我们于 2016-12-02 19:30已接单，请耐心等待...");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	
	@Test
	public void testPropertyrightMessagesWSControl304(){
		String url = "http://192.168.21.109:8678/xmhx-cnlife/ws/threesidesMessages.hx";
//		String url = "http://localhost:8071/xmhx-cnlife/ws/threesidesMessages.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("threesidesNo", "0b9c00d5-ba8a-11e6-9a0b-9ea20a09670a");
		map.put("msgMobile", "15859203465");
		//集采
		map.put("msgTitle", "已发货提醒");
		map.put("msgContent", "您的订单号：10998我们于  2016-12-02 04:30已发出");
		map.put("msgType", "304");
		map.put("discription", "您的订单号：10998我们于  2016-12-02 04:30已发出，请注意查收");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	
	@Test
	public void testPropertyrightMessagesWSControl305(){
		String url = "http://192.168.21.109:8678/xmhx-cnlife/ws/threesidesMessages.hx";
//		String url = "http://localhost:8071/xmhx-cnlife/ws/threesidesMessages.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("threesidesNo", "0b9c00d5-ba8a-11e6-9a0b-9ea20a09670a");
		map.put("msgMobile", "15859203465");
		//集采
		map.put("msgTitle", "买家退单提醒");
		map.put("msgContent", "2016-12-02 13:40 买家因**已退单");
		map.put("msgType", "305");
		map.put("discription", "您2016-12-02 13:40 买家因**已退单，请确认");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	
	@Test
	public void testPropertyrightMessagesWSControl306(){
		String url = "http://192.168.21.109:8678/xmhx-cnlife/ws/threesidesMessages.hx";
//		String url = "http://localhost:8071/xmhx-cnlife/ws/threesidesMessages.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("threesidesNo", "0b9c00d5-ba8a-11e6-9a0b-9ea20a09670a");
		map.put("msgMobile", "15859203465");
		//集采
		map.put("msgTitle", "商家退单提醒");
		map.put("msgContent", "抱歉！您的订单号：11000因地址错误，我们于2016-12-02 11:50 已关闭（申请退款成功）");
		map.put("msgType", "306");
		map.put("discription", "抱歉！您的订单号：11000因地址错误，我们于2016-12-02 11:50 已关闭（申请退款成功），请确认");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
}
