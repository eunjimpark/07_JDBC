package edu.kh.zooList.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.zooList.model.dto.Zoo;

public interface ZooDAO {

	int getAnimalCount(Connection conn) throws SQLException;

	List<Zoo> selectAll(Connection conn) throws SQLException;

	int deleteZoo(Connection conn, int zooNo);

	int updateZoo(Connection conn, String zooLocation, String zooAnimal);

	

}
