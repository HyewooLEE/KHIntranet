package model.notice;
  
public class NoticeDataBean {
	private int notice_no;
	private int notice_emp_no;
	private String notice_title;
	private String notice_content;
	private String notice_date;
	private String notice_file_name;
	private String notice_file_addr;
	private String notice_password;
	private String emp_name;
		
	public String getNotice_password() {
		return notice_password;
	}
	public void setNotice_password(String notice_password) {
		this.notice_password = notice_password;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	public int getNotice_emp_no() {
		return notice_emp_no;
	}
	public void setNotice_emp_no(int notice_emp_no) {
		this.notice_emp_no = notice_emp_no;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	public String getNotice_file_name() {
		return notice_file_name;
	}
	public void setNotice_file_name(String notice_file_name) {
		this.notice_file_name = notice_file_name;
	}
	public String getNotice_file_addr() {
		return notice_file_addr;
	}
	public void setNotice_file_addr(String notice_file_addr) {
		this.notice_file_addr = notice_file_addr;
	}
	
}
