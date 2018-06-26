package com.xmhx.cnlife.buzz.platform.model.manages.user;

import com.xmhx.cnlife.core.annotation.Entity;
import com.xmhx.cnlife.core.model.BaseDTO;

@Entity
public class UserDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	private String member_nick;
	private String member_name;
	private String member_mobile;
	private String member_pwd;
	private String member_head;
	private String first_char;
	private String member_sex;
	private String member_email;	
	private String pattern_lock;
	// 虚拟字段，列表中查询用
	private String nameormobile;
	
	public String getMember_nick() {
		return member_nick;
	}
	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_mobile() {
		return member_mobile;
	}
	public void setMember_mobile(String member_mobile) {
		this.member_mobile = member_mobile;
	}
	public String getMember_pwd() {
		return member_pwd;
	}
	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
	}
	public String getMember_head() {
		return member_head;
	}
	public void setMember_head(String member_head) {
		this.member_head = member_head;
	}
	public String getFirst_char() {
		return first_char;
	}
	public void setFirst_char(String first_char) {
		this.first_char = first_char;
	}
	public String getMember_sex() {
		return member_sex;
	}
	public void setMember_sex(String member_sex) {
		this.member_sex = member_sex;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getNameormobile() {
		return nameormobile;
	}
	public void setNameormobile(String nameormobile) {
		this.nameormobile = nameormobile;
	}
	public String getPattern_lock() {
		return pattern_lock;
	}
	public void setPattern_lock(String pattern_lock) {
		this.pattern_lock = pattern_lock;
	}
}
