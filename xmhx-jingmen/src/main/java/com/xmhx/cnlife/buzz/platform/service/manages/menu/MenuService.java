package com.xmhx.cnlife.buzz.platform.service.manages.menu;

import java.util.List;

import com.xmhx.cnlife.buzz.exception.ResponseException;
import com.xmhx.cnlife.buzz.platform.model.manages.init.MenuDTO;

public interface MenuService {

	List<MenuDTO> getAllmenus() throws ResponseException;
	
	List<MenuDTO> getChildrenmenu(String umname, String menucode) throws ResponseException;
	
//	MenuDTO findById(String id) throws ResponseException;
	
}
