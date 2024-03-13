package edu.kh.dept.model.dao;

import static edu.kh.dept.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.dept.common.JDBCTemplate;
import edu.kh.model.dto.Department;

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
		
	
}
