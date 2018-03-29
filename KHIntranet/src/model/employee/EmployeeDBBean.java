package model.employee;
  
import java.sql.*;
import java.util.*;

import jdbc.JdbcUtil;
import model.employee.EmployeeDataBean;

public class EmployeeDBBean {  //DB와 관련된 일을 하는 클래스 : DBBean, DAO
   
   private static EmployeeDBBean instance = new EmployeeDBBean();

   
   //LogonDBBean.getInstance();   싱글턴패턴
   public static EmployeeDBBean getInstance() {
      return instance;
   }
   
   private EmployeeDBBean() {}
   
   private Connection getConnection() throws Exception{
      String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
      return DriverManager.getConnection(jdbcDriver);
   }
   
   
   
 //modifyPro.jsp
   public int updateMember(String emp_id,String emp_name, String emp_pw,String emp_pn,String emp_email,String emp_addr1,String emp_addr2,String emp_addr3) throws Exception{
      Connection conn = null;
      PreparedStatement pstmt = null;
      int x =0;
      try {
         conn = getConnection();
         String sql ="update EMPLOYEE set emp_name=?, emp_pw=?,  emp_email=?, emp_pn=?, emp_addr1=?, emp_addr2=?, emp_addr3=? where emp_id=?";
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, emp_name);
         pstmt.setString(2, emp_pw);
         pstmt.setString(3, emp_email);
         pstmt.setString(4, emp_pn);
         pstmt.setString(5, emp_addr1);
         pstmt.setString(6, emp_addr2);
         pstmt.setString(7, emp_addr3);
         pstmt.setString(8, emp_id);
         x = pstmt.executeUpdate();
         
      }catch(Exception ex) {
         ex.printStackTrace();
      }finally {
         if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
         if(conn !=null)try {conn.close();}catch(SQLException ex) {}
      }
      return x;
   }
   
   //주소 찾기
   public Vector zipcodeRead(String area4) {
      Connection  con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      Vector vecList = new Vector();
      
      try {
         con = getConnection();                    //like 와일트카드 %포함한것 찾기
         String strQuery = "select * from zipcode where area4 like '"+area4 +"%'";
         pstmt = con.prepareStatement(strQuery);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            /*ZipcodeBean tempZipcode = new ZipcodeBean();
            tempZipcode.setZipcode(rs.getString("zipcode"));
            tempZipcode.setArea1(rs.getString("area1"));
            tempZipcode.setArea2(rs.getString("area2"));
            tempZipcode.setArea3(rs.getString("area3"));
            tempZipcode.setArea4(rs.getString("area4"));
            vecList.addElement(tempZipcode);*/
         }
      }catch(Exception ex) {
         ex.printStackTrace();
      }finally {
         if(rs !=null) try {rs.close();} catch(SQLException ex) {}
         if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
         if(con !=null)try {con.close();}catch(SQLException ex) {}
      }
      return vecList;     //size=0 저장한적 없음
   }
   
   
   
   
      
      //프로필사진 업로드
      public int uploadProfile(String fileName , String emp_pt_addr ,int emp_no) {
    	  Connection conn= null;
    	  PreparedStatement pstmt = null;
    	  try {
    		  conn =getConnection();
    		  String sql = "UPDATE EMPLOYEE SET EMP_PT_NAME = ? , EMP_PT_ADDR = ?  WHERE EMP_NO=?";
    		  pstmt = conn.prepareStatement(sql);
    		  pstmt.setString(1, fileName);
    		  pstmt.setString(2, emp_pt_addr);
    		  pstmt.setInt(3, emp_no);
    		  return pstmt.executeUpdate();
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	  }finally {
    		  JdbcUtil.close(pstmt);
    		  JdbcUtil.close(conn);
    	  }
    	  return  -1;
      }

  	//입력
  	//inputProAction.java
  	public void insertEmployee(EmployeeDataBean member) throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		
  		try {
  			
  			
  			conn = getConnection();
  			
  			//DriverManager.getConnection(jdbc:apache:commons:dbcp:pool);
  			String sql = "insert into EMPLOYEE values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  			pstmt = conn.prepareStatement(sql);
  			
  			pstmt.setInt(1, member.getEmp_no());
  			pstmt.setString(2, member.getEmp_id());
  			pstmt.setString(3, member.getEmp_name());
  			pstmt.setString(4, member.getEmp_pw());
  			pstmt.setString(5, member.getEmp_gender());
  			pstmt.setString(6, member.getEmp_jumin1());
  			pstmt.setString(7, member.getEmp_addr1());
  			pstmt.setString(8, member.getEmp_addr2());
  			pstmt.setString(9, member.getEmp_addr3());
  			pstmt.setString(10, member.getEmp_email());
  			pstmt.setString(11, member.getEmp_pn());
  			pstmt.setString(12, member.getEmp_date());
  			pstmt.setString(13, member.getEmp_resign());
  			pstmt.setInt(14, member.getPosition_rank());
  			pstmt.setInt(15, member.getEmp_sal());
  			pstmt.setString(16, member.getEmp_pt_name());
  			pstmt.setString(17, member.getEmp_pt_addr());
  			pstmt.setInt(18, member.getDept_no());
  			pstmt.setInt(19, member.getEmp_annual());
  		
  			pstmt.executeUpdate();
  			
  		}catch(Exception ex) {
  			ex.printStackTrace();
  		}finally {
  			if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
  			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
  		}
  	}
  	
  	//employee 전체 찾아오기
  	   public List selectEmp() throws Exception {
  	      Connection conn = null;
  	      PreparedStatement pstmt = null;
  	      ResultSet rs = null;
  	      List empList = null;

  	      try {
  	         conn = getConnection();

  	         pstmt = conn.prepareStatement("select * from employee order by position_rank");
  	         rs = pstmt.executeQuery();

  	         if (rs.next()) {
  	            empList = new ArrayList();
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
  	               article.setEmp_addr3(rs.getString("emp_addr3"));
  	               article.setEmp_pn(rs.getString("emp_pn"));
  	               article.setEmp_email(rs.getString("emp_email"));
  	               article.setEmp_date(rs.getString("emp_date"));
  	               article.setEmp_resign(rs.getString("emp_resign"));
  	               article.setPosition_rank(rs.getInt("position_rank"));
  	               article.setEmp_sal(rs.getInt("emp_sal"));
  	               article.setEmp_pt_name(rs.getString("emp_pt_name"));
  	               article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
  	               article.setDept_no(rs.getInt("dept_no"));
  	               article.setEmp_annual(rs.getInt("emp_annual"));

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
  	
  	//사원번호 존재여분 검사
  	//inputProAction.java
  	public int userEmp_no(int emp_no) throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		int db_no =0; //사원번호 존재확인
  		int x =1;
  		
  		try {
  			conn = getConnection();
  			String sql = "select emp_no from EMPLOYEE";
  			pstmt = conn.prepareStatement(sql);
  			rs = pstmt.executeQuery();
  			
  			while(rs.next()) {
  				EmployeeDataBean dto = new EmployeeDataBean();
  				dto.setEmp_no(rs.getInt("emp_no"));
  				if(emp_no == rs.getInt("emp_no")) {
  					x=0;
  					break;
  				}
  				}
  		}catch(Exception ex) {
  			ex.printStackTrace();
  		}finally {
  			if(rs !=null) try {rs.close();}catch(SQLException ex) {}
  			if(pstmt !=null) try {pstmt.close();}catch(SQLException ex) {}
  			if(conn !=null) try {conn.close();}catch(SQLException ex) {}
  		}
  		return x;
  	}
  	
  	
  	//로그인
  	//loginPro.jsp
  	public int userCheck(String emp_id, String emp_pw) throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		int dbrank =0; //직급이 과장 이상만 로그인이 가능하다.
  		String dbpasswd ="";
  		int x =-1;
  		
  		try {
  			conn = getConnection();
  			String sql = "select emp_pw from EMPLOYEE where emp_id = ?";
  			pstmt = conn.prepareStatement(sql);
  			pstmt.setString(1, emp_id);
  			rs = pstmt.executeQuery();					
  			
  			if(rs.next()) {
  				dbpasswd = rs.getString("emp_pw");
  				if(dbpasswd.equals(emp_pw)) {  //비밀번호 비교
  					
  					sql = "select POSITION_RANK from EMPLOYEE where emp_id =?";
  					pstmt = conn.prepareStatement(sql);
  					pstmt.setString(1, emp_id);
  					rs = pstmt.executeQuery();
  					if(rs.next()){
  						dbrank = rs.getInt(1);
  						if(dbrank==0) {
  							x = 1; //승인 대기자 입니다.
  						}else {
  							x=2;   //로그인 가능
  						}
  					}

  				}else {
  					x=0;  //비밀번호 틀립
  				}
  				
  			}else {
  				x=-1; //해당아이디 없음
  			}
  		}catch(Exception ex) {
  			ex.printStackTrace();
  		}finally {
  			if(rs !=null) try {rs.close();}catch(SQLException ex) {}
  			if(pstmt !=null) try {pstmt.close();}catch(SQLException ex) {}
  			if(conn !=null) try {conn.close();}catch(SQLException ex) {}
  		}
  		return x;
  	}
  	
  	
  	//nav로 넘아갈때 가져갈것 들
  	//id값 가지고 이름, 직급, 부서,
  	//LoginAction.java, loginPro.jsp
  	public EmployeeDataBean getEmp_id(String emp_id) throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		EmployeeDataBean article = null;
  		
  		try {
  			conn = getConnection();
  			
  			pstmt = conn.prepareStatement("select * from employee where emp_id = ?"); //게시물의 내용을 불러온다.
  			pstmt.setString(1, emp_id);
  			rs = pstmt.executeQuery();
  			
  			if(rs.next()) {
  				article = new EmployeeDataBean();
  				
  				article.setEmp_no(rs.getInt("emp_no"));
  	               article.setEmp_id(rs.getString("emp_id"));
  	               article.setEmp_name(rs.getString("emp_name"));
  	               article.setEmp_pw(rs.getString("emp_pw"));
  	               article.setEmp_gender(rs.getString("emp_gender"));
  	               article.setEmp_jumin1(rs.getString("emp_jumin1"));
  	               article.setEmp_addr1(rs.getString("emp_addr1"));
  	               article.setEmp_addr2(rs.getString("emp_addr2"));
  	               article.setEmp_addr3(rs.getString("emp_addr3"));
  	               article.setEmp_pn(rs.getString("emp_pn"));
  	               article.setEmp_email(rs.getString("emp_email"));
  	               article.setEmp_date(rs.getString("emp_date"));
  	               article.setEmp_resign(rs.getString("emp_resign"));
  	               article.setPosition_rank(rs.getInt("position_rank"));
  	               article.setEmp_sal(rs.getInt("emp_sal"));
  	               article.setEmp_pt_name(rs.getString("emp_pt_name"));
  	               article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
  	               article.setDept_no(rs.getInt("dept_no"));
  	               article.setEmp_annual(rs.getInt("emp_annual"));
  				
  			}
  		}catch(Exception ex) {
  			ex.printStackTrace();
  		}finally {
  			if(rs !=null) try {rs.close();} catch(SQLException ex) {}
  			if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
  			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
  		}
  		return article;
  	}
  	
  	
  	
  	//회원가입할 때 아이디 찾기
  	//confirmId.jsp
  	public int confirmId(String id) throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		int x=-1;  //경우의 수
  		
  		 try {
  			 conn = getConnection();
  			 
  			 String sql = "select EMP_ID from EMPLOYEE where EMP_ID = ?";
  			 pstmt = conn.prepareStatement(sql);
  			 pstmt.setString(1, id);
  			 rs = pstmt.executeQuery();
  			 
  			 if(rs.next())
  				 x=1; //해당 아이디 있음
  			 else
  				 x=0;  //해당 아이디 없음
  		 }catch(Exception ex) {
  			 ex.printStackTrace();
  		 }finally {
  			 if(rs !=null)try {rs.close();}catch(SQLException ex) {}
  			 if(pstmt !=null) try {pstmt.close();}catch(SQLException ex) {}
  			 if(conn !=null)try {conn.close(); }catch(SQLException ex) {}
  		 }
  		 return x;
  	}
  	
  	
  		//회원가입 승인 대기자 가져오기
  			public List getArticles(int rank) throws Exception{  //게시물의 시작,게시물의 마지막지점   
  				Connection conn = null;
  				PreparedStatement pstmt = null;
  				ResultSet rs = null;
  				List articleList = null;
  				
  				String sql = "select * from EMPLOYEE where POSITION_RANK = ? ORDER BY EMP_DATE";
  				
  				try {
  					conn = getConnection();
  					
  					
  					pstmt = conn.prepareStatement(sql);
  																
  					pstmt.setInt(1, rank);
  					
  					rs = pstmt.executeQuery();
  					
  					if(rs.next()) {
  						articleList = new ArrayList();
  						
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
  				               article.setEmp_addr3(rs.getString("emp_addr3"));
  				               article.setEmp_pn(rs.getString("emp_pn"));
  				               article.setEmp_email(rs.getString("emp_email"));
  				               article.setEmp_date(rs.getString("emp_date"));
  				               article.setEmp_resign(rs.getString("emp_resign"));
  				               article.setPosition_rank(rs.getInt("position_rank"));
  				               article.setEmp_sal(rs.getInt("emp_sal"));
  				               article.setEmp_pt_name(rs.getString("emp_pt_name"));
  				               article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
  				               article.setDept_no(rs.getInt("dept_no"));
  				               article.setEmp_annual(rs.getInt("emp_annual"));
  				               
  							articleList.add(article);
  						}while(rs.next());
  					}
  				}catch(Exception ex) {
  					ex.printStackTrace();
  				}finally {
  					if(rs !=null) try {rs.close();} catch(SQLException ex) {}
  					if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
  					if(conn !=null) try {conn.close();} catch(SQLException ex) {}
  				}
  				return articleList;
  			}
  	
  	
  	//modifyForm.jsp
  	public EmployeeDataBean getMember(String id) throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs =null;
  		EmployeeDataBean member=null;
  		
  		try {
  			conn = getConnection();
  			
  			String sql = "select * from EMPLOYEE where id=?";
  			pstmt = conn.prepareStatement(sql);
  			pstmt.setString(1, id);
  			rs = pstmt.executeQuery();
  			
  			if(rs.next()) {
  		/*		member = new LogonDataBean();
  				member.setId(rs.getString("id"));
  				member.setPasswd(rs.getString("passwd"));
  				member.setName(rs.getString("name"));
  				member.setJumin1(rs.getString("Jumin1"));
  				member.setJumin2(rs.getString("Jumin2"));
  				member.setEmail(rs.getString("email"));
  				member.setBlog(rs.getString("blog"));
  				member.setReg_date(rs.getTimestamp("reg_date"));
  				member.setZipcode(rs.getString("zipcode"));
  				member.setAddress(rs.getString("address"));*/
  			}
  		}catch(Exception ex) {
  			ex.printStackTrace();
  		}finally {
  			if(rs !=null) try {rs.close();} catch(SQLException ex) {}
  			if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
  			if(conn !=null)try {conn.close();}catch(SQLException ex) {}
  		}
  		return member;
  	}
  	
  	//modifyPro.jsp
  	public void updateMember(EmployeeDataBean member) throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		
  		try {
  			conn = getConnection();
  			
  			String sql ="update EMPLOYEE set passwd=?, name=?, email=?, blog=?, zipcode=?, address=? where id=?";
  			pstmt = conn.prepareStatement(sql);
  	/*		pstmt.setString(1, member.getPasswd());
  			pstmt.setString(2, member.getName());
  			pstmt.setString(3, member.getEmail());
  			pstmt.setString(4, member.getBlog());
  			pstmt.setString(5, member.getZipcode());
  			pstmt.setString(6, member.getAddress());
  			pstmt.setString(7, member.getId());
  			*/
  			pstmt.executeUpdate();
  		}catch(Exception ex) {
  			ex.printStackTrace();
  		}finally {
  			if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
  			if(conn !=null)try {conn.close();}catch(SQLException ex) {}
  		}
  	}
  	
  	
  	//삭제 사용?
  	public int deleteMember(String id, String passwd) throws Exception{
  		Connection conn=null;
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		String dbpasswd="";
  		int x=-1;
  		
  		try {
  			conn = getConnection();
  			
  			String sql = "select passwd from EMPLOYEE where id=?";
  			pstmt = conn.prepareStatement(sql);
  			pstmt.setString(1, id);
  			rs = pstmt.executeQuery();
  			
  			if(rs.next()) {
  				dbpasswd = rs.getString("passwd");    //id와 일치하는 비밀번호
  				if(dbpasswd.equals(passwd)) {  //비밀번호가 일치하면
  					
  					String sql2 = "delete from EMPLOYEE where id=?";
  					pstmt = conn.prepareStatement(sql2);
  					pstmt.setString(1, id);
  					pstmt.executeUpdate();
  					x=1; //회원탈퇴 성공
  				}else
  					x=0;  //비밀번호 틀림
  			}
  		}catch(Exception ex) {
  			ex.printStackTrace();
  		}finally {
  			if(rs !=null) try {rs.close();} catch(SQLException ex) {}
  			if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
  			if(conn !=null)try {conn.close();}catch(SQLException ex) {}
  		}
  		return x;
  	}
  	
  	//findId.jsp
  	public EmployeeDataBean findId(String name, String jumin1){
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs =null;
  		EmployeeDataBean member = null;
  		
  		try {
  			conn = getConnection();
  			
  			String sql = "select id, jumin1 from EMPLOYEE where name=?";
  			pstmt = conn.prepareStatement(sql);
  			pstmt.setString(1, name);
  			rs = pstmt.executeQuery();

  			if(rs.next()) {
  				System.out.println("3");

  	/*			member = new LogonDataBean();
  				member.setId(rs.getString("id"));*/
  			}
  			/*if(rs.next()) {
  				member =rs.getString("id");
  			}*/
  		}catch(Exception ex) {
  			ex.printStackTrace();
  		}finally {
  			if(rs !=null) try {rs.close();} catch(SQLException ex) {}
  			if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
  			if(conn !=null)try {conn.close();}catch(SQLException ex) {}
  		}
  		return member;

  	}
  	
  	
  	//findPassPro.jsp
  	public int findpass(String id,String name, String jumin1){
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs =null;
  		String dbpasswd = null;
  		int x=-1;
  		
  		try {
  			conn = getConnection();
  			
  			String sql = "select passwd from EMPLOYEE where name=? and jumin1=? and id=?";
  			pstmt = conn.prepareStatement(sql);
  			pstmt.setString(1, name);
  			pstmt.setString(2, jumin1);
  			pstmt.setString(3, id);
  			rs = pstmt.executeQuery();


  			if(rs.next()) {
  					x=1; //
  			}else if(!rs.next())
  				x=0;  //비밀번호 틀림
  		}catch(Exception ex) {
  			ex.printStackTrace();
  		}finally {
  			if(rs !=null) try {rs.close();} catch(SQLException ex) {}
  			if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
  			if(conn !=null)try {conn.close();}catch(SQLException ex) {}
  		}
  		return x;
  	}
  	
  	
  	//passmodify.jsp
  	public void updatepass(String passwd, String id) throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		
  		try {
  			conn = getConnection();
  			
  			String sql ="update EMPLOYEE set passwd=? where id=?";
  			pstmt = conn.prepareStatement(sql);
  			pstmt.setString(1, passwd);
  			pstmt.setString(2, id);
  			
  			pstmt.executeUpdate();
  		}catch(Exception ex) {
  			ex.printStackTrace();
  		}finally {
  			if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
  			if(conn !=null)try {conn.close();}catch(SQLException ex) {}
  		}
  	}
  	
  	
  	//chat에 쓸 name값 찾기
  		public String selectName(String id) {
  				Connection conn = null;
  				PreparedStatement pstmt = null;
  				ResultSet rs = null;
  				String name= "";
  				
  				try {
  					conn=getConnection();
  					
  					pstmt = conn.prepareStatement("select name from EMPLOYEE where id = ?");
  					pstmt.setString(1, id);
  					
  					rs = pstmt.executeQuery();
  					
  					if(rs.next()) {
  						name = rs.getString("name");  //num을 이용해 검색해온 비밀번호
  					}
  				}catch(Exception ex) {
  					ex.printStackTrace();
  				}finally {
  					if(rs !=null) try {rs.close();} catch(SQLException ex) {}
  					if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
  					if(conn !=null) try {conn.close();} catch(SQLException ex) {}
  				}
  				return name;
  			}
  	
  		//가입 승인
  		//UpdatePro.jsp
  		public void upDateJoin(EmployeeDataBean article) throws Exception{
  			Connection conn = null;
  			PreparedStatement pstmt = null;
  			
  			try {
  				conn = getConnection();
  				
  				String sql ="update EMPLOYEE set Position_rank=?, emp_sal = ?, dept_no = ?, emp_date = ?  where emp_no = ?";
  				pstmt = conn.prepareStatement(sql);
  				
  				pstmt.setInt(1,article.getPosition_rank());
  				pstmt.setInt(2, article.getEmp_sal());
  				pstmt.setInt(3, article.getDept_no());
  				pstmt.setString(4, article.getEmp_date());
  				pstmt.setInt(5, article.getEmp_no());
  				
  				pstmt.executeUpdate();
  			}catch(Exception ex) {
  				ex.printStackTrace();
  			}finally {
  				if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
  				if(conn !=null)try {conn.close();}catch(SQLException ex) {}
  			}
  		}
  		
  		//회원 정보 수정
  		public void upDateChange(EmployeeDataBean article) throws Exception{
  			Connection conn = null;
  			PreparedStatement pstmt = null;
  			
  			try {
  				conn = getConnection();
  				
  				String sql ="update EMPLOYEE set Position_rank=?, emp_sal = ?, dept_no = ?  where emp_no = ?";
  				pstmt = conn.prepareStatement(sql);
  				
  				pstmt.setInt(1,article.getPosition_rank());
  				pstmt.setInt(2, article.getEmp_sal());
  				pstmt.setInt(3, article.getDept_no());
  				pstmt.setInt(4, article.getEmp_no());
  				
  				pstmt.executeUpdate();
  			}catch(Exception ex) {
  				ex.printStackTrace();
  			}finally {
  				if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
  				if(conn !=null)try {conn.close();}catch(SQLException ex) {}
  			}
  		}
  		
  		
  		//가입 거절
  		//deletePro.jsp
  		public void deleteMember(int emp_no) throws Exception{
  			Connection conn = null;
  			PreparedStatement pstmt = null;
  			
  			try {
  				conn = getConnection();
  				
  				String sql ="DELETE FROM employee WHERE emp_no = ?";
  				pstmt = conn.prepareStatement(sql);

  				pstmt.setInt(1,emp_no);
  				
  				pstmt.executeUpdate();
  			}catch(Exception ex) {
  				ex.printStackTrace();
  			}finally {
  				if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
  				if(conn !=null)try {conn.close();}catch(SQLException ex) {}
  			}
  		}
}