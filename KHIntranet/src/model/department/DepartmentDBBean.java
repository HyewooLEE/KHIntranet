package model.department;
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDBBean {
	
	private static DepartmentDBBean instance = new DepartmentDBBean();
	
	public static DepartmentDBBean getInstance() {
		return instance;
	}
	
	private DepartmentDBBean() {}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	 
	//dept 다찾아오기
	public List selectDept() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		List dept =null;

		try {
			conn = getConnection();
			
			String sql = "select * from department order by dept_no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dept = new ArrayList();
				do {
					DepartmentDataBean article = new DepartmentDataBean();
					article.setDept_no(rs.getInt("dept_no"));
					article.setDept_name(rs.getString("dept_name"));

					dept.add(article);
				}while(rs.next());
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs !=null) try {rs.close();} catch(SQLException ex) {}
			if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
			if(conn !=null)try {conn.close();}catch(SQLException ex) {}
		}
		return dept;
	}
	
		//insert
			public void insertDept(int dept_no, String dept_name) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				
				try {
					
					
					conn = getConnection();
					
					//DriverManager.getConnection(jdbc:apache:commons:dbcp:pool);
					String sql = "insert into DEPARTMENT values(?,?)";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, dept_no);
					pstmt.setString(2, dept_name);
				
					pstmt.executeUpdate();
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
					if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
					if(conn !=null) try {conn.close();} catch(SQLException ex) {}
				}
			}

			//delete
			public void deleteDept(int dept_no) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				
				try {
					
					
					conn = getConnection();
					
					//DriverManager.getConnection(jdbc:apache:commons:dbcp:pool);
					String sql = "DELETE FROM DEPARTMENT where dept_no= ? ";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, dept_no);
				
					pstmt.executeUpdate();
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
					if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
					if(conn !=null) try {conn.close();} catch(SQLException ex) {}
				}
			}
			
			//찾기
			public String getDept_name(int dept_no) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				String dept_name = "";
				ResultSet rs =null;
				
				try {
					
					
					conn = getConnection();
					
					//DriverManager.getConnection(jdbc:apache:commons:dbcp:pool);

					String sql = "select * from department where dept_no=? ";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, dept_no);
				
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						dept_name = rs.getString("dept_name");
					}
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
					if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
					if(conn !=null) try {conn.close();} catch(SQLException ex) {}
				}
				return dept_name;
			}
}
