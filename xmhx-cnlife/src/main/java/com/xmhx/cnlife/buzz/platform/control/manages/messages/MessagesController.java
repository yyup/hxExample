package com.xmhx.cnlife.buzz.platform.control.manages.messages;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmhx.cnlife.buzz.platform.model.webservices.MessagesDTO;
import com.xmhx.cnlife.buzz.platform.service.webservices.PropertyrightMessagesWSService;
import com.xmhx.cnlife.core.authority.LoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;
import com.xmhx.cnlife.core.model.PageBean;
import com.xmhx.cnlife.core.model.PageVO;

/**
 * 消息控制类
 * @author yyp
 *
 */
@Controller
@RequestMapping(value="/manages/")
public class MessagesController extends BaseController {
	@Resource
	private PropertyrightMessagesWSService propertyrightMessagesWSService;

	/**
	 * 跳转到消息管理页面
	 * @return
	 * 创建日期：2016-12-19  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value="webservicesmessages/list.hx", method=RequestMethod.GET)
	public String list() {
		return "manages/messages/webservicesmessagesList";
	}
	/**
	 * 消息列表
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param message
	 * @return
	 * 创建日期：2016-12-20  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value="webservicesmessages/pagelist.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object pagelist(@RequestParam(defaultValue="1")int page, int rows, String sort, String order, MessagesDTO message) {
		PageBean<MessagesDTO> pageBean = propertyrightMessagesWSService.getMessagesByPage(page, rows, sort, order, message);
		if(pageBean != null) {
			PageVO<MessagesDTO> pagevo = new PageVO<MessagesDTO>();
			pagevo.setTotal(pageBean.getRecordCount());
			pagevo.setRows(pageBean.getDataList());
			return pagevo;
		}
		return null;
	}
	/**
	 * 删除消息
	 * @param ids
	 * @return
	 * 创建日期：2016-12-21  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value="webservicesmessages/delMessages.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object delMessages(String ids){
		try {
			propertyrightMessagesWSService.delMessages(ids);
			return pushMsg(Boolean.TRUE,"删除成功");
		} catch (Exception e) {
			return pushMsg(Boolean.FALSE,"删除失败");
		}
	}
	/**
	 * 推送消息
	 * @param ids
	 * @return
	 * 创建日期：2016-12-21  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value="webservicesmessages/jpusMessages.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object jpusMessages(String ids){
		try {
			propertyrightMessagesWSService.jpusMessages(ids);
			return pushMsg(Boolean.TRUE,"推送成功");
		} catch (Exception e) {
			return pushMsg(Boolean.FALSE,"推送失败");
		}
	}
	
	/**
	 * 根据id查询消息详情
	 * @param ids
	 * @return
	 * 创建日期：2016-12-22  创建人：yyp
	 */
	@LoginAuth
	@RequestMapping(value="webservicesmessages/queryMessageById.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object queryMessageById(MessagesDTO message){
		try {
			MessagesDTO messageDTO = propertyrightMessagesWSService.queryMessage(message);
			return pushMsg(Boolean.TRUE,"查询消息详情成功", "messageDTO", messageDTO);
		} catch (Exception e) {
			return pushMsg(Boolean.FALSE,"查询消息详情失败");
		}
	}
}
