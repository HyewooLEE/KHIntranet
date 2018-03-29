package model.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionDBBean {
	
private static ConnectionDBBean instance = new ConnectionDBBean();
	
	public static ConnectionDBBean getInstance() {
		return instance;
	}

	private ConnectionDBBean() {}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	//현재 접속자수
	public  int nowConnection() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		
		String sql = "select count(*) from Connection";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			i = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();} catch(SQLException ex) {}
			if(pstmt !=null)try {pstmt.close();	}catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
		return i;
	}
	
	

	public  int InsertCon(int emp_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String Connection = "접속중";
		try {
			conn = getConnection();
			String sql = "select connection_emp_no from Connection where connection_emp_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp_no);
			rs = pstmt.executeQuery();		
			 
			if(rs.next()) {}else {
					 sql = "insert into Connection values(?, ?)";
					 
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, emp_no);
					pstmt.setString(2, Connection);
					pstmt.executeUpdate();
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();} catch(SQLException ex) {}
			if(pstmt !=null)try {pstmt.close();	}catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
		return -1;
	}
	
	//삭제
	public  void DeleteCon(int emp_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		String sql = "delete from connection where connection_emp_no = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp_no);
			pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();} catch(SQLException ex) {}
			if(pstmt !=null)try {pstmt.close();	}catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
	}
	

}
