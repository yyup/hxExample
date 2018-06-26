package com.xmhx.cnlife.buzz.platform.control.webservices;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xmhx.cnlife.buzz.common.CnlifeConstants;
import com.xmhx.cnlife.buzz.common.JavaSmsApi;
import com.xmhx.cnlife.buzz.common.SessionContext;
import com.xmhx.cnlife.buzz.platform.control.online.OnlineMessage;
import com.xmhx.cnlife.buzz.platform.control.online.OnlineUser;
import com.xmhx.cnlife.buzz.platform.control.online.TokenServer;
import com.xmhx.cnlife.buzz.platform.model.manages.user.UserDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.user.UserService;
import com.xmhx.cnlife.buzz.utils.FileUtils;
import com.xmhx.cnlife.buzz.utils.StrUtil;
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
@RequestMapping(value="/ws/")
public class UserWSControl extends BaseController {
	@Autowired
	private UserService userService;
	
	/**
	 * 登录
	 * @param hxuuid
	 * @param member_name
	 * @param member_mobile
	 * @return 返回token
	 */
	private String login(String hxuuid,String member_name,String member_mobile){
		logger.info("新建登录OnlineUser对象");
		try {
			OnlineUser onlineUser = new OnlineUser(hxuuid,member_name,member_mobile);
			String token = null;
			token = CryptUtil.encryptToMD5(onlineUser.getTel()+onlineUser.getMemberNm()+new Date()).toLowerCase();
			onlineUser.setToken(token);
			logger.info("token生成成功，设置TokenServer对象");
			TokenServer.checkLoginState(hxuuid);	// 是否重复登录操作
			TokenServer.tokenCreated(onlineUser);
			logger.info("登录成功，产生token = " + token);
			return token;
		} catch (Exception e) {
			logger.error("登录失败"+e.getMessage());
			return null;
		}
	}

	/**
	 * 登录
	 * @param mobile
	 * @param pwd
	 * @param pwdtype 1 普通密码 2手势密码
	 */
	@NoLoginAuth
	@RequestMapping("login.hx")
	@ResponseBody
	public Object login(String mobile, String pwd, @RequestParam(defaultValue="1")int pwdtype) {
		try{
			logger.info("登录中......");
			boolean chk = checkParam(mobile,pwd);
			if(!chk){
				return pushMsg(Boolean.FALSE, ERROR_PARAM);
			}
			if(2 != pwdtype && 1 != pwdtype){
				return pushMsg(Boolean.FALSE, ERROR_PARAM);
			}
			if(!mobile.matches(configMap.get("xmhx.mobileregex"))){
				return pushMsg(Boolean.FALSE, "手机号不合法");
			}
			UserDTO userDTO = userService.getUserByMobile(mobile);
			if(null == userDTO){
				return pushMsg(Boolean.FALSE, "用户名不存在");
			}else if("N".equals(userDTO.getData_state())){
				return pushMsg(Boolean.FALSE, "该用户禁用");
			}else{
				String realPwd = null;
				if(2 == pwdtype){
					realPwd = userDTO.getPattern_lock();
				}else if(1 == pwdtype){
					realPwd = userDTO.getMember_pwd();
				}
				if(TextUtils.isEmpty(realPwd)){
					return pushMsg(Boolean.FALSE, "密码不存在，请重新设置或找回密码");
				}else if(realPwd.equals(CryptUtil.encryptToMD5(pwd))){
					logger.info("手机号与密码校验已通过");
					//登录并生成token
					String token = login(userDTO.getHxuuid(),userDTO.getMember_name(),userDTO.getMember_mobile());
					return pushMsg(Boolean.TRUE, "登录成功", "token", token,"userDTO",userDTO);
				}else{
					logger.info("密码不正确，请重新输入");
					return pushMsg(Boolean.FALSE, "密码不正确，请重新输入");
				}
			}
		}catch (Exception e) {
			logger.error("登录失败 ===>" + e.getMessage());
			return pushMsg(Boolean.FALSE,"登录失败");
		}
	}
	
