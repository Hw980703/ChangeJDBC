package com.kh.jdbc.day02.student.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day01.student.controller.StudentController;
import com.kh.jdbc.day01.student.model.vo.Student;

public class StudentView {

	private StudentController controller;
	private Student student = null;
	private List<Student> sList = null;

	public StudentView() {
		controller = new StudentController();
	}

	public void startProgram() throws SQLException {
		finish: while (true) {
			int choice = printMenu();
			switch (choice) {
			case 1:
				sList = controller.printStudentList();

				if ( !sList.isEmpty()) {
					showAllStudents(sList);
					
				}else {displayError("학생 정보 없다");}
				

				break;
			case 2:

				// 아이디로 조회하는 쿼리문 생각해보기 (리턴형은 무엇으로? 매개변수는 무엇으로?)
				String studentId = inputStudentId();
//				ResultSet studentById = controller.printStudentById(studentId);
				student = controller.printStudentById(studentId);
				// printStudentById() 메소가 학생 정보를 조회

				showAllStudents(student);
				// showStudent() 메소드로 학생 정보를 출력

				break;
			case 3:

				// 쿼리문 생각해보기 (매개변수 유무, 리턴형은?)
				String studentName = inputStudentName();
//				 = controller.printStudentsByName(studentName);
				// printStudentByName , printStudentsByName(?)

				sList = controller.printStudentsByName(studentName);
				// selectOneByName , selectAllByName(?);
				showAllStudents(sList);
				// showStudent , showAllStudents

				break;
			case 4:
				// 인서트 쿼리문을 써야함
				// INSERT INTO 테이블명 VALUES();
				student = inputStudent();
				int result = controller.insertStudent(student);
				if (result > 0) {
					// 성공 메시지 출력

					displaySuccess("학생 정보 등록 성공");
				} else {
					// 실패 메시지 출력
					displayError("학생 정보 등록 실패");
				}
				break;
			case 5:
			//	UPDATE STUDENT_TBL SET MEMBER_PWD = 'pass1123',EMAIL =
			//	'4224lrplepr@naver.com' , HOBBY = '일,낮잠' WHERE MEMBER_ID = 'khuser01';
				student = modifyStudent();
				result = controller.modifyStudent(student);
				if (result > 0) {
					//성공 메시지 출력
					displaySuccess("학생 정보 변경 됨");
				}else {
					//실패 메시지 출력
					displayError("학생 정보 수정 안됨 ㅋㅋ");
				}
				break;
			case 6:
				// 쿼리문 생성해보기(매개변수 필요 유무,반환형)
				// DELETE FROM STUDENT_TBL WHERE MEMBER_ID = 'zkdhk1';
				studentId = inputStudentId();
				result = controller.deleteStudent(studentId);
				if (result > 0) {
					// 성공 메시지 출력
					displaySuccess("학생 정보 삭제 성공");
				} else {
					displayError("학생 정보 삭제 실패");
				}

				break;
			case 0:
				break finish;
			}

		}

	}

	private Student modifyStudent() {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String studentId = sc.next();
		System.out.print("비밀번호 : ");
		String studentPw = sc.next();
//		System.out.print("이름 : ");
//		String studentName = sc.next();
//		System.out.print("성별 : ");
//		char gender = sc.next().charAt(0);
//		System.out.print("나이 : ");
//		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine(); // 개행 제거, 엔터 제거, 전화번호에서 엔터 친게 들어갈 수 있기 때문임
		String address = sc.nextLine();
		System.out.print("취미(,로 구분) : ");
		String hobby = sc.nextLine();
		Student student = new Student(studentId, studentPw, email, phone, address, hobby);
		return student;
	}

	private void showAllStudents(Student student2) {
		// TODO Auto-generated method stub System.out.println("학생 정보 출력");

		System.out.printf(
				"이름 : %s, 나이 : %d, 아이디 : %s 성별 : %s \n, 이메일 : %s, 전화번호 : %s \n, 주소 : %s, 취미 : %s, 가입날짜 : %s, \n",
				student.getStudentName(), student.getAge(), student.getStudentID(), student.getGender(),
				student.getEmail(), student.getPhone(), student.getAddress(), student.getHobby(),
				student.getEnrollDate());
	}

