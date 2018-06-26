package com.xmhx.buzz.platform.control.manages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectcycle.business.utils.Base64Encode;
import com.xmhx.buzz.common.SessionSettings;
import com.xmhx.buzz.platform.model.manages.init.MenuDTO;
import com.xmhx.buzz.platform.model.manages.init.UserDTO;
import com.xmhx.buzz.platform.service.manages.init.MenuService;
import com.xmhx.buzz.utils.DateUtils;
import com.xmhx.core.authority.LoginAuth;
import com.xmhx.core.authority.NoLoginAuth;
import com.xmhx.core.buzz.BaseController;

@Controller
@RequestMapping(value="/manages/")
public class AdminLoginController extends BaseController {

	@Autowired
	private MenuService menuService;
	
	/**
	 * 跳转登录页面
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="gologon.do", method=RequestMethod.GET)
	public String gologon() {
		return "manages/main/login";
	}
	
	/**
	 * 登录
	 * @param usrNo
	 * @param usrPwd
	 * @param session
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="dologon.do", method=RequestMethod.POST)
	@ResponseBody
	public Object dologon(HttpSession session, String usrNo, String usrPwd) {
		logger.info("登录信息 = " + usrNo + " --> " + DateUtils.formatDate(new Date()));
		String pwd = Base64Encode.encode(usrPwd);
		UserDTO userDTO = new UserDTO();
		userDTO.setMemberNm(usrNo);
		userDTO.setMemberPwd(pwd);
		session.setAttribute(SessionSettings.USER_SESSION, userDTO);
		session.setMaxInactiveInterval(60*15);
		return pushMsg(Boolean.TRUE, "index.do");
	}
	
	/**
	 * 进入首页
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="index.do", method=RequestMethod.GET)
	public String index(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		List<MenuDTO> allmenusList = menuService.getAllmenus();
		session.setAttribute(SessionSettings.STR_MENU_URLS, getMenuUrls(allmenusList));
		map.put("topmenus", getTopMenus(allmenusList));
		map.put("logineduser", userDTO.getMemberNm());
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
	@RequestMapping(value="childrenmenu.do", method=RequestMethod.GET)
	public String childrenmenu(HttpSession session, String menucode, ModelMap map) {
		UserDTO userDTO = getUser(session);
		List<MenuDTO> menus = menuService.getChildrenmenu(userDTO.getMemberNm(), menucode);
		map.put("menus", menus);
		return "manages/main/menu";
	}
	
	/**
	 * 首页main内容（可做为待办任务）
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="main.do", method=RequestMethod.GET)
	public String main() {
		return "manages/main/main";
	}
	
	/**
	 * 退出系统
	 * @param request
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="logout.do", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		UserDTO userDTO = getUser(session);
		session.removeAttribute(SessionSettings.USER_SESSION);
		logger.info("退出系统 = " + userDTO.getMemberNm() + " --> " + DateUtils.formatDate(new Date()));
		return "redirect:gologon.do";
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

//	/* **************** 以下为待办任务功能，暂无实现 **************************************************** */
//	@LoginAuth
//	@RequestMapping(value="queryTabtitle.do", method=RequestMethod.POST)
//	@ResponseBody
//	public Object queryTabtitle(HttpSession session, int todoDays) {
////		String nowDate = DateUtils.getTimeNows();				//当前时间
////		String beforeDate = DateUtils.getDate(-todoDays);		//当前时间todoDays天前
////		UserDTO user = getUser(session);
////		String umname = user.getUmname();
////		int page = 1,rows = 10;
////		PageBean<RmDTO> prm = rmService.rmTodoTask(page, rows, null, null, umname,beforeDate,nowDate);
////		PageBean<SrDTO> psr = srService.srTodoTask(page, rows, null, null, umname, beforeDate, nowDate);
////		PageBean<BugDTO> pbug =bugService.bugTodoTask(page, rows, null, null, umname,beforeDate,nowDate);
////		PageBean<PrdDTO> pprd= prdService.prdTodoTask(page, rows, null, null, umname,beforeDate,nowDate);
////		int rmcount = prm == null ? 0 : prm.getRecordCount();
////		int srcount = psr == null ? 0 : psr.getRecordCount();
////		int bugcount = pbug == null ? 0 : pbug.getRecordCount();
////		int prdcount = pprd == null ? 0 : pprd.getRecordCount();
////	
////		Map<String, Integer> map = new HashMap<String, Integer>();
////		map.put("rmcount", rmcount);
////		map.put("srcount", srcount);
////		map.put("bugcount", bugcount);
////		map.put("prdcount", prdcount);
////		return pushMsg("Success", Boolean.TRUE, "map", map);
//		
//		return null;
//	}
//	
//	//版本待办任务
//	@LoginAuth
//	@RequestMapping(value="rmTodoTask.do", method=RequestMethod.POST)
//	@ResponseBody
//	public Object rmTodoTask(@RequestParam(defaultValue="1")int page, int rows, String sort, String order, HttpSession session, @RequestParam(defaultValue="30")int todoDays) {
////		UserDTO user = getUser(session);
////		String umname = user.getUmname();
////		String nowDate = DateUtils.getTimeNows();//当前时间
////		String beforeDate = DateUtils.getDate(-todoDays);//当前时间todoDays天前
////		PageBean<RmDTO> p = rmService.rmTodoTask(page, rows, sort, order, umname,beforeDate,nowDate);
////		if (p != null) {
////			PageVO<RmDTO> pagevo = new PageVO<RmDTO>();
////			pagevo.setTotal(p.getRecordCount());
////			pagevo.setRows(p.getDataList());
////			return pagevo;
////		}
//		return null;
//	}
//	
//	//sr待办任务
//	@LoginAuth
//	@RequestMapping(value="srTodoTask.do", method=RequestMethod.POST)
//	@ResponseBody
//	public Object srTodoTask(@RequestParam(defaultValue="1")int page, int rows, String sort, String order,HttpSession session,@RequestParam(defaultValue="30")int todoDays) {
////		UserDTO user = getUser(session);
////		String umname = user.getUmname();
////		String nowDate = DateUtils.getTimeNows();//当前时间
////		String beforeDate = DateUtils.getDate(-todoDays);//当前时间todoDays天前
////		PageBean<SrDTO> p = srService.srTodoTask(page, rows, sort, order, umname,beforeDate,nowDate);
////		if (p != null) {
////			PageVO<SrDTO> pagevo = new PageVO<SrDTO>();
////			pagevo.setTotal(p.getRecordCount());
////			pagevo.setRows(p.getDataList());
////			return pagevo;
////		}
//		return null;
//	}
//	
//	//bug待办任务
//	@LoginAuth
//	@RequestMapping(value="bugTodoTask.do", method=RequestMethod.POST)
//	@ResponseBody
//	public Object bugTodoTask(@RequestParam(defaultValue="1")int page, int rows, String sort, String order,HttpSession session,@RequestParam(defaultValue="30")int todoDays) {
////		UserDTO user = getUser(session);
////		String umname = user.getUmname();
////		String nowDate = DateUtils.getTimeNows();//当前时间
////		String beforeDate = DateUtils.getDate(-todoDays);//当前时间todoDays天前
////		PageBean<BugDTO> p = bugService.bugTodoTask(page, rows, sort, order, umname,beforeDate,nowDate);
////		if (p != null) {
////			PageVO<BugDTO> pagevo = new PageVO<BugDTO>();
////			pagevo.setTotal(p.getRecordCount());
////			pagevo.setRows(p.getDataList());
////			return pagevo;
////		}
//		return null;
//	}
//	
//	//prd待办任务
//	@LoginAuth
//	@RequestMapping(value="prdTodoTask.do", method=RequestMethod.POST)
//	@ResponseBody
//	public Object prdTodoTask(@RequestParam(defaultValue="1")int page, int rows, String sort, String order,HttpSession session,@RequestParam(defaultValue="30")int todoDays) {
////		UserDTO user = getUser(session);
////		String umname = user.getUmname();
////		String nowDate = DateUtils.getTimeNows();//当前时间
////		String beforeDate = DateUtils.getDate(-todoDays);//当前时间todoDays天前
////		PageBean<PrdDTO> p = prdService.prdTodoTask(page, rows, sort, order, umname,beforeDate,nowDate);
////		if (p != null) {
////			PageVO<PrdDTO> pagevo = new PageVO<PrdDTO>();
////			pagevo.setTotal(p.getRecordCount());
////			pagevo.setRows(p.getDataList());
////			return pagevo;
////		}
//		return null;
//	}
//		
//	@LoginAuth
//	@RequestMapping(value="searchcodeFormain.do", method=RequestMethod.POST)
//	@ResponseBody
//	public Object searchcodeFormain(String searchcode) {
////		if (TextUtils.notEmpty(searchcode)) {
////			searchcode = searchcode.trim();
////			String regexSR = "^SR_.+$";
////			String regexBUG = "^BUG_.+$";
////			String regexSbug = "^sbug_.+$";
////			String regexPRD = "^PRD_.+$";
////			String regexSprd = "^sprd_.+$";
////			String regexRD = "^RD_.+$";
////			String regexRM = ".+";
////			if (searchcode.matches(regexSR)) {
////				SrDTO srDTO = srService.srFindBySrid(searchcode);
////				if (srDTO != null) {
////					return pushMsg("success", Boolean.TRUE);
////				} else {
////					return pushMsg("fail", Boolean.FALSE);
////				}
////			} else if (searchcode.matches(regexBUG)) {
////				BugDTO bugDTO = bugService.bugFindBybugid(searchcode);
////				if (bugDTO != null) {
////					return pushMsg("success", Boolean.TRUE);
////				} else {
////					return pushMsg("fail", Boolean.FALSE);
////				}
////			} else if (searchcode.matches(regexSbug)) {
////				SbugDTO sbugDTO = bugService.sbugFindBySbugid(searchcode);
////				if (sbugDTO != null) {
////					return pushMsg("success", Boolean.TRUE);
////				} else {
////					return pushMsg("fail", Boolean.FALSE);
////				}
////			} else if (searchcode.matches(regexPRD)) {
////				PrdDTO prdDTO = prdService.prdFindByPrdid(searchcode);
////				if (prdDTO != null) {
////					return pushMsg("success", Boolean.TRUE);
////				} else {
////					return pushMsg("fail", Boolean.FALSE);
////				}
////			} else if (searchcode.matches(regexSprd)) {
////				SprdDTO sprdDTO = prdService.sprdFindBySprdid(searchcode);
////				if (sprdDTO != null) {
////					return pushMsg("success", Boolean.TRUE);
////				} else {
////					return pushMsg("fail", Boolean.FALSE);
////				}
////			} else if (searchcode.matches(regexRD)) {
////				RdDTO rdDTO = rdService.getRdDtoById(searchcode);
////				if (rdDTO != null) {
////					return pushMsg("success", Boolean.TRUE);
////				} else {
////					return pushMsg("fail", Boolean.FALSE);
////				}
////			} else if (searchcode.matches(regexRM)) {
////				RmDTO rmDTO = rmService.findRmByRmid(searchcode);
////				if (rmDTO != null) {
////					return pushMsg("success", Boolean.TRUE,"rmdto",rmDTO);
////				} else {
////					return pushMsg("fail", Boolean.FALSE);
////				}
////			} else {
////				return pushMsg("fail", Boolean.FALSE);
////			}
////		}else{
////			return pushMsg("fail", Boolean.FALSE);
////		}
//		return null;
//	}

}
