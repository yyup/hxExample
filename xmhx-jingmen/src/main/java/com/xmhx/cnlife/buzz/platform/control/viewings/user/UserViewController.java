package com.xmhx.cnlife.buzz.platform.control.viewings.user;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xmhx.cnlife.buzz.common.SessionSettings;
import com.xmhx.cnlife.buzz.platform.model.manages.user.UserDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.user.UserService;
import com.xmhx.cnlife.buzz.utils.DateUtils;
import com.xmhx.cnlife.buzz.utils.FileUtils;
import com.xmhx.cnlife.buzz.utils.StrUtil;
import com.xmhx.cnlife.buzz.utils.TextUtils;
import com.xmhx.cnlife.buzz.utils.encrypt.CryptUtil;
import com.xmhx.cnlife.core.authority.LoginAuth;
import com.xmhx.cnlife.core.authority.NoLoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;

@Controller
@RequestMapping("/")
public class UserViewController extends BaseController {
	@Resource
	private UserService userService;
	
	/**
	 * 到会员中心页面
	 * @param map
	 * @return
	 * 创建日期：2017-1-22  创建人：yyp
	 */
	@NoLoginAuth
	@RequestMapping(value="usrCenter.hx", method=RequestMethod.GET)
	public String usrCenter(ModelMap map, HttpSession session) {
		//查询用户信息
		UserDTO userDTO = (UserDTO) session.getAttribute(SessionSettings.VIEWUSER_SESSION);
//		String usrNm = (String) session.getAttribute("usrNm");
//		System.out.println(usrNm);
		map.put("userDTO", userDTO);
		map.put("activeMemberCenter", 1);
		return "viewings/memberCenter";
	}
	
	/**
	 * 修改用户信息
	 * @param request
	 * @param usr
	 * @return
	 * 创建日期：2017-1-22  创建人：yyp
	 */
	@NoLoginAuth
	@RequestMapping(value="updateUser.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object updateUser(HttpServletRequest request, HttpSession session, UserDTO usr) {
		try{
			usr.setUpdated_by(usr.getMember_name());
			int rst = userService.modifyUser(usr);
			if (rst>0) {//修改成功
				UserDTO userDTO = userService.getUserByUUID(usr.getHxuuid());//根据id查询用户信息
				//更新session信息
				session.setAttribute(SessionSettings.VIEWUSER_SESSION, userDTO);
				session.setAttribute("usrNm", userDTO.getMember_name());
				return pushMsg(Boolean.TRUE, "修改成功", "usrNm", userDTO.getMember_name());
			} else {
				return pushMsg(Boolean.FALSE, "修改失败");
			}
		} catch (Exception e) {
			logger.error("修改失败"+e.getMessage());
			return pushMsg(Boolean.FALSE, "修改失败");
		}
	}
	/**
	 * 注册
	 * @param request
	 * @param usr
	 * @return
	 * 创建日期：2017-1-22  创建人：yyp
	 */
	@NoLoginAuth
	@RequestMapping(value="regist.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object regist(HttpServletRequest request, HttpSession session, UserDTO usr) {
		logger.info("用户注册信息 = " + usr.getMember_mobile() + " --> " + DateUtils.formatDate(new Date()));
		try{
			//验证是否已经注册过
			UserDTO userDTO = userService.getUserByMobile(usr.getMember_mobile());
			if(userDTO!=null){
				return pushMsg(Boolean.FALSE, "此手机号已注册，不能重复注册");
			}
			usr.setCreated_by(usr.getMember_name());
			if (TextUtils.notEmpty(usr.getMember_pwd())) {//有密码输入密码
				usr.setMember_pwd(CryptUtil.encryptToMD5(usr.getMember_pwd()));
			} else {//无密码使用默认密码（123456）
				//密码为空，则默认为123456
				String password = CryptUtil.encryptToMD5("123456");
				usr.setMember_pwd(password);
			}
			userService.addUser(usr);
			session.setAttribute(SessionSettings.VIEWUSER_SESSION, usr);//只作超时判断，不要使用里面信息，因为可能会更新而不同步
			session.setAttribute("usrNm", usr.getMember_name());
			return pushMsg(Boolean.TRUE, "注册成功", "userDTO", usr);
		} catch (Exception e) {
			logger.error("注册失败"+e.getMessage());
			return pushMsg(Boolean.FALSE, "注册失败");
		}
	}
	
