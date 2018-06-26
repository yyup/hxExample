package com.xmhx.cnlife.buzz.platform.model.manages.rights;

import com.xmhx.cnlife.core.annotation.Column;
import com.xmhx.cnlife.core.annotation.Entity;
import com.xmhx.cnlife.core.model.BaseEntity;

@Entity
public class RoleDTO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
