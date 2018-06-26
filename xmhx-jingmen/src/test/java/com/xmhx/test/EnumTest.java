package com.xmhx.test;

import org.junit.Test;

import com.xmhx.cnlife.buzz.utils.EnumUtils.ThreesidesNo;

public class EnumTest {

	@Test
	public void testThreesidesNo() {
		String code = ThreesidesNo.PROPERTYRIGHT.getCode();
		String name = ThreesidesNo.PROPERTYRIGHT.getName();
		System.out.println(code + " --> " + name);
	}
	
}
