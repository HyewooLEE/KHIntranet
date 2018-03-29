package model.notice;
import java.sql.*;
import java.util.*;

import model.project.ProjectDataBean;
  
public class NoticeDBBean { //DB와 관련된 일을 하는 클래스 : DBBean, DAO
	
	private static NoticeDBBean instance = new NoticeDBBean();

	public static NoticeDBBean getInstance() {
		return instance;
	}
	
	private NoticeDBBean() {}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	// 글 등록
	public void insertNotice(NoticeDataBean notice) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO NOTICE VALUES (NOTICE_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, notice.getNotice_emp_no());
			pstmt.setString(2, notice.getNotice_title());
			pstmt.setString(3, notice.getNotice_content());
			pstmt.setString(4, notice.getNotice_date());
			pstmt.setString(5, notice.getNotice_file_name());
			pstmt.setString(6, notice.getNotice_file_addr());
			
			pstmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
	}
	
	public void updateWork(NoticeDataBean notice) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "UPDATE NOTICE SET NOTICE_TITLE=?, NOTICE_CONTENT=?, NOTICE_DATE=?, NOTICE_FILE_NAME=?, NOTICE_FILE_ADDR=? WHERE NOTICE_NO=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, notice.getNotice_title());
			pstmt.setString(2, notice.getNotice_content());
			pstmt.setString(3, notice.getNotice_date());
			pstmt.setString(4, notice.getNotice_file_name());
			pstmt.setString(5, notice.getNotice_file_addr());
			pstmt.setInt(6, notice.getNotice_no());
							
			pstmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
	}
	
	public void deleteNotice(int notice_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM NOTICE WHERE NOTICE_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_no);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
			if(conn != null) try { conn.close(); } catch(Exception e) {}
		}
	}
	
	// 글 갯수 가지고 오기
	public int getNoticeCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int notice_count = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM NOTICE");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice_count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
			if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
			if(conn != null) try { conn.close(); } catch(Exception e) {}
		}
		return notice_count;
	}
	// 글 가지고 오기
	public NoticeDataBean getNotice(int notice_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeDataBean ndb = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement( "SELECT * FROM NOTICE WHERE NOTICE_NO = ?" );
			pstmt.setInt(1, notice_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ndb = new NoticeDataBean();
				ndb.setNotice_no(rs.getInt("notice_no"));
				ndb.setNotice_emp_no(rs.getInt("notice_emp_no"));
				ndb.setNotice_title(rs.getString("notice_title"));
				ndb.setNotice_content(rs.getString("notice_content"));
				ndb.setNotice_date(rs.getString("notice_date"));
				ndb.setNotice_file_name(rs.getString("notice_file_name"));
				ndb.setNotice_file_addr(rs.getString("notice_file_addr"));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if( rs != null) try {rs.close();} catch(SQLException ex) {}
			if( pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if( conn != null) try {conn.close();} catch(SQLException ex) {}
		}
		return ndb;
	}
	
	public List<NoticeDataBean> getNoticeList() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeDataBean> noticeList = null;
		try {
			conn = getConnection();
			String sql = "SELECT N.NOTICE_NO, N.NOTICE_EMP_NO, N.NOTICE_TITLE, N.NOTICE_CONTENT, N.NOTICE_DATE, E.EMP_NAME FROM NOTICE N, EMPLOYEE E WHERE E.EMP_NO = N.NOTICE_EMP_NO";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			noticeList = new ArrayList<NoticeDataBean>();
			if(rs.next()) {
				do {
					NoticeDataBean ndb = new NoticeDataBean();
					ndb.setNotice_no(rs.getInt("notice_no"));
					ndb.setNotice_emp_no(rs.getInt("notice_emp_no"));
					ndb.setNotice_title(rs.getString("notice_title"));
					ndb.setNotice_content(rs.getString("notice_content"));
					ndb.setNotice_date(rs.getString("notice_date"));
					ndb.setEmp_name(rs.getString("emp_name"));
					
					noticeList.add(ndb);
				}while(rs.next());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if( rs != null) try {rs.close();} catch(SQLException ex) {}
			if( pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if( conn != null) try {conn.close();} catch(SQLException ex) {}
		}
		return noticeList;
	}
	
	public List<NoticeDataBean> getNoticeMainList() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeDataBean> noticeList = null;
		try {
			conn = getConnection();
			String sql = "SELECT N.NOTICE_NO, N.NOTICE_EMP_NO, N.NOTICE_TITLE, N.NOTICE_CONTENT, N.NOTICE_DATE, E.EMP_NAME FROM NOTICE N, EMPLOYEE E WHERE E.EMP_NO = N.NOTICE_EMP_NO AND  ROWNUM >= 1 AND ROWNUM <= 5 ORDER BY NOTICE_DATE DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			noticeList = new ArrayList<NoticeDataBean>();
			if(rs.next()) {
				do {
					NoticeDataBean ndb = new NoticeDataBean();
					ndb.setNotice_no(rs.getInt("notice_no"));
					ndb.setNotice_emp_no(rs.getInt("notice_emp_no"));
					ndb.setNotice_title(rs.getString("notice_title"));
					ndb.setNotice_content(rs.getString("notice_content"));
					ndb.setNotice_date(rs.getString("notice_date"));
					ndb.setEmp_name(rs.getString("emp_name"));
					
					noticeList.add(ndb);
				}while(rs.next());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if( rs != null) try {rs.close();} catch(SQLException ex) {}
			if( pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if( conn != null) try {conn.close();} catch(SQLException ex) {}
		}
		return noticeList;
	}
	
	public List<ProjectDataBean> getProjectNameList(int pro_emp_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProjectDataBean> projectList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement( "SELECT PRO_TITLE FROM PROJECT WHERE EMP_NO = ?" );
			pstmt.setInt(1, pro_emp_no);
			rs = pstmt.executeQuery();
				
			if(rs.next());
			projectList = new ArrayList<ProjectDataBean>();
			do {
				ProjectDataBean pdb = new ProjectDataBean();
				pdb.setPro_title(rs.getString("pro_title"));
	            
				projectList.add(pdb);
			}while(rs.next());
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if( rs != null) try {rs.close();} catch(SQLException ex) {}
			if( pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if( conn != null) try {conn.close();} catch(SQLException ex) {}
		}
		return projectList;
	}
	
	public String getEmailList(int pro_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String emp_email = null;
		try {
			conn = getConnection();
			String sql = "SELECT PRO_PARTICIPANT FROM PROJECT WHERE PRO_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pro_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				emp_email = rs.getString("pro_participant");
			}
			 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if( rs != null) try {rs.close();} catch(SQLException ex) {}
			if( pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if( conn != null) try {conn.close();} catch(SQLException ex) {}
		}
		return emp_email;
	}
}
