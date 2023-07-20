package com.kh.jdbc.day01.student.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day01.student.model.vo.Student;

public class StudentDAO {

	 public List<Student> selectAll() {

		/*
		 * 1. 드라이버 등록 2. DB 연결 생성 3. 쿼리문 실행 준비 4. 쿼리문 실행 및 5. 결과 받기 6. 자원 해제 (close())
		 */

		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "student";
		String password = "student";
		String query = "SELECT * FROM STUDENT_TBL";
		List<Student> sList = null;

		try {
			// 1번. 드라이브 등록
			Class.forName(driverName);
			// 2번. DB 연결 생성 드라이브 매니저
			Connection conn = DriverManager.getConnection(url, user, password);

			// 3번. 쿼리문 실행 준비
			Statement stmt = conn.createStatement();

			// SELECT 는 테이블 형태라서 ResultSet 임!!
			ResultSet rset = stmt.executeQuery(query);
			sList = new ArrayList<Student>();

			while (rset.next()) {
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

}
