package com.xmhx.buzz.common;

import com.xmhx.buzz.utils.DateUtils;

/**
 * 文件保存路径配置
 * @author wujin
 * 
 */
public class FilepathSettings {
	
	/** 上传资源保存nginx根目录  **/
	public final static String basePath = "/mnt/data";
	/** 保存文件根目录 **/
	public final static String rootPath = "data";
	/** 银票票据图片保存路径  **/
	public static String bankTicket = rootPath + "/bank_ticket/"+DateUtils.getDateNow();
	/** 商票票据图片保存路径   **/
	public static String enterpriseTicket = rootPath + "/enterprise_ticket/"+DateUtils.getDateNow();
	/** 保理项目图片保存路径   **/
	public static String factoringProduct = rootPath + "/factoring_product/"+DateUtils.getDateNow();
	
	/** 项目审核项图片保存路径   **/
	public static String audit_pic_path = rootPath + "/audit_pic/"+DateUtils.getDateNow();
	/** 用户实名认证图片保存路径   **/
	public static String idcard = rootPath + "/id_card/"+DateUtils.getDateNow();
	/** 友情链接保存图片   **/
	public static String friend_link = rootPath + "/flink/"+DateUtils.getDateNow();
	
}
