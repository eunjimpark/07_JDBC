package edu.kh.zooList.model.dao;

import static edu.kh.zooList.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import edu.kh.zooList.model.dto.Zoo;

public class ZooDAOImpl implements ZooDAO{
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	//
	private Properties prop;
	
	//객체생성+xml파일 읽어오기
	public ZooDAOImpl() {
	try {
		prop = new Properties();
		String path = ZooDAOImpl.class.getResource("/eud/kh/zooList/sql/sql.xml").getPath();
		
		prop.loadFromXML(new FileInputStream(path));
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

	@Override
	public int getAnimalCount(Connection conn) throws SQLException {
		
		return 0;
	}

	@Override
	public List<Zoo> selectAll(Connection conn) throws SQLException {
		
		return null;
	}

	@Override
	public int deleteZoo(Connection conn, int zooNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateZoo(Connection conn, String zooLocation, String zooAnimal) {
		// TODO Auto-generated method stub
		return 0;
	}

}
