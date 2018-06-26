package com.xmhx.cnlife.buzz.platform.service.webservices;

import java.util.List;

import com.xmhx.cnlife.buzz.platform.model.webservices.SysApplyDTO;

public interface SysApplyWSService {
	
	List<SysApplyDTO> queryAllSysApply(String user_mobile);//查询所有系统应用

}
