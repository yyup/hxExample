package com.xmhx.cnlife.buzz.platform.control.webservices;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmhx.cnlife.buzz.platform.model.webservices.MessagesDTO;
import com.xmhx.cnlife.buzz.platform.service.webservices.PropertyrightMessagesWSService;
import com.xmhx.cnlife.buzz.utils.EnumUtils;
import com.xmhx.cnlife.buzz.utils.EnumUtils.ThreesidesNo;
import com.xmhx.cnlife.buzz.utils.StrUtil;
import com.xmhx.cnlife.core.authority.NoLoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;

/**
 * 弘信三方消息接口
 * @author 吴进 by 20161205
 * <p>
 */
@Controller
@RequestMapping(value="/ws/")
public class ThreesidesMessagesWSControl extends BaseController {
	@Resource
	private PropertyrightMessagesWSService propertyrightMessagesWSService;

	/**
	 * 物业合同租赁等逾期提醒接口
	 * @param messageDTO
	 * @return
	 * 创建日期：2016-12-1  创建人：yyp
	 */
	@NoLoginAuth
	@RequestMapping(value="threesidesMessages.hx", method=RequestMethod.POST)
	@ResponseBody
	public Object threesidesMessages(MessagesDTO messageDTO) {
		try {
			String threesidesNo = messageDTO.getThreesidesNo();//三方编号
			String firstLetter = messageDTO.getMsgType().substring(0, 1);//传参类型首字母
			ThreesidesNo lt = EnumUtils.ThreesidesNo.typeOf(threesidesNo);//根据类型首字母获取三方编号
			if (StrUtil.isNull(lt) || !lt.getFirstLetter().equals(firstLetter)) {//获取信息为空，或编号不一致
				return pushMsg(Boolean.FALSE, "threesidesNo参数编号不正确，请核对此参数传值是否正确");
			}
			messageDTO.setHxuuid(UUID.randomUUID().toString());
			messageDTO.setIfreply(MessagesDTO.REPLY_NOT);
			messageDTO.setCreated_by("system");
			messageDTO.setUpdated_by("system");
			propertyrightMessagesWSService.addPropertyOverdue(messageDTO);
			return pushMsg(Boolean.TRUE, "添加成功");
		} catch (Exception e) {
			return pushMsg(Boolean.FALSE, "添加失败");
		}
	}

}
