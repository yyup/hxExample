package com.xmhx.cnlife.buzz.platform.control.manages.menu;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmhx.cnlife.buzz.common.SessionSettings;
import com.xmhx.cnlife.buzz.platform.model.manages.init.MenuDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.user.UserDTO;
//import com.xmhx.cnlife.buzz.platform.model.manages.init.UserDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.menu.MenuService;
import com.xmhx.cnlife.core.authority.LoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;

/**
 * 菜单控制层
 * @author 吴进 by 20161117
 *
 */
@Controller
@RequestMapping(value="/manages/")
public class MenuController extends BaseController {

	@Autowired
	private MenuService menuService;
	
	/**
	 * 进入首页
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="index.hx", method=RequestMethod.GET)
	public String index(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		List<MenuDTO> allmenusList = menuService.getAllmenus();
		session.setAttribute(SessionSettings.STR_MENU_URLS, getMenuUrls(allmenusList));
		map.put("topmenus", getTopMenus(allmenusList));
		map.put("logineduser", userDTO.getMember_mobile());
		return "manages/main/index";
	}
	
	/**
	 * 左支导航栏子菜单节点
	 * @param request
	 * @param menucode
	 * @param map
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="menu/childrenmenu.hx", method=RequestMethod.GET)
	public String childrenmenu(HttpSession session, String menucode, ModelMap map) {
		UserDTO userDTO = getUser(session);
		List<MenuDTO> menus = menuService.getChildrenmenu(userDTO.getMember_mobile(), menucode);
		map.put("menus", menus);
		return "manages/main/menu";
	}
	
	/**
	 * 全部菜单URL转换成String
	 * @param menulist
	 * @return
	 */
	private String getMenuUrls(List<MenuDTO> menulist) {
		if (menulist != null) {
			StringBuilder menu_urls  = new StringBuilder();
			for (MenuDTO adminMenu : menulist) {
				if (adminMenu != null)
					menu_urls.append("," + adminMenu.getMenu_url());
			}
			return menu_urls.toString();
		}
		return null;
	}
	
	/**
	 * 一级菜单转换成String
	 * @param menulist
	 * @return
	 */
	private List<MenuDTO> getTopMenus(List<MenuDTO> menulist) {
		if (menulist != null && !menulist.isEmpty()) {
			List<MenuDTO> menus = new ArrayList<MenuDTO>();
			for (MenuDTO adminMenuDTO : menulist) {
				if ("top".equals(adminMenuDTO.getParent_menu_code())) {
					menus.add(adminMenuDTO);
				}
			}
			return menus;
		}
		return null;
	}

}
