package com.xmhx.cnlife.buzz.platform.control.webservices;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmhx.cnlife.buzz.platform.control.online.OnlineMessage;
import com.xmhx.cnlife.buzz.platform.control.online.TokenServer;
import com.xmhx.cnlife.buzz.platform.model.webservices.MessagesDTO;
import com.xmhx.cnlife.buzz.platform.service.webservices.PropertyrightMessagesWSService;
import com.xmhx.cnlife.buzz.utils.StrUtil;
import com.xmhx.cnlife.core.authority.NoLoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;

/**
 * 移动端消息接口
 * @author yyp
 *
 */
@Controller
@RequestMapping(value="/ws/")
public class MobileterminalMessagesWSControl extends BaseController {
	@Resource
	private PropertyrightMessagesWSService propertyrightMessagesWSService;
	
	/**
	 * 更新应答状态
	 * @param uuids 接收到推送的消息id，多个以“,”隔开
	 * @return
	 * 创建日期：2016-12-6  创建人：yyp
	 */
	@NoLoginAuth
	@RequestMapping(value="updateMessagesIfreply.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object updateMessagesIfreply(String token, String uuids){
		try {
			OnlineMessage message = TokenServer.tokenCheck(token);
			if (message.isSuccess()==false) {
				return pushMsg(Boolean.FALSE, message.getMessage());
			}
			if (!StrUtil.isNull(uuids)) {//id不为空
				String[] ids = uuids.split(",");
				propertyrightMessagesWSService.updateMessagesIfreply(ids, MessagesDTO.REPLY_OK);
			}
			return pushMsg(Boolean.TRUE, "修改应答状态成功");
		} catch (Exception e) {
			return pushMsg(Boolean.FALSE, "修改应答状态失败");
		}
	}
	/**
	 * 根据id查询消息
	 * @param token
	 * @param ms ---hxuuid
	 * @return
	 * 创建日期：2016-12-6  创建人：yyp
	 */
	@NoLoginAuth
	@RequestMapping(value="queryMessageById.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object queryMessageById(String token, MessagesDTO ms){
		try {
			OnlineMessage message = TokenServer.tokenCheck(token);
			if (message.isSuccess()==false) {
				return pushMsg(Boolean.FALSE, message.getMessage());
			}
			MessagesDTO messageDTO =  propertyrightMessagesWSService.queryMessage(ms);
			return pushMsg(Boolean.TRUE, "修改应答状态成功", "messageDTO", messageDTO);
		} catch (Exception e) {
			return pushMsg(Boolean.FALSE, "修改应答状态失败");
		}
	}
}