	private String inputStudentName() {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		System.out.print("학생 이름을 입력하세요.");
		String name = sc.next();
		return name;
	}

	private void showStudent(Student student) {
		// TODO Auto-generated method stub
		System.out.println("학생 정보 출력");
		System.out.printf(
				"이름 : %s, 나이 : %d, 아이디 : %s 성별 : %s \n, 이메일 : %s, 전화번호 : %s \n, 주소 : %s, 취미 : %s, 가입날짜 : %s, \n",
				student.getStudentName(), student.getAge(), student.getStudentID(), student.getGender(),
				student.getEmail(), student.getPhone(), student.getAddress(), student.getHobby(),
				student.getEnrollDate());
	}

	private void showStudent(ResultSet studentById) {
		// TODO Auto-generated method stub

		try {
			System.out.printf("사원명 : %s, 급여 : %s\n", studentById.getString("MEMBER_NAME"), studentById.getInt(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		while (studentById.next()) {
//			Student student = new Student();
//			student.setStudentID(rset.getString("MEMBER_ID"));
//			student.setStudentPwd(rset.getString("MEMBER_PWD"));
//			student.setStudentName(rset.getString("MEMBER_NAME"));
//			student.setAge(rset.getInt("AGE"));
//			student.setEmail(rset.getString("EMAIL"));
//			student.setPhone(rset.getString("PHONE"));
//
//			// 문자는 문자열에 오는 단얼르 charAt 해서 자르면 되이이임!!
//			student.setGender(rset.getString("GENDER").charAt(0));
//			student.setAddress(rset.getString("ADDRESS"));
//			student.setHobby(rset.getString("HOBBY"));
//			student.setEnrollDate(rset.getDate("ENROLL_DATE"));
//			sList.add(student);
//
//			System.out.printf("아이디 : %s, 이름 : %s \n", rset.getString("MEMBER_ID"), rset.getString("MEMBER_NAME"));
//		}

	}

	private String inputStudentId() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디를 입력 하세요 : ");
		String id = sc.next();
		return id;
	}

	private void displayError(String string) {
		// TODO Auto-generated method stub

		System.out.println("[서비스 실패]" + string);

	}

	private void displaySuccess(String string) {
		// TODO Auto-generated method stub
		System.out.println("[서비스 성공]" + string);
	}

	private Student inputStudent() {

		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String studentId = sc.next();
		System.out.print("비밀번호 : ");
		String studentPw = sc.next();
		System.out.print("이름 : ");
		String studentName = sc.next();
		System.out.print("성별 : ");
		char gender = sc.next().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine(); // 개행 제거, 엔터 제거, 전화번호에서 엔터 친게 들어갈 수 있기 때문임
		String address = sc.nextLine();
		System.out.print("취미(,로 구분) : ");
		String hobby = sc.nextLine();

		Student student = new Student(studentId, studentPw, studentName, gender, age, email, phone, address, hobby);

		return student;
	}

	public int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" ===== 학생관리 프로그램 =====");
		System.out.println(" 1. 학생 전체 조회");
		System.out.println(" 2. 학생 아이디로 조회");
		System.out.println(" 3. 학생 이름으로 조회");
		System.out.println(" 4. 학생 정보 등록");
		System.out.println(" 5. 학생 정보 수정");
		System.out.println(" 6. 학생 정보 삭제");
		System.out.println(" 0. 프로그램 종료");
		System.out.print(" 메뉴 선택 : ");
		int input = sc.nextInt();
		return input;

	}

	public void showAllStudents(List<Student> sList) {
		System.out.println(" ===== 학생 전체 정보 출력 =====");
		for (Student student : sList) {
			System.out.printf(
					"이름 : %s, 나이 : %d, 아이디 : %s 성별 : %s \n, 이메일 : %s, 전화번호 : %s \n, 주소 : %s, 취미 : %s, 가입날짜 : %s, \n",
					student.getStudentName(), student.getAge(), student.getStudentID(), student.getGender(),
					student.getEmail(), student.getPhone(), student.getAddress(), student.getHobby(),
					student.getEnrollDate());
		}
	}

}
