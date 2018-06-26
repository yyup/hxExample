package com.xmhx.buzz.platform.control.manages;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmhx.buzz.platform.model.manages.init.NewsDTO;
import com.xmhx.buzz.platform.model.manages.init.UserDTO;
import com.xmhx.buzz.platform.service.manages.init.NewsService;
import com.xmhx.buzz.utils.UUIDUtils;
import com.xmhx.core.authority.LoginAuth;
import com.xmhx.core.authority.NoLoginAuth;
import com.xmhx.core.buzz.BaseController;

@Controller
@RequestMapping(value="/manages/")
public class NewsController  extends BaseController {
	@Resource
	private NewsService newsService;

	/**
	 * 一对一
	 * @param session
	 * @param map
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="zxmanage.do", method=RequestMethod.GET)
	public String zxmanage(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		List<NewsDTO> newsList = newsService.getNewsList();
		map.put("newsList", newsList);
		return "manages/system/news/zxmanage";
	}
	/**
	 * 一对多
	 * @param session
	 * @param map
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="newsOnetoMore.do", method=RequestMethod.GET)
	public String newsOnetoMore(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		List<NewsDTO> newsList = newsService.getNewsOnetoMore();
		map.put("newsList", newsList);
		return "manages/system/news/zxmanage";
	}
	/**
	 * 批量插入数据
	 * @param session
	 * @param map
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="addNewsOne.do", method=RequestMethod.GET)
	public String addNewsOne(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		NewsDTO news = new NewsDTO();
		String uid = UUIDUtils.getStringTypeUUID();
		news.setNotice_id(uid);
		news.setTitle("标题");
		news.setNotice_tp("1");
		news.setContent("内容");
		newsService.addNewsOne(news);
		return "manages/system/news/zxmanage";
	}
	/**
	 * 批量插入数据
	 * @param session
	 * @param map
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="addNewsList.do", method=RequestMethod.GET)
	public String addNewsList(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		List<NewsDTO> newsList = new ArrayList<NewsDTO>();
		for (int i=0;i<3;i++) {
			NewsDTO news = new NewsDTO();
			String uid = UUIDUtils.getStringTypeUUID();
			news.setNotice_id(uid);
			news.setTitle("标题"+i);
			news.setNotice_tp("1");
			news.setContent("内容"+i);
			newsList.add(news);
		}
		newsService.addNewsList(newsList);
		return "manages/system/news/zxmanage";
	}
}
