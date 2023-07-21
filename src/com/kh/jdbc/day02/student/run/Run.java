package com.kh.jdbc.day02.student.run;

import java.sql.SQLException;

import com.kh.jdbc.day01.student.view.StudentView;

public class Run {
	public static void main(String[] args) throws SQLException {
		
		StudentView view = new StudentView();
		view.startProgram();
	}
}
