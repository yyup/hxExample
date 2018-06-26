package com.xmhx.cnlife.buzz.platform.dao;

import java.util.List;

import com.xmhx.cnlife.buzz.platform.model.webservices.SysApplyDTO;

public interface Sys_apply_AppDAO {
	public List<SysApplyDTO> queryAllSysApply(String user_mobile);
}
