package com.xmhx.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import net.sf.json.JSONObject;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.xmhx.cnlife.buzz.utils.SSOUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserWSControlTest_patternlock {
	private String token = null;
	@Before
	public void setUp() throws Exception {
		login();
	}

	@After
	public void tearDown() throws Exception {
	}

	//登录
	private void login() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/login.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "13590142074");
		map.put("pwd", "111111");
		map.put("pwdtype", "1");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
		JSONObject jsonobj = JSONObject.fromObject(ret);
		boolean status = (boolean)jsonobj.get("status");
		if(status){
			JSONObject obj2 = jsonobj.getJSONObject("attr");
			this.token = obj2.get("token").toString();
		}
	}
	//打开并设置手势密码接口
	@Test
	public void test_01_patternlock() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/patternlock.hx";
		Map<String, String> map = new HashMap<String, String>();
		TestCase.assertNotNull(token);
		map.put("token", token);
		map.put("patternlock", "654321");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
		JSONObject jsonobj = JSONObject.fromObject(ret);
		TestCase.assertEquals(jsonobj.get("status").toString(), "true");
		TestCase.assertEquals(jsonobj.get("info").toString(),"设置手势密码成功");
	}
	//查询手势密码开关
	@Test
	public void test_01a_queryifexistpatternlock() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/queryifexistpatternlock.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "13590142074");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
		JSONObject jsonobj = JSONObject.fromObject(ret);
		TestCase.assertEquals(jsonobj.get("status").toString(), "true");
		TestCase.assertEquals(jsonobj.get("info").toString(),"查询成功");
		TestCase.assertEquals(jsonobj.getJSONObject("attr").get("ifpatternlock").toString(),"1");
	}
	//关闭手势密码
	@Test
	public void test_02_closepatternlock() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/closepatternlock.hx";
		Map<String, String> map = new HashMap<String, String>();
		TestCase.assertNotNull(token);
		map.put("token", token);
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
		JSONObject jsonobj = JSONObject.fromObject(ret);
		TestCase.assertEquals(jsonobj.get("status").toString(), "true");
		TestCase.assertEquals(jsonobj.get("info").toString(),"关闭手势密码成功");
	}
	//忘记手势密码
	@Test
	public void test_03_clearpatternlock_1() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/clearpatternlock.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "13590142074");
		map.put("optiontype", "1");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
		JSONObject jsonobj = JSONObject.fromObject(ret);
		TestCase.assertEquals(jsonobj.get("status").toString(), "true");
		TestCase.assertEquals(jsonobj.get("info").toString(),"关闭手势密码成功");
	}
	//打开并设置手势密码接口
	@Test
	public void test_03a_patternlock() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/patternlock.hx";
		Map<String, String> map = new HashMap<String, String>();
		TestCase.assertNotNull(token);
		map.put("token", token);
		map.put("patternlock", "654321");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
		JSONObject jsonobj = JSONObject.fromObject(ret);
		TestCase.assertEquals(jsonobj.get("status").toString(), "true");
		TestCase.assertEquals(jsonobj.get("info").toString(),"设置手势密码成功");
	}
	//切换账户登录
	@Test
	public void test_04_clearpatternlock_2() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/clearpatternlock.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "13590142074");
		map.put("optiontype", "2");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
		JSONObject jsonobj = JSONObject.fromObject(ret);
		TestCase.assertEquals(jsonobj.get("status").toString(), "true");
		TestCase.assertEquals(jsonobj.get("info").toString(),"关闭手势密码成功");
	}
	//查询手势密码开关
	@Test
	public void test_05_queryifexistpatternlock() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/queryifexistpatternlock.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "13590142074");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
		JSONObject jsonobj = JSONObject.fromObject(ret);
		TestCase.assertEquals(jsonobj.get("status").toString(), "true");
		TestCase.assertEquals(jsonobj.get("info").toString(),"查询成功");
		TestCase.assertEquals(jsonobj.getJSONObject("attr").get("ifpatternlock").toString(),"0");
	}

}
