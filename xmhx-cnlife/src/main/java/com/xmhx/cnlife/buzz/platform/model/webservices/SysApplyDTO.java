package com.xmhx.cnlife.buzz.platform.model.webservices;

import com.xmhx.cnlife.core.model.BaseDTO;

public class SysApplyDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private String apply_name;	//应用名称
	private String apply_code;	//应用编码
	private String apply_icon;	//应用图标 (暂未使用)
	private String apply_title;	//应用副标题 (暂未使用)
	private String apply_desc;	//应用简介 (暂未使用)
	private String apply_belong;	//归属(1移动办公 2企业管理 3其他服务)
	private String apply_ifprime;	//是否推荐(0否 1是) (暂未使用)
	private String apply_ifwap;	//应用类型(0原生 1wap)
	private String apply_url;	//URL访问地址
	private String apply_no;	//排序
	//虚拟字段
	private String user_mobile;	//用户手机号
	
	public String getApply_name() {
		return apply_name;
	}
	public void setApply_name(String apply_name) {
		this.apply_name = apply_name;
	}
	public String getApply_code() {
		return apply_code;
	}
	public void setApply_code(String apply_code) {
		this.apply_code = apply_code;
	}
	public String getApply_icon() {
		return apply_icon;
	}
	public void setApply_icon(String apply_icon) {
		this.apply_icon = apply_icon;
	}
	public String getApply_title() {
		return apply_title;
	}
	public void setApply_title(String apply_title) {
		this.apply_title = apply_title;
	}
	public String getApply_desc() {
		return apply_desc;
	}
	public void setApply_desc(String apply_desc) {
		this.apply_desc = apply_desc;
	}
	public String getApply_belong() {
		return apply_belong;
	}
	public void setApply_belong(String apply_belong) {
		this.apply_belong = apply_belong;
	}
	public String getApply_ifprime() {
		return apply_ifprime;
	}
	public void setApply_ifprime(String apply_ifprime) {
		this.apply_ifprime = apply_ifprime;
	}
	public String getApply_ifwap() {
		return apply_ifwap;
	}
	public void setApply_ifwap(String apply_ifwap) {
		this.apply_ifwap = apply_ifwap;
	}
	public String getApply_url() {
		return apply_url;
	}
	public void setApply_url(String apply_url) {
		this.apply_url = apply_url;
	}
	public String getApply_no() {
		return apply_no;
	}
	public void setApply_no(String apply_no) {
		this.apply_no = apply_no;
	}
	public String getUser_mobile() {
		return user_mobile;
	}
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

	
}
