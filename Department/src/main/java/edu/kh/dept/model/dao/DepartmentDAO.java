package edu.kh.dept.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dto.Department;

public interface DepartmentDAO {

	List<Department> selectAll(Connection conn) throws  SQLException;



	
	
	/** 부서 추가
	 * @param conn
	 * @param dept
	 * @return result
	 * @throws SQLException
	 */
	int insertDepartment(Connection conn, Department dept) throws SQLException;

	//부서삭제
	
	int deleteDepartment(Connection conn, String deptId) throws SQLException;



//부서1행조회

	Department selectOne(Connection conn, String deptId) throws SQLException;
	
	
	//부서수정

	int updateDepartment(Connection conn, Department dept) throws SQLException;
	
	
	/** 부서명 검색
	 * @param conn
	 * @param keyword
	 * @return deptList (조회 결과 없으면 비어있음)
	 * @throws SQLException
	 */
	List<Department> searchDepartment(Connection conn, String keyword) throws SQLException;

	
	
	
	
}






