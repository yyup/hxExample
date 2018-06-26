package com.xmhx.buzz.platform.service.impl.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xmhx.buzz.platform.dao.test.GradeDao;
import com.xmhx.buzz.platform.model.test.GradeDTO;
import com.xmhx.buzz.platform.service.manages.test.GradeService;

@Service
public class GradeServiceImpl implements GradeService{
	@Resource
	private GradeDao gradeDao;

	@Override
	public List<GradeDTO> getGradeList() {
		return gradeDao.getGradeList();
	}

	@Override
	public List<GradeDTO> getGradeStudentList() {
		return gradeDao.getGradeStudentList();
	}

	@Override
	public List<GradeDTO> getStudents(GradeDTO grade) {
		return gradeDao.getStudents(grade);
	}

	@Override
	public List<GradeDTO> queryStudentsByIn(String[] nms) {
		return gradeDao.queryStudentsByIn(nms);
	}

	@Override
	public void updateGradeBatch(List<GradeDTO> gradeList) {
		gradeDao.updateGradeBatch(gradeList);
		
	}

	@Override
	public void updateGradeOne(GradeDTO grade) {
		gradeDao.updateGradeOne(grade);
	}

	@Override
	public void delGradeBatch(int[] ids) {
		gradeDao.delGradeBatch(ids);
	}

	@Override
	public GradeDTO queryGradeById(int id) {
		return gradeDao.queryGradeById(id);
	}

	@Override
	public void updateGradeBatch2(Integer[] ids, String nm) {
//		Map<String, Object> params = new HashMap<String, Object>(2); 
//		params.put("ids", ids); 
//		params.put("nm", nm);
		gradeDao.queryGradeByIdArray(ids, nm);
	}

}
