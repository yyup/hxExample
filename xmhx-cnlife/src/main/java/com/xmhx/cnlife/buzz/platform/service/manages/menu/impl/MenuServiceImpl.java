package com.xmhx.cnlife.buzz.platform.service.manages.menu.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmhx.cnlife.buzz.exception.ResponseException;
import com.xmhx.cnlife.buzz.platform.dao.Manages_menu_MenuDAO;
import com.xmhx.cnlife.buzz.platform.model.manages.init.MenuDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.menu.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private Manages_menu_MenuDAO menuDAO;
	
	@Override
	public List<MenuDTO> getAllmenus() throws ResponseException {
		return menuDAO.getAllmenus();
	}
	
	@Override
	public List<MenuDTO> getChildrenmenu(String umname, String menucode) throws ResponseException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("umname", umname);
		map.put("menucode", menucode);
		return menuDAO.getChildrenmenu(map);
	}

//	@Override
//	public MenuDTO findById(String id) throws ResponseException {
//		return menuDAO.findById(id);
//	}

}
