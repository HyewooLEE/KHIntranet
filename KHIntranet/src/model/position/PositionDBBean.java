package model.position;
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PositionDBBean {
	
	private static PositionDBBean instance = new PositionDBBean();
	
	public static PositionDBBean getInstance() {
		return instance;
	}
	
	private PositionDBBean() {}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	//POSITION 다찾아오기
			public List selectPosition() throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs =null;
				List postion =null;
				try {
					conn = getConnection();
					
					String sql = "select * from POSITION order by position_rank";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						postion = new ArrayList();
						do {
							PositionDataBean article = new PositionDataBean();
							article.setPosition_rank(rs.getInt("position_rank"));
							article.setPosition_name(rs.getString("position_name"));
							
							postion.add(article);
						}while(rs.next());
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
					if(rs !=null) try {rs.close();} catch(SQLException ex) {}
					if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
					if(conn !=null)try {conn.close();}catch(SQLException ex) {}
				}
				return postion;
			}
		

			//insert
			public void insertPosition(int position_rank, String position_name) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				
				try {
					
					
					conn = getConnection();
					
					//DriverManager.getConnection(jdbc:apache:commons:dbcp:pool);
					String sql = "insert into Position values(?,?)";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, position_rank);
					pstmt.setString(2, position_name);

					pstmt.executeUpdate();
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
					if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
					if(conn !=null) try {conn.close();} catch(SQLException ex) {}
				}
			}
			
			
			
			
			//delete
			public void deletePosition(int position_rank) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				
				try {
					
					
					conn = getConnection();
					
					//DriverManager.getConnection(jdbc:apache:commons:dbcp:pool);
					String sql = "delete from position where position_rank=?";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, position_rank);

				
					pstmt.executeUpdate();
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
					if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
					if(conn !=null) try {conn.close();} catch(SQLException ex) {}
				}
			}
			
			//찾기
			public String getPosition_name(int position_rank) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				String position_name = "";
				ResultSet rs =null;
				
				try {
					
					
					conn = getConnection();
					
					//DriverManager.getConnection(jdbc:apache:commons:dbcp:pool);

					String sql = "select * from position where position_rank=? ";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, position_rank);
				
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						position_name = rs.getString("position_name");
					}
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
					if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
					if(conn !=null) try {conn.close();} catch(SQLException ex) {}
				}
				return position_name;
			}

}
