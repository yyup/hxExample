package com.xmhx.buzz.platform.service.manages.init;

import java.util.List;

import com.xmhx.buzz.exception.ResponseException;
import com.xmhx.buzz.platform.model.manages.init.MenuDTO;

public interface MenuService {

	List<MenuDTO> getAllmenus() throws ResponseException;
	
	List<MenuDTO> getChildrenmenu(String umname, String menucode) throws ResponseException;
	
//	MenuDTO findById(String id) throws ResponseException;
	
}
