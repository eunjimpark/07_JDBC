package edu.kh.dept.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.model.dto.Department;

public interface DepartmentDAO {

	List<Department> selectAll(Connection conn) throws  SQLException;



	
	
	/** 부서 추가
	 * @param conn
	 * @param dept
	 * @return result
	 * @throws SQLException
	 */
	int insertDepartment(Connection conn, Department dept) throws SQLException;

	
	
}
