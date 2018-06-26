package com.xmhx.cnlife.buzz.platform.dao;

import java.util.List;

import com.xmhx.cnlife.buzz.platform.model.webservices.SysApplyDTO;
import com.xmhx.cnlife.buzz.platform.model.webservices.SysUserApplyDTO;

public interface Sys_user_apply_UserAppDAO {
	List<SysApplyDTO> querySysUserApply(String mobile);//根据手机号查询用户应用

	int addSysUserApply(SysUserApplyDTO sysUserApplyDTO);//添加用户应用
	
	int deleteSysUserApply(SysUserApplyDTO sysUserApplyDTO);//删除用户应用
}
