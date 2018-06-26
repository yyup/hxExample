package com.xmhx.buzz.platform.model;

import com.xmhx.buzz.utils.EnumUtils.DataType;
import com.xmhx.core.annotation.AutoIncrement;
import com.xmhx.core.annotation.Column;
import com.xmhx.core.annotation.Entity;
import com.xmhx.core.annotation.Id;
import com.xmhx.core.model.BaseEntity;

/**
 * 附件
 * @author ex_kjkfb_wujin by 20150921
 *
 */
@Entity
public class AttachDTO extends BaseEntity {

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
	
	@Id
	@AutoIncrement
	@Column(columnName="id", dataType=DataType.INT)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(columnName="attachname")
	public String getAttachname() {
		return attachname;
	}
	public void setAttachname(String attachname) {
		this.attachname = attachname;
	}
	@Column(columnName="attachorigname")
	public String getAttachorigname() {
		return attachorigname;
	}
	public void setAttachorigname(String attachorigname) {
		this.attachorigname = attachorigname;
	}
	@Column(columnName="attachpath")
	public String getAttachpath() {
		return attachpath;
	}
	public void setAttachpath(String attachpath) {
		this.attachpath = attachpath;
	}
	@Column(columnName="attachtype")
	public String getAttachtype() {
		return attachtype;
	}
	public void setAttachtype(String attachtype) {
		this.attachtype = attachtype;
	}
	@Column(columnName="attachmod")
	public String getAttachmod() {
		return attachmod;
	}
	public void setAttachmod(String attachmod) {
		this.attachmod = attachmod;
	}
	@Column(columnName="attachfkid")
	public String getAttachfkid() {
		return attachfkid;
	}
	public void setAttachfkid(String attachfkid) {
		this.attachfkid = attachfkid;
	}
	
}
