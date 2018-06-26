package com.xmhx.buzz.platform.model.test;

import com.xmhx.core.annotation.AutoIncrement;
import com.xmhx.core.annotation.Column;
import com.xmhx.core.annotation.Id;

public class StudentDTO {

	private Integer sid;
	private String sname;
	private Integer gid;
	
	
	
	@Id
	@AutoIncrement
	@Column(columnName = "sid")
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	@Column(columnName="sname")
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	@Column(columnName="gid")
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	
	
}
