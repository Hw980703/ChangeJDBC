package com.kh.jdbc.day02.student.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kh.jdbc.day01.student.model.dao.StudentDAO;
import com.kh.jdbc.day01.student.model.vo.Student;

public class StudentController {

	private StudentDAO studentDao;

	public StudentController() {
		studentDao = new StudentDAO();
	}

	public List<Student> printStudentList() {
		List<Student> sList = studentDao.selectAll();
		return sList;
	}

	public int insertStudent(Student student) {

	int result	= studentDao.insertStudent(student);
		
			
		return result;

	}
//
//	public  ResultSet printStudentById(String studentId) {
//		// TODO Auto-generated method stub
//		ResultSet studnet = studentDao.printStudentById(studentId);
//		
//		
//		return studnet;
//	}

	public Student printStudentById(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		
		Student student = studentDao.selectOneById(studentId);
		return student;
	}

	public List<Student> printStudentsByName(String studentName) {
		// TODO Auto-generated method stub
		
		List<Student> sList = studentDao.selectAllByName(studentName);
		return sList;
	}

	public int deleteStudent(String studentId) {
		int result = studentDao.deleteStudent(studentId);
		
		return result;
	}

	public int modifyStudent(Student student) {
		int result = studentDao.updateStudent(student);
		
		return result;
	}

//	public Student printStudentsByName(String studentName) throws SQLException {
//		// TODO Auto-generated method stub
//		
//		Student student = studentDao.printStudentsByName(studentName);
//		
//		return student;
//	}
}
