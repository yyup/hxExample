package com.xmhx.cnlife.buzz.platform.control.online;

import java.io.Serializable;
import java.util.Date;

/**
 * 在线用户内存 类.
 * 
 */
public class OnlineUser implements Serializable {

	private static final long serialVersionUID = 1418954919330809213L;

	// 当前用户访问唯一标识
	private String token;
	// 用户hxuuid
	private String memId;
	// 姓名
	private String memberNm;
	// 手机号码
	private String tel;

	// 访问时间
	private Date accessTime;
	// 验证码(临时变量)
	private String code;

	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	/** 构造器 **/
	public OnlineUser() {
		super();
	}
	public OnlineUser(String tel){
		this.tel = tel;
	}
	public OnlineUser(String memId, String tel){
		this.memId = memId;
		this.tel = tel;
	}
	public OnlineUser(String memId, String memberNm, String tel) {
		this.memId = memId;
		this.memberNm = memberNm;
		this.tel = tel;
	}
	
}
