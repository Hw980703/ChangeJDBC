package com.kh.jdbc.day04.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

	private Properties prop;

//	private final String Driver_NAME = "oracle.jdbc.driver.OracleDriver";
//	private final String URL = "jdbc:oracle:thin:@localHost:1521:xe";
//	private final String USER = "student";
//	private final String PASSWORD = "student";

	// 디자인 패턴 : 각기 다른 소프트웨어 모듈이나 기능을 가진 응용 SW를
	// 개발할 때 공통 되는 설계 문제를 해결하기 위하여 사용되는 패턴임.
	// --> 효율적인 방식을 위함!
	// 패턴의 종류 : 생성패턴 , 구조 패턴, 행위패턴, .....
	// 1. 생성패턴 : 싱글톤 패턴,추상팩토리,팩토리 메서드
	// 2. 구조패턴 : 컴포지트,데코레이트
	// 3. 행위패턴 : 옵저버,스테리읕,전략,템플릿 메서드

	/*
	 * private static Singleton instance;
	 * 
	 * private Singletone() {}
	 * 
	 * public static Singletone getInstance() { if ( instance == null) { instance =
	 * new Singletone(); } }
	 */

	private static JDBCTemplate instance;
	private static Connection conn;

	public JDBCTemplate() {

	}

	// 무조건 딱 한번만 생성 되고 없을 때에만 생성 된다.
	// 이미 존재하면 존재하는 객체를 사용함
	public static JDBCTemplate getInstance() {
		// 이미 만들어져있는지 체크 하고
//		if (JDBC객체 만들어져 있니?) {
//			안만들었으면 만들어서 사용해ㅋ
//		JDBC 객체 생성
//		} 만들어져 있으면 그거 사용해ㅋ 
//		JDBC 객체 

		if (instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;

		// 만들어져 있으면 그거 사용해
	}

	public Connection createConnection() {
		Connection conn = null;
		prop = new Properties();
		try {
			Reader reader = new FileReader("resources/dev.properties");
			prop.load(reader);
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");

			if (conn == null || conn.isClosed()) {
				Class.forName(prop.getProperty("driverName"));
				conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(false);
				// 오토 커밋 풀어주세요
			}

			// DBCP(DataBase Connection Pool)
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}

	public void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {

				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {

			if (conn != null && !conn.isClosed()) {

				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close() {
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					conn.close();

				}
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
	}

}
