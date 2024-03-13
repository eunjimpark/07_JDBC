package edu.kh.dept.model.exception;

//사용자 정의 예외 만드는 방법!
//-> 아무 Exception 클래스를 상속 받으면 된다!
//+ RuntimeException 또는 그 자식 예외를 상속 받으면
//Unchecked Exception으로 설정된다!!

public class DepartmentInsertException extends RuntimeException {
	
	public DepartmentInsertException() {
		super("부서 추가 중 예외발생 제약조건위배");
		
	}
	
	public DepartmentInsertException(String message) {
		super(message);
		
	}

}
