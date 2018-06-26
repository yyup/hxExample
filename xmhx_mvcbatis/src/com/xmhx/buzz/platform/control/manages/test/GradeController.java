package com.xmhx.buzz.platform.control.manages.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmhx.buzz.platform.model.manages.init.NewsDTO;
import com.xmhx.buzz.platform.model.manages.init.UserDTO;
import com.xmhx.buzz.platform.model.test.GradeDTO;
import com.xmhx.buzz.platform.model.test.StudentDTO;
import com.xmhx.buzz.platform.service.manages.test.GradeService;
import com.xmhx.core.authority.NoLoginAuth;
import com.xmhx.core.buzz.BaseController;

@Controller
@RequestMapping(value="/manages/")
public class GradeController extends BaseController{
	@Resource
	private GradeService gradeService;
	//根据id查询信息
	@NoLoginAuth
	@RequestMapping(value="queryGradeById.do", method=RequestMethod.GET)
	public void queryGradeById(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		GradeDTO grade = gradeService.queryGradeById(1);
		System.out.println(grade.getGname());
	}
	//一对一
	@NoLoginAuth
	@RequestMapping(value="queryClassTearcher.do", method=RequestMethod.GET)
	public void queryClassTearcher(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		List<GradeDTO> gradeList = gradeService.getGradeList();
		System.out.println(gradeList.get(0).getTeacher().getTid());
		map.put("gradeList", gradeList);
	}
	
	//一对多
	@NoLoginAuth
	@RequestMapping(value="queryClassStudent.do", method=RequestMethod.GET)
	public void queryClassStudent(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		List<GradeDTO> gradeStudentList = gradeService.getGradeStudentList();
		map.put("gradeStudentList", gradeStudentList);
	}
	
	//查询带条件(模糊查询)
	@NoLoginAuth
	@RequestMapping(value="queryStudents.do", method=RequestMethod.GET)
	public void queryStudents(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		GradeDTO grade = new GradeDTO();
		grade.setGname("1班");
		List<GradeDTO> gradeStudentList = gradeService.getStudents(grade);
		map.put("gradeStudentList", gradeStudentList);
	}
	//查询带条件(in)
	@NoLoginAuth
	@RequestMapping(value="queryStudentsByIn.do", method=RequestMethod.GET)
	public void queryStudentsByIn(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		String[] nms = {"1班","2班"};
		List<GradeDTO> gradeStudentList = gradeService.queryStudentsByIn(nms);
		map.put("gradeStudentList", gradeStudentList);
	}
		
	//批量更新
	@NoLoginAuth
	@RequestMapping(value="updateGradeBatch.do", method=RequestMethod.GET)
	public void updateGradeBatch(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		List<GradeDTO> gradeList = new ArrayList<GradeDTO>();
		GradeDTO grade1 = new GradeDTO();
		grade1.setId(1);
		grade1.setGname("一");
		gradeList.add(grade1);
		GradeDTO grade2 = new GradeDTO();
		grade2.setId(2);
		grade2.setGname("二");
		gradeList.add(grade2);
		gradeService.updateGradeBatch(gradeList);
	}
	//批量更新（数组）
	@NoLoginAuth
	@RequestMapping(value="updateGradeBatch2.do", method=RequestMethod.GET)
	public void updateGradeBatch2(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		Integer[] ids = {1,2};
		gradeService.updateGradeBatch2(ids, "1");
	}
	
	//单个更新
	@NoLoginAuth
	@RequestMapping(value="updateGradeOne.do", method=RequestMethod.GET)
	public void updateGradeOne(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		List<GradeDTO> gradeList = new ArrayList<GradeDTO>();
		GradeDTO grade = new GradeDTO();
		grade.setId(1);
		grade.setGname("一");
		gradeService.updateGradeOne(grade);
	}
	
	//批量删除
	@NoLoginAuth
	@RequestMapping(value="delGradeBatch.do", method=RequestMethod.GET)
	public void delGradeBatch(HttpSession session, ModelMap map) {
		UserDTO userDTO = getUser(session);
		int[] ids = {5,6};
		gradeService.delGradeBatch(ids);
	}
	
}
