package edu.kh.jdbc.view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.model.dao.DepartmentDAO;
import edu.kh.jdbc.model.dto.Department;

public class DepartmentView {
	
	//객체의 필드가 초기화되지않은 경우 생성시 기본값으로 자동 초기화
	private Scanner sc;
	private DepartmentDAO dao;
	
	
	
	// 기본 생성자
	public DepartmentView() {
		sc = new Scanner(System.in);
		dao = new DepartmentDAO();
	}
	
	
	public void displayMenu() {
		
		int input = 0; //메뉴번호 입력받을변수
		
		do {
			try {
				System.out.println("\n===== 부서 관리 프로그램 =====\n");
				System.out.println("1. 부서 추가");
				System.out.println("2. 부서 전체 조회");
				System.out.println("3. 검색어가 부서명에 포함되는 부서 조회");
				System.out.println("4. 부서 삭제");
				System.out.println("5. 부서명 변경");
				System.out.println("0. 프로그램 종료");
				
				System.out.println("메뉴선택 >>");			
				
				input = sc.nextInt();
				
				switch(input) {
				case 1 : insertDepartment();  break;
				case 2 : selectAll(); break;
				case 3 : selectDepartmentTitle(); break;
				case 4 : deleteDepartment(); break;
				case 5 : updateDepartment(); break;
				case 0 : System.out.println("\n프로그램 종료\n"); break;
				default: System.out.println("\n메뉴에 없는 번호에요\n");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("\n입력 형식이 잘못되었습니다\n");
				input = -1;
				sc.nextLine();
			}
			catch(Exception e) {
				e.printStackTrace();
			}		
		}while(input !=0);	
	}
	
	
private void insertDepartment() throws SQLException {
	System.out.println("\n----부서추가----\n");
	System.out.println("부서코드 (deptId) 입력:");
	String deptId = sc.next();
	
	System.out.println("부서명 (deptTitle) 입력 :");
	String deptTitle = sc.next();
	
	System.out.println("지역 코드 (locationId) 입력 :");
	String locationId = sc.next();
	
	//입력받은 값을 한번에 저장할 department 객체 생성
	Department dept = new Department(deptId, deptTitle, locationId);
	
	//DAO메서드 호출후 결과 (삽입성공한 행의 갯수) 반환 받기
	int result = dao.insertDepartment(dept);
	
	if(result > 0) System.out.println("\n삽입성공\n");
	else           System.out.println("\n삽입실패\n");
}
	
private void selectAll() throws SQLException{

	System.out.println("\n---부서 전체 조회---\n");
	
	List<Department> deptList = dao.selectAll();
	
	for(Department dept : deptList) {
		System.out.println(dept);
	}
}

private void selectDepartmentTitle() throws SQLException{
	System.out.println("\n--- 검색어가 부서명에 포함되는 부서 조회 ---\n");
	
	System.out.print("검색할 부서명 : ");
	String title = sc.next();
	
	List<Department> deptList = dao.selectDepartmentTitle(title);
	
	//deptList가 비어있는경우 ->검색결과없음
	if(deptList.isEmpty()) {
		System.out.println(title+"이 포함된 부서가 없습니다.");
	}else {
		for(Department dept : deptList) {
			System.out.println(dept);
		}
	}
}

private void deleteDepartment() throws SQLException{
	System.out.println("\n---부서 삭제---\n");
	
	System.out.print("삭제할 부서코드 : ");
	String deptId = sc.next();
		
	int check = dao.checkDepartment(deptId);
	
	if(check == 0) {
		System.out.println("부서코드가 없습니다");
		return;
	} 
	System.out.println("정말로 삭제하시겠습니까? Y/N");
	char ch = sc.next().toUpperCase().charAt(0);
	
	if(ch=='Y') {
		int result = dao.deleteDepartment(deptId);
		System.out.println("삭제되었습니다");
	}else {
		System.out.println("삭제취소");
	}	
}




private void updateDepartment () throws SQLException{
	System.out.println("\n---부서명 변경---\n");
	
	System.out.print("수정할 부서코드 : ");
	String deptId = sc.next();
	
	int check = dao.checkDepartment(deptId);

	if(check==0) {
		System.out.println("부서코드가 없음");
		return;
	}
	
	System.out.print("변경후 부서명 : ");
	String deptTitle = sc.next();
	
	int result = dao.updateDepartment(deptId, deptTitle);
	
	if(result>0) {
		System.out.println("변경 성공");
	}else {
		System.out.println("변경 실패");
	}
}

















	
	
}
