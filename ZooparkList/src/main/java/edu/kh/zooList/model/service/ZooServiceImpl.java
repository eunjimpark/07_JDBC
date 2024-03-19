package edu.kh.zooList.model.service;

import static edu.kh.zooList.common.JDBCTemplate.close;
import static edu.kh.zooList.common.JDBCTemplate.commit;
import static edu.kh.zooList.common.JDBCTemplate.getConnection;
import static edu.kh.zooList.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.zooList.common.JDBCTemplate;
import edu.kh.zooList.model.dao.ZooDAO;
import edu.kh.zooList.model.dao.ZooDAOImpl;
import edu.kh.zooList.model.dto.Zoo;

public class ZooServiceImpl implements ZooService{
	
	//DAO객체 생성
	private ZooDAO dao = null;
	
	public ZooServiceImpl() {
		dao = new ZooDAOImpl();
	}
	public Map<String, Object> selectAll() throws SQLException{
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<Zoo> zooList = dao.selectAll(conn);
		
		int completeCount = dao.getAnimalCount(conn);
		
		close(conn);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("todoList", zooList);
		map.put("completeCOunt", completeCount);
		
		return map;
	}

	
	//동물수정
	@Override
	public int updateZoo(String zooLocation, String zooAnimal) throws SQLException {
		Connection conn = getConnection();
		
		int result = dao.updateZoo(conn, zooLocation, zooAnimal);
		
		if(result>0) commit(conn);
		else         rollback(conn);
		
		close(conn);
		
		return result;
	}

			
	
	// 동물 삭제
	@Override
	public int deleteZoo(int zooNo) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.deleteZoo(conn, zooNo);
		
		if(result > 0)	commit(conn);
		else						rollback(conn);
		
		close(conn);
		
		return result;
		
	}
	@Override
	public Zoo selectZoo(int zooNo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
