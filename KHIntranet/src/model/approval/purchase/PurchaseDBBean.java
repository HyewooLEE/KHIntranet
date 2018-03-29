package model.approval.purchase;
  
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

public class PurchaseDBBean{
	
	private static PurchaseDBBean instance = new PurchaseDBBean();

	public static PurchaseDBBean getInstance() {
		return instance;
	}

	private PurchaseDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	// writeFormPro.jsp
	public void insertPurchase(PurchaseDataBean article) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into PURCHASE values (PURCHASE_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, article.getPur_title());
			pstmt.setInt(2, article.getPur_emp_no());
			pstmt.setString(3, article.getPur_date());
			pstmt.setString(4, article.getPur_receiver());
			pstmt.setString(5, article.getPur_name());
			pstmt.setString(6, article.getPur_standard());
			pstmt.setString(7, article.getPur_quan());
			pstmt.setInt(8, article.getPur_price());
			pstmt.setInt(9, article.getPur_sum());
			pstmt.setString(10, article.getPur_use());
			pstmt.setString(11, article.getPur_note());
			pstmt.setString(12, article.getPur_file_name());
			pstmt.setString(13, article.getPur_file_path());
			pstmt.setString(14, article.getPur_status_ny());
			pstmt.setInt(15, article.getDoc_no());

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
	
	// Purchase - Document join
	public List<PurchaseDataBean> purchaseDoc() throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PurchaseDataBean> PurchaseList = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from PURCHASE p, DOCUMENT d where p.DOC_NO=d.DOC_NO ");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				PurchaseList = new ArrayList<PurchaseDataBean>();
				do {
					PurchaseDataBean article = new PurchaseDataBean();

					article.setPur_no(rs.getInt("pur_no"));
					article.setPur_title(rs.getString("pur_title"));
					article.setPur_emp_no(rs.getInt("pur_emp_no"));
					article.setPur_date(rs.getString("pur_date"));
					article.setPur_receiver(rs.getString("pur_receiver"));
					article.setPur_name(rs.getString("pur_name"));
					article.setPur_standard(rs.getString("pur_standard"));
					article.setPur_quan(rs.getString("pur_quan"));
					article.setPur_price(rs.getInt("pur_price"));
					article.setPur_sum(rs.getInt("pur_sum"));
					article.setPur_use(rs.getString("pur_use"));
					article.setPur_note(rs.getString("pur_note"));
					article.setPur_file_name(rs.getString("pur_file_name"));
					article.setPur_file_path(rs.getString("pur_file_path"));
					article.setPur_status_ny(rs.getString("pur_status_ny"));
					article.setDoc_no(rs.getInt("doc_no"));
					article.setDoc_name(rs.getString("doc_name"));
					
					PurchaseList.add(article);

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
		return PurchaseList;
	}
	
	
	public List<PurchaseDataBean> purchaseDoc(EmployeeAllDataBean emp_all) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PurchaseDataBean> PurchaseList = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from PURCHASE p, DOCUMENT d where p.DOC_NO=d.DOC_NO ");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				PurchaseList = new ArrayList<PurchaseDataBean>();
				do {
					PurchaseDataBean article = new PurchaseDataBean();

					article.setPur_no(rs.getInt("pur_no"));
					article.setPur_title(rs.getString("pur_title"));
					article.setPur_emp_no(rs.getInt("pur_emp_no"));
					article.setPur_date(rs.getString("pur_date"));
					
					String parti = "";
		               
		            parti = (rs.getString("pur_receiver")).trim();
		                          
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
					
					article.setPur_names(name);
					article.setPur_receiver(rs.getString("pur_receiver"));
					article.setPur_name(rs.getString("pur_name"));
					article.setPur_standard(rs.getString("pur_standard"));
					article.setPur_quan(rs.getString("pur_quan"));
					article.setPur_price(rs.getInt("pur_price"));
					article.setPur_sum(rs.getInt("pur_sum"));
					article.setPur_use(rs.getString("pur_use"));
					article.setPur_note(rs.getString("pur_note"));
					article.setPur_file_name(rs.getString("pur_file_name"));
					article.setPur_file_path(rs.getString("pur_file_path"));
					article.setPur_status_ny(rs.getString("pur_status_ny"));
					article.setDoc_no(rs.getInt("doc_no"));
					article.setDoc_name(rs.getString("doc_name"));
					
					PurchaseList.add(article);

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
		return PurchaseList;
	}
	
	
	//물품구매신청 결재요청받은 전체 글 갯수
    public int getArticleCount(int emp_no) throws Exception{
    	
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
      
       int x = 0;
       try {
          conn = getConnection();
          pstmt = conn.prepareStatement("select count(*) from PURCHASE where substr(PUR_RECEIVER,1,instr(PUR_RECEIVER,',')-1) = ?");
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
   
    
     //물품구매신청 결재요청받은 전체 글 페이징
     public List<PurchaseDataBean> getArticles(int emp_no, int start, int end) throws Exception{
    	 
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PurchaseDataBean> PurchaseList = null;
        String sql = "select pur_no, pur_title, pur_emp_no, pur_date, pur_receiver, pur_name, pur_standard, pur_quan, pur_price, pur_sum, pur_use, pur_note, pur_file_name, pur_file_path, pur_status_ny, doc_no " + 
              "from (select pur_no, pur_title, pur_emp_no, pur_date, pur_receiver, pur_name, pur_standard, pur_quan, pur_price, pur_sum, pur_use, pur_note, pur_file_name, pur_file_path, pur_status_ny, doc_no, rownum r " + 
              "from (select pur_no, pur_title, pur_emp_no, pur_date, pur_receiver, pur_name, pur_standard, pur_quan, pur_price, pur_sum, pur_use, pur_note, pur_file_name, pur_file_path, pur_status_ny, doc_no " + 
              "from PURCHASE where substr(PUR_RECEIVER,1,instr(PUR_RECEIVER,',')-1)=? order by pur_no desc) order by pur_no desc ) where r >= ? and r <= ?";
      
        try {
           conn = getConnection();
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, emp_no);
           pstmt.setInt(2, start);
           pstmt.setInt(3, end);
           rs = pstmt.executeQuery();
         
           if(rs.next()) {
        	   PurchaseList = new ArrayList<PurchaseDataBean>(end);
              do {
            	  PurchaseDataBean article = new PurchaseDataBean();
            	  
            	  article.setPur_no(rs.getInt("pur_no"));
				  article.setPur_title(rs.getString("pur_title"));
				  article.setPur_emp_no(rs.getInt("pur_emp_no"));
				  article.setPur_date(rs.getString("pur_date"));
				  article.setPur_receiver(rs.getString("pur_receiver"));
				  article.setPur_name(rs.getString("pur_name"));
				  article.setPur_standard(rs.getString("pur_standard"));
				  article.setPur_quan(rs.getString("pur_quan"));
				  article.setPur_price(rs.getInt("pur_price"));
				  article.setPur_sum(rs.getInt("pur_sum"));
				  article.setPur_use(rs.getString("pur_use"));
				  article.setPur_note(rs.getString("pur_note"));
				  article.setPur_file_name(rs.getString("pur_file_name"));
				  article.setPur_file_path(rs.getString("pur_file_path"));
				  article.setPur_status_ny(rs.getString("pur_status_ny"));
				  article.setDoc_no(rs.getInt("doc_no"));
				  
				  PurchaseList.add(article);
               
              }while(rs.next());
         }
      }catch(Exception e) {
           e.printStackTrace();
      }finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
      }
      return PurchaseList;
    }
	

}
