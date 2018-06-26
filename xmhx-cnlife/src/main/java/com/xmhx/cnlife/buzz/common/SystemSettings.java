package com.xmhx.cnlife.buzz.common;

/**
 * 系统设置参数
 * @author 吴进
 * @since 2015-05-20
 */
public class SystemSettings {
	/**
	 * 得到项目根目录
	 * 在系统AppContextListener进行封装
	 * @return 项目部署根目录
	 */
	public static String getRootPath() {
		return System.getProperty("root");
	}
}
