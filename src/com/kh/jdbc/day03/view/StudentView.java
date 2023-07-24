package com.kh.jdbc.day03.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day03.controller.StudentController;
import com.kh.jdbc.day03.model.vo.Student;

public class StudentView {

	private StudentController controller;

	public StudentView() {
		controller = new StudentController();

	}

	public void studentProgram() {
		List<Student> result = null;
		end: while (true) {
			int input = printMenu();
			switch (input) {
			case 1:
				result = controller.selectAllStudent();
				printAllStudent(result);

				break;
			case 2:

				String id = printId();
				Student student = controller.selectNameById(id);
				showStudent(student);

				break;
			case 3:

				String studentName = inputStdName();
				result = controller.selectAllByNameStudent(studentName);
				printAllStudent(result);

				break;
			case 4:

				student = insertStudent();
				int num = controller.insertStudent(student);
				if (num > 0) {
					System.out.println("등록 성공");
				} else {
					System.out.println("등록 실패");
				}

				break;
			case 5:
				student = modifyStudent();
				num = controller.modifyStudent(student);
				if (num > 0) {
					System.out.println("수정 성공");
				} else {
					System.out.println("수정 실패");
				}

				break;
			case 6:
				studentName = deleteStudentName();
				num = controller.deleterStudent(studentName);

				if (num > 0) {
					System.out.println("삭제 성공");
				} else {
					System.out.println("삭제 실패");
				}
				break;

			case 9:
				// SELECT * FROM STUDENT_TBL WHERE STUDENT_ID ='' AND STUDENT_PWD = '';

				student = inputLoginInfo();
				student = controller.studentLogin(student);
				if (student != null) {
					// 로그인 성공
					System.out.println("로그인 성공");

				} else {
					// 로그인 실패
					System.out.println("로그인 실패");
				}

				break;
			case 0:
				break end;

			}

		}

	}

	private Student inputLoginInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" ===== 학생 로그인 =====");
		System.out.print("아이디 : ");
		String studentId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String studentPw = sc.next();
		Student student = new Student(studentId, studentPw);
		System.out.println(student.toString());

		return student;
	}

	private String deleteStudentName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("정보를 삭제 할 학생의 이름을 입력하세요.");
		String name = sc.next();
		return name;
	}

	private Student modifyStudent() {
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
		Student student = new Student(studentId, studentPw, email, phone, address, hobby);
		return student;
	}

	private Student insertStudent() {
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

	private String modifyprintId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정 할 학생 아이디를 입력하세요.");
		String id = sc.next();
		return id;
	}

	private String printId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("학생 아이디를 입력하세요.");
		String id = sc.next();
		return id;
	}

	private void printAllStudent(List<Student> result) {

		System.out.println(" ===== 학생 전체 조회 =====");
		for (Student student : result) {
			System.out.printf(
					"이름 : %s, 나이 : %d, 아이디 : %s 성별 : %s \n, 이메일 : %s, 전화번호 : %s \n, 주소 : %s, 취미 : %s, 가입날짜 : %s, \n",
					student.getStudentName(), student.getAge(), student.getStudentId(), student.getGender(),
					student.getEmail(), student.getPhone(), student.getAddress(), student.getHobby(),
					student.getEnrollDate());
		}
	}

	private int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" ===== 학생관리 프로그램 =====");
		System.out.println(" 9. 학생 로그인");
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

	private void showStudent(Student student) {
		// TODO Auto-generated method stub
		System.out.println("학생 정보 출력");
		System.out.printf(
				"이름 : %s, 나이 : %d, 아이디 : %s 성별 : %s \n, 이메일 : %s, 전화번호 : %s \n, 주소 : %s, 취미 : %s, 가입날짜 : %s, \n",
				student.getStudentName(), student.getAge(), student.getStudentId(), student.getGender(),
				student.getEmail(), student.getPhone(), student.getAddress(), student.getHobby(),
				student.getEnrollDate());
	}

}
