package com.xmhx.buzz.platform.service.manages.test;

import java.util.List;

import com.xmhx.buzz.platform.model.test.GradeDTO;
import com.xmhx.buzz.platform.model.test.StudentDTO;

public interface GradeService {

	List<GradeDTO> getGradeList();

	List<GradeDTO> getGradeStudentList();

	List<GradeDTO> getStudents(GradeDTO grade);

	List<GradeDTO> queryStudentsByIn(String[] nms);

	void updateGradeBatch(List<GradeDTO> gradeList);

	void updateGradeOne(GradeDTO grade);

	void delGradeBatch(int[] ids);

	GradeDTO queryGradeById(int i);

	void updateGradeBatch2(Integer[] ids, String nm);

}
