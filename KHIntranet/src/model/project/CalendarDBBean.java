package model.project;
import java.sql.*;
import java.util.*;
  
public class CalendarDBBean { //DB와 관련된 일을 하는 클래스 : DBBean, DAO
	
	private static CalendarDBBean instance = new CalendarDBBean();

	public static CalendarDBBean getInstance() {
		return instance;
	}
	
	private CalendarDBBean() {}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	// Calendar Event 등록
	public void insertEvent(CalendarDataBean calendar) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "INSERT INTO CALENDAR VALUES (CALENDAR_SEQ.NEXTVAL,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, calendar.getEmp_no());
			pstmt.setString(2, calendar.getEventTitle());
			pstmt.setString(3, calendar.getEventStartDate());
			pstmt.setString(4, calendar.getEventEndDate());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if(conn != null) try {conn.close();} catch(SQLException ex) {}
		}
	}
	// Calendar Event 수정
	public void updateEvent(CalendarDataBean calendar, String dt_title, String dt_start, String dt_end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "UPDATE CALENDAR SET CAL_TITLE=?, CAL_START_DATE=?, CAL_END_DATE=? WHERE CAL_EMP_NO = ? AND CAL_TITLE = ? AND CAL_START_DATE = ? AND CAL_END_DATE = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, calendar.getEventTitle());
			pstmt.setString(2, calendar.getEventStartDate());
			pstmt.setString(3, calendar.getEventEndDate());
			pstmt.setInt(4, calendar.getEmp_no());
			pstmt.setString(5, dt_title);
			pstmt.setString(6, dt_start);
			pstmt.setString(7, dt_end);
			
			pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
	}
	// Calendar Event 삭제
	public void deleteEvent(CalendarDataBean calendar) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM CALENDAR WHERE CAL_EMP_NO = ? AND CAL_TITLE = ? AND CAL_START_DATE = ? AND CAL_END_DATE = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, calendar.getEmp_no());
			pstmt.setString(2, calendar.getEventTitle());
			pstmt.setString(3, calendar.getEventStartDate());
			pstmt.setString(4, calendar.getEventEndDate());
			
			pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) { conn.close(); }
			if(pstmt != null) { pstmt.close(); }
		}
	}
	// Calendar Event 리스트 가지고오기
	public List<CalendarDataBean> getEventList(int emp_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CalendarDataBean> eventList = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT * FROM CALENDAR WHERE CAL_EMP_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp_no);
			rs = pstmt.executeQuery();
			
			eventList = new ArrayList<CalendarDataBean>();
			if(rs.next()) {
				do {
					CalendarDataBean cal = new CalendarDataBean();
					
					cal.setEventNo(rs.getInt("cal_no"));
					cal.setEmp_no(rs.getInt("cal_emp_no"));
					cal.setEventTitle(rs.getString("cal_title"));
					cal.setEventStartDate(rs.getString("cal_start_date"));
					cal.setEventEndDate(rs.getString("cal_end_date"));
					
					eventList.add(cal);
				} while(rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) { rs.close(); }
			if(pstmt != null) { pstmt.close(); }
			if(conn != null) { conn.close(); }
		}
		return eventList;
	}
	public int getEventList(int emp_no, int today) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CalendarDataBean> eventList = null;
		int count = 0;
		try {
			conn = getConnection();
			String sql = "SELECT CAL_START_DATE, CAL_END_DATE FROM CALENDAR WHERE CAL_EMP_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp_no);
			rs = pstmt.executeQuery();
			
			eventList = new ArrayList<CalendarDataBean>();
			if(rs.next()) {
				do {
					CalendarDataBean cal = new CalendarDataBean();
					
					cal.setEventStartDate(rs.getString("cal_start_date").replace("-", ""));
					cal.setEventEndDate(rs.getString("cal_end_date").replace("-", ""));
					
					eventList.add(cal);
				} while(rs.next());
			}
			
			count = getEventCount(eventList, today, emp_no);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) { rs.close(); }
			if(pstmt != null) { pstmt.close(); }
			if(conn != null) { conn.close(); }
		}
		return count;
	}
	
	public int getEventCount(List<CalendarDataBean> eventList, int today, int emp_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int eventCount = 0;	
		for(int i=0; i<eventList.size(); i++) {
			int cal_start_date = Integer.parseInt(eventList.get(i).getEventStartDate());
			int cal_end_date = Integer.parseInt(eventList.get(i).getEventEndDate());
			
			try {
				conn = getConnection();
				String sql = "SELECT COUNT(*) FROM CALENDAR WHERE CAL_EMP_NO=? AND ? BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, emp_no);
				pstmt.setInt(2, today);
				pstmt.setInt(3, cal_start_date);
				pstmt.setInt(4, cal_end_date);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getInt(1) != 0) {
						eventCount ++;
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(rs != null) try {rs.close();} catch(Exception e) {} 
				if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
				if(conn != null) try { conn.close(); } catch(Exception e) {}
			}
		}
		
		return eventCount;
	}
	
	// Calendar Event 가져오기
	public CalendarDataBean getEvent(CalendarDataBean calendar) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CalendarDataBean cal = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT * FROM CALENDAR WHERE CAL_NO = ? AND CAL_EMP_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, calendar.getEventNo());
			pstmt.setInt(2, calendar.getEmp_no());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cal = new CalendarDataBean();
				cal.setEmp_no(rs.getInt("cal_emp_no"));
				cal.setEventTitle(rs.getString("cal_title"));
				cal.setEventContent(rs.getString("cal_content"));
				cal.setEventStartDate(rs.getString("cal_start_date"));
				cal.setEventEndDate(rs.getString("cal_end_date"));
			}
		} catch(Exception e ) { 
			e.printStackTrace();
		} finally {
			if(rs != null) { rs.close(); }
			if(pstmt != null) { pstmt.close(); }
			if(conn != null) { conn.close(); }
		}
		return cal;
	}
}
