package com.xmhx.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmhx.cnlife.buzz.platform.service.manages.user.UserService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest extends BaseTest{
	@Autowired
	private UserService userService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_01_modifyPatternlock() {
		String mobile="13590142074";
		String patternlock = "123456789";
		int num = userService.modifyPatternlock(mobile, patternlock);
		Assert.assertEquals(num, 1);
	}
	@Test
	public void test_02_queryIfExistPatternlock() {
		String mobile="13590142074";
		int num = userService.queryIfExistPatternlock(mobile);
		Assert.assertEquals(num, 1);
	}
	@Test
	public void test_03_modifyPatternlock() {
		String mobile="13590142074";
		String patternlock = null;
		int num = userService.modifyPatternlock(mobile, patternlock);
		Assert.assertEquals(num, 1);
	}
	@Test
	public void test_04_queryIfExistPatternlock() {
		String mobile="14759206561";
		int num = userService.queryIfExistPatternlock(mobile);
		Assert.assertEquals(num, 0);
	}


}
