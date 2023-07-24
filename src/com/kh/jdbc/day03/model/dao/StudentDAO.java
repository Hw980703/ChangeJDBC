package com.kh.jdbc.day03.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day03.model.vo.Student;

public class StudentDAO {

	private final String Driver_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localHost:1521:xe";
	private final String USER = "student";
	private final String PASSWORD = "student";

	public List<Student> selectAll() {
		String query = "SELECT * FROM STUDENT_TBL";
		List<Student> sList = new ArrayList<Student>();
		try {
			Class.forName(Driver_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(query);

			while (rSet.next()) {
				Student student = rSetToStudent(rSet);
				sList.add(student);
			}

			conn.close();
			stmt.close();
			rSet.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sList;
	}

	private Student rSetToStudent(ResultSet rset) throws SQLException {
		// TODO Auto-generated method stub

		Student student = new Student();
		student.setStudentId(rset.getString("MEMBER_ID"));
		student.setStudentPwd(rset.getString("MEMBER_PWD"));
		student.setStudentName(rset.getString("MEMBER_NAME"));
		student.setAge(rset.getInt("AGE"));
		student.setEmail(rset.getString("EMAIL"));
		student.setPhone(rset.getString("PHONE"));

		// 문자는 문자열에 오는 단얼르 charAt 해서 자르면 되이이임!!
		student.setGender(rset.getString("GENDER").charAt(0));
		student.setAddress(rset.getString("ADDRESS"));
		student.setHobby(rset.getString("HOBBY"));
		student.setEnrollDate(rset.getDate("ENROLL_DATE"));

		return student;

	}

	public Student selectIdName(String id) {
		// 1.위치홀더 세팅
		// 2. PreparedStatement 객체 생성 with query
		// 3. 입력값 세팅
		// 4. 쿼리문 실행 및 결과 받기 (feat.method())

		String query = "SELECT * FROM STUDENT_TBL WHERE MEMBER_ID = ?";
		ResultSet reset = null;
		Student student = null;
		try {
			Class.forName(Driver_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);

			// Statement smtm = conn.createStatement();
			reset = pstmt.executeQuery();

//			JDBC
//			ResultSet 은 후처리 필수
//						rset.next();
//						 여러개면 while
//						 한개면 if
//					

			if (reset.next()) {
				student = rSetToStudent(reset);

			}
			student = rSetToStudent(reset);
			conn.close();
			pstmt.close();
			reset.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return student;
	}

	public List<Student> selectAll(String studentName) {

		String query = "SELECT * FROM STUDENT_TBL WHERE MEMBER_NAME = ?";
		List<Student> sList = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			Class.forName(Driver_NAME);

			 conn = DriverManager.getConnection(URL, USER, PASSWORD);
			 pstmt = conn.prepareStatement(query);
//			Statement stmt = conn.createStatement();
			pstmt.setString(1, studentName);
			 rSet = pstmt.executeQuery();

			while (rSet.next()) {
				Student student = rSetToStudent(rSet);
				sList.add(student);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return sList;

	}

	public int insetStudent(Student student) {
		int num = 0;
		String query = "INSERT INTO STUDENT_TBL VALUES( ?,?,?,?,?,?,?,?,?,SYSDATE)";
		try {
			Class.forName(Driver_NAME);

			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudentId());
			pstmt.setString(2, student.getStudentPwd());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, student.getGender() + ""); // 중요 String.valueOf(student.getGender()) 도 가능함.
			pstmt.setInt(5, student.getAge()); // 중요 Int로 바꿈
			pstmt.setString(6, student.getEmail());
			pstmt.setString(7, student.getPhone());
			pstmt.setString(8, student.getAddress());
			pstmt.setString(9, student.getHobby());

			pstmt.setString(9, student.getHobby());

//			Statement stmt = conn.createStatement();
			num = pstmt.executeUpdate(); // 쿼리문 실행 빼먹지않귀이이이이이잉!!

			conn.close();
			pstmt.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num;
	}

	public int modifyStudent(Student student) {
		int num = 0;
		String query = "UPDATE STUDENT_TBL SET  MEMBER_PWD = ?,EMAIL =  ?,PHONE = ?,ADDRESS = ?,HOBBY = ? WHERE MEMBER_ID = ?";
		try {
			Class.forName(Driver_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			num = stmt.executeUpdate(query);

			conn.close();
			stmt.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;

	}

	public int deleteStudent(String studentName) {
		int num = 0;

		String query = "DELETE FROM STUDENT_TBL WHERE MEMBER_NAME = ?";
		try {
			Class.forName(Driver_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, studentName);

//			Statement stmt = conn.createStatement();
			num = pstmt.executeUpdate();

			conn.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;

	}

	public Student selectLoginInfo(Student student) {

		String query = "SELECT * FROM STUDENT_TBL WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		// ?는 위치홀더라고 한다.
		ResultSet reset = null;
		Student result = null;

		try {
			Class.forName(Driver_NAME);

			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudentId());
			pstmt.setString(2, student.getStudentPwd()); // 숫자 시작은 1이며 마지막 수는 ? 개수와 같다잉ㅋㅋ
			ResultSet rset = pstmt.executeQuery();

			conn.close();
			pstmt.close();
			rset.close();

			// Statement smtm = conn.createStatement();
//			reset = smtm.executeQuery(query);

//			JDBC
//			ResultSet 은 후처리 필수
//						rset.next();
//						 여러개면 while
//						 한개면 if
//					

			if (rset.next()) {
				result = rSetToStudent(rset);

			}
//			result = rSetToStudent(reset);
			rset.close();
			pstmt.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

}
