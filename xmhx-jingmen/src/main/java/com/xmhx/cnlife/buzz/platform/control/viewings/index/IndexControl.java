package com.xmhx.cnlife.buzz.platform.control.viewings.index;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmhx.cnlife.buzz.platform.model.manages.attach.AttachDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.news.NewsDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.attach.AttachService;
import com.xmhx.cnlife.buzz.platform.service.manages.news.NewsService;
import com.xmhx.cnlife.buzz.utils.EnumUtils.ConstantPagelimit;
import com.xmhx.cnlife.core.authority.NoLoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;
import com.xmhx.cnlife.core.model.PageBean;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/")
public class IndexControl extends BaseController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private AttachService attachService;
	
	/**
	 * 首页
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="index.hx", method=RequestMethod.GET)
	public String index(ModelMap map) {
		int page = 1;
		int rows = 8;
		String sort = "date_created";
		String order = "desc";
		NewsDTO newsDTO = new NewsDTO();
		PageBean<NewsDTO> pb = newsService.getNewsByPage(page, rows, sort, order, newsDTO);
		if(pb!=null && !pb.getDataList().isEmpty()){
			//取前2个新闻的图
			List<NewsDTO> newslist = pb.getDataList();
			List<String> attachpathlist = new ArrayList<String>();
			int i = 0;
			for (NewsDTO news : newslist) {
				i++;
				if (i==3) {
					break;
				}
				String id=news.getHxuuid();
				List<AttachDTO> attachDTO = attachService.queryAttachByfkid(id);
				String attachpath = null;
				for (AttachDTO attach : attachDTO) {
					attachpath = "/upload/" + attach.getAttachPath() + "/"+attach.getAttachName() + attach.getAttachType();
					attachpathlist.add(attachpath);
					break;
				}
			}
			map.put("imglist", attachpathlist);
			map.put("newslist", pb.getDataList());
		}
		map.put("activeIndex", 1);
		return "viewings/index";
	}
	
	/**
	 * 新闻动态
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="news.hx", method=RequestMethod.GET)
	public String news(@RequestParam(defaultValue="1")int page, ModelMap map) {
		int rows = ConstantPagelimit.newspagelimit.getNumber();
		String sort = "date_created";
		String order = "desc";
		// 按某条件查询
		NewsDTO newsDTO = new NewsDTO();
		//分页查询新闻列表
		PageBean<NewsDTO> pb = newsService.getNewsAndAttachByPage(page, rows, sort, order, newsDTO);
		if (pb != null && !pb.getDataList().isEmpty()) {
			map.put("newslist", pb.getDataList());
		}
		//分页
		map.put("pageBean", pb);
		map.put("activeNews", 1);
		return "viewings/news";
	}
	
	/**
	 * 连锁运营
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="chainBusiness.hx", method=RequestMethod.GET)
	public String chainBusiness(String anchorpoint, ModelMap map) {
		map.put("activeChainBusiness", 1);		// 导航栏焦点高亮
		map.put("anchorpoint", anchorpoint);	// 锚定
		return "viewings/chainBusiness";
	}
	
	/**
	 * 园区介绍
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="gardenDesc.hx", method=RequestMethod.GET)
	public String gardenDesc(ModelMap map) {
		map.put("activeGardenDesc", 1);
		return "viewings/gardenDesc";
	}
	
	/**
	 * 关于我们(公司简介)
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="aboutus.hx", method=RequestMethod.GET)
	public String aboutus(ModelMap map) {
		map.put("activeAboutus", 1);
		return "viewings/aboutus";
	}
	
	/**
	 * 关于我们(进驻企业)
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="containCompanys.hx", method=RequestMethod.GET)
	public String containCompanys(String anchorpoint, ModelMap map) {
		map.put("activeAboutus", 1);			// 导航栏焦点高亮
		map.put("anchorpoint", anchorpoint);	// 锚定
		return "viewings/containCompanys";
	}
	
	/**
	 * 关于我们(联系我们)
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="contactus.hx", method=RequestMethod.GET)
	public String contactus(ModelMap map) {
		map.put("activeAboutus", 1);
		return "viewings/contactus";
	}
	
	/**
	 * 园区介绍(厦门园区)
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="gardenXiaMen.hx", method=RequestMethod.GET)
	public String gardenXiaMen(ModelMap map) {
		map.put("activeGardenDesc", 1);
		return "viewings/gardenXiaMen";
	}
	
	/**
	 * 园区介绍(资阳园区)
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="gardenZiYang.hx", method=RequestMethod.GET)
	public String gardenZiYang(ModelMap map) {
		map.put("activeGardenDesc", 1);
		return "viewings/gardenZiYang";
	}
	
	/**
	 * 园区介绍(荆门园区)
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="gardenJingMen.hx", method=RequestMethod.GET)
	public String gardenJingMen(ModelMap map) {
		map.put("activeGardenDesc", 1);
		return "viewings/gardenJingMen";
	}
	
	
	/**************** 具体功能点 ************************************************************************/
	/**
	 * 新闻详情
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="newsDetail.hx", method=RequestMethod.GET)
	public String newsDetail(String id, ModelMap map) {
		NewsDTO newsDTO = newsService.queryNewsById(id);
		List<AttachDTO> attachDTO = attachService.queryAttachByfkid(id);
		String attachpath = null;
		for (AttachDTO attach : attachDTO) {
			attachpath = "/upload/" + attach.getAttachPath() + "/"+attach.getAttachName() + attach.getAttachType();
			break;
		}
		map.put("news", newsDTO);
		map.put("attachpath", attachpath);
		map.put("activeNews", 1);
		return "viewings/newsDetail";
	}
	
	/**
	 * 上一条下一条新闻
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="newsPreviousAndNext.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object newsPreviousAndNext(int id) {
		NewsDTO previousNewsDTO = newsService.queryPreviousNewsById(id);
		NewsDTO nextNewsDTO = newsService.queryNextNewsById(id);
		return pushMsg(Boolean.TRUE, "上下新闻查询成功", "previousNews", previousNewsDTO, "nextNews", nextNewsDTO);
	}
	
}
