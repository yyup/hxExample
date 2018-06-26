package com.xmhx.buzz.platform.dao.test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmhx.buzz.platform.model.test.GradeDTO;
import com.xmhx.buzz.platform.model.test.StudentDTO;

public interface GradeDao {

	List<GradeDTO> getGradeList();

	List<GradeDTO> getGradeStudentList();

	List<GradeDTO> getStudents(GradeDTO grade);

	List<GradeDTO> queryStudentsByIn(String[] nms);

	Object updateGradeBatch(List<GradeDTO> gradeList);

	void updateGradeOne(GradeDTO grade);

	void delGradeBatch(int[] ids);

	GradeDTO queryGradeById(int id);

	void queryGradeByIdArray(@Param("ids") Integer[] ids, @Param("nm") String nm);

}
