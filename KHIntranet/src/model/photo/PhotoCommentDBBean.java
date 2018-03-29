package model.photo;
    
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PhotoCommentDBBean {

   private static PhotoCommentDBBean instance = new PhotoCommentDBBean();
   
   public static PhotoCommentDBBean getInstance() {
      return instance;
   }
   private Connection getConnection() throws Exception{
      String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
      return DriverManager.getConnection(jdbcDriver);
   }

         public void insertPhoto_comment(PhotoCommentDataBean photo_comment) throws Exception{
            
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            
            //int emp_no = article.getEmp_no();
            
            String sql = "insert into photo_comment values(PHOTO_COMMENT_SEQ.NEXTVAL,?,?,?,?,?)";
            try {
            
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, photo_comment.getComm_writer());
            pstmt.setString(2, photo_comment.getComm_content());
            pstmt.setString(3, photo_comment.getComm_password());
            pstmt.setInt(4, photo_comment.getPhoto_no());
            pstmt.setTimestamp(5, photo_comment.getComm_date());
            pstmt.executeUpdate();
            }catch(Exception e) {
               e.printStackTrace();
            }finally {
               if (rs != null) try { rs.close(); } catch(SQLException ex) {}
                 if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
                 if (conn != null) try { conn.close(); } catch(SQLException ex) {}
            }
         }      
         
               public int deletePhoto_comment(int comm_no, String comm_password) throws Exception{
                  Connection conn = null;
                  PreparedStatement pstmt = null;
                    ResultSet rs= null;
                    String comm_password1="";
                    int x= -1;
                    try {
                       conn = getConnection();
                       
                       pstmt = conn.prepareStatement(
                             "select comm_password from photo_comment where comm_no = ?");
                       pstmt.setInt(1, comm_no);
                       rs = pstmt.executeQuery();
                   
                       if(rs.next()) {
                          comm_password1 = rs.getString("comm_password");
                          if(comm_password1.equals(comm_password)) {
                             pstmt = conn.prepareStatement("delete from photo_comment where comm_no=?");
                             pstmt.setInt(1, comm_no);
                             pstmt.executeUpdate();
                             x= 1;//글삭제 성공
                          }else
                             x = 0;//비밀번호 틀림
                       }
                       
                       }catch(Exception ex) {
                          ex.printStackTrace();
                       }finally {
                          if (rs != null) try { rs.close(); } catch(SQLException ex) {}
                            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
                            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
                       }
                       return x;
                    }
            
               public int updatePhoto_comment(PhotoCommentDataBean photo_comment) throws Exception{
                  Connection conn = null;
                  PreparedStatement pstmt = null;
                  ResultSet rs = null;
                  
                  String comm_password = "";
                  String sql = " ";
               int x = -1;
               try {
                  conn = getConnection();
                  
                  pstmt = conn.prepareStatement("select comm_password from photo_comment where comm_no = ?");
                  pstmt.setInt(1, photo_comment.getComm_no());
                  rs = pstmt.executeQuery();
                  
                  
                  
                  if(rs.next()) {
                  comm_password = rs.getString("comm_password");
                     if(comm_password.equals(photo_comment.getComm_password())) {
                        sql="update photo_comment set comm_writer=?,comm_password=?,comm_content=?";
                        sql+=",where comm_no=?";
                        
                        pstmt = conn.prepareStatement(sql);
                        
                        pstmt.setString(1, photo_comment.getComm_writer());
                        pstmt.setString(2, photo_comment.getComm_password());
                        pstmt.setString(3, photo_comment.getComm_content());
                        pstmt.executeUpdate();
                        
                        x = 1;
                        }else {
                           x = 0;
                        }
                     }
                        }catch(Exception ex) {
                           ex.printStackTrace();
                        }finally {
                         if (rs != null) try { rs.close(); } catch(SQLException ex) {}
                          if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
                          if (conn != null) try { conn.close(); } catch(SQLException ex) {}   
                        }
               return x;
               }
               
               public PhotoCommentDataBean updateGetPhoto_comment(int comm_no) throws Exception {
                  Connection conn = null;
                  PreparedStatement pstmt = null;
                  ResultSet rs = null;
                  PhotoCommentDataBean photo_comment = null;
                  try {
                     conn = getConnection();
                     
                     pstmt = conn.prepareStatement(
                           "select * from photo_comment where comm_no = ?");
                     pstmt.setInt(1, comm_no);
                     rs = pstmt.executeQuery();
                     
                     if(rs.next()) {
                        photo_comment = new PhotoCommentDataBean();
                        photo_comment.setCommNo(rs.getInt("comm_no"));
                        photo_comment.setComm_password(rs.getString("comm_password"));
                        photo_comment.setComm_writer(rs.getString("comm_writer"));
                        }
                     }catch(Exception ex) {
                        ex.printStackTrace();
                     }finally{
                        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
                           if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
                           if (conn != null) try { conn.close(); } catch(SQLException ex) {}
                     }
                  return photo_comment;
               }
            

            
               public ArrayList<PhotoCommentDataBean> getPhoto_comment(int photo_no) throws Exception{
                  Connection conn = null;
                  PreparedStatement pstmt = null;
                  ResultSet rs = null;
                  ArrayList<PhotoCommentDataBean> CommentList = null;
                  String sql = "select comm_no, comm_writer, comm_content, comm_password, photo_no, comm_date " + 
                        "from (select comm_no, comm_writer, comm_content, comm_password, photo_no, comm_date, rownum r " + 
                        "from (select comm_no, comm_writer, comm_content, comm_password, photo_no, comm_date " + 
                        "from photo_comment order by comm_no desc) order by comm_no desc ) where photo_no = ?";
                  try {
                     conn = getConnection();
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setInt(1, photo_no);
                     rs = pstmt.executeQuery();
                     if(rs.next()) {
                        CommentList = new ArrayList<PhotoCommentDataBean>();
                        do {
                           PhotoCommentDataBean Comment = new PhotoCommentDataBean();
                           
                           Comment.setCommNo(rs.getInt("comm_no"));
                           Comment.setComm_writer(rs.getString("comm_writer"));
                           Comment.setComm_content(rs.getString("comm_content"));
                           Comment.setComm_password(rs.getString("comm_password"));
                           Comment.setPhoto_no(rs.getInt("photo_no"));
                           Comment.setComm_date(rs.getTimestamp("comm_date"));
                           CommentList.add(Comment);
                        }while(rs.next());
                     }
                  }catch(Exception e) {
                     e.printStackTrace();
                  }finally {
                      if (rs != null) try { rs.close(); } catch(SQLException ex) {}
                        if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
                        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
                  }
                  return CommentList;
               }
               
               public int getCommentCount(int photo_no)throws Exception{
           		Connection conn = null;
           		PreparedStatement pstmt = null;
           		ResultSet rs = null;
           		int count = 0;
           		try {
           			conn = getConnection();
           			String sql = "select count(*) from photo_comment where photo_no= ?";
           			pstmt = conn.prepareStatement(sql);
           			pstmt.setInt(1, photo_no);
           			rs = pstmt.executeQuery();
           			if(rs.next()) {
           				count = rs.getInt(1);
           			}
           		}catch (Exception ex) {
           			ex.printStackTrace();
           		}finally {
           		  if (rs != null) try { rs.close(); } catch(SQLException ex) {}
                  if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
                  if (conn != null) try { conn.close(); } catch(SQLException ex) {}
           		}
           		return count;
           	}
               
               //코멘트 다지우기
               public int deleteCommentAll(int photo_no)throws Exception{
              		Connection conn = null;
              		PreparedStatement pstmt = null;
              		try {
              			conn = getConnection();
              			String sql = "delete from photo_comment where photo_no= ?";
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
