package com.xmhx.buzz.platform.model.manages.init;

import com.xmhx.core.annotation.Column;
import com.xmhx.core.annotation.Entity;
import com.xmhx.core.model.BaseEntity;
@Entity
public class RoleDTO extends BaseEntity {
	private String rolename;
	private String roledesc;
	
	@Column(columnName="rolename")
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	@Column(columnName="roledesc")
	public String getRoledesc() {
		return roledesc;
	}
	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}
	
}
