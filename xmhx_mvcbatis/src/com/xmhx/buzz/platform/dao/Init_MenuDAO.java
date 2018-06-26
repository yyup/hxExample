package com.xmhx.buzz.platform.dao;

import java.util.List;
import java.util.Map;

import com.xmhx.buzz.platform.model.manages.init.MenuDTO;

/**
 * 菜单
 * @author 吴进 by 20161007
 *
 */
public interface Init_MenuDAO {

	List<MenuDTO> getAllmenus();
	
	List<MenuDTO> getChildrenmenu(Map<String, String> map);
	
//	MenuDTO findById(String id);
	
}
