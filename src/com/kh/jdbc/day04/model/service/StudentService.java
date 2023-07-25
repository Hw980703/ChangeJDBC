package com.kh.jdbc.day04.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kh.jdbc.day04.common.JDBCTemplate;
import com.kh.jdbc.day04.model.dao.StudentDAO;
import com.kh.jdbc.day04.model.vo.Student;

public class StudentService {
	private StudentDAO sDao;
	private JDBCTemplate JDBCTemplate;

	public StudentService() {
		sDao = new StudentDAO();
		JDBCTemplate = JDBCTemplate.getInstance();
	}

	public List<Student> selectAll() {
		Connection conn = JDBCTemplate.createConnection();
		List<Student> sList = null;
		try {
			sList = sDao.selectAll(conn);
			JDBCTemplate.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sList;
	}

	public Student selectOneById(String studentId) {

		Connection conn = JDBCTemplate.createConnection();
		Student student = sDao.selectOneById(conn, studentId);
		JDBCTemplate.close();
		return student;
	}

	public List<Student> selectAllByName(String studentName) {

		Connection conn = JDBCTemplate.createConnection();
		List<Student> sList = sDao.selectAllByName(conn, studentName);
		JDBCTemplate.close();
		return sList;
	}

	public int deleteStudent(String studentId) {

		Connection conn = JDBCTemplate.createConnection();
		int result = sDao.deleteStudent(conn, studentId);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close();
		return result;
	}

	public int insertStudent(Student student) {

		Connection conn = JDBCTemplate.createConnection();
		int result = sDao.insertStudent(conn, student);
		result += sDao.updateStudent(conn, student);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close();
		return result;

	}

	public int updateStudent(Student student) {
		Connection conn = JDBCTemplate.createConnection();
		int result = sDao.updateStudent(conn, student);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close();

		return result;
	}
}
