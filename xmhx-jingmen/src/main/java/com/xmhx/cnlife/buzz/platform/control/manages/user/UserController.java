package com.xmhx.cnlife.buzz.platform.control.manages.user;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmhx.cnlife.buzz.common.SessionSettings;
import com.xmhx.cnlife.buzz.platform.model.manages.user.UserDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.user.UserService;
import com.xmhx.cnlife.buzz.utils.DateUtils;
import com.xmhx.cnlife.buzz.utils.TextUtils;
import com.xmhx.cnlife.buzz.utils.encrypt.CryptUtil;
import com.xmhx.cnlife.core.authority.LoginAuth;
import com.xmhx.cnlife.core.authority.NoLoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;
import com.xmhx.cnlife.core.model.PageBean;
import com.xmhx.cnlife.core.model.PageVO;

/**
 * 用户控制层
 * @author 吴进 by 20161117
 *
 */
@Controller
@RequestMapping(value="/manages/")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	

	
	/************************ 系统登录 Start *****************************************************/
	/**
	 * 跳转登录页面
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="gologon.hx", method=RequestMethod.GET)
	public String gologon() {
		return "manages/main/login";
	}
	
	/**
	 * 登录
	 * @param usrNo 用户名
	 * @param usrPwd 密码
	 * @param session
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="dologon.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object dologon(HttpSession session, String usrNo, String usrPwd) {
		logger.info("登录信息 = " + usrNo + " --> " + DateUtils.formatDate(new Date()));
		boolean chk = checkParam(usrNo,usrPwd);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		try {
			String encryt_pwd = CryptUtil.encryptToMD5(usrPwd);
			UserDTO userDTO = userService.getUserByMobileOrAdmin(usrNo);
			if(userDTO != null){
				if(encryt_pwd.equals(userDTO.getMember_pwd())){
					//密码校验通过
					session.setAttribute(SessionSettings.USER_SESSION, userDTO);//只作超时判断，不要使用里面信息，因为可能会更新而不同步
					session.setMaxInactiveInterval(60*15);
					return pushMsg(Boolean.TRUE, "index.hx");
				}else{
					return pushMsg(Boolean.FALSE, "密码错误，登录失败");
				}
			}else{
				return pushMsg(Boolean.FALSE, "用户不存在，登录失败");
			}
		} catch (Exception e) {
			logger.error("登录失败"+e.getMessage());
			return pushMsg(Boolean.FALSE, "登录失败");
		}
		
	}

	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="logout.hx", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		UserDTO userDTO = getUser(session);
		session.removeAttribute(SessionSettings.USER_SESSION);
		logger.info("退出系统 = " + userDTO.getMember_mobile() + " --> " + DateUtils.formatDate(new Date()));
		return "redirect:gologon.hx";
	}
	/************************ 系统登录 End *****************************************************/
	
	/************************ 业务模块 Start *****************************************************/
	
	@LoginAuth
	@RequestMapping(value="commonmember/list.hx", method=RequestMethod.GET)
	public String list() {
		return "manages/user/commonmemberList";
	}
	
	/**
	 * 人员管理列表
	 * @param page 必填 第几页
	 * @param rows 必填 每页显示几行
	 * @param sort 排序字段
	 * @param order 正序或倒序
	 * @param userDTO 参数为nameormobile，用于模糊查询用户名和手机号
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="commonmember/pagelist.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object pagelist(@RequestParam(defaultValue="1")int page, int rows, String sort, String order, UserDTO userDTO) {
		PageBean<UserDTO> pageBean = userService.getUserByPage(page, rows, sort, order, userDTO);
		if(pageBean != null) {
			PageVO<UserDTO> pagevo = new PageVO<UserDTO>();
			pagevo.setTotal(pageBean.getRecordCount());
			pagevo.setRows(pageBean.getDataList());
			return pagevo;
		}
		return null;
	}
	
	/**
	 * 添加用户/修改用户
	 * @param session
	 * @param userDTO
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="commonmember/saveuser.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object saveuser(HttpSession session,UserDTO userDTO) {
		boolean chk = checkParam(userDTO,userDTO.getMember_mobile(),userDTO.getMember_name(),userDTO.getMember_sex());
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		//判断各字段是否合法
		String membername = userDTO.getMember_name();
		String membernick = userDTO.getMember_nick();
		String sex = userDTO.getMember_sex();
		String email = userDTO.getMember_email();
		String memberpwd = userDTO.getMember_pwd();
		if(membername.length()>10){
			return pushMsg(Boolean.FALSE, "姓名长度不能超过10个字");
		}
		if(membernick.length()>10){
			return pushMsg(Boolean.FALSE, "昵称长度不能超过10个字");
		}
		if(!("1".equals(sex) || "2".equals(sex))){
			return pushMsg(Boolean.FALSE, "性别不合法");
		}
		if(TextUtils.notEmpty(email)){
			if(email.length()>30){
				return pushMsg(Boolean.FALSE, "邮件地址长度不能超过30个字");
			}
			if(!email.matches(configMap.get("xmhx.emailregex"))){
				return pushMsg(Boolean.FALSE, "邮件地址不合法");
			}
		}
		if("mobile".equals(membername)||"system".equals(membername)||"root".equals(membername)){
			return pushMsg(Boolean.FALSE, "姓名"+membername+"不能注册，请更改");
		}
		if(TextUtils.notEmpty(memberpwd)){
			if(memberpwd.length()>18){
				return pushMsg(Boolean.FALSE, "密码长度不能超过18个字");
			}
		}
		//获取登录用户
		UserDTO logon_user = getUser(session);
		if(!TextUtils.notEmpty(userDTO.getHxuuid())){
			//添加用户
			try {
				//校验手机号是否合法
				String mobile = userDTO.getMember_mobile();
				if(!mobile.matches(configMap.get("xmhx.mobileregex"))){
					return pushMsg(Boolean.FALSE, "手机号不合法");
				}
				//查询手机号是否重复添加
				UserDTO user = userService.getUserByMobile(mobile);
				if(user!=null){
					return pushMsg(Boolean.FALSE, "此手机号已注册，不能重复注册");
				}
				//设置创建人
				String logonname = logon_user.getMember_name();
				userDTO.setCreated_by(logonname);
				userDTO.setUpdated_by(logonname);
				//如果有初始密码
				if (TextUtils.notEmpty(memberpwd)) {
					String password = CryptUtil.encryptToMD5(memberpwd);
					userDTO.setMember_pwd(password);					
				} else {
					//默认初始密码
					String password = CryptUtil.encryptToMD5("123456");
					userDTO.setMember_pwd(password);
				}
				String uuid = userService.addUser(userDTO);
				return pushMsg(Boolean.TRUE,"添加成功",uuid);
			} catch (Exception e) {
				logger.error("添加失败===>" +e.getMessage());
				return pushMsg(Boolean.FALSE, "添加失败");
			}
		}else{
			//修改用户
			try {
				userDTO.setMember_mobile(null);	//不修改手机号
				userDTO.setMember_head(null);   //不修改头像路径
				userDTO.setUpdated_by(logon_user.getMember_name());
				if(TextUtils.notEmpty(memberpwd)){
					String password = CryptUtil.encryptToMD5(memberpwd);
					userDTO.setMember_pwd(password);
				}
				userService.modifyUser(userDTO);
				return pushMsg(Boolean.TRUE,"修改成功");
			}catch (Exception e) {
				logger.error("修改失败===>" +e.getMessage());
				return pushMsg(Boolean.FALSE, "修改失败");
			}
		}
	} 
}
