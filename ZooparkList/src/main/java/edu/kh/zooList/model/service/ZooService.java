package edu.kh.zooList.model.service;

import java.sql.SQLException;
import java.util.Map;

import edu.kh.zooList.model.dto.Zoo;

public interface ZooService {
	
	
	
	
	
	Map<String,Object> selectAll() throws SQLException;
	
	
	
	//수정
	int updateZoo(String zooLocation, String zooAnimal) throws SQLException;

	
	
	//삭제
	int deleteZoo(int zooNo) throws SQLException;



	Zoo selectZoo(int zooNo) throws SQLException;




	
	
}
