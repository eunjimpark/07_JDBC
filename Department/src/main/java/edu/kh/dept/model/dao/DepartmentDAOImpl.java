package edu.kh.dept.model.dao;

import static edu.kh.dept.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.dept.model.dto.Department;

public class DepartmentDAOImpl implements DepartmentDAO{
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public DepartmentDAOImpl() {
		
		try {
			prop = new Properties();
			String path = DepartmentDAOImpl.class.getResource("/edu/kh/dept/sql/sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(path));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//부서전체조회
	@Override
	public List<Department> selectAll(Connection conn) throws SQLException {
		
		List<Department> deptList = new ArrayList<Department>();
		
		try {
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs= stmt.executeQuery(sql);
			
			while(rs.next()) {
				String deptId = rs.getString("DEPT_ID");
				String deptTitle = rs.getString("DEPT_TITLE");
				String locationId = rs.getString("LOCATION_ID");
				
				Department dept = new Department(deptId, deptTitle, locationId);
				deptList.add(dept);
			}
			
		}
		finally {
			close(rs);
			close(stmt);
		}
		return deptList;
	}
	

	// 부서 추가
	@Override
	public int insertDepartment(Connection conn, Department dept) throws SQLException {
	
		// 1. 결과 저장용 변수 선언 / 객체 생성
		int result = 0;
		
		try {
			// 2. SQL 얻어오기
			String sql = prop.getProperty("insertDepartment");
			
			//3.프리페얼드스테이트먼트객체생성
			pstmt = conn.prepareStatement(sql);
			
			//4. ? 에 알맞은값대입
			pstmt.setString(1, dept.getDeptId());
			pstmt.setString(2, dept.getDeptTitle());
			pstmt.setString(3, dept.getLocationId());
			
			//5.sql수행후 결과반환받기
			result = pstmt.executeUpdate();
			
			
		}finally {
			//6.사용한jdbc객체자원반환 단 커넥션은제외
			close(pstmt);
			
		}
		
		return result;
	}
	
	
	@Override
	public int deleteDepartment(Connection conn, String deptId) throws SQLException {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteDepartment");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, deptId);
			
			result = pstmt.executeUpdate(); // DML 수행
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	// 부서 1행 조회
	@Override
	public Department selectOne(Connection conn, String deptId) throws SQLException {
		
		// 결과 저장용 변수 선언
		Department dept = null; 
		
		try {
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptId);
			
			// SQL(SELECT)수행후 결과 반환받기
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dept = new Department(
													rs.getString("DEPT_ID"),
													rs.getString("DEPT_TITLE"),
													rs.getString("LOCATION_ID"));
			}
			
			
		}finally {
			//사용한 JDBC 객체 지원 반환
			close(rs);
			close(pstmt);
			
		}
		
		
		
		return dept; // 조회 실패 시 null, 성공시 null 아님
	}
	
	

	
  //부서 수정
	@Override
	public int updateDepartment(Connection conn, Department dept) throws SQLException {
		
		int result = 0; // 결과 저자용 변수 선언
		
		try {
		
			String sql = prop.getProperty("updateDepartment");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dept.getDeptTitle());
			pstmt.setString(2, dept.getLocationId());
			pstmt.setString(3, dept.getDeptId());
			
			result = pstmt.executeUpdate();
			
			
			
			
		} finally { // JDBC 객체 자원 무조건 반환 하려고 finally 구문 사용
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	// 부서명 검색
	@Override
	public List<Department> searchDepartment(Connection conn, String keyword) throws SQLException {
		
		// 결과를 저장할 변수/객체 생성
		List<Department> deptList = new ArrayList<Department>();
		
		try {
				//sql얻어오기
			String sql = prop.getProperty("searchDepartment");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String deptId 		= rs.getString(1); // 조회 결과 컬럼 순서 (1번 컬럼)
				String deptTitle 	= rs.getString(2); // 조회 결과 컬럼 순서 (2번 컬럼)
				String locationId = rs.getString(3); // 조회 결과 컬럼
				
				Department dept = new Department(deptId, deptTitle, locationId);
				
				deptList.add(dept);
			}
			
			
		}finally {
			
		}
		
		return deptList;
	}
	
	
	
	
		
	
}


