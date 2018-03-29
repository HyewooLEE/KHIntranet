package model.main;

import java.sql.*;


import javax.naming.*;
import java.util.*;

public class ChatDBBean {

	private static ChatDBBean instance = new ChatDBBean();
	
	public static ChatDBBean getInstance() {
		return instance;
	}

	private ChatDBBean() {}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	
	public ArrayList<ChatDTO> getChatList(String nowTime){  //현재 채팅리스트 불러오는
		Connection conn = null;
		ArrayList<ChatDTO> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from chat where chat_time >= ? order by chat_no";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nowTime);
			rs = pstmt.executeQuery();
			
			chatList = new ArrayList<ChatDTO>();
			
			while(rs.next()) {
				
				ChatDTO chat = new ChatDTO();
				chat.setChat_emp_no(rs.getInt(1));
				chat.setChat_emp_no(rs.getInt(2));
				chat.setChat_emp_name(rs.getString(3));
				chat.setChat_content(rs.getString(4).replaceAll(" ", "&nbsp;").replaceAll("<", "&it;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				
				System.out.println(rs.getString("chat_time"));
				//오전, 오후 나누기
				int chatTime = Integer.parseInt(rs.getString("chat_time").substring(11, 13));
				
				String timeType = "오전";
				if(Integer.parseInt(rs.getString("chat_time").substring(11, 13)) >= 12){
					timeType = "오후";
					chatTime -= 12; 
				}
				
				chat.setChat_time(rs.getString(4).substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chat_time").substring(14, 16) + "");
				
				chatList.add(chat);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();} catch(SQLException ex) {}
			if(pstmt !=null)try {pstmt.close();	}catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
		return chatList;
	}
	
	
	public ArrayList<ChatDTO> getChatListByRecent(int number){  //최근 데이터 불러오기
		Connection conn = null;
		ArrayList<ChatDTO> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select * from chat where chat_no > (select MAX(chat_no) - ? FROM CHAT) order by chat_no";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			
			chatList = new ArrayList<ChatDTO>();
			
			while(rs.next()) {				
	
				ChatDTO chat = new ChatDTO();
				chat.setChat_no(rs.getInt("chat_no"));
				chat.setChat_emp_no(rs.getInt("chat_emp_no"));
				chat.setChat_emp_name(rs.getString(3));
				chat.setChat_content(rs.getString(4).replaceAll(" ", "&nbsp;").replaceAll("<", "&it;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				
				//오전, 오후 나누기
				int chatTime = Integer.parseInt(rs.getString(5).substring(11, 13));
				String timeType = "오전";
				if(Integer.parseInt(rs.getString(5).substring(11, 13)) >= 12){
					timeType = "오후";
					chatTime -= 12; 
				}
				
				chat.setChat_time(rs.getString(5).substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chat_time").substring(14, 16) + "");
				
				chat.setChat_time(rs.getString("chat_time"));
				
				chatList.add(chat);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();} catch(SQLException ex) {}
			if(pstmt !=null)try {pstmt.close();	}catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
		return chatList;
	}
	
	
	public ArrayList<ChatDTO> getChatListByRecent(String chatID){  //최근 데이터 불러오기
		Connection conn = null;
		ArrayList<ChatDTO> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select * from chat where chat_no > ? order by chat_no";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(chatID));
			rs = pstmt.executeQuery();
			
			chatList = new ArrayList<ChatDTO>();
			
			while(rs.next()) {				
				ChatDTO chat = new ChatDTO();
				chat.setChat_no(rs.getInt("chat_no"));
				chat.setChat_emp_no(rs.getInt(2));
				chat.setChat_emp_name(rs.getString(3));
				chat.setChat_content(rs.getString(4).replaceAll(" ", "&nbsp;").replaceAll("<", "&it;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				
				//오전, 오후 나누기
				int chatTime = Integer.parseInt(rs.getString(5).substring(11, 13));
				String timeType = "오전";
				if(Integer.parseInt(rs.getString(5).substring(11, 13)) >= 12){
					timeType = "오후";
					chatTime -= 12; 
				}
				
				chat.setChat_time(rs.getString(5).substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chat_time").substring(14, 16) + "");
				
				chat.setChat_time(rs.getString("chat_time"));
				
				chatList.add(chat);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();} catch(SQLException ex) {}
			if(pstmt !=null)try {pstmt.close();	}catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
		return chatList;
	}
	
	
	
	
	public  int submit(int chatNo,String chatName, String chatContent, String today) {
		Connection conn = null;
		ArrayList<ChatDTO> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "insert into chat values(CHAT_NO_seq.nextval,?, ?, ?, ?)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatNo);
			pstmt.setString(2, chatName);
			pstmt.setString(3, chatContent);
			pstmt.setString(4, today);

			return pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();} catch(SQLException ex) {}
			if(pstmt !=null)try {pstmt.close();	}catch(SQLException ex) {}
			if(conn !=null) try {conn.close();} catch(SQLException ex) {}
		}
		return -1;
	}

}



