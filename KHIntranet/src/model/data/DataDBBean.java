package model.data;
import java.sql.*;
import java.util.*;
  
public class DataDBBean { //DB와 관련된 일을 하는 클래스 : DBBean, DAO
	
	private static DataDBBean instance = new DataDBBean();

	public static DataDBBean getInstance() {
		return instance;
	}
	
	private DataDBBean() {}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	// 글 등록
	public void insertData(DataDataBean data) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO DATA VALUES (DATA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, data.getData_emp_no());
			pstmt.setString(2, data.getData_title());
			pstmt.setString(3, data.getData_content());
			pstmt.setString(4, data.getData_date());
			pstmt.setString(5, data.getData_file_name());
			pstmt.setString(6, data.getData_file_addr());
			
			pstmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
	}
	
	public void updateData(DataDataBean data) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "UPDATE DATA SET DATA_TITLE=?, DATA_CONTENT=?, DATA_DATE=?, DATA_FILE_NAME=?, DATA_FILE_ADDR=? WHERE DATA_NO=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, data.getData_title());
			pstmt.setString(2, data.getData_content());
			pstmt.setString(3, data.getData_date());
			pstmt.setString(4, data.getData_file_name());
			pstmt.setString(5, data.getData_file_addr());
			pstmt.setInt(6, data.getData_no());
							
			pstmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
	}
	
	public void deleteData(int data_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM DATA WHERE DATA_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, data_no);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
			if(conn != null) try { conn.close(); } catch(Exception e) {}
		}
	}
	
	// 글 갯수 가지고 오기
	public int getDataCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int data_count = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM DATA");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				data_count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
			if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
			if(conn != null) try { conn.close(); } catch(Exception e) {}
		}
		return data_count;
	}
	// 글 가지고 오기
	public DataDataBean getData(int data_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataDataBean ddb = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement( "SELECT * FROM DATA WHERE DATA_NO = ?" );
			pstmt.setInt(1, data_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ddb = new DataDataBean();
				ddb.setData_no(rs.getInt("data_no"));
				ddb.setData_emp_no(rs.getInt("data_emp_no"));
				ddb.setData_title(rs.getString("data_title"));
				ddb.setData_content(rs.getString("data_content"));
				ddb.setData_date(rs.getString("data_date"));
				ddb.setData_file_name(rs.getString("data_file_name"));
				ddb.setData_file_addr(rs.getString("data_file_addr"));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if( rs != null) try {rs.close();} catch(SQLException ex) {}
			if( pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if( conn != null) try {conn.close();} catch(SQLException ex) {}
		}
		return ddb;
	}
	
	public List<DataDataBean> getDataList() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DataDataBean> dataList = null;
		try {
			conn = getConnection();
			String sql = "SELECT D.DATA_NO, D.DATA_EMP_NO, D.DATA_TITLE, D.DATA_CONTENT, D.DATA_DATE, E.EMP_NAME FROM DATA D, EMPLOYEE E WHERE D.DATA_EMP_NO = E.EMP_NO";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			dataList = new ArrayList<DataDataBean>();
			if(rs.next()) {
				do {
					DataDataBean ddb = new DataDataBean();
					ddb.setData_no(rs.getInt("data_no"));
					ddb.setData_emp_no(rs.getInt("data_emp_no"));
					ddb.setData_title(rs.getString("data_title"));
					ddb.setData_content(rs.getString("data_content"));
					ddb.setData_date(rs.getString("data_date"));
					ddb.setEmp_name(rs.getString("emp_name"));
					
					dataList.add(ddb);
				}while(rs.next());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if( rs != null) try {rs.close();} catch(SQLException ex) {}
			if( pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if( conn != null) try {conn.close();} catch(SQLException ex) {}
		}
		return dataList;
	}
}
