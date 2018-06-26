package com.xmhx.cnlife.buzz.platform.model.manages.attach;

import com.xmhx.cnlife.core.annotation.Entity;
import com.xmhx.cnlife.core.model.BaseDTO;

@Entity
public class AttachDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	
	private String attachName;		//附件上传名称
	private String attachOrigName;		//附件原始名称
	private String attachPath;		//附件保存路径
	private String attachType;		//附件类型(png|jpg|gif|txt|doc|docx|xls|xlsx)
	private String attachMod;		//附件来源模块 1 新闻 2 政策
	private String attachfkId;		//附件来源ID
	
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public String getAttachOrigName() {
		return attachOrigName;
	}
	public void setAttachOrigName(String attachOrigName) {
		this.attachOrigName = attachOrigName;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getAttachType() {
		return attachType;
	}
	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}
	public String getAttachMod() {
		return attachMod;
	}
	public void setAttachMod(String attachMod) {
		this.attachMod = attachMod;
	}
	public String getAttachfkId() {
		return attachfkId;
	}
	public void setAttachfkId(String attachfkId) {
		this.attachfkId = attachfkId;
	}

}
