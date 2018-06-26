package com.xmhx.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.xmhx.cnlife.buzz.utils.SSOUtils;

public class SysUserApplyWSControlTest {
	@Test
	public void myapplylist() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/sysuserapply/myapplylist.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", "dc23b322d1fe867e1c770c0346cfdadb");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	@Test
	public void allapplylist() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/sysuserapply/allapplylist.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", "dc23b322d1fe867e1c770c0346cfdadb");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	@Test
	public void addsysapply() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/sysuserapply/addsysapply.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", "dc23b322d1fe867e1c770c0346cfdadb");
		map.put("applycode", "easypushproject");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}
	@Test
	public void delsysapply() {
		String url = "http://localhost:8080/xmhx-cnlife/ws/sysuserapply/delsysapply.hx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", "dc23b322d1fe867e1c770c0346cfdadb");
		map.put("applycode", "easypushproject");
		String ret = SSOUtils.remoteStringService(map, url);
		System.out.println(ret);
	}

}
