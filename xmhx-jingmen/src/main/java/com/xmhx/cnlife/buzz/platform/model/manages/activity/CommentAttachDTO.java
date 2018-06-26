package com.xmhx.cnlife.buzz.platform.model.manages.activity;

import com.xmhx.cnlife.core.annotation.Entity;
import com.xmhx.cnlife.core.model.BaseDTO;

/**
 * 评论附件
 * @author yyp by 20160406
 *
 */
@Entity
public class CommentAttachDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	private int id;
	// 附件上传名称
	private String attachname;
	// 附件中文名称
	private String attachorigname;
	// 保存路径
	private String attachpath;
	// 附件类型(.png\.jpg\.txt\.xls\.xlsx)
	private String attachtype;
	// 附件来源类型(1生产问题附件)
	private String attachmod;
	// 附件来源ID(FK)
	private String attachfkid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttachname() {
		return attachname;
	}
	public void setAttachname(String attachname) {
		this.attachname = attachname;
	}
	public String getAttachorigname() {
		return attachorigname;
	}
	public void setAttachorigname(String attachorigname) {
		this.attachorigname = attachorigname;
	}
	public String getAttachpath() {
		return attachpath;
	}
	public void setAttachpath(String attachpath) {
		this.attachpath = attachpath;
	}
	public String getAttachtype() {
		return attachtype;
	}
	public void setAttachtype(String attachtype) {
		this.attachtype = attachtype;
	}
	public String getAttachmod() {
		return attachmod;
	}
	public void setAttachmod(String attachmod) {
		this.attachmod = attachmod;
	}
	public String getAttachfkid() {
		return attachfkid;
	}
	public void setAttachfkid(String attachfkid) {
		this.attachfkid = attachfkid;
	}
	
}
