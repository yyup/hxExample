package com.xmhx.cnlife.core.buzz;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.xmhx.cnlife.buzz.common.SessionSettings;
import com.xmhx.cnlife.buzz.platform.model.manages.user.UserDTO;
import com.xmhx.cnlife.buzz.utils.PropertiesUtils;
import com.xmhx.cnlife.buzz.utils.encrypt.SecurityUtil;
import com.xmhx.cnlife.core.model.PushMsg;

/**
 * Basic Controller
 * @author 吴进
 *
 */
public class BaseController {
	
	protected Logger logger = Logger.getLogger(getClass());
	
	public final static String ERROR_PARAM = "参数不完整";
	
	protected static final Map<String, String> configMap = PropertiesUtils.mapConfigAll();
	/**
	 * 得到当前用户信息
	 * @param http 可为HttpSession对象或者HttpServletRequest对象
	 * @return
	 */
	public UserDTO getUser(Object http) {
		HttpSession session = null;
		if(http instanceof HttpSession) session = (HttpSession) http;
		else if (http instanceof HttpServletRequest ) session = ((HttpServletRequest)http).getSession();
		Object object = session.getAttribute(SessionSettings.USER_SESSION);
		if(object!=null){
			if(object instanceof UserDTO){
				return (UserDTO)object;
			}
		}
		return null;
	}
	
//	/**
//	 * 获取所有普通用户
//	 * @param http
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public List<UserDTO> getUsers(HttpServletRequest request) {
//		return (List<UserDTO>) request.getSession().getServletContext().getAttribute(SessionSettings.GENERAL_USERS);
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<UserDTO> getUsers(HttpSession session) {
//		return (List<UserDTO>) session.getAttribute(SessionSettings.GENERAL_USERS);
//	}
//	
//	/**
//	 * 获取所有的菜单
//	 * @param request
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public List<MenuDTO> getMenus(HttpServletRequest request) {
//		return (List<MenuDTO>) request.getSession().getServletContext().getAttribute(SessionSettings.ALL_MENU_URLS);
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<MenuDTO> getMenus(HttpSession session) {
//		return (List<MenuDTO>) session.getAttribute(SessionSettings.ALL_MENU_URLS);
//	}
	
	/**
	 * 推送消息到客户端
	 * @param info
	 * @param status
	 * @return
	 */
	public static PushMsg pushMsg(Boolean status, Object info) {
		return pushMsg(status, info, 0);
	}
	
	/**
	 * 推送消息到客户端
	 * @param info
	 * @param status
	 * @return
	 */
	public static PushMsg pushMsg(Boolean status, Object info, int arg1) {
		PushMsg pushMsg = new PushMsg();
		pushMsg.setArg1(arg1);
		pushMsg.setInfo(info);
		pushMsg.setStatus(status);
		return pushMsg;
	}
	
	/**
	 * 推送消息到客户端
	 * @param info
	 * @param status
	 * @param entrys 附加属性值，Key：Value
	 * @return
	 */
	public static PushMsg pushMsg(Boolean status, Object info, Object... entrys) {
		return pushMsg(status, info, 0, entrys);
	}
	
	/**
	 * 推送消息到客户端
	 * @param info
	 * @param status
	 * @param arg1
	 * @param entrys 附加属性值，Key：Value
	 * @return
	 */
	public static PushMsg pushMsg(Boolean status, Object info, int arg1, Object... entrys) {
		PushMsg pushMsg = new PushMsg();
		pushMsg.setArg1(arg1);
		pushMsg.setInfo(info);
		pushMsg.setStatus(status);
		if (entrys.length % 2 == 0) {
			for (int i = 0, n = entrys.length; i < n; i+=2) {
				pushMsg.getAttr().put(String.valueOf(entrys[i]), entrys[i+1]);
			}
		}
		return pushMsg;
	}
	
	/**
	 * 推送消息到客户端
	 * @param status
	 * @param info
	 * @param map
	 * @return
	 */
	public static PushMsg pushMsg(Boolean status, Object info, Map<String, Object> map) {
		return pushMsg(status, info, 0, map);
	}
	
	/**
	 * 推送消息到客户端
	 * @param status
	 * @param info
	 * @param arg1
	 * @param map
	 * @return
	 */
	public static PushMsg pushMsg(Boolean status, Object info, int arg1, Map<String, Object> map) {
		PushMsg pushMsg = new PushMsg();
		pushMsg.setArg1(arg1);
		pushMsg.setInfo(info);
		pushMsg.setStatus(status);
		if (map != null && !map.isEmpty()) {
			pushMsg.setAttr(map);
		}
		return pushMsg;
	}
	
	/**
	 * 附件路径
	 * @return
	 */
	protected String getUploadfiledir() {
		Map<String, String> map = PropertiesUtils.mapConfigAll();
		String uploadfiledir = map.get("xmhx.uploadfiledir");
		return uploadfiledir;
	}
	
	/**
	 * 得到ROOT路径(资源根路径)
	 * 
	 * @param request
	 * @return
	 */
	protected String getDataFilePath(HttpServletRequest request) {
		File file = new File("/nfsc/abs");
		if (!file.exists()) {
			return request.getSession().getServletContext().getRealPath("");
		} else {
			return "/nfsc/abs";
		}
	}
	
	/**
	 * 文件上传并自动分类
	 * @param request
	 * @param file
	 * @param dictoryInfo
	 * @return
	 */
	protected File readfiles(HttpServletRequest request, MultipartFile file, String dictoryInfo) {
		if (file != null && !file.isEmpty() && file.getSize() > 0) {
			try {
				File dictoryinfo = new File(getDataFilePath(request) + File.separatorChar + dictoryInfo);
				if(!dictoryinfo.exists()) {
					// 目录不存在则创建
					dictoryinfo.mkdirs();
				}
				String path = getFileName(file.getOriginalFilename());
				File create = new File(dictoryinfo, path);
				file.transferTo(create);
				return create;
			} catch (Exception e) {
				throw new RuntimeException("Excel文件获取失败，"+e.getMessage(),e);
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 获取为文件生成唯一路径
	 * @param fileName
	 * @return
	 */
	private String getFileName(String fileName) {
		String exc = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String filename = path();
		return filename + exc;
	}
	
	/**
	 * 根据ID获取文件路径
	 * @param id
	 * @return
	 */
	private String path() {
		String key = "www.straw-soft.com";
		String hash = SecurityUtil.MD5(key + "\t" + System.currentTimeMillis()
				+ "\t" + String.valueOf(System.currentTimeMillis()).length()
				+ "\t" + System.currentTimeMillis() % 10);
		String uuid = UUID.randomUUID().toString();
		hash += uuid.substring(uuid.length()-8,uuid.length());
		hash = hash.substring(hash.length() - 24, hash.length());
		return hash;
	}
	
	/**
	 * 向页面发送信息JSON格式
	 * @param response
	 * @param json
	 */
	protected void sendJsonResponse(HttpServletResponse response, String json) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public boolean checkParam(Object... params) {
		for (Object object : params) {
			if (object instanceof String) {
				if (null == object || "".equals(object.toString().trim())) {
					return false;
				}
			} else {
				if (null == object) {
					return false;
				}
			}
		}
		return true;
	}
}
