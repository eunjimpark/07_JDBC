package edu.kh.dept.service;

import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.exception.DepartmentInsertException;
import edu.kh.model.dto.Department;

public interface DepartmentService {

	List<Department> selectAll() throws SQLException;
	
	/** 부서 추가 서비스
	 * @param dept
	 * @return result(삽입된 행의 개수)
	 */
	int insertDepartment(Department dept) throws DepartmentInsertException;
	
	
}
