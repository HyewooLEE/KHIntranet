package model.photo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDBBean {
	   private static LikeDBBean instance = new LikeDBBean();
	   
	   public static LikeDBBean getInstance() {
	      return instance;
	   }
	   private LikeDBBean() {
	      
	   }
	   private Connection getConnection() throws Exception{
	      String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
	      return DriverManager.getConnection(jdbcDriver);
	   }
	   
	 //���ƿ� ���� �Խù����� Ȯ���ϱ�
	   public int cofirmLike (int like_photo_no,int like_emp_no) throws Exception {
		   	  Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      try {
		         conn = getConnection();
		         pstmt = conn.prepareStatement("select * from photo_like where like_photo_no = ? and like_emp_no = ?");
		         pstmt.setInt(1, like_photo_no);
		         pstmt.setInt(2, like_emp_no);
		         rs =  pstmt.executeQuery();
		         if(rs.next()) {
		        	 return 1;
		         }else {
		        	 return 0;
		         }
		   }catch (Exception e) {
		      e.printStackTrace();
		   }finally {
		      if(rs != null) try {rs.close();} catch(SQLException e) {}
		      if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		   }
		   return -1;
		   }
	   
	   //���ƿ� ������
	   public int updateLike (int like_photo_no,int like_emp_no) throws Exception {
		   	  Connection conn = null;
		      PreparedStatement pstmt = null;
		      try {
		         conn = getConnection();
		         pstmt = conn.prepareStatement("insert into photo_like values ( ? , ? )");
		         pstmt.setInt(1, like_photo_no);
		         pstmt.setInt(2, like_emp_no);
		         return pstmt.executeUpdate();
		   }catch (Exception e) {
		      e.printStackTrace();
		   }finally {
		      if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		   }
		   return -1;
		   }
	   //���ƿ� ����ϱ�
	   public int deleteLike (int like_photo_no,int like_emp_no) throws Exception {
		   	  Connection conn = null;
		      PreparedStatement pstmt = null;
		      try {
		         conn = getConnection();
		         pstmt = conn.prepareStatement("delete photo_like where like_photo_no = ? and like_emp_no = ?");
		         pstmt.setInt(1, like_photo_no);
		         pstmt.setInt(2, like_emp_no);
		         return pstmt.executeUpdate();
		   }catch (Exception e) {
		      e.printStackTrace();
		   }finally {
		      if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		   }
		   return -1;
		   }
	   
	   
	   
	   //���ƿ� ����
	   public int getLikeCount(int like_photo_no) throws Exception{
		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      
		      int x = 0;
		      try {
		         conn = getConnection();
		         pstmt = conn.prepareStatement("select count(*) from photo_like where like_photo_no = ?");
		         pstmt.setInt(1, like_photo_no);
		         rs = pstmt.executeQuery();
		         if(rs.next()) {
		            x = rs.getInt(1);
		         }
		   }catch (Exception e) {
		      e.printStackTrace();
		   }finally {
		      if(rs != null) try {rs.close();} catch(SQLException e) {}
		      if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		   }
		   return x;
		   }
	   
	   //���ƿ� �������
	   public int deleteLikeAll(int photo_no)throws Exception{
     		Connection conn = null;
     		PreparedStatement pstmt = null;
     		try {
     			conn = getConnection();
     			String sql = "delete from photo_like where like_photo_no= ?";
     			pstmt = conn.prepareStatement(sql);
     			pstmt.setInt(1, photo_no);
     			return pstmt.executeUpdate();
     		}catch (Exception ex) {
     			ex.printStackTrace();
     		}finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
     		}
     		return -1;
     	}
	   
	
}
