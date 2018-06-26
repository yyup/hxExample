package com.xmhx.cnlife.buzz.platform.control.manages.schedule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmhx.cnlife.core.authority.LoginAuth;
import com.xmhx.cnlife.core.buzz.BaseController;

/**
 * 待办任务控制层
 * @author 吴进 by 20161117
 *
 */
@Controller
@RequestMapping(value="/manages/")
public class ScheduleController extends BaseController {

	/**
	 * 首页main内容（待办任务）
	 * @return
	 */
	@LoginAuth
	@RequestMapping(value="main.hx", method=RequestMethod.GET)
	public String main() {
		return "manages/main/main";
	}
	
}
