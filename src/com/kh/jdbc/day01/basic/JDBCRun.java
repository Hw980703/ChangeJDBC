package com.kh.jdbc.day01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {

	public static void main(String[] args) {

		/*
		 * 	JDBC 코딩 절차
		 *	1. 드라이버 등록 - 레퍼런스 라이브러리 jar파일과 관련이 있음 
		 *	2. DBMS 연결 생성 - 드라이버 매니저 사용 연결 가져오기
		 *	3. Statement 객체 생성 - 쿼리문 실행 준비
		 *	 	- new Statment(); (안됨) 가 아니라 연결을 통해서 객체 생성함..ㅋ
		 *	4. SQL 전송 (쿼리문 실행)
		 *	5. 결과 받기 (ResultSet으로 바로 받아버림)
		 *	6. 자원해제(close()) - 안쓰면 다 끊어줘야함
		 *
		 */
		
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String userid = "KH";
			String pw = "KH";
			String query = "SELECT EMP_NAME, SALARY FROM EMPLOYEE";
			
			// 1번 드라이버 등록
			//시험 볼때 안 외워도됨, 쓰다보면 외워짐
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2번 DBMC 연결 생성
			Connection conn = 
			DriverManager.getConnection(url, userid, pw);
			
			//3번 쿼리문 실행 준비 (Statement 객체 생성)
			
			Statement stnt = conn.createStatement();
			
			//4번 쿼리문 실행 (SELECT 면 ResultSet 써라) 5.결과값 받기(ResultSet은 테이블 형태)
			ResultSet rset = 
			stnt.executeQuery(query);
			
			//후처리 필요 ㅋ - DB에서 가져온 데이터를 사용하기 위함.
			
			while(rset.next()) {
				System.out.printf("사원명 : %s, 급여 : %s\n",rset.getString("EMP_NAME"),rset.getInt(2));
			}
			
			// 6번 자원해제
			
			rset.close();
			stnt.close();
			conn.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
	}

}
