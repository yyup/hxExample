package com.xmhx.cnlife.core.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.xmhx.cnlife.buzz.common.SessionSettings;
import com.xmhx.cnlife.buzz.utils.HttpUtils;
import com.xmhx.cnlife.buzz.utils.TextUtils;
import com.xmhx.cnlife.core.authority.LoginAuth;
import com.xmhx.cnlife.core.authority.NoLoginAuth;

/**
 * 管理控制台登录拦截器
 * @author 吴进
 * @since 2014-05-28
 */
public class AdminLoginInterceptor extends HandlerInterceptorAdapter {
	/** 已登录不能进入的页面 **/
	private static String[] nologin_filter_uri = new String[] { "manages/gologon.hx", "/xmhx/" };

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HandlerMethod authhandler = (HandlerMethod) handler;
		HttpUtils.printHTTP(request, authhandler);	// 打印请求情况
		return doFilter(request, response, authhandler);
	}

	private boolean doFilter(HttpServletRequest request,
			HttpServletResponse response, HandlerMethod authhandler)
			throws IOException {

			// 过滤Html+JS,判断是否包含非法参数
			if (HttpUtils.filterHtml(request)) {
				if (HttpUtils.isAjaxRequest(request)) {
					JSONObject object = new JSONObject();
					object.put("info", "请求参数非法，请重新填写!");
					object.put("status", false);
					response.getWriter().print(object.toJSONString());
				} else {
					// 非法请求，跳转登录页面
					response.sendRedirect(request.getContextPath() + "/manages/gologon.hx");
				}
				return false;
			}
		
		// 只要不声明NoLoginAuth的都需要做登录校验，声明了的可以直接放行
		NoLoginAuth nologin = authhandler.getMethodAnnotation(NoLoginAuth.class);
		if (nologin != null) {
			return filterURL(request,response);
		}
		
		// 声明LoginAuth的都需要做登录校验
		LoginAuth loginauth = authhandler.getMethodAnnotation(LoginAuth.class);
		if (loginauth != null) {
			if (isLogin(request)) {
				//------- 验证用户菜单资源权限 --------
//				String uri = request.getRequestURI();
//				String real_uri = uri.substring(uri.lastIndexOf("/")+1, uri.length());
//				String all_menu_urls = System.getProperty(SessionSettings.ALL_MENU_URLS);
//				String user_menu_urls = (String)session.getAttribute(SessionSettings.MENU_URLS);
//				if(all_menu_urls.contains(real_uri)){
//					if(!user_menu_urls.contains(real_uri)){
//						session.setAttribute("msg", "用户非法访问资源！！！");
//						session.removeAttribute(SessionSettings.USER_SESSION);
//						response.sendRedirect(request.getContextPath()+ "/manages/gologon.hx");// 未登录用户跳转到登陆页
//						return false;
//					}
//				}
				//--------- end ---------
				
				//------- 验证用户其他访问资源权限 --------
//				UserMenuRightAuth auth = authhandler.getMethodAnnotation(UserMenuRightAuth.class);
//				if (auth != null) {
//					String userMenuCodes = (String)session.getAttribute("menu_codes");
//					if (!userMenuCodes.contains(auth.menuCode())) {
//						session.setAttribute("msg", "用户非法访问资源！！！");
//						session.removeAttribute(SessionSettings.USER_SESSION);
//						response.sendRedirect(request.getContextPath()+ "/manages/gologon.hx");// 未登录用户跳转到登陆页
//						return false;
//					}
//				}
				//--------- end ---------
				return true;
			} else {
				// 未登录用户跳转到登陆页或提示超时
				if (HttpUtils.isAjaxRequest(request)) {
					JSONObject object = new JSONObject();
					object.put("info", "您闲置的时间太长了，请重新登录");
					object.put("status", false);
					response.getWriter().print(object.toJSONString());
				} else {
					response.sendRedirect(request.getContextPath() + "/manages/gologon.hx");
				}
				return false;
			}
		} else {
			// 代码一定要声明注解
			response.sendRedirect(request.getContextPath()+ "/manages/gologon.hx");
			return false;
		}
	}

	/**
	 * 判断是否已登录
	 * @param request
	 * @return
	 */
	private boolean isLogin(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(SessionSettings.USER_SESSION);
		if (null == obj) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 过滤请求
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private boolean filterURL(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean pass = true;
		if (isLogin(request)) {
			//如果已登录，输入地址如果是未登录的地址，也会跳转到登录后的首页
			String requestURI = request.getRequestURI();
			for (String uri : nologin_filter_uri) {
				if (requestURI.endsWith(uri)) {
					// 主页
					response.sendRedirect(request.getContextPath() + "/manages/index.hx");
					pass = false;
					break;
				}
			}
		}
		return pass;
	}
	
}
