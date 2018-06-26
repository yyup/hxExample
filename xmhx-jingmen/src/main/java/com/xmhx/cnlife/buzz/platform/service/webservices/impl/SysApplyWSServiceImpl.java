package com.xmhx.cnlife.buzz.platform.service.webservices.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xmhx.cnlife.buzz.exception.ResponseException;
import com.xmhx.cnlife.buzz.platform.dao.Sys_apply_AppDAO;
import com.xmhx.cnlife.buzz.platform.model.webservices.SysApplyDTO;
import com.xmhx.cnlife.buzz.platform.service.webservices.SysApplyWSService;

@Service
public class SysApplyWSServiceImpl implements SysApplyWSService {
	@Resource
	private Sys_apply_AppDAO sysApplyDAO;
	
	@Override
	public List<SysApplyDTO> queryAllSysApply(String user_mobile) throws ResponseException {
		return sysApplyDAO.queryAllSysApply(user_mobile);
	}

}
