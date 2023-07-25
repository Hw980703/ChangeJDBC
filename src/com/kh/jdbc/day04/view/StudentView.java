package com.kh.jdbc.day04.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day04.controller.StudentController;
import com.kh.jdbc.day04.model.vo.Student;

public class StudentView {

	private StudentController controller;

	public StudentView() {
		controller = new StudentController();
	}

	public void startProgram() {
		List<Student> sList = null;
		String studentId = null;
		int result = 0;
		end: while (true) {
			int choice = printMenu();

			switch (choice) {

			case 1:

				// SELECT * FROM STUDENT_TBL
				// SELECT COUNT(*) FROM STUDENT_TBL = INT로 받을 수 있음.

				sList = controller.selectAll();
				if (!sList.isEmpty()) {
					printAllStudents(sList);
				} else {
					System.out.println("데이터 조회에 실패하였습니다.");
				}
				break;
			case 2:

				// SELECT * FROM STUDENT_TBL WHERE MEMBER_ID = 'ID';
				studentId = inputStdId();
				Student student = controller.selectOneById(studentId);

				if (student != null) {
					printStudent(student);
				} else {
					System.out.println("데이터가 없음");
				}

				break;
			case 3:
				// SELECT * FROM STUDENT_TBL WHERE MEMBER_NAME = 'NAME';

				String studentName = inputStdName();
				sList = controller.selectAllByName(studentName);

				// sList가 널인지 확인
				// if ( sList.size()>0)

				if (!sList.isEmpty()) {
					printAllStudents(sList);
				} else {
					System.out.println("조회 실패함 ㅋㅋ");
				}
				break;
			case 4:

				student = inputStudent();
				result = controller.insertStudent(student);
				if (result > 0) {
					System.out.println("등록 성공");
				} else {
					System.out.println("등록 실패");
				}

				break;
			case 5:
				
				studentId = inputStdId();
				student=controller.selectOneById(studentId);
				if ( student != null) {
					//수정하기
					student = modifyStudent(student);
					result = controller.updateStudent(student);
					if ( result > 0) {
						System.out.println("수정 성공 ㅋㅋ");
					}else {System.out.println("수정 실패 ㄷ");}
				}else { System.out.println("데이터를 찾지 못했다");}
				
				break;
			case 6:
				studentId = inputStdId();
				// DELETE FROM STUDENT_TBL WHERE STUDENT_ID = 'ID';
				 result = controller.deleteStudent(studentId);

				if (result > 0) {
					System.out.println("삭제 성공");
				} else {
					System.out.println("삭제 실패");
				}

				break;
			case 0:
				break end;

			}
		}

	}

	private Student modifyStudent(Student student) {
		Scanner sc = new Scanner(System.in);
		System.out.print("수정 할 학생의 아이디 : ");
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
		student.setStudentPwd(studentPw);
		student.setEmail(email);
		student.setPhone(phone);
		student.setAddress(address);
		student.setHobby(hobby);
		
//		Student student = new Student(studentId, studentPw, email, phone, address, hobby);
		return student;
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

	private String inputStdName() {
		Scanner sc = new Scanner(System.in);

		System.out.println("학생 이름을 입력하세요.");
		String name = sc.next();
		return name;
	}

	private void printStudent(Student student) {
		System.out.println("학생 정보 출력");
		System.out.printf(
				"이름 : %s, 나이 : %d, 아이디 : %s 성별 : %s \n, 이메일 : %s, 전화번호 : %s \n, 주소 : %s, 취미 : %s, 가입날짜 : %s, \n",
				student.getStudentName(), student.getAge(), student.getStudentID(), student.getGender(),
				student.getEmail(), student.getPhone(), student.getAddress(), student.getHobby(),
				student.getEnrollDate());
	}

	private String inputStdId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("학생 아이디를 입력하세요.");
		String id = sc.next();
		return id;
	}

	private void printAllStudents(List<Student> sList) {
		// TODO Auto-generated method stub
		System.out.println(" ===== 학생 전체 조회 =====");
		for (Student student : sList) {
			System.out.printf(
					"이름 : %s, 나이 : %d, 아이디 : %s 성별 : %s \n, 이메일 : %s, 전화번호 : %s \n, 주소 : %s, 취미 : %s, 가입날짜 : %s, \n",
					student.getStudentName(), student.getAge(), student.getStudentID(), student.getGender(),
					student.getEmail(), student.getPhone(), student.getAddress(), student.getHobby(),
					student.getEnrollDate());
		}
	}

	private int printMenu() {
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
}
