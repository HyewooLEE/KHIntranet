package model.approval.attendance;
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.employee.EmployeeDataBean;

public class AttendanceDBBean {

	private static AttendanceDBBean instance = new AttendanceDBBean();

	public static AttendanceDBBean getInstance() {
		return instance;
	}

	private AttendanceDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}


	// employee 전체 찾아오기
	public List<EmployeeDataBean> selectEmp() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmployeeDataBean> empList = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from employee order by position_rank");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				empList = new ArrayList<EmployeeDataBean>();
				do {
					EmployeeDataBean article = new EmployeeDataBean();

					article.setEmp_no(rs.getInt("emp_no"));
					article.setEmp_id(rs.getString("emp_id"));
					article.setEmp_name(rs.getString("emp_name"));
					article.setEmp_pw(rs.getString("emp_pw"));
					article.setEmp_gender(rs.getString("emp_gender"));
					article.setEmp_jumin1(rs.getString("emp_jumin1"));
					article.setEmp_addr1(rs.getString("emp_addr1"));
					article.setEmp_addr2(rs.getString("emp_addr2"));
					article.setEmp_pn(rs.getString("emp_pn"));
					article.setEmp_email(rs.getString("emp_email"));
					article.setEmp_date(rs.getString("emp_date"));
					article.setEmp_resign(rs.getString("emp_resign"));
					article.setPosition_rank(rs.getInt("position_rank"));
					article.setEmp_sal(rs.getInt("emp_sal"));
					article.setEmp_pt_name(rs.getString("emp_pt_name"));
					article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
					article.setDept_no(rs.getInt("dept_no"));

					empList.add(article);

				} while (rs.next());

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return empList;
	}

	// writeFormPro.jsp
	public void insertAttendance(AttendanceDataBean article) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into ATTENDANCE values (ATTENDANCE_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, article.getAtd_emp_no());
			pstmt.setString(2, article.getAtd_date());
			pstmt.setString(3, article.getAtd_receiver());
			pstmt.setString(4, article.getAtd_div());
			pstmt.setString(5, article.getAtd_start_date());
			pstmt.setString(6, article.getAtd_end_date());
			pstmt.setString(7, article.getAtd_note());
			pstmt.setString(8, article.getAtd_status_ny());
			pstmt.setString(9, article.getAtd_file_name());
			pstmt.setString(10, article.getAtd_file_path());
			pstmt.setInt(11, article.getDoc_no());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

	// Attendance - Document join
	public List<AttendanceDataBean> attendDoc() throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttendanceDataBean> AttendList = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from ATTENDANCE a, DOCUMENT d where a.DOC_NO=d.DOC_NO ");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				AttendList = new ArrayList<AttendanceDataBean>();
				do {
					AttendanceDataBean article = new AttendanceDataBean();

					article.setAtd_no(rs.getInt("atd_no"));
					article.setAtd_emp_no(rs.getInt("atd_emp_no"));
					article.setAtd_date(rs.getString("atd_date").substring(0,10));
					article.setAtd_receiver(rs.getString("atd_receiver"));
					article.setAtd_div(rs.getString("atd_div"));
					article.setAtd_start_date(rs.getString("atd_start_date").substring(0,10));
					if(rs.getString("atd_end_date") != null) {
						article.setAtd_end_date(rs.getString("atd_end_date").substring(0, 10));
					}else {
						article.setAtd_end_date(rs.getString("atd_end_date"));
					}	
					article.setAtd_note(rs.getString("atd_note"));
					article.setAtd_status_ny(rs.getString("atd_status_ny"));
					article.setAtd_file_name(rs.getString("atd_file_name"));
					article.setAtd_file_path(rs.getString("atd_file_path"));
					article.setDoc_no(rs.getInt("doc_no"));	
					article.setDoc_name(rs.getString("doc_name"));
					
					AttendList.add(article);

				} while (rs.next());

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return AttendList;
	}
	
	
	//수신자 이름 가져오기
	public List<AttendanceDataBean> attendDoc(EmployeeAllDataBean emp_all) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttendanceDataBean> AttendList = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from ATTENDANCE a, DOCUMENT d where a.DOC_NO=d.DOC_NO");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				AttendList = new ArrayList<AttendanceDataBean>();

				do {
					AttendanceDataBean article = new AttendanceDataBean();

					article.setAtd_no(rs.getInt("atd_no"));
					article.setAtd_emp_no(rs.getInt("atd_emp_no"));
					article.setAtd_date(rs.getString("atd_date").substring(0,10));
				
					String parti = "";
		               
		            parti = (rs.getString("atd_receiver")).trim();
		                          
	                StringTokenizer st = new StringTokenizer(parti, ",");
	                          
	                int i = 0;
	                String name = "";
	                int stSize = st.countTokens(); //몇명
	                          
	                while(st.hasMoreTokens()) {
	                    /*int emp_no = Integer.parseInt(st.nextToken().trim());
	                    name += getEmp_name(emp_no);*/
	            		int emp_no = emp_all.getEmp_no();
	                	
	                	EmployeeAllDBBean eadb = EmployeeAllDBBean.getInstance();
	                    EmployeeAllDataBean edb = eadb.getEmp_no(Integer.parseInt(st.nextToken()));
	                    
	                    if(emp_no != edb.getEmp_no()) {
	                 	   name += edb.getEmp_name();
	                    }
	                    
	                    if(i < stSize -2) {
	                       name += " , ";
	                    }
	                    i++;        
	                }
					
					article.setAtd_names(name);
					article.setAtd_div(rs.getString("atd_receiver"));
					article.setAtd_div(rs.getString("atd_div"));
					article.setAtd_start_date(rs.getString("atd_start_date").substring(0,10));
					if(rs.getString("atd_end_date") != null) {
						article.setAtd_end_date(rs.getString("atd_end_date").substring(0, 10));
					}else {
						article.setAtd_end_date(rs.getString("atd_end_date"));
					}	
					article.setAtd_note(rs.getString("atd_note"));
					article.setAtd_status_ny(rs.getString("atd_status_ny"));
					article.setAtd_file_name(rs.getString("atd_file_name"));
					article.setAtd_file_path(rs.getString("atd_file_path"));
					article.setDoc_no(rs.getInt("doc_no"));	
					article.setDoc_name(rs.getString("doc_name"));
					
					AttendList.add(article);

				} while (rs.next());

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return AttendList;
	}
		

	// Attendance 전부 뽑아오기
	public List<AttendanceDataBean> listAttendance(int atd_emp_no) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttendanceDataBean> AttendList = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from Attendance where atd_emp_no=?");
			pstmt.setInt(1, atd_emp_no);
			rs = pstmt.executeQuery();		

			if (rs.next()) {
				AttendList = new ArrayList<AttendanceDataBean>();
				do {
					AttendanceDataBean article = new AttendanceDataBean();

					article.setAtd_no(rs.getInt("atd_no"));
					article.setAtd_emp_no(rs.getInt("atd_emp_no"));
					article.setAtd_date(rs.getString("atd_date").substring(0,10));
					article.setAtd_receiver(rs.getString("atd_receiver"));
					article.setAtd_div(rs.getString("atd_div"));
					article.setAtd_start_date(rs.getString("atd_start_date").substring(0, 10));
					if(rs.getString("atd_end_date") != null) {
						article.setAtd_end_date(rs.getString("atd_end_date").substring(0, 10));
					}else {
						article.setAtd_end_date(rs.getString("atd_end_date"));
					}					
					article.setAtd_note(rs.getString("atd_note"));
					article.setAtd_status_ny(rs.getString("atd_status_ny"));
					article.setAtd_file_name(rs.getString("atd_file_name"));
					article.setAtd_file_path(rs.getString("atd_file_path"));
					
					AttendList.add(article);

				} while (rs.next());

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return AttendList;
	}
	
	
	//근태신청 결재요청받은 전체 글 갯수
    public int getArticleCount(int emp_no) throws Exception{
    	
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
      
       int x = 0;
       try {
          conn = getConnection();
          pstmt = conn.prepareStatement("select count(*) from ATTENDANCE where substr(ATD_RECEIVER,1,instr(ATD_RECEIVER,',')-1) = ?");
          pstmt.setInt(1, emp_no);
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
   
    
     //근태신청 결재요청받은 전체 글 페이징
     public List<AttendanceDataBean> getArticles(int emp_no, int start, int end) throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<AttendanceDataBean> AttendList = null;
        String sql = "select atd_no, atd_emp_no, atd_date, atd_receiver, atd_div, atd_start_date, atd_end_date, atd_note, atd_status_ny, atd_file_name, atd_file_path, doc_no " + 
              "from (select atd_no, atd_emp_no, atd_date, atd_receiver, atd_div, atd_start_date, atd_end_date, atd_note, atd_status_ny, atd_file_name, atd_file_path, doc_no, rownum r " + 
              "from (select atd_no, atd_emp_no, atd_date, atd_receiver, atd_div, atd_start_date, atd_end_date, atd_note, atd_status_ny, atd_file_name, atd_file_path, doc_no " + 
              "from ATTENDANCE where substr(ATD_RECEIVER,1,instr(ATD_RECEIVER,',')-1)=? order by atd_no desc) order by atd_no desc ) where r >= ? and r <= ?";
      
        try {
           conn = getConnection();
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, emp_no);
           pstmt.setInt(2, start);
           pstmt.setInt(3, end);
           rs = pstmt.executeQuery();
         
           if(rs.next()) {
        	   AttendList = new ArrayList<AttendanceDataBean>(end);
              do {
            	  AttendanceDataBean article = new AttendanceDataBean();

			  	  article.setAtd_no(rs.getInt("atd_no"));
				  article.setAtd_emp_no(rs.getInt("atd_emp_no"));
				  article.setAtd_date(rs.getString("atd_date").substring(0,10));
				  article.setAtd_receiver(rs.getString("atd_receiver"));
				  article.setAtd_div(rs.getString("atd_div"));
				  article.setAtd_start_date(rs.getString("atd_start_date").substring(0, 10));
				  if(rs.getString("atd_end_date") != null) {
					  article.setAtd_end_date(rs.getString("atd_end_date").substring(0, 10));
				  }else {
					  article.setAtd_end_date(rs.getString("atd_end_date"));
				  }					
				  article.setAtd_note(rs.getString("atd_note"));
				  article.setAtd_status_ny(rs.getString("atd_status_ny"));
				  article.setAtd_file_name(rs.getString("atd_file_name"));
				  article.setAtd_file_path(rs.getString("atd_file_path"));
				
				  AttendList.add(article);
               
              }while(rs.next());
         }
      }catch(Exception e) {
           e.printStackTrace();
      }finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
      }
      return AttendList;
    }
	
	
	//연차 사용일 뽑기
    public int selectUseAnnDate(int atd_emp_no) throws Exception {
    	
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        int useAnnday = 0;
        
        try {
        	conn = getConnection();
           
			pstmt = conn.prepareStatement(
					"select sum(useAnnday) " + 
					"from (select (TO_DATE(atd_end_date, 'YYYY-MM-DD')-TO_DATE(atd_start_date, 'YYYY-MM-DD')) useAnnday "
					+ "from attendance where atd_emp_no=? and atd_status_ny='결재완료' )");
	        pstmt.setInt(1, atd_emp_no);
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()){
	        	useAnnday = rs.getInt(1);
	        }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return useAnnday;
    }
    
    
    //휴가자 뽑기
    public int countHoliyday() throws Exception {
    	
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        int countHoliday = 0;
        
        try {
        	conn = getConnection();
           
			pstmt = conn.prepareStatement("select count(*) from attendance where atd_div='연차' and atd_status_ny='결재완료' and trunc(sysdate)>=atd_start_date and trunc(sysdate)<=atd_end_date");
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()){
	        	countHoliday = rs.getInt(1);
	        }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return countHoliday;
    }
	
}
