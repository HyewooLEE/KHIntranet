package model.employee;
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAllDBBean {
   
private static EmployeeAllDBBean instance = new EmployeeAllDBBean();
   
   public static EmployeeAllDBBean getInstance() {
      return instance;
   }
   
   private EmployeeAllDBBean() {}
   
   private Connection getConnection() throws Exception{
      String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
      return DriverManager.getConnection(jdbcDriver);
   }

   
   //emp_no
   public EmployeeAllDataBean getEmp_no(int emp_no) throws Exception{
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      EmployeeAllDataBean article = null;
      
      try {
         conn = getConnection();
         
         pstmt = conn.prepareStatement("select * from employee NATURAL JOIN DEPARTMENT NATURAL JOIN POSITION where emp_no = ? order by position_rank"); //게시물의 내용을 불러온다.
         pstmt.setInt(1, emp_no);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            article = new EmployeeAllDataBean();
            
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
            article.setEmp_date(rs.getString("emp_date").substring(0, 10));
            article.setEmp_resign(rs.getString("emp_resign"));
            article.setPosition_rank(rs.getInt("position_rank"));
            article.setEmp_sal(rs.getInt("emp_sal"));
            article.setEmp_pt_name(rs.getString("emp_pt_name"));
            article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
            article.setDept_no(rs.getInt("dept_no"));
            article.setDept_name(rs.getString("dept_name"));
            article.setPosition_name(rs.getString("position_name"));
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
   
   
   //read.jsp : DB로부터 한줄의 데이터를 가져온다.
         //no : 사원번호,   이용해서 찾기
         public EmployeeAllDataBean getEmp_id(String emp_id) throws Exception{
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            EmployeeAllDataBean article = null;
            
            try {
               conn = getConnection();
               
               pstmt = conn.prepareStatement("select * from employee NATURAL JOIN DEPARTMENT NATURAL JOIN POSITION where emp_id = ? order by position_rank"); //게시물의 내용을 불러온다.
               pstmt.setString(1, emp_id);
               rs = pstmt.executeQuery();
               
               if(rs.next()) {
                  article = new EmployeeAllDataBean();
                  
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
                  article.setEmp_date(rs.getString("emp_date").substring(0, 10));
                  article.setEmp_resign(rs.getString("emp_resign"));
                  article.setPosition_rank(rs.getInt("position_rank"));
                  article.setEmp_sal(rs.getInt("emp_sal"));
                  article.setEmp_pt_name(rs.getString("emp_pt_name"));
                  article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
                  article.setDept_no(rs.getInt("dept_no"));
                  article.setDept_name(rs.getString("dept_name"));
                  article.setPosition_name(rs.getString("position_name"));
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
         
         //emp_name으로 찾기
         public EmployeeAllDataBean getEmp_name(String emp_name) throws Exception{
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            EmployeeAllDataBean article = null;
            
            try {
               conn = getConnection();
               
               pstmt = conn.prepareStatement("select * from employee NATURAL JOIN DEPARTMENT NATURAL JOIN POSITION where emp_name = ? order by position_rank"); //게시물의 내용을 불러온다.
               pstmt.setString(1, emp_name);
               rs = pstmt.executeQuery();
               
               if(rs.next()) {
                  article = new EmployeeAllDataBean();
                  
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
                  article.setEmp_date(rs.getString("emp_date").substring(0, 10));
                  article.setEmp_resign(rs.getString("emp_resign"));
                  article.setPosition_rank(rs.getInt("position_rank"));
                  article.setEmp_sal(rs.getInt("emp_sal"));
                  article.setEmp_pt_name(rs.getString("emp_pt_name"));
                  article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
                  article.setDept_no(rs.getInt("dept_no"));
                  article.setDept_name(rs.getString("dept_name"));
                  article.setPosition_name(rs.getString("position_name"));
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
         
         
         
          //Dept_name으로
            public List getDept_name(String dept_name) throws Exception{
               Connection conn = null;
               PreparedStatement pstmt = null;
               ResultSet rs = null;
               List articleList = null;
               
               try {
                  conn = getConnection();                  
                  pstmt = conn.prepareStatement("select * from employee NATURAL JOIN DEPARTMENT NATURAL JOIN POSITION where dept_name = ? order by position_rank"); //게시물의 내용을 불러온다.
                  pstmt.setString(1, dept_name);
                  rs = pstmt.executeQuery();
                  
                  if(rs.next()) {
                     articleList = new ArrayList();
                     do {
                       EmployeeAllDataBean article = new EmployeeAllDataBean();
                        article = new EmployeeAllDataBean();
                        
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
                  article.setEmp_date(rs.getString("emp_date").substring(0, 10));
                  article.setEmp_resign(rs.getString("emp_resign"));
                  article.setPosition_rank(rs.getInt("position_rank"));
                  article.setEmp_sal(rs.getInt("emp_sal"));
                  article.setEmp_pt_name(rs.getString("emp_pt_name"));
                  article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
                  article.setDept_no(rs.getInt("dept_no"));
                  article.setDept_name(rs.getString("dept_name"));
                  article.setPosition_name(rs.getString("position_name"));
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
            
            
            
            //Dept_no로
            public List getDept_no(int dept_no) throws Exception{
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                List articleList = null;
                
                try {
                   conn = getConnection();                  
                   pstmt = conn.prepareStatement("select * from employee NATURAL JOIN DEPARTMENT NATURAL JOIN POSITION where dept_no = ? order by position_rank"); //게시물의 내용을 불러온다.
                   pstmt.setInt(1, dept_no);
                   rs = pstmt.executeQuery();
                   
                   if(rs.next()) {
                      articleList = new ArrayList();
                      do {
                        EmployeeAllDataBean article = new EmployeeAllDataBean();
                         article = new EmployeeAllDataBean();
                         
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
                   article.setEmp_date(rs.getString("emp_date").substring(0, 10));
                   article.setEmp_resign(rs.getString("emp_resign"));
                   article.setPosition_rank(rs.getInt("position_rank"));
                   article.setEmp_sal(rs.getInt("emp_sal"));
                   article.setEmp_pt_name(rs.getString("emp_pt_name"));
                   article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
                   article.setDept_no(rs.getInt("dept_no"));
                   article.setDept_name(rs.getString("dept_name"));
                   article.setPosition_name(rs.getString("position_name"));
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
            
            //position_rank로
            public List getPosition_rank(int getPosition_rank) throws Exception{
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                List articleList = null;
                
                try {
                   conn = getConnection();                  
                   pstmt = conn.prepareStatement("select * from employee NATURAL JOIN DEPARTMENT NATURAL JOIN POSITION where position_rank = ? order by position_rank"); //게시물의 내용을 불러온다.
                   pstmt.setInt(1, getPosition_rank);
                   rs = pstmt.executeQuery();
                   
                   if(rs.next()) {
                      articleList = new ArrayList();
                      do {
                        EmployeeAllDataBean article = new EmployeeAllDataBean();
                         article = new EmployeeAllDataBean();
                         
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
                   article.setEmp_date(rs.getString("emp_date").substring(0, 10));
                   article.setEmp_resign(rs.getString("emp_resign"));
                   article.setPosition_rank(rs.getInt("position_rank"));
                   article.setEmp_sal(rs.getInt("emp_sal"));
                   article.setEmp_pt_name(rs.getString("emp_pt_name"));
                   article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
                   article.setDept_no(rs.getInt("dept_no"));
                   article.setDept_name(rs.getString("dept_name"));
                   article.setPosition_name(rs.getString("position_name"));
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
            
            
            //position_name으로
            public List getPosition_name(String position_name) throws Exception{
               Connection conn = null;
               PreparedStatement pstmt = null;
               ResultSet rs = null;
               List articleList = null;
               
               try {
                  conn = getConnection();                  
                  pstmt = conn.prepareStatement("select * from employee NATURAL JOIN DEPARTMENT NATURAL JOIN POSITION where position_name = ? order by position_rank"); //게시물의 내용을 불러온다.
                  pstmt.setString(1, position_name);
                  rs = pstmt.executeQuery();
                  
                  if(rs.next()) {
                     articleList = new ArrayList();
                     do {
                       EmployeeAllDataBean article = new EmployeeAllDataBean();
                        article = new EmployeeAllDataBean();
                        
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
                  article.setEmp_date(rs.getString("emp_date").substring(0, 10));
                  article.setEmp_resign(rs.getString("emp_resign"));
                  article.setPosition_rank(rs.getInt("position_rank"));
                  article.setEmp_sal(rs.getInt("emp_sal"));
                  article.setEmp_pt_name(rs.getString("emp_pt_name"));
                  article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
                  article.setDept_no(rs.getInt("dept_no"));
                  article.setDept_name(rs.getString("dept_name"));
                  article.setPosition_name(rs.getString("position_name"));
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
            
            
            
            //dept_name, position_rank
            public List Dept_PositionRank(String dept_name, int position_rank) throws Exception{
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                List articleList = null;
                
                try {
                   conn = getConnection();                  
                   pstmt = conn.prepareStatement("select * from employee NATURAL JOIN DEPARTMENT NATURAL JOIN POSITION where dept_name = ? and position_rank = ? order by position_rank"); //게시물의 내용을 불러온다.
                   pstmt.setString(1, dept_name);
                   pstmt.setInt(2, position_rank);
                   rs = pstmt.executeQuery();
                   
                   if(rs.next()) {
                      articleList = new ArrayList();
                      do {
                        EmployeeAllDataBean article = new EmployeeAllDataBean();
                         article = new EmployeeAllDataBean();
                         
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
                   article.setEmp_date(rs.getString("emp_date").substring(0, 10));
                   article.setEmp_resign(rs.getString("emp_resign"));
                   article.setPosition_rank(rs.getInt("position_rank"));
                   article.setEmp_sal(rs.getInt("emp_sal"));
                   article.setEmp_pt_name(rs.getString("emp_pt_name"));
                   article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
                   article.setDept_no(rs.getInt("dept_no"));
                   article.setDept_name(rs.getString("dept_name"));
                   article.setPosition_name(rs.getString("position_name"));
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
            
            
            //position_name, dept_no
            public List Position_DeptNo(String position_name, int dept_no) throws Exception{
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                List articleList = null;
                
                try {
                   conn = getConnection();                  
                   pstmt = conn.prepareStatement("select * from employee NATURAL JOIN DEPARTMENT NATURAL JOIN POSITION where position_name = ? and dept_no = ? order by position_rank"); //게시물의 내용을 불러온다.
                   pstmt.setString(1, position_name);
                   pstmt.setInt(2, dept_no);
                   rs = pstmt.executeQuery();
                   
                   if(rs.next()) {
                      articleList = new ArrayList();
                      do {
                        EmployeeAllDataBean article = new EmployeeAllDataBean();
                         article = new EmployeeAllDataBean();
                         
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
                   article.setEmp_date(rs.getString("emp_date").substring(0, 10));
                   article.setEmp_resign(rs.getString("emp_resign"));
                   article.setPosition_rank(rs.getInt("position_rank"));
                   article.setEmp_sal(rs.getInt("emp_sal"));
                   article.setEmp_pt_name(rs.getString("emp_pt_name"));
                   article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
                   article.setDept_no(rs.getInt("dept_no"));
                   article.setDept_name(rs.getString("dept_name"));
                   article.setPosition_name(rs.getString("position_name"));
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
            
         
         
         //All 다찾아오기
         public List selectAll() throws Exception{
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs =null;
            List empAll =null;

            try {
               conn = getConnection();
               
               String sql = "select * from employee NATURAL JOIN DEPARTMENT NATURAL JOIN POSITION order by position_rank";
               pstmt = conn.prepareStatement(sql);
               rs = pstmt.executeQuery();
               
               if(rs.next()) {
                  empAll = new ArrayList();
                  do {
                     EmployeeAllDataBean article = new EmployeeAllDataBean();
                     article = new EmployeeAllDataBean();

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
                     article.setEmp_date(rs.getString("emp_date").substring(0, 10));
                     article.setEmp_resign(rs.getString("emp_resign"));
                     article.setPosition_rank(rs.getInt("position_rank"));
                     article.setEmp_sal(rs.getInt("emp_sal"));
                     article.setEmp_pt_name(rs.getString("emp_pt_name"));
                     article.setEmp_pt_addr(rs.getString("emp_pt_addr"));
                     article.setDept_no(rs.getInt("dept_no"));
                     article.setDept_name(rs.getString("dept_name"));
                     article.setPosition_name(rs.getString("position_name"));
                     article.setEmp_annual(rs.getInt("emp_annual"));
                     
                     empAll.add(article);
                  }while(rs.next());
                  
               }
            }catch(Exception ex) {
               ex.printStackTrace();
            }finally {
               if(rs !=null) try {rs.close();} catch(SQLException ex) {}
               if(pstmt !=null)try {pstmt.close();}catch(SQLException ex) {}
               if(conn !=null)try {conn.close();}catch(SQLException ex) {}
            }
            return empAll;
         }
         
       //emp_name 포함 값 다찾기
         public ArrayList<EmployeeAllDataBean> getEmp_nameList(String emp_name) throws Exception{
           ArrayList<EmployeeAllDataBean> searchList=null;
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            EmployeeAllDataBean article = null;
            
            try {
               conn = getConnection();
               
               pstmt = conn.prepareStatement("select * from EMPLOYEE NATURAL JOIN DEPARTMENT NATURAL JOIN POSITION WHERE emp_name like ?"); //게시물의 내용을 불러온다.
               pstmt.setString(1, "%" + emp_name + "%");
               rs = pstmt.executeQuery();
               searchList = new ArrayList<EmployeeAllDataBean>();
               if(rs.next()) {
               do{
                  article = new EmployeeAllDataBean();         
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
                  article.setDept_name(rs.getString("dept_name"));
                  article.setPosition_name(rs.getString("position_name"));
                  searchList.add(article);
               }while(rs.next());
               }
            }catch(Exception ex) {
               ex.printStackTrace();
            }finally {
               if(rs !=null) try {rs.close();} catch(SQLException ex) {}
               if(pstmt !=null) try {pstmt.close();} catch(SQLException ex) {}
               if(conn !=null) try {conn.close();} catch(SQLException ex) {}
            }
            return searchList;
         }
   
}