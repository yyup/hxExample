package com.xmhx.cnlife.buzz.platform.control.manages.activity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmhx.cnlife.buzz.platform.model.manages.activity.ActivityDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.user.UserDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.activity.ActivityService;
import com.xmhx.cnlife.core.authority.LoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;
import com.xmhx.cnlife.core.model.PageBean;
import com.xmhx.cnlife.core.model.PageVO;

/**
 * 活动审核，PC管理台
 * @author yyp
 *
 */
@Controller
@RequestMapping(value="/manages/")
public class ActivityController extends BaseController {

	@Autowired
	private ActivityService activityService;
//	@Autowired
//	private HuodongService huodongService;
//	@Autowired
//	private YqMemberService yqMemberService;
	
	/**
	 * 跳转活动审核
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="activity/huodongverify.hx", method=RequestMethod.GET)
	public String gotoHuodongverify() {
		return "manages/activity/huodongverifylist";
	}
	
	/**
	 * 活动列表分页
	 * @param newsDTO
	 * @param page
	 * @param rows
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="pageActivity.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object pageActivity(HttpSession session, ActivityDTO activityDTO, @RequestParam(defaultValue="1")int page, @RequestParam(defaultValue="10")int rows) {
		PageBean<ActivityDTO> p = this.activityService.queryActivityPage(activityDTO, page, rows);
		if (p != null) {
			PageVO<ActivityDTO> pagevo = new PageVO<ActivityDTO>();
			pagevo.setTotal(p.getRecordCount());
			pagevo.setRows(p.getDataList());
			return pagevo;
		}
		return null;
	}
	/**
	 * 根据id查询活动信息
	 * @param session
	 * @param activityid
	 * @param map
	 * @return
	 * 创建日期：2017-2-9  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value="lookActivityById.hx", method=RequestMethod.GET)
	public String lookActivityById(HttpSession session, String activityid, ModelMap map) {
//		UserDTO userDTO = getUser(session);
		String targetDirectory = getDisplayphotodir();
		ActivityDTO activityDTO = this.activityService.queryActivityById(activityid, targetDirectory);
		map.put("activityVO", activityDTO);
		return "manages/activity/lookhuodong";
	}
	/**
	 * 活动审核通过
	 * @param request
	 * @param activityid
	 * @return
	 * 创建日期：2017-2-10  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value="huodongverifypass.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object huodongverifypass(HttpServletRequest request, String activityid) {
		UserDTO userDTO = getUser(request);
		String umname = userDTO.getMember_name();
//		SysMemberDTO member = yqMemberService.queryMemberByid(userDTO.getId());//根据自增id查询成员信息
		ActivityDTO activityDTO = new ActivityDTO();
		activityDTO.setHxuuid(activityid);
		activityDTO.setVerifystatus("1");
		activityDTO.setUpdated_by(umname);
		activityDTO.setVerifytorid(userDTO.getHxuuid());//活动审核人id
		activityDTO.setVerifytor(umname);//活动审核人姓名
		boolean rsval = activityService.updatehuodongverify(activityDTO);
		if (rsval) {
//			/* *********************************************************
//			 * 消息推送
//			 * *********************************************************/
//			ActivityDTO activity = yqfwActivityService.queryActivityById(activityid);//获得活动
//			SysMemberDTO memberActiviissue = yqMemberService.queryMemberByuuid(activity.getActiviissueid(), databasename);//获取活动发布人
//			String ifpass = "pass";
//			String memberid = String.valueOf(userDTO.getId());//推送人id
//			// --------------先做信息保存--------------
//			//调用云上新生活的接口，保存消息到临时表，并推送
//			String url = configMap.get("xmhx.cnlifeURL") + configMap.get("xmhx.cloudVerifyActivity");
//			//临时表所需保存的字段
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("receive_mobile", memberActiviissue.getMemberMobile());
//			map.put("member_id", memberid);
//			map.put("receive_id", memberActiviissue.getMemberId().toString());
//			map.put("ifpass", ifpass);
//			map.put("belong_nm", activity.getActivityid());
//			map.put("title", activity.getTitle());
//			//调用云上新生活的接口,到云上做处理
//			String ret = SSOUtils.remoteStringService(map, url);
			
			return pushMsg(Boolean.TRUE, "活动审核通过");
		} else {
			return pushMsg(Boolean.FALSE, "活动审核通过失败");
		}
	}
	/**
	 * 审核不通过
	 * @param request
	 * @param activityid
	 * @param verifydesc
	 * @return
	 * 创建日期：2017-2-13  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value="huodongverifyno.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object huodongverifyno(HttpServletRequest request, String activityid, String verifydesc) {
		UserDTO userDTO = getUser(request);
		String umname = userDTO.getMember_name();
		ActivityDTO activityDTO = new ActivityDTO();
		activityDTO.setHxuuid(activityid);
		activityDTO.setVerifystatus("2");
		activityDTO.setVerifytor(umname);//活动审核人姓名
		activityDTO.setVerifytorid(userDTO.getHxuuid());//活动审核人id
		activityDTO.setVerifydesc(verifydesc);
		activityDTO.setUpdated_by(umname);
		boolean rsval = activityService.updatehuodongverify(activityDTO);
		if (rsval) {
			
			/* *********************************************************
			 * 消息推送
			 * *********************************************************/
//			ActivityDTO activity = yqfwActivityService.queryActivityById(activityid, databasename);//获得活动
//			SysMemberDTO memberActiviissue = yqMemberService.queryMemberByuuid(activity.getActiviissueid(), databasename);//获取活动发布人
//			String ifpass = "fail";
//			String memberid = String.valueOf(userDTO.getId());//推送人id
//			// --------------先做信息保存--------------
//			//调用云上新生活的接口，保存消息到临时表，并推送
//			String url = configMap.get("xmhx.cnlifeURL") + configMap.get("xmhx.cloudVerifyActivity");
//			//临时表所需保存的字段
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("receive_mobile", memberActiviissue.getMemberMobile());
//			map.put("member_id", memberid);
//			map.put("receive_id", memberActiviissue.getMemberId().toString());
//			map.put("ifpass", ifpass);
//			map.put("belong_nm", activity.getActivityid());
//			map.put("title", activity.getTitle());
//			//调用云上新生活的接口,到云上做处理
//			String ret = SSOUtils.remoteStringService(map, url);
			
			return pushMsg(Boolean.TRUE, "活动审核通过");
		} else {
			return pushMsg(Boolean.FALSE, "活动审核通过失败");
		}
	}
	
}
