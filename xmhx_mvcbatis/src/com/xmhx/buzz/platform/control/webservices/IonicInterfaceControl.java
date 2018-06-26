//package com.xmhx.buzz.platform.control.webservices;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.xmhx.buzz.platform.model.manages.init.PersonDTO;
//import com.xmhx.buzz.utils.JsonUtils;
//import com.xmhx.buzz.utils.TextUtils;
//import com.xmhx.core.authority.NoLoginAuth;
//import com.xmhx.core.buzz.BaseController;
//
//import net.sf.json.JSONObject;
//
///**
// * 提供给ionic的测试接口
// * @author 吴进 by 20160918
// *
// */
//@Controller
//@RequestMapping(value="/")
//public class IonicInterfaceControl extends BaseController {
//
//	@NoLoginAuth
//	@ResponseBody
//	@RequestMapping(value="queryUserinfo.do")
//	public Object queryUserinfo(String memberMobile, String sex) {
//		logger.info("IonicInterfaceControl.queryUserinfo.memberMobile = " + memberMobile + " - " + sex);
//		List<PersonDTO> userlist = new ArrayList<PersonDTO>();
//		PersonDTO userDTO = new PersonDTO();
//		userDTO.setMemberId(1);
//		userDTO.setMemberNm("runner1721");
//		userDTO.setMemberName("涂耀芳");
//		userDTO.setMemberMobile(memberMobile);
//		userDTO.setMemberPwd("123456");
//		userDTO.setSex(sex);
//		userDTO.setMemberEmail("runner1721@126.com");
//		userlist.add(userDTO);
//		
//		userDTO = new PersonDTO();
//		userDTO.setMemberId(2);
//		userDTO.setMemberNm("shanshan");
//		userDTO.setMemberName("吴佳丽");
//		userDTO.setMemberMobile(memberMobile);
//		userDTO.setMemberPwd("123456");
//		userDTO.setSex(sex);
//		userDTO.setMemberEmail("shanshan@sina.com.cn");
//		userlist.add(userDTO);
//		logger.info("返回 = " + userlist);
//		
//		return pushMsg(Boolean.TRUE, "OK", "data", userlist);
//	}
//	
//	@NoLoginAuth
//	@RequestMapping(value="queryUserinfo2.do")
//	public void queryUserinfo2(String memberMobile, HttpServletResponse response) {
//		logger.info("IonicInterfaceControl.queryUserinfo.memberMobile = " + memberMobile);
//		List<PersonDTO> userlist = new ArrayList<PersonDTO>();
//		PersonDTO userDTO = new PersonDTO();
//		userDTO.setMemberId(1);
//		userDTO.setMemberNm("runner1721");
//		userDTO.setMemberName("吴进");
//		userDTO.setMemberMobile(memberMobile);
//		userDTO.setMemberPwd("123456");
//		userDTO.setMemberEmail("runner1721@126.com");
//		userlist.add(userDTO);
//		
//		userDTO = new PersonDTO();
//		userDTO.setMemberId(2);
//		userDTO.setMemberNm("shanshan");
//		userDTO.setMemberName("杉杉");
//		userDTO.setMemberMobile(memberMobile);
//		userDTO.setMemberPwd("123456");
//		userDTO.setMemberEmail("shanshan@sina.com.cn");
//		userlist.add(userDTO);
//		
//		logger.info("返回 = " + userlist);
//		
//		JSONObject json = JsonUtils.buildJSONObject(Boolean.TRUE, "用户查询成功", "memberList", userlist);
//		sendJsonResponse(response, json.toString());
//	}
//	
//	@NoLoginAuth
//	@ResponseBody
//	@RequestMapping(value="jsonToList.do")
//	public Object jsonToList(String json) {
//		if (TextUtils.notEmpty(json)) {
//			List list = JsonUtils.json2Array(json, PersonDTO.class);
//			System.out.println(list);
//		}		
//		return "Json转换的操作完成，正确与错误请分析内存";
//	} 
//	
//}
