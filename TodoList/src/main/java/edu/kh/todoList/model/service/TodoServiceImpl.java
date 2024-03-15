package edu.kh.todoList.model.service;

// 지정된 위치의 static을 모두 가져와 사용
import static edu.kh.todoList.common.JDBCTemplate.close;
import static edu.kh.todoList.common.JDBCTemplate.commit;
import static edu.kh.todoList.common.JDBCTemplate.getConnection;
import static edu.kh.todoList.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.model.dao.TodoDAO;
import edu.kh.todoList.model.dao.TodoDAOImpl;
import edu.kh.todoList.model.dto.Todo;

public class TodoServiceImpl implements TodoService{

	//DAO객체생성
	private TodoDAO dao = null;
	
	public TodoServiceImpl() {
		dao = new TodoDAOImpl();
	}
	
	@Override
	public Map<String, Object> selectAll() throws SQLException {
		
		//1.커넥션얻어오기
		Connection conn = getConnection();
		
		//2.할일목록조회 DAO메서드 호출후 결과반환
		List<Todo> todoList = dao.selectAll(conn);
		
		//3.완료된 할일 개수 조회 DAO메서드 호출후 결과반환받기
		int completeCount= dao.getCompleteCount(conn);
		
		//4.커넥션반환
		close(conn);
		
		//5. 맵을 생성해서 다오 호출 결과를 한번에 묶어서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("todoList",todoList);
		map.put("completeCount", completeCount);
		
		return map;

	}
	
	
	// 할 일 추가
	@Override
	public int addTodo(String todoTitle, String todoContent) throws SQLException {
		
		Connection conn = getConnection(); // 커넥션 생성
		
		int result = dao.addTodo(conn, todoTitle, todoContent); // dao 호출
		
		// 트랜잭션 제어 처리
		if(result > 0)	commit(conn);
		else						rollback(conn);
		
		close(conn); // 커넥션 반환
		
		return result;
	}
	
	
	// 할 일 상세 조회
	@Override
	public Todo selectTodo(int todoNo) throws SQLException {
		
		Connection conn = getConnection();
		
		Todo todo = dao.selectTodo(conn, todoNo);
		
		close(conn);
		
		return todo;
	}
	
	
	//완료 여부 수정
	@Override
	public int changeComplete(int todoNo, String complete) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.changeComplete(conn, todoNo, complete);
		
		if(result > 0) 	commit(conn);
		else						rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	// 할 일 수정
	@Override
	public int updateTodo(Todo todo) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.updateTodo(conn, todo);
		
		if(result > 0)	commit(conn);
		else						rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	// 할 일 삭제
	@Override
	public int deleteTodo(int todoNo) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.deleteTodo(conn, todoNo);
		
		if(result > 0)	commit(conn);
		else						rollback(conn);
		
		close(conn);
		
		return result;
		
	}
	
	
	
	
	
	
	
}
