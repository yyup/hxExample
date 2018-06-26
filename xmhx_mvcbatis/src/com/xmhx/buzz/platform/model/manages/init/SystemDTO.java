package com.xmhx.buzz.platform.model.manages.init;

import com.xmhx.core.annotation.Column;
import com.xmhx.core.annotation.Entity;
import com.xmhx.core.model.BaseEntity;
@Entity
public class SystemDTO extends BaseEntity {
	private String systemname;
	
	@Column(columnName="systemname")
	public String getSystemname() {
		return systemname;
	}
	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}
}
