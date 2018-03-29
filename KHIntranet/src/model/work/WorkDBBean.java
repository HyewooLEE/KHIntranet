package model.work;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.project.ProjectDataBean;
  
public class WorkDBBean { //DB와 관련된 일을 하는 클래스 : DBBean, DAO
	
	private static WorkDBBean instance = new WorkDBBean();

	public static WorkDBBean getInstance() {
		return instance;
	}
	
	private WorkDBBean() {}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	// 글 등록
	public void insertWork(WorkDataBean work, ProjectDataBean project) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			/*System.out.println("work_emp_no : "+ work.getWork_emp_no());
			System.out.println("work_ct_pb : "+ work.getWork_ct_pb());
			System.out.println("work_ct_sg : "+ work.getWork_ct_sg());
			System.out.println("work_date : "+ work.getWork_date());
			System.out.println("work_file_name : "+ work.getWork_file_name());
			System.out.println("work_file_addr : "+ work.getWork_file_addr());
			System.out.println("pro_no : "+ project.getPro_no());*/
			
			String sql = "INSERT INTO WORK VALUES (WORK_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, work.getWork_emp_no());
			pstmt.setString(2, work.getWork_ct_pb());
			pstmt.setString(3, work.getWork_ct_sg());
			pstmt.setString(4, work.getWork_date());
			pstmt.setString(5, work.getWork_file_name());
			pstmt.setString(6, work.getWork_file_addr());
			pstmt.setString(7, work.getPro_percent());
			pstmt.setInt(8, project.getPro_no());
			pstmt.executeUpdate();
			
			updateProject(project);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
	}
	
	public void updateProject(ProjectDataBean project) throws Exception {
		System.out.println("updateProject..");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "UPDATE PROJECT SET PRO_PERCENT=? WHERE PRO_NO=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, project.getPro_percent());
			pstmt.setInt(2, project.getPro_no());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
	}
	
	public void updateWork(WorkDataBean work, ProjectDataBean project) throws Exception{
		System.out.println("update work...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "UPDATE WORK SET WORK_EMP_NO=?, WORK_CT_PB=?, WORK_CT_SG=?, WORK_DATE=?, WORK_FILE_NAME=?, WORK_FILE_ADDR=?, PRO_PERCENT=?, PRO_NO=? WHERE WORK_NO=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, work.getWork_emp_no());
			pstmt.setString(2, work.getWork_ct_pb());
			pstmt.setString(3, work.getWork_ct_sg());
			pstmt.setString(4, work.getWork_date());
			pstmt.setString(5, work.getWork_file_name());
			pstmt.setString(6, work.getWork_file_addr());
			pstmt.setString(7, work.getPro_percent());
			pstmt.setInt(8, project.getPro_no());
			pstmt.setInt(9, work.getWork_no());
							
			pstmt.executeUpdate();
			
			updateProject(project);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
	}
	
	public void deleteWork(int work_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM WORK WHERE WORK_NO = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, work_no);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) { conn.close(); }
			if(pstmt != null) { pstmt.close(); }
		}
	}
	
	// 글 갯수 가지고 오기
	public int getWorkCount(int work_emp_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM WORK WHERE WORK_EMP_NO = ?");
			pstmt.setInt(1, work_emp_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
			if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
			if(conn != null) try { conn.close(); } catch(Exception e) {}
		}
		return count;
	}
	// 글 가지고 오기
	public WorkDataBean getWork(int work_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WorkDataBean wdb = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement( "SELECT WORK_NO, WORK_EMP_NO, WORK_CT_PB, WORK_CT_SG, WORK_DATE, WORK_FILE_NAME, WORK_FILE_ADDR, P.PRO_TITLE, P.PRO_NO, P.PRO_PERCENT, P.PRO_PARTICIPANT FROM WORK W, PROJECT P WHERE W.WORK_NO = ? AND W.PRO_NO = P.PRO_NO" );
			pstmt.setInt(1, work_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				wdb = new WorkDataBean();
				wdb.setWork_no(rs.getInt("work_no"));
				wdb.setWork_emp_no(rs.getInt("work_emp_no"));
				wdb.setWork_ct_pb(rs.getString("work_ct_pb"));
				wdb.setWork_ct_sg(rs.getString("work_ct_sg"));
				wdb.setWork_date(rs.getString("work_date"));
				wdb.setWork_file_name(rs.getString("work_file_name"));
				wdb.setWork_file_addr(rs.getString("work_file_addr"));
				wdb.setPro_title(rs.getString("pro_title"));
				wdb.setPro_no(rs.getInt("pro_no"));
				wdb.setPro_percent(rs.getString("pro_percent"));
				wdb.setPro_participant(rs.getString("pro_participant"));
				/*System.out.println("work_no : "+ wdb.getWork_no());
				System.out.println("work_emp_no : "+ wdb.getWork_emp_no());
				System.out.println("work_ct_pb : "+ wdb.getWork_ct_pb());
				System.out.println("work_ct_sg : "+ wdb.getWork_ct_sg());
				System.out.println("work_date : "+ wdb.getWork_date());
				System.out.println("work_file_name : "+ wdb.getWork_file_name());
				System.out.println("work_file_addr : "+ wdb.getWork_file_addr());
				System.out.println("pro_title : "+ wdb.getPro_title());
				System.out.println("pro_percent : "+ wdb.getPro_percent());*/
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if( rs != null) try {rs.close();} catch(SQLException ex) {}
			if( pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if( conn != null) try {conn.close();} catch(SQLException ex) {}
		}
		return wdb;
	}
	
	public List<WorkDataBean> getWorkList(int work_emp_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<WorkDataBean> workList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement( "SELECT W.WORK_NO, W.WORK_EMP_NO, W.WORK_CT_PB, W.WORK_CT_SG, W.WORK_DATE, W.WORK_FILE_NAME, W.WORK_FILE_ADDR, W.PRO_PERCENT, P.PRO_TITLE, P.PRO_NO "
										   + "FROM WORK W, PROJECT P "
										   + "WHERE W.WORK_EMP_NO = ? AND W.PRO_NO = P.PRO_NO" );
			pstmt.setInt(1, work_emp_no);
			rs = pstmt.executeQuery();
			workList = new ArrayList<WorkDataBean>();
			if(rs.next()) {
				do {
					WorkDataBean wdb = new WorkDataBean();
					wdb.setWork_no(rs.getInt("work_no"));
					wdb.setWork_emp_no(rs.getInt("work_emp_no"));
					wdb.setWork_ct_pb(rs.getString("work_ct_pb"));
					wdb.setWork_ct_sg(rs.getString("work_ct_sg"));
					wdb.setWork_date(rs.getString("work_date"));
					wdb.setWork_file_name(rs.getString("work_file_name"));
					wdb.setWork_file_addr(rs.getString("work_file_addr"));
					wdb.setPro_percent(rs.getString("pro_percent"));
					wdb.setPro_title(rs.getString("pro_title"));
					wdb.setPro_no(rs.getInt("pro_no"));
			
					workList.add(wdb);
				}while(rs.next());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if( rs != null) try {rs.close();} catch(SQLException ex) {}
			if( pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if( conn != null) try {conn.close();} catch(SQLException ex) {}
		}
		return workList;
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
