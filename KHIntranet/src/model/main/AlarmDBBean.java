package model.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import model.approval.attendance.AttendanceDataBean;
import model.approval.purchase.PurchaseDataBean;
import model.mypage.message.MessageDataBean;

public class AlarmDBBean {
	
private static AlarmDBBean instance = new AlarmDBBean();

	public static AlarmDBBean getInstance() {
		return instance;
	}

	private AlarmDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	// insert
	public void insertAlarm(int emp_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into alarm values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, emp_no);
			pstmt.setInt(2, 1);
			pstmt.setInt(3, 1);
			pstmt.setInt(4, 1);
			pstmt.setInt(5, 1);

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

	// update 채팅
	public void updateChat(int emp_no, int chatID) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "update alarm set chat_no=? where alarm_emp_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatID);
			pstmt.setInt(2, emp_no);

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}

	// update 쪽지
	public void updateMsg(int emp_no, int MSG_NO) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update alarm set msg_no=? where alarm_emp_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, MSG_NO);
			pstmt.setInt(2, emp_no);

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}

	// update 물품
	public void updatePur(int emp_no, int pur_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update alarm set pur_no=? where alarm_emp_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pur_no);
			pstmt.setInt(2, emp_no);
			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}

	// update 근태
	public void updateAtd(int emp_no, int atd_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update alarm set atd_no=? where alarm_emp_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, atd_no);
			pstmt.setInt(2, emp_no);
			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}

	// 채팅수
	public int countChat(int emp_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int chatNumber = 0;

		try {
			conn = getConnection();

			String sql = "select count(*) from chat where chat_no > (select CHAT_NO from ALARM where alarm_emp_no=? ) order by chat_no";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				chatNumber = rs.getInt(1);
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
		return chatNumber;
	}

	// message 가져오기 emp_no
	public List messageList(int emp_no, int ID) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List messageList = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from MESSAGE where MSG_TONO = ? and MSG_NO > ? order by MSG_NO");
			pstmt.setInt(1, emp_no);
			pstmt.setInt(2, ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				messageList = new ArrayList();
				do {
					MessageDataBean msg = new MessageDataBean();

					msg.setMsg_no(rs.getInt("MSG_NO"));
					msg.setMsg_fromno(rs.getInt("MSG_FROMNO"));
					msg.setMsg_tono(rs.getInt("MSG_TONO"));
					msg.setMsg_content(rs.getString("MSG_CONTENT").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
							.replaceAll(">", "&gt;").replaceAll("\n", "<br/>"));
					msg.setMsg_from_time(rs.getString("MSG_FROM_TIME"));

					messageList.add(msg);

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
		return messageList;
	}

	// attendance 가져오기 emp_no
	public List attendanceList(int emp_no, int ID) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List AttendanceList = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select * from attendance where atd_no > ? and substr(ATD_RECEIVER,1,instr(ATD_RECEIVER,',')-1)=? order by atd_no");
			pstmt.setInt(1, ID);
			pstmt.setInt(2, emp_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				AttendanceList = new ArrayList();
				do {
					AttendanceDataBean atd = new AttendanceDataBean();

					atd.setAtd_no(rs.getInt("atd_no"));
					atd.setAtd_emp_no(rs.getInt("atd_emp_no"));
					atd.setAtd_div(rs.getString("atd_div").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
							.replaceAll(">", "&gt;").replaceAll("\n", "<br/>"));
					atd.setAtd_date(rs.getString("atd_date"));

					AttendanceList.add(atd);

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
		return AttendanceList;
	}

	// message 가져오기 emp_no
	public List PurchaseList(int emp_no, int ID) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List purchaseList = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select * from purchase where pur_no > ? and substr(pur_receiver,1,instr(pur_receiver,',')-1)=? order by pur_no");
			pstmt.setInt(1, ID);
			pstmt.setInt(2, emp_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				purchaseList = new ArrayList();
				do {
					PurchaseDataBean pur = new PurchaseDataBean();

					pur.setPur_no(rs.getInt("pur_no"));
					pur.setPur_emp_no(rs.getInt("pur_emp_no"));
					pur.setPur_title(rs.getString("pur_title").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
							.replaceAll(">", "&gt;").replaceAll("\n", "<br/>"));
					pur.setPur_date(rs.getString("pur_date"));

					purchaseList.add(pur);

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
		return purchaseList;
	}

	public List AlarmSList(int emp_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List AllList = null;

		String sql = "select * from(" + "select distinct dd, cc, ss, bb, ff from("
				+ "SELECT decode(mod(rownum-1,3)+1, 1,MSG_FROM_TIME, 2,PUR_DATE,3, ATD_DATE ) bb, decode(mod(rownum-1,3)+1, 1,MSG_TONO, 2,PUR_EMP_NO, 3, ATD_EMP_NO ) cc, decode(mod(rownum-1,3)+1, 1,MSG_CONTENT, 2,PUR_TITLE,3, ATD_DIV) ss,"
				+ "	decode(mod(rownum-1,3)+1, 1,MSG_NO, 2,PUR_NO, 3, ATD_NO ) dd ,decode(mod(rownum-1,3)+1, 1,'1', 2,'2', 3, '3' ) ff"
				+ "	FROM (SELECT 1 FROM dual CONNECT BY LEVEL <=3)," + " (select * from message"
				+ ",(select substr(pur_receiver,1,instr(pur_receiver,',')-1)ptt,PUR_DATE,PUR_EMP_NO,PUR_TITLE,PUR_NO from PURCHASE)"
				+ " ,(select substr(ATD_RECEIVER,1,instr(ATD_RECEIVER,',')-1)att, ATD_DATE, ATD_DIV,ATD_EMP_NO,ATD_NO from ATTENDANCE) at"
				+ " ,(select ALARM_EMP_NO, MSG_NO M, PUR_NO P, ATD_NO A from alarm)"
				+ "where message.MSG_TONO = ptt and message.MSG_TONO = at.att and message.MSG_TONO = ALARM_EMP_NO and MSG_TONO = ?) aa where MSG_NO > M-1 and PUR_NO > P-1 and ATD_NO > A-1"
				+ "order by bb desc)" + "order by bb desc) where rownum >= 1 and rownum <= 10";
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				AllList = new ArrayList();
				do {
					AlarmDataBean pur = new AlarmDataBean();

					pur.setLastID(rs.getInt(1));
					pur.setEmp_no(rs.getInt(2));
					pur.setCountent(rs.getString(3).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
							.replaceAll(">", "&gt;").replaceAll("\n", "<br/>"));
					pur.setTime(rs.getString(4));
					pur.setType(rs.getInt(5));

					AllList.add(pur);

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
		return AllList;
	}

	// 메시지
	public int messageLast(int emp_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;

		try {
			conn = getConnection();

			String sql = "select max(msg_no) from message where msg_tono = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(rs !=null)try {rs.close();}catch(SQLException ex) {}
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
		return i;
	}

	// 근태
	public int attLast(int emp_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;

		try {
			conn = getConnection();

			String sql = "select max(atd_no) from ATTENDANCE where substr(ATD_RECEIVER,1,instr(ATD_RECEIVER,',')-1)=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				i = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(rs !=null)try {rs.close();}catch(SQLException ex) {}
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
		return i;
	}

	// 물품
	public int purLast(int emp_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;

		try {
			conn = getConnection();

			String sql = "select max(pur_no) from PURCHASE where substr(pur_RECEIVER,1,instr(pur_RECEIVER,',')-1)=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				i = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(rs !=null)try {rs.close();}catch(SQLException ex) {}
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
		return i;
	}

}
