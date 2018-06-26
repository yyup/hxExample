package com.xmhx.buzz.platform.model.test;

import java.util.List;

import com.xmhx.core.annotation.AutoIncrement;
import com.xmhx.core.annotation.Column;
import com.xmhx.core.annotation.Id;

public class GradeDTO {

	private Integer id;
	private String gname;
	
	//一对一
	private TeacherDTO teacher;
	
	//一对多
	private List<StudentDTO> students;
	
	@Column(columnName="teacher")
	public TeacherDTO getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherDTO teacher) {
		this.teacher = teacher;
	}
	
	@Id
	@AutoIncrement
	@Column(columnName = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(columnName="gname")
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	
	@Column(columnName="students")
	public List<StudentDTO> getStudents() {
		return students;
	}
	public void setStudents(List<StudentDTO> students) {
		this.students = students;
	}
	
	
	
}
