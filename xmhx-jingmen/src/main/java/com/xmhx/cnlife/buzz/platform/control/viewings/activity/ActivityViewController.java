package com.xmhx.cnlife.buzz.platform.control.viewings.activity;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmhx.cnlife.buzz.common.SessionSettings;
import com.xmhx.cnlife.buzz.platform.model.manages.activity.ActivityDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.attach.AttachDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.user.UserDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.activity.ActivityService;
import com.xmhx.cnlife.buzz.platform.service.manages.attach.AttachService;
import com.xmhx.cnlife.buzz.utils.FileUtils;
import com.xmhx.cnlife.core.authority.LoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;
import com.xmhx.cnlife.core.model.PageBean;


@Controller
@RequestMapping(value="/")
public class ActivityViewController extends BaseController {
	@Resource
	private ActivityService activityService;
	@Resource
	private AttachService attachService;

	/**
	 * 会员中心-活动列表页
	 * @param session
	 * @param activity
	 * @param pageNumber
	 * @param map
	 * @param request
	 * @return
	 * 创建日期：2017-2-4  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value = "memberActityPage.hx")
	public String memberActityPage(HttpSession session, ActivityDTO activity,
			@RequestParam(defaultValue = "1") int pageNumber, ModelMap map,
			HttpServletRequest request) {
		//获取用户登录信息
		UserDTO userDTO = (UserDTO) session.getAttribute(SessionSettings.VIEWUSER_SESSION);
		activity.setActiviissueid(userDTO.getHxuuid());
		activity.setTp(null);
		// 查询所有类型活动列表
		PageBean<ActivityDTO> p = activityService.queryActivityForPage(pageNumber, 4, activity);// 分页查询

		// 设置图片路径
		String targetDirectory = getDisplayphotodir() + File.separatorChar;
		// 活动列表
		map.put("activityList", p.getDataList());

		map.put("attachpath", targetDirectory);
		// 分页
		map.put("pageBean", p);
		// 查询条件
		map.put("activity", activity);
		// 类型
		map.put("leftTp", "2");
	    //园区导航高亮
	  	map.put("topTp", "3");

		return "viewings/memberActivity/memberactivitypage";
	}
	/**
	 * 我的参与
	 * @param activity
	 * @param pageNumber
	 * @param map
	 * @param request
	 * @param session
	 * @return
	 * 创建日期：2017-2-14  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value = "memberJoinActityPage.hx")
	public String memberJoinActityPage(ActivityDTO activity, @RequestParam(defaultValue = "1") int pageNumber, ModelMap map,
			HttpServletRequest request, HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute(SessionSettings.VIEWUSER_SESSION);
		activity.setEntertel(userDTO.getMember_mobile());
		// 查询所有类型活动列表
		PageBean<ActivityDTO> p = activityService.queryActivityForPage(pageNumber, 4, activity);// 分页查询

		// 设置图片路径
		String targetDirectory = getDisplayphotodir() + File.separatorChar;
		// 活动列表
		map.put("activityList", p.getDataList());

		map.put("attachpath", targetDirectory);
		// 分页
		map.put("pageBean", p);
		// 查询条件
		map.put("activity", activity);
		// 类型
		map.put("leftTp", "3");
	    //园区导航高亮
	  	map.put("topTp", "3");

	  	return "viewings/memberActivity/memberjoinactivitypage";
	}
	
	/**
	 * 到发布页面
	 * @param map
	 * @return
	 * 创建日期：2017-2-4  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value = "toMemberberActivity.hx")
	public String toMemberberActivity(ModelMap map) {
		// 类型
		map.put("leftTp", "2");
	    //园区导航高亮
	  	map.put("topTp", "3");
		return "viewings/memberActivity/addmemberactivity";
	}
	/**
	 * 添加活动
	 * @param activityDTO
	 * @param attachid
	 * @param request
	 * @return
	 * 创建日期：2017-2-6  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value = "addMemberActivity.hx", method = RequestMethod.POST)
	@ResponseBody
	public Object addMemberActivity(ActivityDTO activityDTO, String attachid,
			HttpServletRequest request) {
		// 获取登录信息
		UserDTO user = getLoginInfo(request);
		activityDTO.setActiviissueid(user.getHxuuid());//发表人
		// 添加活动
		String activityid =  UUID.randomUUID().toString();
		activityDTO.setHxuuid(activityid);
		activityDTO.setCreated_by(user.getMember_name());
		activityDTO.setEnternum(0);
		activityDTO.setPraisenum(0);
		activityDTO.setVerifystatus("0");// 默认为0未审核
		int id = activityService.addActivity(activityDTO, attachid);
		if (id > 0) {
			return pushMsg(Boolean.TRUE, "添加成功");
		} else {
			return pushMsg(Boolean.FALSE, "添加失败");
		}
	}
	/**
	 * 删除活动图片
	 * @param request
	 * @param attachDTO
	 * @return
	 * 创建日期：2017-2-6  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value = "delActivityImg.hx")
	@ResponseBody
	public Object delattach(HttpServletRequest request, AttachDTO attachDTO) {
		AttachDTO attach = attachService.queryAttachmentById(attachDTO.getHxuuid());
		String attachpath = attach.getAttachPath();
		String targetDirectory = getUploadfiledir();
		targetDirectory = targetDirectory + File.separatorChar + attachpath;
		int result = attachService.delAttachById(attachDTO.getHxuuid());
		if (result>0) {
			FileUtils.delAllFile(targetDirectory);
			return pushMsg(Boolean.TRUE, "附件删除成功");
		} else {
			return pushMsg(Boolean.FALSE, "附件删除失败");
		}
	}
	/**
	 * 跳转到活动页面
	 * @param request
	 * @param attachDTO
	 * @return
	 * 创建日期：2017-2-7  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value = "activity.hx")
	public String activity(HttpServletRequest request, ActivityDTO activity, @RequestParam(defaultValue = "1") int pageNumber, ModelMap map) {
		//查询活动
		PageBean<ActivityDTO> p = activityService.queryActivityForPage(pageNumber, 6, activity);
		
		// 设置图片路径
		String targetDirectory = getDisplayphotodir() + File.separatorChar;
		// 活动列表
		map.put("activityList", p.getDataList());

		map.put("attachpath", targetDirectory);
		// 分页
		map.put("pageBean", p);
		// 查询条件
		map.put("activity", activity);
	    //园区导航高亮
	  	map.put("activeActivity", "1");
	  	
		return "viewings/activity/activity";
		
	}
	
	
}
