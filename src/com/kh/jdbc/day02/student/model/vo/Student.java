package com.kh.jdbc.day02.student.model.vo;

import java.sql.Date;

public class Student {
	private String studentID;
	private String studentPwd;
	private String studentName;
	private char gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate;

	public Student() {
		// 기본 생성자임
	}

	
	



	public Student(String studentID, String studentPwd, String email, String phone, String address, String hobby) {
		super();
		this.studentID = studentID;
		this.studentPwd = studentPwd;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}






	public Student(String studentID, String studentPwd, String studentName, char gender, int age, String email,
			String phone, String address, String hobby) {
		super();
		this.studentID = studentID;
		this.studentPwd = studentPwd;
		this.studentName = studentName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}



	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getStudentPwd() {
		return studentPwd;
	}

	public void setStudentPwd(String studentPwd) {
		this.studentPwd = studentPwd;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "학생 [아이디=" + studentID + ", 비번=" + studentPwd + ", 이름=" + studentName + ", 성별=" + gender + ", 나이=" + age
				+ ", 이메일=" + email + ", 폰번호=" + phone + ", 주소=" + address + ", 취미=" + hobby + ", enrollDate="
				+ enrollDate + "]";
	}

}
