package model.photo;
    
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhotoDBBean {
   private static PhotoDBBean instance = new PhotoDBBean();
   
   public static PhotoDBBean getInstance() {
      return instance;
   }
   private PhotoDBBean() {
      
   }
   private Connection getConnection() throws Exception{
      String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
      return DriverManager.getConnection(jdbcDriver);
   }
   
   //writePro.jsp
   public int insertArtcile(PhotoDataBean article) throws Exception{
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      //int emp_no = article.getEmp_no();
      
      String sql = "insert into photo values(PHOTO_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
      try {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, article.getEmp_no());
      pstmt.setString(2, article.getPhoto_title());
      pstmt.setString(3, article.getPhoto_content());
      pstmt.setString(4, article.getPhoto_date());
      pstmt.setString(5, article.getPhoto_file_nm());
      pstmt.setString(6, article.getPhoto_file_addr());
      pstmt.setString(7, article.getPassword());
      return pstmt.executeUpdate();
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         if (rs != null) try { rs.close(); } catch(SQLException ex) {}
           if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
           if (conn != null) try { conn.close(); } catch(SQLException ex) {}
      }
      return -1;
   }      

   //list.jsp : 페이징을 위해서 전체 DB에 입력된 행의 수가 필요하다
   public int getArticleCount() throws Exception{
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      int x = 0;
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement("select count(*) from photo");
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
   //list.jsp => paging DB로부터 여러행을 결과로 받는다.
   public List getArticles(int start, int end) throws Exception{
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List articleList = null;
      String sql = "select photo_no, emp_no, photo_title, photo_content, photo_date, photo_file_nm, photo_FILE_addr, password " + 
            "from (select photo_no, emp_no, photo_title, photo_content, photo_date, photo_file_nm, photo_FILE_addr, password, rownum r " + 
            "from (select photo_no, emp_no, photo_title, photo_content, photo_date, photo_file_nm, photo_FILE_addr, password " + 
            "from photo order by photo_no desc) order by photo_no desc ) where r >= ? and r <= ?";
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, start);
         pstmt.setInt(2, end);
         rs = pstmt.executeQuery();
         if(rs.next()) {
            articleList = new ArrayList(end);
            do {
               PhotoDataBean article = new PhotoDataBean();
               article.setPhoto_no(rs.getInt("photo_no"));
               article.setEmp_no(rs.getInt("emp_no"));
               article.setPhoto_title(rs.getString("photo_title"));
               article.setPhoto_content(rs.getString("photo_content"));
               article.setPhoto_date(rs.getString("photo_date"));
               int x = rs.getString("photo_file_nm").indexOf(",");
               String photo_nm = rs.getString("photo_file_nm").substring(0, x);
               
               article.setPhoto_file_nm(photo_nm);
               article.setPhoto_file_addr(rs.getString("photo_file_addr"));
               article.setPassword(rs.getString("password"));
               articleList.add(article);
               
            }while(rs.next());
         }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
          if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
      }
      return articleList;
   }
   //read.jsp : DB로부터 한줄의 데이터를 가져온다.
   public PhotoDataBean getArticle(int photo_no) throws Exception{
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      PhotoDataBean article = null;
      try {
         conn = getConnection();
         
            pstmt = conn.prepareStatement(
            "select * from photo where photo_no = ?");
            pstmt.setInt(1, photo_no);
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
               article = new PhotoDataBean();
               article.setPhoto_no(rs.getInt("photo_no"));
               article.setEmp_no(rs.getInt("emp_no"));
               article.setPhoto_title(rs.getString("photo_title"));
               article.setPhoto_content(rs.getString("photo_content"));
               article.setPhoto_date(rs.getString("photo_date"));
               article.setPhoto_file_nm(rs.getString("photo_file_nm"));
               article.setPhoto_file_addr(rs.getString("photo_file_addr"));
               article.setPassword(rs.getString("password"));
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
   //updateForm.jsp : 수정중에 한줄의 데이터를 가져올때.
   public PhotoDataBean updateGetArticle(int photo_no) throws Exception {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      PhotoDataBean article = null;
      try {
         conn = getConnection();
         
         pstmt = conn.prepareStatement(
               "select * from photo where photo_no = ?");
         pstmt.setInt(1, photo_no);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            article = new PhotoDataBean();
            article.setPhoto_no(rs.getInt("photo_no"));
            article.setEmp_no(rs.getInt("emp_no"));
            article.setPhoto_title(rs.getString("photo_title"));
            article.setPhoto_date(rs.getString("photo_date"));
            article.setPhoto_file_nm(rs.getString("photo_file_nm"));
            article.setPhoto_file_addr(rs.getString("photo_file_addr"));
            article.setPhoto_content(rs.getString("photo_content"));
            article.setPassword(rs.getString("password"));
            }
         }catch(Exception ex) {
            ex.printStackTrace();
         }finally{
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
               if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
               if (conn != null) try { conn.close(); } catch(SQLException ex) {}
         }
      return article;
   }
   //updatePro.jsp : 실제 데이터를 수정하는 메소드
      public int updateArticle(PhotoDataBean article) throws Exception{
         Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         
         String password = "";
         String sql = "";
      int x = -1;
      try {
         conn = getConnection();
         
         pstmt = conn.prepareStatement("select password from photo where photo_no = ?");
         pstmt.setInt(1, article.getPhoto_no());
         rs = pstmt.executeQuery();
         
         
         
         if(rs.next()) {
         password = rs.getString("password");
            if(password.equals(article.getPassword())) {
               sql="update photo set photo_title=?,photo_content=?,photo_file_nm=?";
               sql+=",photo_file_addr=? where photo_no=?";
               
               //pstmt.setInt(1, Integer.parseInt("articlePhoto_no"));
               pstmt = conn.prepareStatement(sql);
               
               pstmt.setString(1, article.getPhoto_title());
               pstmt.setString(2, article.getPhoto_content());
               pstmt.setString(3, article.getPhoto_file_nm());
               pstmt.setString(4, article.getPhoto_file_addr());
               /*pstmt.setString(5, article.getPassword());*/
               pstmt.setInt(5, article.getPhoto_no());
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
      
      //deletePro.jsp : 실제 데이터를 삭제하는 메소드
      
      public int deleteArticle(int photo_no, String password) throws Exception{
         Connection conn = null;
         PreparedStatement pstmt = null;
           ResultSet rs= null;
           String password1="";
           int x= -1;
           try {
              conn = getConnection();
              
              pstmt = conn.prepareStatement(
                    "select password from photo where photo_no = ?");
              pstmt.setInt(1, photo_no);
              rs = pstmt.executeQuery();
          
              if(rs.next()) {
                 password1 = rs.getString("password");
                 if(password1.equals(password)) {
                    pstmt = conn.prepareStatement("delete from photo where photo_no=?");
                    pstmt.setInt(1, photo_no);
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
      
      //writePro.jsp 코멘트 작성
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
      //deletePro.jsp 코멘트 삭제
      
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
            //updateForm.jsp 코멘트 수정 폼
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
            //read.jsp 코멘트 디비에서 가져오기
            public PhotoCommentDataBean getPhoto_comment(int comm_no) throws Exception{
               Connection conn = null;
               PreparedStatement pstmt = null;
               ResultSet rs = null;
               PhotoCommentDataBean photo_comment = null;
               try {
                  conn = getConnection();
                  
                     pstmt = conn.prepareStatement(
                     "select * from photo_comment where photo_no = ?");
                     pstmt.setInt(1, comm_no);
                     rs = pstmt.executeQuery();
                     
                     if(rs.next()) {
                        photo_comment = new PhotoCommentDataBean();
                        photo_comment.setCommNo(rs.getInt("comm_no"));
                        photo_comment.setComm_writer(rs.getString("comm_writer"));
                        photo_comment.setComm_password(rs.getString("comm_password"));
                        photo_comment.setComm_content(rs.getString("comm_content"));
                        }
                     }catch(Exception ex) {
                        ex.printStackTrace();
                     }finally {
                        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
                           if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
                           if (conn != null) try { conn.close(); } catch(SQLException ex) {}
                     }
                     return photo_comment;
            }

         //list.jsp => paging DB로부터 여러행을 결과로 받는다.
            public List getPhoto_comment(int start, int end) throws Exception{
               Connection conn = null;
               PreparedStatement pstmt = null;
               ResultSet rs = null;
               List CommentList = null;
               String sql = "select comm_no, comm_writer, comm_content, comm_password, photo_no, comm_date " + 
                     "from (select comm_no, comm_writer, comm_content, comm_password, photo_no, comm_date, rownum r " + 
                     "from (select comm_no, comm_writer, comm_content, comm_password, photo_no, comm_date " + 
                     "from photo_comment order by comm_no asc) order by comm_no asc ) where r >= 1 and r <= 10";
               try {
                  conn = getConnection();
                  pstmt = conn.prepareStatement(sql);
                  rs = pstmt.executeQuery();
                  if(rs.next()) {
                     CommentList = new ArrayList(end);
                     do {
                        PhotoCommentDataBean Comment = new PhotoCommentDataBean();
                        
                        Comment.setCommNo(rs.getInt("comm_no"));
                        Comment.setComm_writer(rs.getString("comm_writer"));
                        Comment.setComm_content(rs.getString("comm_content"));
                        Comment.setComm_password(rs.getString("comm_password"));
                        Comment.setPhoto_no(rs.getInt("photo_np"));
                        Comment.setComm_date(rs.getTimestamp("comm-date"));
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
            public void PhotoCommentDelete(PhotoCommentDataBean photo_comment) {
               // TODO Auto-generated method stub
               
            }
      }