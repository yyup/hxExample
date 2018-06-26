package com.xmhx.buzz.platform.model.test;

import com.xmhx.core.annotation.AutoIncrement;
import com.xmhx.core.annotation.Column;
import com.xmhx.core.annotation.Id;

public class TeacherDTO {

	private Integer tid;
	private String tname;
	private Integer gid;
	
	
	
	
	@Id
	@AutoIncrement
	@Column(columnName = "tid")
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	@Column(columnName="tname")
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	@Column(columnName="gid")
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	
	
}
