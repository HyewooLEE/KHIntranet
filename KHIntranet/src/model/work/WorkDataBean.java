package model.work;
  
public class WorkDataBean {
	private int work_no;
	private int work_emp_no;
	private String work_ct_pb;
	private String work_ct_sg;
	private String work_date;
	private String work_check;
	private String work_file_name;
	private String work_file_addr;
	private String pro_percent;
	private int pro_no;
	private String pro_title;
	private String pro_participant;
		
	public String getPro_participant() {
		return pro_participant;
	}
	public void setPro_participant(String pro_participant) {
		this.pro_participant = pro_participant;
	}
	public String getPro_title() {
		return pro_title;
	}
	public void setPro_title(String pro_title) {
		this.pro_title = pro_title;
	}
	public int getWork_no() {
		return work_no;
	}
	public void setWork_no(int work_no) {
		this.work_no = work_no;
	}
	public int getWork_emp_no() {
		return work_emp_no;
	}
	public void setWork_emp_no(int work_emp_no) {
		this.work_emp_no = work_emp_no;
	}
	public String getWork_ct_pb() {
		return work_ct_pb;
	}
	public void setWork_ct_pb(String work_ct_pb) {
		this.work_ct_pb = work_ct_pb;
	}
	public String getWork_ct_sg() {
		return work_ct_sg;
	}
	public void setWork_ct_sg(String work_ct_sg) {
		this.work_ct_sg = work_ct_sg;
	}
	public String getWork_date() {
		return work_date;
	}
	public void setWork_date(String work_date) {
		this.work_date = work_date;
	}
	public String getWork_check() {
		return work_check;
	}
	public void setWork_check(String work_check) {
		this.work_check = work_check;
	}
	public String getWork_file_name() {
		return work_file_name;
	}
	public void setWork_file_name(String work_file_name) {
		this.work_file_name = work_file_name;
	}
	public String getWork_file_addr() {
		return work_file_addr;
	}
	public void setWork_file_addr(String work_file_addr) {
		this.work_file_addr = work_file_addr;
	}
	public int getPro_no() {
		return pro_no;
	}
	public void setPro_no(int pro_no) {
		this.pro_no = pro_no;
	}
	public String getPro_percent() {
		return pro_percent;
	}
	public void setPro_percent(String pro_percent) {
		this.pro_percent = pro_percent;
	}
}
