package edu.kh.jdbc;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCExample6 {
	public static void main(String[] args) {
		
		/* 1. JDBC 객체 참조 변수 선언 */
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			
			/* 2. DriverManager 객체를 이용해서 Connection 생성하기 */

			/* 2-1) Oracle JDBC Driver 객체를 메모리에 적재 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			/* 2-2) DB 연결 정보를 이용해서 Connection 생성*/
			String type     = "jdbc:oracle:thin:@"; // 드라이버 종류
			String host     = "localhost";  // DB 서버 컴퓨터의 IP 주소
			String port     = ":1521";  // DB 서버 컴퓨터에 DB 프로그램 연결 번호
			String dbName   = ":xe";    // DB 이름
			String userName = "KH_PEJ"; // 사용자 계정
			String pw       = "KH1234"; // 계정 비밀번호
			
			
			conn 
				= DriverManager.getConnection(type + host + port + dbName, userName, pw);
			
			
			/*만들어진 커넥션으로 SQL수행시 자동커밋 비활성화*/
			conn.setAutoCommit(false);
			
			
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("부서코드 입력 : ");
			String deptCode = sc.next();
			
			System.out.print("부서명 입력 : ");
			String deptTitle = sc.next();
			
			System.out.print("지역코드 입력 : ");
			String locationId = sc.next();
			
			String sql = String.format("INSERT INTO DEPARTMENT4 VALUES('%S','%S','%S')",
					deptCode, deptTitle, locationId);
			
			
			/*4. Statement 객체 생성 */
			stmt = conn.createStatement();
			
			/*5. SQL 수행후 결과 반환 받기*/
			
			
			/*DML 수행 결과는 결과행의 수*/
			//UPDATE
			int result = stmt.executeUpdate(sql);
					
	
			/*6. 수행 결과에 따라 트랜잭션 제어 처리*/
			if(result>0) {
				System.out.println("삽입성공");
				conn.commit();
			}else {
				System.out.println("삽입실패");
				conn.rollback();
			}
			
			
			
			
		}catch(Exception e ){
			e.printStackTrace();
			}finally {
				
				/*7. JDBC자원 반환*/
				try {
					if(stmt != null) stmt.close();
					if(conn != null) conn.close();		
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		
		
	}
	
	
	
	
	
	
}
