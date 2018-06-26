package com.xmhx.cnlife.buzz.common;

/**
 * 对系统中封装于Session|Application域中的对象进行KEY的定义
 * @author 吴进
 *
 */
public class SessionSettings {
	//---------- Session域中 ----------
	/** 前台用户Session KEY  **/
	public final static String VIEWUSER_SESSION = "view_user";
	/** 后台用户Session KEY **/
	public final static String USER_SESSION = "user";
	/** 短信SMS KEY **/
	public final static String SMS_SESSION = "sms_key";
	/** 短信SMS 发送的手机号码 **/
	public final static String SMS_PHONE_SESSION = "sms_phone";
	/** 手机短信 KEY **/
	public final static String MOBILE_MODIFIED_SMS_SESSION = "modify_smsverifycode";
	/** 输入错误出现验证码次数 **/
	public static int input_count = 1;
	/** 短信超时时间  **/
	public static int SMS_TIMELIMIT = 20*60*1000;
	
	//---------- Application域中 ----------
	/** 系统SEO信息 **/
	public final static String SEO = "seo";
	/** 系统网站信息 **/
	public final static String INTERNET = "internet";
	/** 关于导航条  **/
	public final static String ABOUT_BAR = "about_bar";
	/** 友情链接  **/
	public static final String LINK_LIST = "link_list";

	//---------- 普通域中 ----------
	/** 全部权限Object型 **/
	public static final String ALL_MENU_URLS = "all_menu_urls";
	/** 全部权限String型 **/
	public static final String STR_MENU_URLS = "menu_urls";
	/** 普通用户 **/
	public static final String GENERAL_USERS = "general_users";
	
}
