package com.xmhx.test;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmhx.cnlife.buzz.platform.model.webservices.SysApplyDTO;
import com.xmhx.cnlife.buzz.platform.model.webservices.SysUserApplyDTO;
import com.xmhx.cnlife.buzz.platform.service.webservices.SysApplyWSService;
import com.xmhx.cnlife.buzz.platform.service.webservices.SysUserApplyWSService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SysUserAppServiceTest extends BaseTest {
	@Autowired
	private SysUserApplyWSService sysUserApplyWSService;
	@Autowired
	private SysApplyWSService sysApplyWSService;
	@Before
	public void before(){
		test_04_deleteSysUserApply();
	}
	//测试场景1
//	@Test
//	public void testScene1(){
//		test_2_addSysUserApply();
//		test_4_querySysUserApply();
//		test_1_queryAllSysApply();
//		test_3_deleteSysUserApply();
//	}
	@Test
	public void test_01_queryAllSysApply() {
		String mobile="13590142074";
		List<SysApplyDTO> sysApplyDTOList = sysApplyWSService.queryAllSysApply(mobile);
		System.out.println(sysApplyDTOList.toString());
	}
	@Test
	public void test_02_addSysUserApply() {
		String mobile="13590142074";
		SysUserApplyDTO sysUserApplyDTO = new SysUserApplyDTO();
		sysUserApplyDTO.setApply_code("easypushproject");
		sysUserApplyDTO.setApply_code("businessshow");
		sysUserApplyDTO.setUser_mobile(mobile);
		int num = sysUserApplyWSService.addSysUserApply(sysUserApplyDTO);
		System.out.println("添加APP："+num);
	}
	@Test
	public void test_03_querySysUserApply() {
		String mobile="13590142074";
		List<SysApplyDTO> sysApplyDTOList = sysUserApplyWSService.querySysUserApply(mobile);
		System.out.println(sysApplyDTOList.toString());
	}
	@Test
	public void test_04_deleteSysUserApply() {
		String mobile="13590142074";
		SysUserApplyDTO sysUserApplyDTO = new SysUserApplyDTO();
		sysUserApplyDTO.setApply_code("easypushproject");
		sysUserApplyDTO.setApply_code("businessshow");
		sysUserApplyDTO.setUser_mobile(mobile);
		int num = sysUserApplyWSService.deleteSysUserApply(sysUserApplyDTO);
		System.out.println("删除APP"+num);
	}
	

}
