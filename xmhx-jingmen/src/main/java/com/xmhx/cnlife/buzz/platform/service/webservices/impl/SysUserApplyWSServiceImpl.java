package com.xmhx.cnlife.buzz.platform.service.webservices.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xmhx.cnlife.buzz.exception.ResponseException;
import com.xmhx.cnlife.buzz.platform.dao.Sys_user_apply_UserAppDAO;
import com.xmhx.cnlife.buzz.platform.model.webservices.SysApplyDTO;
import com.xmhx.cnlife.buzz.platform.model.webservices.SysUserApplyDTO;
import com.xmhx.cnlife.buzz.platform.service.webservices.SysUserApplyWSService;

@Service
public class SysUserApplyWSServiceImpl implements SysUserApplyWSService {

	@Resource
	private Sys_user_apply_UserAppDAO  sysUserApplyDAO;
	
	@Override
	public List<SysApplyDTO> querySysUserApply(String mobile) throws ResponseException{
		return sysUserApplyDAO.querySysUserApply(mobile);
	}

	@Override
	public int addSysUserApply(SysUserApplyDTO sysUserApplyDTO) throws ResponseException {
		String hxuuid = UUID.randomUUID().toString();
		sysUserApplyDTO.setHxuuid(hxuuid);
		return sysUserApplyDAO.addSysUserApply(sysUserApplyDTO);
	}

	@Override
	public int deleteSysUserApply(SysUserApplyDTO sysUserApplyDTO) throws ResponseException {
		return sysUserApplyDAO.deleteSysUserApply(sysUserApplyDTO);
	}

}
