package edu.kh.dept.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.exception.DepartmentInsertException;

public interface DepartmentService {

	List<Department> selectAll() throws SQLException;
	
	/** 부서 추가 서비스
	 * @param dept
	 * @return result(삽입된 행의 개수)
	 */
	int insertDepartment(Department dept) throws DepartmentInsertException;
	

	/** 여러 부서 추가
	 * @param deptList
	 * @return result(삽입된 행의 개수)
	 * @throws DepartmentInsertException
	 */
	int multiInsert(List<Department> deptList) throws DepartmentInsertException;
	
	//부서삭제
	int deleteDepartment(String deptId) throws SQLException;

	
	//부서1행조회
	Department selectOne(String deptId) throws SQLException;

	//부서수정
	int updateDepartment(Department dept) throws SQLException;
	
	/** 부서명 검색
	 * @param keyword
	 * @return deptList
	 * @throws SQLException
	 */
	List<Department> searchDepartment(String keyword) throws SQLException;
	
	
	
	
}