	/************************ 业务模块 Start *****************************************************/
	
	/**
	 * 人员管理列表
	 * @param page 必填 第几页
	 * @param rows 必填 每页显示几行
	 * @param sort 排序字段，默认null，不排序
	 * @param order 正序或倒序，默认null，不排序
	 * @param userDTO 参数为nameormobile，用于模糊查询用户名和手机号
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="commonmember/pagelist.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object pagelist(String token, @RequestParam(defaultValue="1")int page, int rows, String sort, String order, UserDTO userDTO) {
		logger.info("人员管理列表......");
		OnlineMessage message = TokenServer.tokenCheck(token);
		if(message.isSuccess()==false){
			return pushMsg(Boolean.FALSE, message.getMessage());
		}
		
		PageBean<UserDTO> pageBean = userService.getUserByPage(page, rows, sort, order, userDTO);
		if(pageBean != null) {
			PageVO<UserDTO> pagevo = new PageVO<UserDTO>();
			pagevo.setTotal(pageBean.getRecordCount());
			pagevo.setRows(pageBean.getDataList());
			return pushMsg(Boolean.TRUE, "查询数据成功","pagevo",pagevo);
		}
		return pushMsg(Boolean.FALSE, "查询数据为空");
	}
	
	/**
	 * 获取验证码
	 * @param request
	 * @param mobile 手机号
	 * @param type (1 注册 2 修改手机号码 3 修改密码 4 找回密码)
	 */
	@NoLoginAuth
	@RequestMapping(value="commonmember/getCode.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object getCode(HttpServletRequest request,String mobile,String type){
		boolean chk = checkParam(mobile,type);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		if(!mobile.matches(configMap.get("xmhx.mobileregex"))){
			return pushMsg(Boolean.FALSE, "手机号不合法");
		}
		if (!("1".equals(type) || "2".equals(type) || "3".equals(type) || "4".equals(type))){
			return pushMsg(Boolean.FALSE, "输入参数type的值只能是1-4");
		}
		try{
			UserDTO userDTO = userService.getUserByMobile(mobile);
			if(userDTO == null){
				//如果手机号码未注册
				if("1".equals(type) || "2".equals(type)){
					//进行注册或修改手机号码,可通过
				}else if("3".equals(type)||"4".equals(type)){
					return pushMsg(Boolean.FALSE, "要找回密码的账号未注册或输入有误");
				}
			}else{
				//手机号码已注册
				if("1".equals(type) || "2".equals(type)){
					return pushMsg(Boolean.FALSE, "该号码已经注册过");
				}else if("3".equals(type)||"4".equals(type)){
					//修改密码或找回密码,可通过
				}
			}
			int cnum = (int) (Math.random()*999999+100000);
			String text = "";
			if("1".equals(type)){
				text = "【云上新生活】验证码："+cnum+"，申请手机注册，请尽快验证。";
			}else if("2".equals(type)){
				text = "【云上新生活】验证码："+cnum+"，申请修改手机号码，请尽快验证。";
			}else if("3".equals(type)){
				text = "【云上新生活】验证码："+cnum+"，申请修改密码，请尽快验证。";
			}else if("4".equals(type)){
				text = "【云上新生活】验证码："+cnum+"，申请找回密码，请尽快验证。";
			}else{
				text = "【云上新生活】验证码："+cnum+"。";
			}
			//发送短信验证码
			String str= JavaSmsApi.sendSms(text, mobile);
			net.sf.json.JSONObject result = net.sf.json.JSONObject.fromObject(str);
			Integer status = (Integer) result.get("code");
			if(status>0){//短信发送失败
				logger.error("短信发送失败，错误信息:"+result.get("msg")+",错误码："+status+",错误详情："+result.get("detail")+",号码："+mobile);
				return pushMsg(Boolean.FALSE, "短信发送失败");
			}
			HttpSession session = request.getSession();
			session.setAttribute("type", type);
            session.setAttribute("code", cnum);
            session.setAttribute("mobile", mobile);
            session.setAttribute("sendTime", new Date());
            SessionContext sessionContext = SessionContext.getInstance();
            sessionContext.AddSession(session);
            return pushMsg(Boolean.TRUE, "获取验证码成功","sessionid",session.getId());
		}catch (Exception e) {
			logger.error("获取验证码失败" + e.getMessage());
			return pushMsg(Boolean.FALSE, "获取验证码失败");
		}
	}
	
