package com.kh.jdbc.day02.student.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day01.student.model.vo.Student;
//import com.kh.jdbc.day01.student.view.SET;
//import com.kh.jdbc.day01.student.view.UPDATE;

public class StudentDAO {
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "student";
	private final String PASSWORD = "student";
	private final String QUERY = "SELECT * FROM STUDENT_TBL";
	private ResultSet result;

	private List<Student> sList = null;

	public List<Student> selectAll() {

		/*
		 * 1. 드라이버 등록 2. DB 연결 생성 3. 쿼리문 실행 준비 4. 쿼리문 실행 및 5. 결과 받기 6. 자원 해제 (close())
		 */

		List<Student> sList = null;

		try {
			// 1번. 드라이브 등록
			Class.forName(DRIVER_NAME);
			// 2번. DB 연결 생성 드라이브 매니저
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// 3번. 쿼리문 실행 준비
			Statement stmt = conn.createStatement();

			// SELECT 는 테이블 형태라서 ResultSet 임!!
			ResultSet rset = stmt.executeQuery(QUERY);
			sList = new ArrayList<Student>();

			while (rset.next()) {
				Student student = rsetToStudent(rset);
				sList.add(student);

				System.out.printf("아이디 : %s, 이름 : %s \n", rset.getString("MEMBER_ID"), rset.getString("MEMBER_NAME"));
			}

			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sList;
	}

	public int insertStudent(Student student) {

		/*
		 * 1.드라이버 등록 2. DB 연결 생성 3. 쿼리문 실행 준비 4. 쿼리문 실행 및 5. 결과 받기 6. 자원 해제(close())
		 */

		String query = "INSERT INTO STUDENT_TBL VALUES(" + "'" + student.getStudentID() + "', " + "'"
				+ student.getStudentPwd() + "', " + "'" + student.getStudentName() + "', " + "'" + student.getGender()
				+ "', " + student.getAge() + ", " + "'" + student.getEmail() + "', " + "'" + student.getPhone() + "', "
				+ "'" + student.getAddress() + "', " + "'" + student.getHobby() + "', " + "SYSDATE)";

//		String query = "INSERT INTO STUDENT_TBL VALUES('"+student.getStudentID()+"', '"+student.getStudentPwd()+"', '"+student.getStudentName()+"', '"+student.getGender()+"', "+student.getAge()+", '"+student.getEmail()+"', '"+student.getPhone()+"' , '"+student.getAddress()+"', '"+student.getHobby()+"', SYSDATE)";
		// 홑따옴표 써야함 시험에 나옴 ''은 데이터를 보내는것이고 변수 쓸거면 ""안에 넣으면 됨, +를 해줘야함
		int result = -1;
		try {
			// 1.드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.드라이버 연결 생성
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			// 3. 쿼리 실행 준비
			Statement stmt = conn.createStatement();
			// 4. 실행하고 결과 받기
			// stmt.executeQuery(query); SELECT용이다,시험에 나옴
			result = stmt.executeUpdate(query); // DML(INSERT,UPDATE,DELETE)용이다. 시험에 나옴
			stmt.close();
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

	public ResultSet printStudentById(String studentId) {
		// TODO Auto-generated method stub

		/*
		 * 1.드라이버 등록 2. DB 연결 생성 3. 쿼리문 실행 준비 4. 쿼리문 실행 및 5. 결과 받기 6. 자원 해제(close())
		 */

		String query = "SELECT * FROM STUDENT_TBL WHERE" + studentId;

//		String query = "INSERT INTO STUDENT_TBL VALUES('"+student.getStudentID()+"', '"+student.getStudentPwd()+"', '"+student.getStudentName()+"', '"+student.getGender()+"', "+student.getAge()+", '"+student.getEmail()+"', '"+student.getPhone()+"' , '"+student.getAddress()+"', '"+student.getHobby()+"', SYSDATE)";
		// 홑따옴표 써야함 시험에 나옴 ''은 데이터를 보내는것이고 변수 쓸거면 ""안에 넣으면 됨, +를 해줘야함

		try {
			// 1.드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.드라이버 연결 생성
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			// 3. 쿼리 실행 준비
			Statement stmt = conn.createStatement();
			// 4. 실행하고 결과 받기
			// stmt.executeQuery(query); SELECT용이다,시험에 나옴
			ResultSet result = stmt.executeQuery(query);
			stmt.close();
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

	public Student selectOneById(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM STUDENT_TBL WHERE MEMBER_ID ='" + studentId + "'";
		Student student = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();

			ResultSet rset = stmt.executeQuery(query);
//			rset.next();
			// 여러개면 while
			// 한개면 if
			if (rset.next()) {
				student = rsetToStudent(rset);

			}
			rset.close();
			stmt.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return student;
	}

	public Student printStudentsByName(String studentName) throws SQLException {
		// TODO Auto-generated method stub

		String query = "SELECT * FROM STUDENT_TBL WHERE MEMBER_NAME ='" + studentName + "'";
		Student student = null;
		sList = new ArrayList<Student>();
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();

			ResultSet rset = stmt.executeQuery(query);
//			rset.next();
			// 여러개면 while
			// 한개면 if

			while (rset.next()) {
				student = rsetToStudent(rset);
				sList.add(student);

//				System.out.printf("아이디 : %s, 이름 : %s \n", rset.getString("MEMBER_ID"), rset.getString("MEMBER_NAME"));
			}

//			if ( rset.next()) {
//				student = new Student();
//				student.setStudentID(rset.getString("MEMBER_ID"));
//				student.setStudentPwd(rset.getString("MEMBER_PWD"));
//				student.setStudentName(rset.getString("MEMBER_NAME"));
//				student.setAge(rset.getInt("AGE"));
//				student.setEmail(rset.getString("EMAIL"));
//				student.setPhone(rset.getString("PHONE"));
//
//				// 문자는 문자열에 오는 단얼르 charAt 해서 자르면 되이이임!!
//				student.setGender(rset.getString("GENDER").charAt(0));
//				student.setAddress(rset.getString("ADDRESS"));
//				student.setHobby(rset.getString("HOBBY"));
//				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
//				
//			}
			rset.close();
			stmt.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return student;
	}

	public List<Student> selectAllByName(String studentName) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM STUDENT_TBL WHERE MEMBER_NAME ='" + studentName + "'";
		// = 하고 ' ' 하는거 중요함매

		List<Student> sList = new ArrayList<Student>();
		Student student = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			// 후처리

			while (rset.next()) {
				student = rsetToStudent(rset);
				sList.add(student);

//				System.out.printf("아이디 : %s, 이름 : %s \n", rset.getString("MEMBER_ID"), rset.getString("MEMBER_NAME"));
			}

			rset.close();
			stmt.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		// 문자는 문자열에 오는 단얼르 charAt 해서 자르면 되이이임!!
		student.setGender(rset.getString("GENDER").charAt(0));
		student.setAddress(rset.getString("ADDRESS"));
		student.setHobby(rset.getString("HOBBY"));
		student.setEnrollDate(rset.getDate("ENROLL_DATE"));
		return student;

	}

	public int deleteStudent(String studentId) {
		String query = "DELETE FROM STUDENT_TBL WHERE MEMBER_ID = '" + studentId+"'";
		int result = 0;	
		
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			 result = stmt.executeUpdate(query);
			
			stmt.close();
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

	public int updateStudent(Student student) {
		int reuslt = 0;
		
//		
//		String query = "UPDATE STUDENT_TBL SET MEMBER_PWD = 'pass1123',EMAIL =\r\n"
//				+ "				'4224lrplepr@naver.com' , HOBBY = '일,낮잠' WHERE MEMBER_ID = 'khuser01';";
		
		
		String query = "UPDATE STUDENT_TBL SET "
				+ "MEMBER_PWD = '"+student.getStudentPwd()+"',"
						+ "EMAIL = '"+student.getEmail()+"' , "
								+ "PHONE = '"+student.getPhone()+"' , "
										+ "ADDRESS = '"+student.getAddress()+"' , "
								+ "HOBBY = '"+student.getHobby()+"' "
										+ "WHERE MEMBER_ID = '"+student.getStudentID()+"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			reuslt = stmt.executeUpdate(query);
			stmt.close();
			conn.close();
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return reuslt;
	}

}
