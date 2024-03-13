package edu.kh.dept.service;

import static edu.kh.dept.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dao.DepartmentDAO;
import edu.kh.dept.model.dao.DepartmentDAOImpl;
import edu.kh.dept.model.exception.DepartmentInsertException;
import edu.kh.model.dto.Department;

//Service 
//- 비즈니스 로직 처리
//(데이터 가공, 트랜잭션 제어 처리)

//- 트랜잭션 제어 처리
//-> 하나의 Service 메서드가 여러 DAO 메서드를 호출할 수 있다!


public class DepartmentServiceImpl implements DepartmentService{

	
	private DepartmentDAO dao = new DepartmentDAOImpl();
	
	@Override
	public List<Department> selectAll() throws SQLException {
		
		Connection conn = getConnection();
		
		List<Department> deptList = dao.selectAll(conn);
		
		close(conn);
		
		return deptList;
	
	}

	//부서추가서비스
	@Override
	public int insertDepartment(Department dept) throws DepartmentInsertException {
		
		int result =0;
		
		//1.커넥션 얻어오기
		Connection conn = getConnection();
		
		try {
			//2.DAO메서드 호출후 결과 반환받기
			result = dao.insertDepartment(conn, dept);
			
			//3.DAO 수행 결과에 따라 트랜잭션 제어
			if(result > 0 ) commit(conn);
			else            rollback(conn);
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			rollback(conn);
			
			/* 제약조건 위배로 인해서 정상 수행 되지 않음을 표현하기 위해
			 * 강제로 예외 발생!!!! - 사용자 정의 예외 이용
			 *  */
			throw new DepartmentInsertException();
			
			
			
		}finally {
			
			//4.커넥션 반환처리
			close(conn);
			
		}
		
		//5.결과 반환
		return result;
	}
	
}