	/**
	 * 验证验证码
	 * @param mobile
	 * @param sendcode
	 * @param sessionid
	 * @param type (1 注册 2 修改手机号码 3 修改密码 4 找回密码)
	 */
	private Map<String, Object> verificationSms(String mobile,
			String sendcode, String sessionid, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		/***验证短信验证码****/
		if(StrUtil.isNull(sessionid)){//默认验证码
			if(!CnlifeConstants.SMSTR.equals(sendcode)){
				map.put("status", Boolean.FALSE);
				map.put("info", "验证码错误");
				return map;
			}
		}else{//验证码验证
			SessionContext sessionContext = SessionContext.getInstance();
			HttpSession session = sessionContext.getSession(sessionid);
			
			if(null==session){
				map.put("status", Boolean.FALSE);
				map.put("info", "验证码已失效或过期");
				return map;
			}
			if(!type.equals(session.getAttribute("type"))){
				map.put("status", Boolean.FALSE);
				map.put("info", "验证码类型不匹配");
				return map;
			}
			if(!mobile.equals(session.getAttribute("mobile"))){
				map.put("status", Boolean.FALSE);
				map.put("info", "手机号码与注册验证码不一致");
				return map;
			}
			Date sendTime = (Date) session.getAttribute("sendTime");
		    long time = new Date().getTime() - sendTime.getTime();
		    long minutes = time / 60000;
		    if (minutes > CnlifeConstants.CODE_OVERTIME) {
		    	map.put("status", Boolean.FALSE);
				map.put("info", "验证码超时");
				return map;
		    } else if (!sendcode.equalsIgnoreCase(session.getAttribute("code").toString())) {
		    	map.put("status", Boolean.FALSE);
				map.put("info", "验证码错误");
				return map;
		    }else{
		    	//验证码正确，清除session
		    	sessionContext.DelSession(session);
		    }
		}
		map.put("status", Boolean.TRUE);
		map.put("info", "验证码正确");
		return map;
	}
//	/**
//	 * 验证短信验证码(应该与表单同时进行验证，而不是单独一个请求，故不建议使用)
//	 * @param mobile
//	 * @param sendcode
//	 * @param sessionid
//	 * @param type (1 注册 2 修改手机号码 3 修改密码 4 找回密码)
//	 */
//	@Deprecated
//	@NoLoginAuth
//	@RequestMapping(value="commonmember/verifiySms.hx", method=RequestMethod.POST)
//	@ResponseBody
//	public Object verifiySms(String mobile,
//			String sendcode, String sessionid, String type) {
//		logger.info("验证短信验证码......");
//		boolean chk = checkParam(mobile,sendcode,sessionid,type);
//		if(!chk){
//			return pushMsg(Boolean.FALSE, ERROR_PARAM);
//		}
//		Map<String, Object> map = verificationSms(mobile,
//				sendcode, sessionid, type);
//		return pushMsg(Boolean.valueOf(map.get("status").toString()), map.get("info"));
//	}
	/**
	 * 新用户注册
	 * @param token
	 * @param userDTO --手机号member_mobile，用户名member_name
	 * @param sendcode  验证码
	 * @param sessionid
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="commonmember/register.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object register(UserDTO userDTO,String sendcode,String sessionid) {
		logger.info("新用户注册......");
		boolean chk = checkParam(userDTO,userDTO.getMember_mobile(),userDTO.getMember_name(),sendcode);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		//判断各字段是否合法
		String mobile = userDTO.getMember_mobile();
		String membername = userDTO.getMember_name();
		if(!mobile.matches(configMap.get("xmhx.mobileregex"))){
			return pushMsg(Boolean.FALSE, "手机号不合法");
		}
		if(membername.length()>10){
			return pushMsg(Boolean.FALSE, "姓名长度不能超过10个字");
		}
		if("mobile".equals(membername)||"system".equals(membername)||"root".equals(membername)){
			return pushMsg(Boolean.FALSE, "姓名"+membername+"不能注册，请更改");
		}
		userDTO.setMember_nick(null);
		userDTO.setMember_email(null);
		userDTO.setMember_sex(null);
		userDTO.setMember_head(null);
		//注册用户
		try {
			//验证是否重复注册
			UserDTO user = userService.getUserByMobile(mobile);
			if(user!=null){
				//该手机号已注册
				return pushMsg(Boolean.FALSE, "此手机号已注册，不能重复注册");
			}else{
				//验证验证码
				Map<String, Object> map = verificationSms(mobile, sendcode, sessionid,"1");
				if(!Boolean.valueOf(map.get("status").toString())){
					//如果验证码校验不通过，则终止
					return pushMsg(Boolean.valueOf(map.get("status").toString()), map.get("info"));
				}
				//对未注册的，进行注册
				String createdby = "mobile";
				userDTO.setCreated_by(createdby);
				userDTO.setUpdated_by(createdby);
				if (TextUtils.notEmpty(userDTO.getMember_pwd())) {
					//如果密码不为空，则保存密码
					String password = CryptUtil.encryptToMD5(userDTO.getMember_pwd());
					userDTO.setMember_pwd(password);					
				} else {
					//密码为空，则默认为123456
					String password = CryptUtil.encryptToMD5("123456");
					userDTO.setMember_pwd(password);
				}
				//添加用户
				String uuid = userService.addUser(userDTO);
				userDTO.setHxuuid(uuid);
				String token = login(userDTO.getHxuuid(),userDTO.getMember_name(),userDTO.getMember_mobile());
				logger.info("注册成功,uuid=" + uuid + "token=" + token);
				return pushMsg(Boolean.TRUE,"注册成功","uuid",uuid,"token",token);
			}
		} catch (Exception e) {
			logger.error("注册失败===>" + e.getMessage());
			return pushMsg(Boolean.FALSE, "注册失败");
		}
	} 
	
	/**
	 * 上传头像/更新头像
	 * @param request
	 * @param token
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="commonmember/updateHeadPhoto.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object updateHeadPhoto(HttpServletRequest request, String token) {
		logger.info("接口，更新头像......");
		OnlineMessage message = TokenServer.tokenCheck(token);
		if(message.isSuccess()==false){
			return pushMsg(Boolean.FALSE, message.getMessage());
		}
		OnlineUser onlineUser = message.getOnlineMember();
		String memid = onlineUser.getMemId();
		
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
		UserDTO userDTO = userService.getUserByUUID(memid);
		//覆盖原头像文件
		String newfullfilepath = null;
		try {
			String fileName = StrUtil.upLoadFile(file, uploadmemberheaddir, null);
			if(TextUtils.notEmpty(fileName)){
				newfullfilepath = uploadmemberheaddir+"/" + fileName;
				logger.info("上传头像文件到服务器指定路径成功，newfullfilepath=" + newfullfilepath);
				
				//删除以前的头像文件
				String oldfilename = userDTO.getMember_head();
				if(TextUtils.notEmpty(oldfilename)){
					boolean isok = FileUtils.delFile(uploadmemberheaddir+"/"+oldfilename);
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
	
	/**
	 * 修改密码(登录后)
	 * @param token
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="commonmember/modifyPassword.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object modifyPassword(String token,String oldpassword, String newpassword, String sendcode, String sessionid) {
		logger.info("接口，修改密码......");
		boolean chk = checkParam(token,oldpassword,newpassword,sendcode);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		if(newpassword.length()>18){
			return pushMsg(Boolean.FALSE, "新密码长度不能超过18个字");
		}
		OnlineMessage message = TokenServer.tokenCheck(token);
		if(message.isSuccess()==false){
			return pushMsg(Boolean.FALSE, message.getMessage());
		}
		OnlineUser onlineUser = message.getOnlineMember();
		String memid = onlineUser.getMemId();
		String mobile = onlineUser.getTel();
		try {
			String oldpwd = CryptUtil.encryptToMD5(oldpassword);
			String newpwd = CryptUtil.encryptToMD5(newpassword);
			//获取用户信息
			UserDTO userDTO = userService.getUserByUUID(memid);
			String pwd = userDTO.getMember_pwd();
			//比较旧密码是否正确
			if(oldpwd.equals(pwd)){
				//短信验证验证码
				Map<String, Object> map = verificationSms(mobile, sendcode, sessionid,"3");
				if(!Boolean.valueOf(map.get("status").toString())){
					//如果短信验证码校验不通过，则终止
					return pushMsg(Boolean.valueOf(map.get("status").toString()), map.get("info"));
				}else{
					//设置新密码
					userDTO.setMember_pwd(newpwd);
					userService.modifyUser(userDTO);	
					return pushMsg(Boolean.TRUE, "修改密码成功");
				}
			}else{
				return pushMsg(Boolean.FALSE, "修改密码失败,旧密码不正确");
			}
		} catch (Exception e) {
			logger.error("修改密码失败===>" + e.getMessage());
			return pushMsg(Boolean.FALSE, "修改密码失败");
		}
		
	}
	
	/**
	 * 忘记密码（重置密码）
	 * @param mobile
	 * @param newpassword
	 * @param sendcode
	 * @param sessionid
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value="commonmember/resetPassword.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object resetPassword(String mobile, String newpassword, String sendcode, String sessionid) {
		logger.info("接口，重置密码......");
		boolean chk = checkParam(mobile,newpassword,sendcode);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		if(!mobile.matches(configMap.get("xmhx.mobileregex"))){
			return pushMsg(Boolean.FALSE, "手机号不合法");
		}
		if(newpassword.length()>18){
			return pushMsg(Boolean.FALSE, "新密码长度不能超过18个字");
		}
		//获取用户信息
		UserDTO userDTO = userService.getUserByMobile(mobile);
		if(userDTO == null){
			return pushMsg(Boolean.FALSE, "新密码设置失败,此手机号未注册");
		}
		try {
			//验证验证码
			Map<String, Object> map = verificationSms(mobile, sendcode, sessionid,"4");
			if(!Boolean.valueOf(map.get("status").toString())){
				//如果验证码校验不通过，则终止
				return pushMsg(Boolean.valueOf(map.get("status").toString()), map.get("info"));
			}
			//设置新密码
			String newpwd = CryptUtil.encryptToMD5(newpassword);
			userDTO.setMember_pwd(newpwd);
			userService.modifyUser(userDTO);
			//登录
			String token = login(userDTO.getHxuuid(),userDTO.getMember_name(),userDTO.getMember_mobile());
			logger.info("新密码设置成功,token="+token);
			return pushMsg(Boolean.TRUE, "新密码设置成功","token",token);
			
		} catch (Exception e) {
			logger.error("新密码设置失败===>" + e.getMessage());
			return pushMsg(Boolean.FALSE, "新密码设置失败");
		}
	}
	/**
	 * 修改用户信息（非头像和密码）
	 * @param token
	 * @param userDTO
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="commonmember/modifyUserinfo.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object modifyUserinfo(String token, UserDTO userDTO) {
		logger.info("接口，修改用户信息......");
		boolean chk = checkParam(token,userDTO);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		//判断各字段是否合法
		String membername = userDTO.getMember_name();
		String membernick = userDTO.getMember_nick();
		String sex = userDTO.getMember_sex();
		String email = userDTO.getMember_email();
		if(TextUtils.notEmpty(membername) && membername.length()>10){
			return pushMsg(Boolean.FALSE, "姓名长度不能超过10个字");
		}
		if(TextUtils.notEmpty(membernick) && membernick.length()>10){
			return pushMsg(Boolean.FALSE, "昵称长度不能超过10个字");
		}
		if(TextUtils.notEmpty(sex)){
			if( !("1".equals(sex) || "2".equals(sex))){
				return pushMsg(Boolean.FALSE, "性别不合法");
			}
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
		//不能修改的字段设置为空
		userDTO.setMember_head(null);
		userDTO.setMember_pwd(null);
		userDTO.setMember_mobile(null);
		
		try{
			//获取用户信息
			OnlineMessage message = TokenServer.tokenCheck(token);
			if(message.isSuccess()==false){
				return pushMsg(Boolean.FALSE, message.getMessage());
			}
			OnlineUser onlineUser = message.getOnlineMember();
			String memid = onlineUser.getMemId();
			userDTO.setHxuuid(memid);
			userService.modifyUser(userDTO);
			return pushMsg(Boolean.TRUE, "修改成功");
		} catch (Exception e){
			logger.error("修改用户信息失败===>"+e.getMessage());
		}
		return pushMsg(Boolean.FALSE, "修改失败");
	}
	/**
	 * 根据手机号查询用户信息
	 * @param token
	 * @param userDTO
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="commonmember/queryUserinfo.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object queryUserinfo(String token, String mobile) {
		logger.info("接口，查询用户信息......");
		boolean chk = checkParam(token,mobile);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		OnlineMessage message = TokenServer.tokenCheck(token);
		if(message.isSuccess()==false){
			return pushMsg(Boolean.FALSE, message.getMessage());
		}
		if(!mobile.matches(configMap.get("xmhx.mobileregex"))){
			return pushMsg(Boolean.FALSE, "手机号不合法");
		}
		UserDTO userDTO = userService.getUserByMobile(mobile);
		if(userDTO!=null){
			return pushMsg(Boolean.TRUE, "查询成功", "user", userDTO);
		}else{
			return pushMsg(Boolean.FALSE, "查询数据为空");
		}
	}
	@LoginAuth
	@RequestMapping("logout.hx")
	@ResponseBody
	public Object logout(String token) {
		logger.info("接口，退出......");
		boolean chk = checkParam(token);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		try{
			OnlineMessage message = TokenServer.tokenCheck(token);
			if(message.isSuccess()==false){
				return pushMsg(Boolean.FALSE, message.getMessage());
			}
			TokenServer.tokenDestroyed(message.getOnlineMember());
			return pushMsg(Boolean.TRUE, "退出成功");
		}catch (Exception e) {
			logger.error("退出失败===>"+e.getMessage());
			return pushMsg(Boolean.FALSE, "退出失败");
		}
	}
	/**
	 * 打开并设置手势密码
	 * @param token
	 * @param patternlock
	 * @return
	 */
	@LoginAuth
	@RequestMapping("commonmember/patternlock.hx")
	@ResponseBody
	public Object patternlock(String token,String patternlock) {
		logger.info("接口，打开并设置手势密码......");
		boolean chk = checkParam(token,patternlock);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		try{
			OnlineMessage message = TokenServer.tokenCheck(token);
			if(message.isSuccess()==false){
				return pushMsg(Boolean.FALSE, message.getMessage());
			}
			OnlineUser onlineUser = message.getOnlineMember();
			String mobile = onlineUser.getTel();
			//修改手势密码
			String patternlock_md5 = CryptUtil.encryptToMD5(patternlock);
			int num = userService.modifyPatternlock(mobile,patternlock_md5);
			if(num == 1){
				return pushMsg(Boolean.TRUE, "设置手势密码成功");
			}else{
				logger.error("设置手势密码失败，num="+num);
				return pushMsg(Boolean.FALSE, "设置手势密码失败");
			}
		}catch (Exception e) {
			logger.error("设置手势密码失败===>"+e.getMessage());
			return pushMsg(Boolean.FALSE, "设置手势密码失败");
		}
	}
	
