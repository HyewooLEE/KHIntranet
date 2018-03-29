package model.project;
import java.sql.*;
import java.util.*;

import model.department.DepartmentDataBean;
 
public class ProjectDBBean { //DB와 관련된 일을 하는 클래스 : DBBean, DAO
	
	private static ProjectDBBean instance = new ProjectDBBean();

	public static ProjectDBBean getInstance() {
		return instance;
	}
	
	private ProjectDBBean() {}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	// 글 등록
	public void insertProject(ProjectDataBean project) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try {
			conn = getConnection();
			
			String sql = "INSERT INTO PROJECT VALUES (PROJECT_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, project.getPro_emp_no());
			pstmt.setString(2, project.getPro_title());
			pstmt.setString(3, project.getPro_content());
			pstmt.setString(4, project.getPro_file_name());
			pstmt.setString(5, project.getPro_file_addr());
			pstmt.setString(6, project.getPro_start_date());
			pstmt.setString(7, project.getPro_end_date());
			pstmt.setString(8, project.getPro_date());
			pstmt.setString(9, project.getPro_percent());
			pstmt.setString(10, project.getPro_participant());
			pstmt.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
	}
	// 글 수정
	public void updateProject(ProjectDataBean project) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "UPDATE PROJECT SET PRO_EMP_NO=?, PRO_TITLE=?, PRO_CONTENT=?, PRO_FILE_NAME=?, PRO_FILE_ADDR=?, PRO_START_DATE=?, PRO_END_DATE=?, PRO_DATE=?,PRO_PERCENT=? WHERE PRO_NO=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, project.getPro_emp_no());
			pstmt.setString(2, project.getPro_title());
			pstmt.setString(3, project.getPro_content());
			pstmt.setString(4, project.getPro_file_name());
			pstmt.setString(5, project.getPro_file_addr());
			pstmt.setString(6, project.getPro_start_date());
			pstmt.setString(7, project.getPro_end_date());
			pstmt.setString(8, project.getPro_date());
			pstmt.setString(9, project.getPro_percent());	
			pstmt.setInt(10, project.getPro_no());
			pstmt.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
	}
	
	public void deleteProject(int pro_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM PROJECT WHERE PRO_NO = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pro_no);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) { conn.close(); }
			if(pstmt != null) { pstmt.close(); }
		}
	}
	// 글 목록 가져오기
	public List<ProjectDataBean> getProjectList(int pro_emp_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProjectDataBean> projectList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT P.PRO_NO, P.PRO_EMP_NO, E.EMP_NAME, P.PRO_TITLE, P.PRO_CONTENT, P.PRO_FILE_NAME, P.PRO_FILE_ADDR, P.PRO_DATE, P.PRO_START_DATE, P.PRO_END_DATE, P.PRO_PERCENT FROM PROJECT P, EMPLOYEE E WHERE P.PRO_EMP_NO = ? AND P.PRO_EMP_NO = E.EMP_NO");
			pstmt.setInt(1, pro_emp_no);
			rs = pstmt.executeQuery();
				
			if(rs.next());
			
			projectList = new ArrayList<ProjectDataBean>();
			
			do {
				ProjectDataBean pdb = new ProjectDataBean();
				pdb.setPro_no(rs.getInt("pro_no"));
				pdb.setPro_emp_no(rs.getInt("pro_emp_no"));
				pdb.setEmp_name(rs.getString("emp_name"));
				pdb.setPro_title(rs.getString("pro_title"));
				pdb.setPro_content(rs.getString("pro_content"));
				pdb.setPro_file_name(rs.getString("pro_file_name"));
				pdb.setPro_file_addr(rs.getString("pro_file_addr"));
				pdb.setPro_date(rs.getString("pro_date"));
				pdb.setPro_start_date(rs.getString("pro_start_date"));
				pdb.setPro_end_date(rs.getString("pro_end_date"));
				pdb.setPro_percent(rs.getString("pro_percent"));
					          
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
	// 글 갯수 가지고 오기
	public int getCount(int pro_emp_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM PROJECT WHERE PRO_EMP_NO = ?");
			pstmt.setInt(1, pro_emp_no);
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
	public ProjectDataBean getProject(int pro_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProjectDataBean pdb = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement( "SELECT * FROM PROJECT WHERE PRO_NO = ?" );
			pstmt.setInt(1, pro_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pdb = new ProjectDataBean();
				pdb.setPro_no(rs.getInt("pro_no"));
				pdb.setPro_emp_no(rs.getInt("pro_emp_no"));
				pdb.setPro_title(rs.getString("pro_title"));
				pdb.setPro_content(rs.getString("pro_content"));
				pdb.setPro_file_name(rs.getString("pro_file_name"));
				pdb.setPro_file_addr(rs.getString("pro_file_addr"));
				pdb.setPro_start_date(rs.getString("pro_start_date"));
				pdb.setPro_end_date(rs.getString("pro_end_date"));
				pdb.setPro_date(rs.getString("pro_date"));
				pdb.setPro_percent(rs.getString("pro_percent"));
				pdb.setPro_participant(rs.getString("pro_participant"));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if( rs != null) try {rs.close();} catch(SQLException ex) {}
			if( pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if( conn != null) try {conn.close();} catch(SQLException ex) {}
		}
		return pdb;
	}
	
	// dept 전체 찾아오기
	   public List<DepartmentDataBean> getDeptList() throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List<DepartmentDataBean> deptList = null;

	      try {
	         conn = getConnection();

	         String sql = "select * from department";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();

	         if (rs.next()) {
	            deptList = new ArrayList<DepartmentDataBean>();
	            do {
	               DepartmentDataBean ddb = new DepartmentDataBean();
	               ddb.setDept_no(rs.getInt("dept_no"));
	               ddb.setDept_name(rs.getString("dept_name"));
	               deptList.add(ddb);

	            } while (rs.next());

	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         if (rs != null) try { rs.close(); } catch (SQLException ex) {}
	         if (pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
	         if (conn != null) try { conn.close(); } catch (SQLException ex) {}
	      }
	      return deptList;
	   }
	   
	   public List<ProjectDataBean> getProjectList(int pro_emp_no, int startRow, int endRow) throws Exception{
		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      List<ProjectDataBean> projectList = null;

		      try {
		         conn = getConnection();
		         pstmt = conn.prepareStatement("SELECT P.PRO_PERCENT, P.PRO_TITLE FROM (SELECT ROWNUM RNUM, R.* FROM(SELECT * FROM PROJECT WHERE PRO_EMP_NO = ?) R WHERE ROWNUM <= ?) P WHERE RNUM >= ?");
		         pstmt.setInt(1, pro_emp_no);
		         pstmt.setInt(2, endRow);
		         pstmt.setInt(3, startRow);
		         rs = pstmt.executeQuery();
		            
		         if(rs.next());
		         
		         projectList = new ArrayList<ProjectDataBean>();
		         
		         do {
		            ProjectDataBean pdb = new ProjectDataBean();
		            pdb.setPro_title(rs.getString("pro_title"));
		            pdb.setPro_percent(rs.getString("pro_percent"));
		                         
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
}
