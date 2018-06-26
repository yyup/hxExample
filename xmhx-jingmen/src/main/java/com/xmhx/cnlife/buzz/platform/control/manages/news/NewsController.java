package com.xmhx.cnlife.buzz.platform.control.manages.news;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmhx.cnlife.buzz.platform.model.manages.attach.AttachDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.news.NewsDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.user.UserDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.attach.AttachService;
import com.xmhx.cnlife.buzz.platform.service.manages.news.NewsService;
import com.xmhx.cnlife.buzz.utils.DateUtils;
import com.xmhx.cnlife.buzz.utils.TextUtils;
import com.xmhx.cnlife.core.authority.LoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;
import com.xmhx.cnlife.core.model.PageBean;
import com.xmhx.cnlife.core.model.PageVO;

@Controller
@RequestMapping(value="/manages/")
public class NewsController extends BaseController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private AttachService attachService;
	/**
	 * 跳转到新闻管理页面
	 * @return
	 * 创建日期：2016-1-5  创建人：黄建明
	 */
	@LoginAuth
	@RequestMapping(value="news/list.hx", method=RequestMethod.GET)
	public String list() {
		return "manages/news/newsList";
	}
	/**
	 * 点击进入添加新闻页面
	 */
	@LoginAuth
	@RequestMapping(value="news/addnewspage.hx", method=RequestMethod.GET)
	public String addnewspage() {
		return "manages/news/addnews";
	}
	/**
	 * 点击进入修改新闻页面
	 */
	@LoginAuth
	@RequestMapping(value="news/modifynewspage.hx", method=RequestMethod.GET)
	public String modifynewspage(String hxuuid,ModelMap map) {
		NewsDTO newsDTO = newsService.queryNewsById(hxuuid);
		List<AttachDTO> list = attachService.queryAttachByfkid(hxuuid);
		String attachid = null;
		for (AttachDTO attachDTO : list) {
			attachid = attachDTO.getHxuuid();
			break;
		}
		map.put("news", newsDTO);
		map.put("attachid", attachid);
		return "manages/news/modifynews";
	}
	
	/**
	 * 点击进入新闻详情页面
	 * @param hxuuid
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="news/newsdetail.hx", method=RequestMethod.GET)
	public String newsdetail(String hxuuid,ModelMap map) {
		boolean chk = checkParam(hxuuid);
		if(!chk){
			return null;
		}
		//查询某个新闻的详情
		NewsDTO newsDTO = newsService.queryNewsById(hxuuid);
		List<AttachDTO> attachDTO = attachService.queryAttachByfkid(hxuuid);
		String attachpath = null;
		for (AttachDTO attach : attachDTO) {
			attachpath = "/upload/" + attach.getAttachPath() + "/"+attach.getAttachName() + attach.getAttachType();
			break;
		}
		map.put("news", newsDTO);
		map.put("attachpath", attachpath);
		return "manages/news/newsdetail";
	}
	/**
	 * 添加新闻
	 * @param session
	 * @param newsDTO,attachid
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="news/addnews.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object addnews(HttpSession session,NewsDTO newsDTO,String attachid) {
		boolean chk = checkParam(newsDTO,newsDTO.getNewsTitle(),newsDTO.getNewsSubtitle(),newsDTO.getNewsContent());
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		if(TextUtils.isEmpty(attachid)){
			return pushMsg(Boolean.FALSE, "请上传图片");
		}
		try{
			//获取登录用户
			UserDTO logon_user = getUser(session);
			String name = logon_user.getMember_name();
			newsDTO.setCreated_by(name);
			newsDTO.setUpdated_by(name);
			newsDTO.setVerifyStatus("0");//审核状态为 0未审核
			//添加新闻
			String hxuuid = newsService.addNews(newsDTO);
			//已上传图片，更新附件表，写入外键ID
			int num = attachService.updateAttachfkid(attachid, hxuuid);
			if(num == 1){
				return pushMsg(Boolean.TRUE,"添加成功","hxuuid",hxuuid);
			}else{
				logger.error("添加失败===>num="+num+",hxuuid="+hxuuid);
				return pushMsg(Boolean.FALSE,"添加失败","hxuuid",null);
			}
		}catch(Exception e){
			logger.error("添加失败===>"+e.getMessage());
			return pushMsg(Boolean.FALSE,"添加失败","hxuuid",null);
		}
	}
	
	/**
	 * 修改新闻
	 * @param session
	 * @param newsDTO
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="news/modifynews.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object modifynews(HttpSession session,NewsDTO newsDTO,String attachid) {
		boolean chk = checkParam(newsDTO,newsDTO.getHxuuid(),newsDTO.getNewsTitle(),newsDTO.getNewsSubtitle(),newsDTO.getNewsContent());
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		if(TextUtils.isEmpty(attachid)){
			return pushMsg(Boolean.FALSE, "请上传图片");
		}
		try{
			//获取登录用户
			UserDTO logon_user = getUser(session);
			String name = logon_user.getMember_name();
			newsDTO.setUpdated_by(name);
			//修改新闻
			int ret = newsService.modifyNews(newsDTO);
			//已上传图片，更新附件表，写入外键ID
			String hxuuid = newsDTO.getHxuuid();
			int num = attachService.updateAttachfkid(attachid, hxuuid);
			if( ret==1 && num>0 ){
				return pushMsg(Boolean.TRUE,"修改成功","hxuuid",hxuuid);
			}else{
				logger.error("修改失败===>num="+num+",hxuuid="+hxuuid+",ret="+ret);
				return pushMsg(Boolean.FALSE,"修改失败","hxuuid",null);
			}
		}catch(Exception e){
			logger.error("修改失败===>"+e.getMessage());
			return pushMsg(Boolean.FALSE,"修改失败");
		}
	}
	
	/**
	 * 删除新闻
	 * @param session
	 * @param ids
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="news/deletenews.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object deletenews(String ids) {
		boolean chk = checkParam(ids);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		try{
			//删除新闻
			String[] hxuuids = ids.split(",");
			int num = newsService.deleteNews(hxuuids);
			attachService.deleteAttachByfk(hxuuids);
			if(num>0){
				return pushMsg(Boolean.TRUE,"删除成功");
			}else{
				return pushMsg(Boolean.FALSE,"删除失败");
			}
		}catch(Exception e){
			logger.error("删除失败===>"+e.getMessage());
			return pushMsg(Boolean.FALSE,"删除失败");
		}
	}
	
	/**
	 * 新闻分页查询
	 * @param page 第几页
	 * @param rows 一页可容纳多少行
	 * @param sort 排序字段
	 * @param order ASC|DESC
	 * @param newsDTO 新闻对象
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="news/newslistpage.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object newslistpage(@RequestParam(defaultValue="1")int page, int rows, String sort, String order, NewsDTO newsDTO) {
		PageBean<NewsDTO> pageBean = newsService.getNewsByPage(page, rows, sort, order, newsDTO);
		if(pageBean != null) {
			PageVO<NewsDTO> pagevo = new PageVO<NewsDTO>();
			pagevo.setTotal(pageBean.getRecordCount());
			pagevo.setRows(pageBean.getDataList());
			return pagevo;
		}
		return null;
	}
	/**
	 * 审核新闻
	 * @param verifyStatus
	 * @param hxuuid
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="news/verifyNews.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object verifyNews(HttpSession session, String verifyStatus, String hxuuid) {
		boolean chk = checkParam(verifyStatus,hxuuid);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		try{
			NewsDTO newsDTO = new NewsDTO();
			newsDTO.setHxuuid(hxuuid);
			newsDTO.setVerifyStatus(verifyStatus);
			UserDTO logon_user = getUser(session);
			newsDTO.setVerifytor(logon_user.getMember_name());
			newsDTO.setVerifytorId(logon_user.getMember_mobile());
			newsDTO.setVerifyDate(DateUtils.getDatetimeNow());
			int num = newsService.updateNewsVerifyStatus(newsDTO);
			if(num == 1){
				return pushMsg(Boolean.TRUE,"审核成功");
			}else{
				logger.error("审核失败===>num="+num);
				return pushMsg(Boolean.FALSE,"审核失败");
			}
		}catch(Exception e){
			logger.error("审核失败===>"+e.getMessage());
			return pushMsg(Boolean.FALSE,"审核失败");
		}
	}
	
}
