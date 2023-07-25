package com.kh.jdbc.day04.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.jdbc.day04.common.JDBCTemplate;
import com.kh.jdbc.day04.model.vo.Student;

public class StudentDAO {

	private Properties prop;

//	public StudentDAO() {
//		prop = new Properties();
//		Reader reader = new FileReader("resources/dev.properties");
//		prop.load(null);
//	}
	
	/*
	 *  Checked Exception 과 Unchecked Exception
	 *  2. 예외의 종류 Throwable - Exception(checked exception 한정)
	 *  3. 예외 처리 방법 : throws, try ~ catch - finally
	 *  4. 최상위는 Exception 이며 이거로 퉁칠 수 있음.
	 */

	private final String Driver_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localHost:1521:xe";
	private final String USER = "student";
	private final String PASSWORD = "student";

	public StudentDAO() {
		prop = new Properties();
		Reader reader;
		try {
			reader = new FileReader("resources/query.properties");
			prop.load(reader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Student> selectAll(Connection conn) throws SQLException {
		String URL = prop.getProperty("URL");
		String USER = prop.getProperty("USER");
		String PASSWORD = prop.getProperty("PASSOWORD");
		String QUERY = prop.getProperty("selectAll");
		String DRIVER = prop.getProperty("DRIVER");
		Statement stmt = null;
		ResultSet rset = null;

		List<Student> sList = new ArrayList<Student>();

		conn = new JDBCTemplate().createConnection();
		stmt = conn.createStatement();
		rset = stmt.executeQuery(QUERY);

		while (rset.next()) {
			Student student = rsetToStudent(rset);
			sList.add(student);

		}

		rset.close();
		stmt.close();

//		try {
//
//			// PreparedStatement pstmt = conn.prepareStatement(QUERY);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				rset.close();
//				stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

		return sList;
	}

	private Student rsetToStudent(ResultSet rset) throws SQLException {
		Student student = new Student();
		student.setStudentID(rset.getString("MEMBER_ID"));
		student.setStudentPwd(rset.getString("MEMBER_PWD"));
		student.setStudentName(rset.getString("MEMBER_NAME"));
		student.setAge(rset.getInt("AGE"));
		student.setEmail(rset.getString("EMAIL"));
		student.setPhone(rset.getString("PHONE"));
		// rset.getSting 굉장히 중요
		// 타입이 Int면 int
		// date 면 date임 중요함
		// 문자는 문자열에 오는 단얼르 charAt 해서 자르면 되이이임!!
		student.setGender(rset.getString("GENDER").charAt(0));
		student.setAddress(rset.getString("ADDRESS"));
		student.setHobby(rset.getString("HOBBY"));
		student.setEnrollDate(rset.getDate("ENROLL_DATE"));
		return student;

	}

	public Student selectOneById(Connection conn2, String studentId) {
		// 1.위치홀더 세팅
		// 2. PreparedStatement 객체 생성 with query
		// 3. 입력값 세팅
		// 4. 쿼리문 실행 및 결과 받기 (feat.method())

		String query = prop.getProperty("selectOneById");
		ResultSet reset = null;
		Student student = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = new JDBCTemplate().createConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, studentId);

			// Statement smtm = conn.createStatement();
			reset = pstmt.executeQuery();

//					JDBC
//					ResultSet 은 후처리 필수
//								rset.next();
//								 여러개면 while
//								 한개면 if
//							

			if (reset.next()) {
				student = rsetToStudent(reset);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				reset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return student;

	}

	public List<Student> selectAllByName(Connection conn2, String studentName) {

		String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		String USER = "student";
		String PASSWORD = "student";
		String QUERY = prop.getProperty("selectAllByName");
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Connection conn = null;
		List<Student> sList = new ArrayList<Student>();

		try {
			conn = new JDBCTemplate().createConnection();
			pstmt = conn.prepareStatement(QUERY);
			pstmt.setString(1, studentName);
			// 세팅 중요함 ,위치 홀더 세팅 해야함

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Student student = rsetToStudent(rset);
				sList.add(student);

			}

			// PreparedStatement pstmt = conn.prepareStatement(QUERY);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return sList;
	}

	public int deleteStudent(Connection conn2, String studentId) {

		String query = prop.getProperty("deleteStudent");
		ResultSet reset = null;
		Student student = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int num = 0;
		try {
			conn = new JDBCTemplate().createConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, studentId);
			num = pstmt.executeUpdate();

			// Statement smtm = conn.createStatement();

//					JDBC
//					ResultSet 은 후처리 필수
//								rset.next();
//								 여러개면 while
//								 한개면 if
//							

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return num;
	}

	public int insertStudent(Connection conn2, Student student) {

		String query = prop.getProperty("insertStudent");
		ResultSet reset = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int num = 0;
		try {
			conn = new JDBCTemplate().createConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudentID());
			pstmt.setString(2, student.getStudentPwd());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, student.getGender() + ""); // 중요 String.valueOf(student.getGender()) 도 가능함.
			pstmt.setInt(5, student.getAge()); // 중요 Int로 바꿈
			pstmt.setString(6, student.getEmail());
			pstmt.setString(7, student.getPhone());
			pstmt.setString(8, student.getAddress());
			pstmt.setString(9, student.getHobby());
			num = pstmt.executeUpdate();

			// Statement smtm = conn.createStatement();

//					JDBC
//					ResultSet 은 후처리 필수
//								rset.next();
//								 여러개면 while
//								 한개면 if
//							

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return num;
	}

	public int updateStudent(Connection conn2, Student student) {

		String query = prop.getProperty("updateStudent");
		ResultSet reset = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int num = 0;
		try {
			conn = new JDBCTemplate().createConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudentPwd());
			pstmt.setString(2, student.getEmail());
			pstmt.setString(3, student.getPhone());
			pstmt.setString(4, student.getAddress()); // 중요 String.valueOf(student.getGender()) 도 가능함.
			pstmt.setString(5, student.getHobby()); // 중요 Int로 바꿈
			pstmt.setString(6, student.getStudentID());
			num = pstmt.executeUpdate();

			// Statement smtm = conn.createStatement();

//					JDBC
//					ResultSet 은 후처리 필수
//								rset.next();
//								 여러개면 while
//								 한개면 if
//							

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return num;
	}

}