	/**
	 * 登录
	 * @param request
	 * @param usr
	 * @return
	 * 创建日期：2017-1-23  创建人：yyp
	 */
	@NoLoginAuth
	@RequestMapping(value="loginPlat.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object loginPlat(HttpServletRequest request, HttpSession session, String member_mobile, String member_pwd) {
		logger.info("用户登录信息 = " + member_mobile + " --> " + DateUtils.formatDate(new Date()));
		try {
			String encryt_pwd = CryptUtil.encryptToMD5(member_pwd);
			UserDTO userDTO = userService.getUserByMobileOrAdmin(member_mobile);
			if(userDTO != null){
				if(encryt_pwd.equals(userDTO.getMember_pwd())){
					//密码校验通过
					session.setAttribute(SessionSettings.VIEWUSER_SESSION, userDTO);//只作超时判断，不要使用里面信息，因为可能会更新而不同步
					session.setAttribute("usrNm", userDTO.getMember_name());
					return pushMsg(Boolean.TRUE, userDTO);
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
	 * 创建日期：2017-1-23  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value="loginToOut.hx", method=RequestMethod.GET)
	public String loginToOut(HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute(SessionSettings.VIEWUSER_SESSION);
		logger.info("退出系统 = " + userDTO.getMember_mobile() + " --> " + DateUtils.formatDate(new Date()));
		session.removeAttribute(SessionSettings.VIEWUSER_SESSION);
		session.removeAttribute("usrNm");
		return "redirect:index.hx";
	}
	
	/**
	 * 修改头像
	 * @param request
	 * @param token
	 * @return
	 * 创建日期：2017-1-24  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value="updateMemberHead.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object updateMemberHead(HttpServletRequest request, String hxuuid) {
		logger.info("前端更新头像......");
		
		//上传图片
		//查询原头像文件名称
		String uploadmemberheaddir = configMap.get("xmhx.uploadmemberheaddir");
		MultipartHttpServletRequest imgRequest = null;
		try{
			imgRequest = (MultipartHttpServletRequest)request;
		}catch (Exception e) {
			logger.error("文件上传方式不正确===>" + e.getMessage());
			return pushMsg(Boolean.FALSE, "文件上传方式不正确");
		}
		MultipartFile file = imgRequest.getFile("file");//这里引用的是表单里input框的name为file
		if(StrUtil.isNull(file)){
			return pushMsg(Boolean.FALSE, "文件上传的参数名称不正确，应该命名为file");
		}
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
		if(!(".gif".equals(suffix)||".jpg".equals(suffix)||".jpeg".equals(suffix)||".png".equals(suffix))){
			return pushMsg(Boolean.FALSE, "上传文件的后缀名只支持gif|jpg|jpeg|png");
		}
		UserDTO userDTO = userService.getUserByUUID(hxuuid);
		//覆盖原头像文件
		String newfullfilepath = null;
		try {
			String fileName = StrUtil.upLoadFile(file, uploadmemberheaddir, null);
			if(TextUtils.notEmpty(fileName)){
				newfullfilepath = uploadmemberheaddir+"/" + fileName;
				logger.info("上传头像文件到服务器指定路径成功，newfullfilepath=" + newfullfilepath);
				
				//删除以前的头像文件
				if(null!=userDTO.getMember_head() && TextUtils.notEmpty(userDTO.getMember_head())){
					boolean isok = FileUtils.delFile(uploadmemberheaddir+"/"+userDTO.getMember_head());
					if(isok){
						logger.info("删除以前的头像文件成功");
					}else{
						logger.info("删除以前的头像文件失败");
					}
				}
				//更新用户表的头像路径
				userDTO.setMember_head(fileName);
				userService.modifyUser(userDTO);
				logger.info("更新头像到数据表完成");
				logger.info("上传头像成功");
				return pushMsg(Boolean.TRUE, "上传头像成功","filename",fileName);
			}else{
				logger.error("StrUtil.upLoadFile上传头像失败");
				return pushMsg(Boolean.FALSE, "上传头像失败");
			}
		} catch (IOException e) {
			logger.error("上传头像文件异常===>" + e.getMessage());
			return pushMsg(Boolean.FALSE, "上传头像文件异常");
		}
	}
}
