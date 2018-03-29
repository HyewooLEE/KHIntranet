package model.mypage.approval;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.approval.attendance.AttendanceDataBean;
import model.approval.purchase.PurchaseDataBean;

public class ApprovalDBBean {
	
	private static ApprovalDBBean instance = new ApprovalDBBean();

	public static ApprovalDBBean getInstance() {
		return instance;
	}
	
	private ApprovalDBBean() {}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	//근태결재신청 update
    public void updateAttendApproval(String atd_receiver, String atd_status_ny, int atd_no) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement("update ATTENDANCE SET atd_receiver=?, atd_status_ny=? where atd_no=?");
            pstmt.setString(1, atd_receiver);
            pstmt.setString(2, atd_status_ny);
            pstmt.setInt(3, atd_no);
            
            pstmt.executeUpdate();           

        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    }
    
    
    //물품구매결재신청 update
    public void updatePurchaseApproval(String pur_receiver, String pur_status_ny, int pur_no) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement("update PURCHASE SET pur_receiver=?, pur_status_ny=? where pur_no=?");
            pstmt.setString(1, pur_receiver);
            pstmt.setString(2, pur_status_ny);
            pstmt.setInt(3, pur_no);
            
            pstmt.executeUpdate();           

        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    }
    
 
    //근태신청 글상세보기
  	public AttendanceDataBean getAttendArticle(int atd_no) throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		AttendanceDataBean article = null;
  		
  		try {
  			conn = getConnection();
  			
  			pstmt = conn.prepareStatement("select * from ATTENDANCE where atd_no = ?");
  			pstmt.setInt(1, atd_no);
  			rs = pstmt.executeQuery();
  			
  			if(rs.next()) {
  				article = new AttendanceDataBean();
  				article.setAtd_no(rs.getInt("atd_no"));
  				article.setAtd_emp_no(rs.getInt("atd_emp_no"));
  				article.setAtd_date(rs.getString("atd_date").substring(0,10));
  				article.setAtd_receiver(rs.getString("atd_receiver"));
  				article.setAtd_div(rs.getString("atd_div"));
  				article.setAtd_start_date(rs.getString("atd_start_date").substring(0,10));
  				if(rs.getString("atd_end_date") != null) {
  					article.setAtd_end_date(rs.getString("atd_end_date").substring(0,10));
  				}else {
  					article.setAtd_end_date(rs.getString("atd_end_date"));
  				}
  				article.setAtd_note(rs.getString("atd_note"));
  				article.setAtd_status_ny(rs.getString("atd_status_ny"));
  				article.setAtd_file_name(rs.getString("atd_file_name"));
  				article.setAtd_file_path(rs.getString("atd_file_path"));
  				article.setDoc_no(rs.getInt("doc_no"));
  			
  			}
  		}catch(Exception ex) {
  			ex.printStackTrace();
  		}finally {
  			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
  	        if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  	        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  		}
  		return article;
  		
  	}
  	
  	
  	//근태신청 글 삭제
    public void deleteAttendArticle(int atd_no) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        
        try {
        	conn = getConnection();
           
			pstmt = conn.prepareStatement("delete from ATTENDANCE where ATD_NO=?");
	        pstmt.setInt(1, atd_no);
	        pstmt.executeUpdate();
            
        } catch(Exception ex) {
            ex.printStackTrace();
            
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    }	
  	
  	
  	
  	//물품구매신청서 글 상세보기
  	public PurchaseDataBean getPurArticle(int pur_no) throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		PurchaseDataBean article = null;
  		
  		try {
  			conn = getConnection();
  			
  			pstmt = conn.prepareStatement("select * from PURCHASE where pur_no = ?");
  			pstmt.setInt(1, pur_no);
  			rs = pstmt.executeQuery();
  			
  			if(rs.next()) {
  				article = new PurchaseDataBean();
  				article.setPur_no(rs.getInt("pur_no"));
  				article.setPur_title(rs.getString("pur_title"));
  				article.setPur_emp_no(rs.getInt("pur_emp_no"));
  				article.setPur_date(rs.getString("pur_date").substring(0,10));
  				article.setPur_receiver(rs.getString("pur_receiver"));
  				article.setPur_name(rs.getString("pur_name"));
  				article.setPur_standard(rs.getString("pur_standard"));
  				article.setPur_quan(rs.getString("pur_quan"));
  				article.setPur_price(rs.getInt("pur_price"));
  				article.setPur_sum(rs.getInt("pur_sum"));
  				article.setPur_use(rs.getString("pur_use"));
  				article.setPur_note(rs.getString("pur_note"));
  				article.setPur_status_ny(rs.getString("pur_status_ny"));
  				article.setPur_file_name(rs.getString("pur_file_name"));
  				article.setPur_file_path(rs.getString("pur_file_path"));
  			
  			}
  		}catch(Exception ex) {
  			ex.printStackTrace();
  		}finally {
  			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
  	        if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  	        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  		}
  		return article;
  	}
  	
  	
  	//근태신청 글 삭제
    public void deletePurchaseArticle(int pur_no) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        try {
        	conn = getConnection();
           
			pstmt = conn.prepareStatement("delete from PURCHASE where PUR_NO=?");
	        pstmt.setInt(1, pur_no);
	        pstmt.executeUpdate();
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    }
    
  	
    
}
