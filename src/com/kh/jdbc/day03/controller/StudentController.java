package com.kh.jdbc.day03.controller;

import java.sql.ResultSet;
import java.util.List;

import com.kh.jdbc.day03.model.dao.StudentDAO;
import com.kh.jdbc.day03.model.vo.Student;

public class StudentController {
	private StudentDAO studentDao;

	public StudentController() {
		studentDao = new StudentDAO();
	}

	public List<Student> selectAllStudent() {
		List<Student> sList = studentDao.selectAll();

		return sList;

	}

	public Student selectNameById(String id) {
		Student studnet = studentDao.selectIdName(id);

		return studnet;
	}

	public List<Student> selectAllByNameStudent(String studentName) {

		List<Student> sList = studentDao.selectAll(studentName);

		return sList;
	}

	public int insertStudent(Student student) {
		int num = studentDao.insetStudent(student);
		return num;
	}

	public int modifyStudent(Student student) {

		int num = studentDao.modifyStudent(student);
		return num;
	}

	public int deleterStudent(String studentName) {
		int num = studentDao.deleteStudent(studentName);
		return num;

	}

	public Student studentLogin(Student student) {

		Student result = studentDao.selectLoginInfo(student);
		return result;
	}
}
