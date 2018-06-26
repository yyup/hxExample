package com.xmhx.cnlife.buzz.platform.model.webservices;

import com.xmhx.cnlife.core.model.BaseDTO;

public class SysUserApplyDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private String user_mobile;	//用户手机号
	private String apply_code;	//应用编码
	
	public String getUser_mobile() {
		return user_mobile;
	}
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	public String getApply_code() {
		return apply_code;
	}
	public void setApply_code(String apply_code) {
		this.apply_code = apply_code;
	}
	
}
