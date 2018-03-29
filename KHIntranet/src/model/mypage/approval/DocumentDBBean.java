package model.mypage.approval;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentDBBean {
	
	private static DocumentDBBean instance = new DocumentDBBean();

	public static DocumentDBBean getInstance() {
		return instance;
	}

	private DocumentDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	
	//문서전체 리스트로 뽑아오기
	public List listDoc() throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List DocList = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from DOCUMENT order by doc_no");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				DocList = new ArrayList();
				do {
					DocumentDataBean docdto = new DocumentDataBean();

					docdto.setDoc_no(rs.getInt("doc_no"));
					docdto.setDoc_name(rs.getString("doc_name"));
					
					DocList.add(docdto);

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
		return DocList;
	}
	
	
	//문서번호로 이름 뽑아오기
	/*public String selectDocName(int doc_no) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String DocName = "";

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select doc_name from DOCUMENT where doc_no=?");
			pstmt.setInt(1, doc_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				DocName = rs.getString(1);
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
		return DocName;
	}*/

	
	//테이블명,문서결재상태로 문서정보 뽑아오기
	/*public List selectDoc(String tableName, String docStatus) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List selectDocList = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from ? n, document d where n.doc_no = d.doc_no");
			pstmt.setString(1, tableName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				selectDocList = new ArrayList();
				do {
					DocumentDataBean docdto = new DocumentDataBean();

					docdto.setDoc_no(rs.getInt("doc_no"));
					docdto.setDoc_name(rs.getString("doc_name"));
					
					selectDocList.add(docdto);

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
		return selectDocList;
	}*/
	
}