	/**
	 * 关闭手势密码（登录后）
	 * @param token
	 * @return
	 */
	@LoginAuth
	@RequestMapping("commonmember/closepatternlock.hx")
	@ResponseBody
	public Object closepatternlock(String token) {
		logger.info("接口，关闭手势密码......");
		boolean chk = checkParam(token);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		try{
			OnlineMessage message = TokenServer.tokenCheck(token);
			if(message.isSuccess()==false){
				return pushMsg(Boolean.FALSE, message.getMessage());
			}
			OnlineUser onlineUser = message.getOnlineMember();
			String mobile = onlineUser.getTel();
			//关闭手势密码
			String patternlock = null;
			userService.modifyPatternlock(mobile,patternlock);
			return pushMsg(Boolean.TRUE, "关闭手势密码成功");
		}catch (Exception e) {
			logger.error("关闭手势密码失败===>"+e.getMessage());
			return pushMsg(Boolean.FALSE, "关闭手势密码失败");
		}
	}
	
	/**
	 * 忘记手势密码或切换账户登录
	 * @param mobile
	 * @param optiontype
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping("commonmember/clearpatternlock.hx")
	@ResponseBody
	public Object clearpatternlock(String mobile, String optiontype) {
		boolean chk = checkParam(mobile);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		if("1".equals(optiontype) ){
			logger.info("忘记手势密码......");
		}else if("2".equals(optiontype)){
			logger.info("切换账户登录......");
		}else{
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		if(!mobile.matches(configMap.get("xmhx.mobileregex"))){
			return pushMsg(Boolean.FALSE, "手机号不合法");
		}
		try{
			//关闭手势密码
			String patternlock = null;
			userService.modifyPatternlock(mobile,patternlock);
			return pushMsg(Boolean.TRUE, "关闭手势密码成功");
		}catch (Exception e) {
			logger.error("关闭手势密码失败===>"+e.getMessage());
			return pushMsg(Boolean.FALSE, "关闭手势密码失败");
		}
	}
	
	/**
	 * 查询手势密码开关
	 * @param mobile
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping("commonmember/queryifexistpatternlock.hx")
	@ResponseBody
	public Object queryifexistpatternlock(String mobile) {
		logger.info("接口，查询手势密码开关......");
		boolean chk = checkParam(mobile);
		if(!chk){
			return pushMsg(Boolean.FALSE, ERROR_PARAM);
		}
		if(!mobile.matches(configMap.get("xmhx.mobileregex"))){
			return pushMsg(Boolean.FALSE, "手机号不合法");
		}
		try{
			//查询手势密码开关
			int flag = userService.queryIfExistPatternlock(mobile);
			if(flag == 1){
				//手势密码打开
				return pushMsg(Boolean.TRUE, "查询成功","ifpatternlock",flag);
			}else if(flag == 0){
				//手势密码关闭
				return pushMsg(Boolean.TRUE, "查询成功","ifpatternlock",flag);
			}else{
				logger.error("查询手势密码开关失败===>flag="+flag);
				return pushMsg(Boolean.FALSE, "查询失败");
			}
		}catch (Exception e) {
			logger.error("查询手势密码开关失败===>"+e.getMessage());
			return pushMsg(Boolean.FALSE, "查询失败");
		}
	}
	
}
