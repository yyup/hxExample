package com.xmhx.buzz.platform.service.manages.init.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmhx.buzz.exception.ResponseException;
import com.xmhx.buzz.platform.dao.Init_MenuDAO;
import com.xmhx.buzz.platform.model.manages.init.MenuDTO;
import com.xmhx.buzz.platform.service.manages.init.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private Init_MenuDAO menuDAO;
	
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
