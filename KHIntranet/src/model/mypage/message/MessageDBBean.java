package model.mypage.message;
       
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class MessageDBBean {
	private static MessageDBBean instance = new MessageDBBean();

		public static MessageDBBean getInstance() {
			return instance;
		}
		
		private MessageDBBean() {}
		
		private Connection getConnection() throws Exception{
			String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
			return DriverManager.getConnection(jdbcDriver);
		}
		
		//메시지 보낼때 사원번호 얻기2
				public int getEMP_NO2(String emp_name) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					int emp_no = 0;
					try {
						conn = getConnection();
						String sql = "SELECT EMP_NO FROM EMPLOYEE WHERE EMP_NAME = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, emp_name);
						rs = pstmt.executeQuery();
						if(rs.next()) {
							emp_no = rs.getInt(1);
						}else {
							emp_no = -1;
							return emp_no;
						}
					}catch(Exception e) {
							e.printStackTrace();
					}finally {
						JdbcUtil.close(rs);
						JdbcUtil.close(pstmt);
						JdbcUtil.close(conn);
					}
					return emp_no;		
				}
				//메시지 보낼때 사원번호 얻기2
				public String getEMP_NAME(int emp_no) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String emp_name = "";
					try {
						conn = getConnection();
						String sql = "SELECT EMP_NAME FROM EMPLOYEE WHERE EMP_NO = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, emp_no);
						rs = pstmt.executeQuery();
						if(rs.next()) {
							emp_name = rs.getString(1);
						}else {
							emp_name = "-1";
							return emp_name;
						}
					}catch(Exception e) {
							e.printStackTrace();
					}finally {
						JdbcUtil.close(rs);
						JdbcUtil.close(pstmt);
						JdbcUtil.close(conn);
					}
					return emp_name;		
				}		
		
		// 메세지 보내기 
		public int sendMessage(int from_no, int to_no,String msg_content,String now) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			ResultSet rs = null;
			int x = 0;
			try {
				conn= getConnection();
				String sql = "select count(*) from employee where emp_no = ? ";
				pstmt= conn.prepareStatement(sql);
				pstmt.setInt(1, to_no);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x =rs.getInt(1);
					if(x==1) {
						String sql2 = "insert into Message values(MESSAGE_SEQ.NEXTVAl , ? , ? , ? , ? , '0' , 0 , 0 , 0)";
						pstmt= conn.prepareStatement(sql2);
						pstmt.setInt(1, from_no);
						pstmt.setInt(2, to_no);
						pstmt.setString(3, msg_content);
						pstmt.setString(4, now);
						x = pstmt.executeUpdate();
					}else if(x==0) {
						return x;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
			}
			return x;	
		}
		//안 읽은 메시지 개수
		public int getUnreadMessageTotalCount(int emp_no) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int unreadCount = 0;
			
			String sql = "SELECT COUNT(*) FROM MESSAGE WHERE MSG_TONO = ? AND MSG_TO_TIME = '0' AND MSG_TO_STATUS= 0";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, emp_no);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					unreadCount = rs.getInt(1);		
				}else {
					unreadCount = -1;
					return unreadCount;
				}
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
					JdbcUtil.close(conn);
				}
				return unreadCount;			
		}
		
		// 받은 메시지 개수
		public int getReceivedMessageTotalCount(int emp_no) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int totalCount = 0;
			
			String sql = "SELECT COUNT(*) FROM MESSAGE WHERE MSG_TONO = ? AND MSG_TO_STATUS = 0";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, emp_no);
				rs = pstmt.executeQuery();
				if(rs.next()) {
			
					totalCount = rs.getInt(1);
					
				}else {
					totalCount = -1;
					return totalCount;
				}
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
					JdbcUtil.close(conn);
				}
				return totalCount;			
		}
		// 보낸 메시지 개수
				public int getSendMessageTotalCount(int emp_no) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					int totalCount = 0;
					
					String sql = "SELECT COUNT(*) FROM MESSAGE WHERE MSG_FROMNO = ?  AND MSG_FROM_STATUS = 0";
					try {
						conn = getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, emp_no);
						rs = pstmt.executeQuery();
						if(rs.next()) {
					
							totalCount = rs.getInt(1);
							
						}else {
							totalCount = -1;
							return totalCount;
						}
						}catch (Exception e) {
							e.printStackTrace();
						}finally {
							JdbcUtil.close(rs);
							JdbcUtil.close(pstmt);
							JdbcUtil.close(conn);
						}
						return totalCount;			
				}
		// 휴지통 메시지 개수
				public int getDeletedMessageTotalCount(int emp_no) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					int totalCount = 0;
					
					String sql = "SELECT COUNT(*) FROM MESSAGE WHERE MSG_TONO = ? and MSG_TO_STATUS = 1";
					try {
						conn = getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, emp_no);
						rs = pstmt.executeQuery();
						if(rs.next()) {				
							totalCount = rs.getInt(1);
		
						}else {
							totalCount = -1;
							return totalCount;
						}
						}catch (Exception e) {
							e.printStackTrace();
						}finally {
							JdbcUtil.close(rs);
							JdbcUtil.close(pstmt);
							JdbcUtil.close(conn);
						}
						return totalCount;			
				}
				
		
		// 받은 메시지 리스트 얻기
		public List getMessages(int emp_no,int start,int end)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List messageList = null;
			String sql = "SELECT MSG_NO,MSG_FROMNO,MSG_TONO,MSG_CONTENT,MSG_FROM_TIME,MSG_TO_TIME,MSG_IMPORTANT,MSG_FROM_STATUS,MSG_TO_STATUS,R FROM ( SELECT MSG_NO,MSG_FROMNO,MSG_TONO,MSG_CONTENT,MSG_FROM_TIME,MSG_TO_TIME,MSG_IMPORTANT,MSG_FROM_STATUS,MSG_TO_STATUS,ROWNUM R FROM MESSAGE WHERE MSG_TONO= ? AND MSG_TO_STATUS = 0 ORDER BY MSG_FROM_TIME DESC)  WHERE R >= ? AND R <= ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, emp_no);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs=pstmt.executeQuery();
				if(rs.next()) {
				messageList = new ArrayList(end);
				do {
					MessageDataBean msg = new MessageDataBean();
					msg.setMsg_no(rs.getInt("MSG_NO"));
					msg.setMsg_fromno(rs.getInt("MSG_FROMNO"));
					msg.setMsg_tono(rs.getInt("MSG_TONO"));
					msg.setMsg_content(rs.getString("MSG_CONTENT"));
					msg.setMsg_from_time(rs.getString("MSG_FROM_TIME"));
					msg.setMsg_to_time(rs.getString("MSG_TO_TIME"));
					msg.setMsg_important(rs.getInt("MSG_IMPORTANT"));
					msg.setMsg_from_status(rs.getInt("MSG_FROM_STATUS"));
					msg.setMsg_to_status(rs.getInt("MSG_TO_STATUS"));
					messageList.add(msg);
				}while(rs.next());
					
				
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
			}
			return messageList;
		}
		// 보낸 메시지 리스트 얻기
		public List getSendMessages(int emp_no,int start,int end)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List messageList = null;
			String sql = "SELECT MSG_NO,MSG_FROMNO,MSG_TONO,MSG_CONTENT,MSG_FROM_TIME,MSG_TO_TIME,MSG_IMPORTANT,MSG_FROM_STATUS,MSG_TO_STATUS,R FROM (SELECT MSG_NO,MSG_FROMNO,MSG_TONO,MSG_CONTENT,MSG_FROM_TIME,MSG_TO_TIME,MSG_IMPORTANT,MSG_FROM_STATUS,MSG_TO_STATUS,ROWNUM R FROM MESSAGE WHERE MSG_FROMNO= ? AND MSG_FROM_STATUS = 0 ORDER BY MSG_FROM_TIME DESC)  WHERE R >= ? AND R <= ?";
			
 
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, emp_no);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
				messageList = new ArrayList(end); 
				do{
					MessageDataBean msg = new MessageDataBean();
					msg.setMsg_no(rs.getInt("MSG_NO"));
					msg.setMsg_fromno(rs.getInt("MSG_FROMNO"));
					msg.setMsg_tono(rs.getInt("MSG_TONO"));
					msg.setMsg_content(rs.getString("MSG_CONTENT"));
					msg.setMsg_from_time(rs.getString("MSG_FROM_TIME"));	
					msg.setMsg_to_time(rs.getString("MSG_TO_TIME"));
					msg.setMsg_important(rs.getInt("MSG_IMPORTANT"));
					msg.setMsg_from_status(rs.getInt("MSG_FROM_STATUS"));
					msg.setMsg_to_status(rs.getInt("MSG_TO_STATUS"));
					messageList.add(msg);
				}while(rs.next());
			};
						
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
			}
			return messageList;
		}
		
		//휴지통 쪽지 리스트 얻기
		public List getDeletedMessages(int emp_no,int start,int end)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List messageList = null;
			String sql = "SELECT MSG_NO,MSG_FROMNO,MSG_TONO,MSG_CONTENT,MSG_FROM_TIME,MSG_TO_TIME,MSG_IMPORTANT,MSG_FROM_STATUS,MSG_TO_STATUS,R FROM (SELECT MSG_NO,MSG_FROMNO,MSG_TONO,MSG_CONTENT,MSG_FROM_TIME,MSG_TO_TIME,MSG_IMPORTANT,MSG_FROM_STATUS,MSG_TO_STATUS,ROWNUM R FROM MESSAGE WHERE MSG_TONO= ? AND MSG_TO_STATUS = 1 ORDER BY MSG_FROM_TIME DESC)  WHERE R >= ? AND R <= ?";			

			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, emp_no);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs=pstmt.executeQuery();
				if(rs.next()) {
				messageList = new ArrayList(end);
				do {
					MessageDataBean msg = new MessageDataBean();
					msg.setMsg_no(rs.getInt("MSG_NO"));
					msg.setMsg_fromno(rs.getInt("MSG_FROMNO"));
					msg.setMsg_tono(rs.getInt("MSG_TONO"));
					msg.setMsg_content(rs.getString("MSG_CONTENT"));
					msg.setMsg_from_time(rs.getString("MSG_FROM_TIME"));	
					msg.setMsg_to_time(rs.getString("MSG_TO_TIME"));
					msg.setMsg_important(rs.getInt("MSG_IMPORTANT"));
					msg.setMsg_from_status(rs.getInt("MSG_FROM_STATUS"));
					msg.setMsg_to_status(rs.getInt("MSG_TO_STATUS"));
					messageList.add(msg);
				}while(rs.next());
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
			}
			return messageList;
		}
		

		public MessageDataBean getMessage(int msg_no,String status) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MessageDataBean msg = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    String now = sdf.format(new Timestamp(System.currentTimeMillis())); 
			try {
				conn = getConnection();
				if(status.equals("2")) {
					pstmt = conn.prepareStatement("SELECT * FROM MESSAGE WHERE MSG_NO = ?");
		            pstmt.setInt(1, msg_no);
		            rs = pstmt.executeQuery();
					while(rs.next()) {
					msg = new MessageDataBean();
					msg.setMsg_no(rs.getInt("MSG_NO"));
					msg.setMsg_fromno(rs.getInt("MSG_FROMNO"));
					msg.setMsg_content(rs.getString("MSG_CONTENT").replaceAll(" ","&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br/>"));
					msg.setMsg_from_time(rs.getString("MSG_FROM_TIME"));	
					msg.setMsg_to_time(rs.getString("MSG_TO_TIME"));
					msg.setMsg_important(rs.getInt("MSG_IMPORTANT"));
					msg.setMsg_from_status(rs.getInt("MSG_FROM_STATUS"));
					msg.setMsg_to_status(rs.getInt("MSG_TO_STATUS"));
					}
				}else {
		            pstmt = conn.prepareStatement("UPDATE MESSAGE SET MSG_TO_TIME = ?  WHERE MSG_NO = ? AND MSG_TO_TIME = '0'");
		            pstmt.setString(1, now);
		            pstmt.setInt(2, msg_no);
		            pstmt.executeUpdate();	    
		            pstmt.close();
		            
		            pstmt = conn.prepareStatement("SELECT * FROM MESSAGE WHERE MSG_NO = ?");
		            pstmt.setInt(1, msg_no);
		            rs = pstmt.executeQuery();
					while(rs.next()) {
					msg = new MessageDataBean();
					msg.setMsg_no(rs.getInt("MSG_NO"));
					msg.setMsg_fromno(rs.getInt("MSG_FROMNO"));
					msg.setMsg_content(rs.getString("MSG_CONTENT").replaceAll(" ","&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br/>"));
					msg.setMsg_from_time(rs.getString("MSG_FROM_TIME"));	
					msg.setMsg_to_time(rs.getString("MSG_TO_TIME"));
					msg.setMsg_important(rs.getInt("MSG_IMPORTANT"));
					msg.setMsg_from_status(rs.getInt("MSG_FROM_STATUS"));
					msg.setMsg_to_status(rs.getInt("MSG_TO_STATUS"));
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);				
			}
			return msg;
		}
		//휴지통으로 넣기
		  public int deleteMessage(int msg_no) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					int x = 0;
					try {
						conn = getConnection();
			            pstmt = conn.prepareStatement("UPDATE MESSAGE SET MSG_TO_STATUS = 1 WHERE MSG_NO = ?");
			            pstmt.setInt(1, msg_no);
			            x = pstmt.executeUpdate();	          
					}catch(Exception ex) {
						ex.printStackTrace();
					}finally {
					
						JdbcUtil.close(pstmt);
						JdbcUtil.close(conn);				
					}
					return x;
				}
	   //보낸 매세지 삭제
		  public int deleteSendMessage(int msg_no) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int x = 0;
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement("SELECT MSG_TO_STATUS FROM MESSAGE WHERE MSG_NO = ? ");
					pstmt.setInt(1, msg_no);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						int check =  rs.getInt(1);
						pstmt.close();
						if(check==0 || check==1) {
							pstmt = conn.prepareStatement("UPDATE MESSAGE SET MSG_FROM_STATUS = 1 WHERE MSG_NO = ?");
				            pstmt.setInt(1, msg_no);
				            x = pstmt.executeUpdate();	     
						}else {
							pstmt = conn.prepareStatement("DELETE FROM MESSAGE WHERE MSG_NO = ?");
				            pstmt.setInt(1, msg_no);
				            x = pstmt.executeUpdate();	     
						}
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
				
					JdbcUtil.close(pstmt);
					JdbcUtil.close(conn);				
				}
				return x;
			}
		  //받은 메세지 완전 삭제
		  public int deleteReceiveMessage(int msg_no) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int x = 0;
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement("SELECT MSG_FROM_STATUS FROM MESSAGE WHERE MSG_NO = ? ");
					pstmt.setInt(1, msg_no);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						int check =  rs.getInt(1);
						pstmt.close();
						if(check==0) {
							pstmt = conn.prepareStatement("UPDATE MESSAGE SET MSG_TO_STATUS = 2 WHERE MSG_NO = ?");
				            pstmt.setInt(1, msg_no);
				            x = pstmt.executeUpdate();	     
						}else {
							pstmt = conn.prepareStatement("DELETE FROM MESSAGE WHERE MSG_NO = ?");
				            pstmt.setInt(1, msg_no);
				            x = pstmt.executeUpdate();	     
						}
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
				
					JdbcUtil.close(pstmt);
					JdbcUtil.close(conn);				
				}
				return x;
			}
		  
		  
		  //중요처리하기
		  public int updateImportant(int msg_no)throws Exception{
			  Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int check = 0;
				try {
					conn = getConnection();
						pstmt = conn.prepareStatement("select msg_important from message where msg_no = ?");
						pstmt.setInt(1, msg_no);
						rs = pstmt.executeQuery();
						if(rs.next()) {
						 	check = 	rs.getInt(1);
						 	pstmt.close();
						}
					if(check==0) {
						pstmt = conn.prepareStatement("update message set msg_important = 1 where msg_important = 0 and msg_no =? ");
						pstmt.setInt(1, msg_no);
						pstmt.executeUpdate();
						return 1;
					}else {
						pstmt = conn.prepareStatement("update message set msg_important = 0 where msg_important = 1 and msg_no =? ");
						pstmt.setInt(1, msg_no);
						pstmt.executeUpdate();
						return 0;
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
				
					JdbcUtil.close(pstmt);
					JdbcUtil.close(conn);				
				}
				return -1;
		  }
		  
		  
		  //복구 처리하기
		  public int restoreMessage(int msg_no) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				int x = 0;
				try {
					conn = getConnection();
		            pstmt = conn.prepareStatement("UPDATE MESSAGE SET MSG_TO_STATUS = 0 WHERE MSG_NO = ?");
		            pstmt.setInt(1, msg_no);
		            x = pstmt.executeUpdate();	          
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
				
					JdbcUtil.close(pstmt);
					JdbcUtil.close(conn);				
				}
				return x;
			}
		  
		
}
