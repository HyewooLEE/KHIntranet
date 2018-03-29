package model.main;

public class ChatDTO {
	int	   chat_no;
	int    chat_emp_no;
	String    chat_emp_name;
	String   chat_content;
	String   chat_time;
	
	public int getChat_no() {
		return chat_no;
	}
	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}
	public int getChat_emp_no() {
		return chat_emp_no;
	}
	public void setChat_emp_no(int chat_emp_no) {
		this.chat_emp_no = chat_emp_no;
	}
	public String getChat_emp_name() {
		return chat_emp_name;
	}
	public void setChat_emp_name(String chat_emp_name) {
		this.chat_emp_name = chat_emp_name;
	}
	public String getChat_content() {
		return chat_content;
	}
	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}
	public String getChat_time() {
		return chat_time;
	}
	public void setChat_time(String chat_time) {
		this.chat_time = chat_time;
	}
	
	
}
