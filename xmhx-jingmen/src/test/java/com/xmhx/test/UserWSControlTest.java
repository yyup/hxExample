package com.xmhx.test;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.xmhx.cnlife.buzz.utils.SSOUtils;


public class UserWSControlTest {
	//登录
	@Test
	public void login() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/login.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "13590142074");
		map.put("pwd", "654321");
		map.put("pwdtype", "2");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	//获取用户列表分页（包括查找手机或用户名）
	@Test
	public void pagelist() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/pagelist.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", "19ebf10751d7cee6e4fa7c54be3a427c");
		map.put("rows", "10");
		map.put("nameormobile", "");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	//获取短信验证码
	@Test
	public void getCode() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/getCode.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "13590142074");
		map.put("type", "1"); //type (1 注册 2 修改手机号码 3 修改密码 4 找回密码)
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	//注册
	@Test
	public void register() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/register.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_mobile", "13590142074");
		map.put("member_name", "黄建明");
		//map.put("member_pwd", "123456");
		map.put("sendcode", "935718");// 如果sessionid为空，则通用码 6012888
		map.put("sessionid", "4B735CB9104F5C83E9D8E220D5474B24");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	
//	@Test
//	public void updateHeadPhoto() {
//		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/updateHeadPhoto.hx";
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("token", "6db671409fa4a6dec0541192d7d0a342");
//		String ret = SSOUtils.remoteStringService(map, url);
//		System.out.println(ret);
//	}
	//修改密码
	@Test
	public void modifyPassword() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/modifyPassword.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", "bf045a677074b48feb0609bddb47d4cd");
		map.put("oldpassword", "111111");
		map.put("newpassword", "123456");
		map.put("sendcode", "638416");// 如果sessionid为空，则通用码 6012888
		map.put("sessionid", "BD904ED1A4F08A0FDD5065EC80F1D058");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	//忘记密码（重置密码）
	@Test
	public void resetPassword() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/resetPassword.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "13590142074");
		map.put("newpassword", "111111");
		map.put("sendcode", "6012888");// 如果sessionid为空，则通用码 6012888
		map.put("sessionid", null);
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	//修改用户信息
	@Test
	public void modifyUserinfo() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/modifyUserinfo.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", "3cccc8000c1d1c8ba3d861f39457dfc4");
		//map.put("member_sex", "1");
		map.put("member_nick", "HUANGj0123");
	
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	//查询用户信息
	@Test
	public void queryUserinfo() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/queryUserinfo.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", "3f5b039b48753959a9b7a93ac9427113");
		map.put("mobile", "13590142074");
	
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	//退出
	@Test
	public void logout() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/logout.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", "3cccc8000c1d1c8ba3d861f39457dfc4");
	
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	
	@Test
	public void aaa() {
		
		System.out.println("好啊".length());
	}
	
	
	
	//验证短信验证码
//	@Test
//	public void verifiySms() {
//		String url = "http://localhost:8080/xmhx-cnlife/ws/commonmember/verifiySms.hx";
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("mobile", "13590142074");
//		map.put("sendcode", "244788");// 如果sessionid为空，则通用码 6012888
//		map.put("sessionid", "313C5BDC3884EBB250CA885551C2AD46");
//		map.put("type", "3"); //type (1 注册 2 修改手机号码 3 修改密码 4 找回密码)
//		String ret = SSOUtils.remoteStringService(map, url);
//		System.out.println(ret);
//	}
	
	
}
