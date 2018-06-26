package com.xmhx.buzz.platform.model.manages.init;

import com.xmhx.core.annotation.Column;
import com.xmhx.core.annotation.Entity;
import com.xmhx.core.annotation.Id;
import com.xmhx.core.model.BaseEntity;

@Entity
public class UserDTO extends BaseEntity {
	/**
	 * 主键自增id
	 */
	private Integer memberId;
	/**
	 * 账号
	 */
	private String memberNm; 
	/**
	 * 姓名
	 */
	private String memberName;
	/**
	 * 性别 1：男 2：女
	 */
	private String sex;
	/**
	 * 成员手机号码
	 */
	private String memberMobile;
	/**
	 * 成员密码
	 */
	private String memberPwd;
	/**
	 * 邮箱
	 */
	private String memberEmail;
	/**
	 * 职业
	 */
	private String memberJob; 
	/**
	 * 行业
	 */
	private String memberTrade;
	/**
	 * 头像
	 */
	private String memberHead; 
	/**
	 * 家乡
	 */
	private String memberHometown; 
	/**
	 * 毕业院校
	 */
	private String memberGraduated; 
	/**
	 * 公司
	 */
	private String memberCompany; 
	/**
	 * 简介
	 */
	private String memberDesc;
	/**
	 * 激活状态 1:激活2：未激活
	 */
	private String memberActivate;  
	/**
	 * 激活时间
	 */
	private String memberActivatime; 
	/**
	 * 使用状态 1:使用2：禁用
	 */
	private String memberUse;
	/**
	 * 最后一次登录时间
	 */
	private String memberLogintime;  
	/**
	 * 登录次数
	 */
	private Integer memberLoginnum;
	/**
	 * 短信验证码
	 */
	private String smsNo;
	/**
	 * 首字母
	 */
	private String firstChar;
	
	@Id
	@Column(columnName="member_id")
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	@Column(columnName="member_nm")
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	@Column(columnName="member_name")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Column(columnName="sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(columnName="member_mobile")
	public String getMemberMobile() {
		return memberMobile;
	}
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	@Column(columnName="member_pwd")
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	@Column(columnName="member_email")
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	@Column(columnName="member_job")
	public String getMemberJob() {
		return memberJob;
	}
	public void setMemberJob(String memberJob) {
		this.memberJob = memberJob;
	}
	@Column(columnName="member_trade")
	public String getMemberTrade() {
		return memberTrade;
	}
	public void setMemberTrade(String memberTrade) {
		this.memberTrade = memberTrade;
	}
	@Column(columnName="member_head")
	public String getMemberHead() {
		return memberHead;
	}
	public void setMemberHead(String memberHead) {
		this.memberHead = memberHead;
	}
	@Column(columnName="member_hometown")
	public String getMemberHometown() {
		return memberHometown;
	}
	public void setMemberHometown(String memberHometown) {
		this.memberHometown = memberHometown;
	}
	@Column(columnName="member_graduated")
	public String getMemberGraduated() {
		return memberGraduated;
	}
	public void setMemberGraduated(String memberGraduated) {
		this.memberGraduated = memberGraduated;
	}
	@Column(columnName="member_company")
	public String getMemberCompany() {
		return memberCompany;
	}
	public void setMemberCompany(String memberCompany) {
		this.memberCompany = memberCompany;
	}
	@Column(columnName="member_desc")
	public String getMemberDesc() {
		return memberDesc;
	}
	public void setMemberDesc(String memberDesc) {
		this.memberDesc = memberDesc;
	}
	@Column(columnName="member_activate")
	public String getMemberActivate() {
		return memberActivate;
	}
	public void setMemberActivate(String memberActivate) {
		this.memberActivate = memberActivate;
	}
	@Column(columnName="member_activatime")
	public String getMemberActivatime() {
		return memberActivatime;
	}
	public void setMemberActivatime(String memberActivatime) {
		this.memberActivatime = memberActivatime;
	}
	@Column(columnName="member_use")
	public String getMemberUse() {
		return memberUse;
	}
	public void setMemberUse(String memberUse) {
		this.memberUse = memberUse;
	}
	@Column(columnName="member_logintime")
	public String getMemberLogintime() {
		return memberLogintime;
	}
	public void setMemberLogintime(String memberLogintime) {
		this.memberLogintime = memberLogintime;
	}
	@Column(columnName="member_loginnum")
	public Integer getMemberLoginnum() {
		return memberLoginnum;
	}
	public void setMemberLoginnum(Integer memberLoginnum) {
		this.memberLoginnum = memberLoginnum;
	}
	@Column(columnName="sms_no")
	public String getSmsNo() {
		return smsNo;
	}
	public void setSmsNo(String smsNo) {
		this.smsNo = smsNo;
	}
	@Column(columnName="first_char")
	public String getFirstChar() {
		return firstChar;
	}
	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}  
	
}
