package com.xmhx.cnlife.buzz.platform.control.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmhx.cnlife.buzz.platform.control.online.OnlineMessage;
import com.xmhx.cnlife.buzz.platform.control.online.OnlineUser;
import com.xmhx.cnlife.buzz.platform.control.online.TokenServer;
import com.xmhx.cnlife.buzz.platform.model.webservices.SysApplyDTO;
import com.xmhx.cnlife.buzz.platform.model.webservices.SysUserApplyDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.user.UserService;
import com.xmhx.cnlife.buzz.platform.service.webservices.SysApplyWSService;
import com.xmhx.cnlife.buzz.platform.service.webservices.SysUserApplyWSService;
import com.xmhx.cnlife.core.authority.LoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;
/**
 * 用户应用控制层
 * @author 吴进 by 20161117
 *
 */
@Controller
@RequestMapping(value="/ws/sysuserapply")
public class SysUserApplyWSControl extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private SysUserApplyWSService sysUserApplyWSService;
	@Autowired
	private SysApplyWSService sysApplyWSService;
	
	/**
	 * 我的工作台-添加系统应用
	 * @param token
	 * @param applycode
	 * @return
	 */
	@LoginAuth
	@RequestMapping("addsysapply.hx")
	@ResponseBody
	public Object addsysapply(String token, String applycode) {
		try{
			logger.info("我的工作台-添加系统应用......");
			boolean chk = checkParam(token,applycode);
			if(!chk){
				return pushMsg(Boolean.FALSE, ERROR_PARAM);
			}
			OnlineMessage message = TokenServer.tokenCheck(token);
			if(message.isSuccess()==false){
				return pushMsg(Boolean.FALSE, message.getMessage());
			}
			OnlineUser onlineUser = message.getOnlineMember();
			String mobile = onlineUser.getTel();
			String username = onlineUser.getMemberNm();
			//添加系统应用
			SysUserApplyDTO sysUserApplyDTO = new SysUserApplyDTO();
			sysUserApplyDTO.setApply_code(applycode);
			sysUserApplyDTO.setUser_mobile(mobile);
			sysUserApplyDTO.setCreated_by(username);
			sysUserApplyDTO.setUpdated_by(username);
			int num = sysUserApplyWSService.addSysUserApply(sysUserApplyDTO);
			if(num == 1){
				return pushMsg(Boolean.TRUE,"添加成功");
			}else{
				logger.error("添加失败 ===>未知错误,num="+num);
				return pushMsg(Boolean.FALSE,"添加失败");
			}
		}catch (Exception e) {
			logger.error("添加失败 ===>" + e.getMessage());
			return pushMsg(Boolean.FALSE,"添加失败");
		}
	}
	
	@LoginAuth
	@RequestMapping("delsysapply.hx")
	@ResponseBody
	public Object delsysapply(String token, String applycode) {
		try{
			logger.info("我的工作台-删除系统应用......");
			boolean chk = checkParam(token,applycode);
			if(!chk){
				return pushMsg(Boolean.FALSE, ERROR_PARAM);
			}
			OnlineMessage message = TokenServer.tokenCheck(token);
			if(message.isSuccess()==false){
				return pushMsg(Boolean.FALSE, message.getMessage());
			}
			OnlineUser onlineUser = message.getOnlineMember();
			String mobile = onlineUser.getTel();
			//添加系统应用
			SysUserApplyDTO sysUserApplyDTO = new SysUserApplyDTO();
			sysUserApplyDTO.setApply_code(applycode);
			sysUserApplyDTO.setUser_mobile(mobile);
			int num = sysUserApplyWSService.deleteSysUserApply(sysUserApplyDTO);
			if(num > 0){
				return pushMsg(Boolean.TRUE,"删除成功");
			}else if(num == 0){
				return pushMsg(Boolean.FALSE,"删除失败，应用不存在或已被删除");
			}else{
				logger.error("删除失败 ===>未知错误,num="+num);
				return pushMsg(Boolean.FALSE,"删除失败");
			}
		}catch (Exception e) {
			logger.error("删除失败 ===>" + e.getMessage());
			return pushMsg(Boolean.FALSE,"删除失败");
		}
	}
	
	/**
	 * 我的工作台-应用列表
	 * @param token
	 * @return
	 */
	@LoginAuth
	@RequestMapping("myapplylist.hx")
	@ResponseBody
	public Object myapplylist(String token) {
		try{
			logger.info("我的工作台-应用列表......");
			boolean chk = checkParam(token);
			if(!chk){
				return pushMsg(Boolean.FALSE, ERROR_PARAM);
			}
			OnlineMessage message = TokenServer.tokenCheck(token);
			if(message.isSuccess()==false){
				return pushMsg(Boolean.FALSE, message.getMessage());
			}
			OnlineUser onlineUser = message.getOnlineMember();
			String mobile = onlineUser.getTel();
			//查询我的应用
			List<SysApplyDTO> sysUserApplyList = sysUserApplyWSService.querySysUserApply(mobile);
			return pushMsg(Boolean.TRUE,"查询成功","applist",sysUserApplyList);
		}catch (Exception e) {
			logger.error("查询失败 ===>" + e.getMessage());
			return pushMsg(Boolean.FALSE,"查询失败");
		}
	}
	
	@LoginAuth
	@RequestMapping("allapplylist.hx")
	@ResponseBody
	public Object allapplylist(String token) {
		try{
			logger.info("所有应用列表......");
			boolean chk = checkParam(token);
			if(!chk){
				return pushMsg(Boolean.FALSE, ERROR_PARAM);
			}
			OnlineMessage message = TokenServer.tokenCheck(token);
			if(message.isSuccess()==false){
				return pushMsg(Boolean.FALSE, message.getMessage());
			}
			OnlineUser user = message.getOnlineMember();
			String mobile = user.getTel();
			//查询所有的应用
			List<SysApplyDTO> sysApplyList = sysApplyWSService.queryAllSysApply(mobile);
			return pushMsg(Boolean.TRUE,"查询成功","applist",sysApplyList);
		}catch (Exception e) {
			logger.error("查询失败 ===>" + e.getMessage());
			return pushMsg(Boolean.FALSE,"查询失败");
		}
	}
}
