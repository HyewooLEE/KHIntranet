package model.mypage.certificate;
  
public class SalaryDataBean {
	private int sal_emp_no;
	private int sal_position_rank;
	private int sal_position_sal;
	private int sal_emp_sal;
	private String sal_start_year;
	private String sal_start_month;
	private String sal_end_year;
	private String sal_end_month;
	private String sal_start_date;
	private String sal_end_date;
	private int sal_sum;
	private int sal_ins_first;
	private int sal_ins_second;
	private int sal_ins_third;
	private int sal_ins_sum;
	
	public int getSal_ins_first() {
		return sal_ins_first;
	}
	public void setSal_ins_first(int sal_emp_sal,int sal_position_rank) {
		this.sal_ins_first =  (int) ((sal_emp_sal + sal_position_rank*100000)* 0.045);
	}
	public int getSal_ins_second() {
		return sal_ins_second;
	}
	public void setSal_ins_second(int sal_emp_sal,int sal_position_rank) {
		this.sal_ins_second =  (int) ((sal_emp_sal + sal_position_rank*100000)* 0.0312);
	}
	public int getSal_ins_third() {
		return sal_ins_third;
	}
	public void setSal_ins_third(int sal_emp_sal,int sal_position_rank) {
		this.sal_ins_third =  (int) ((sal_emp_sal + sal_position_rank*100000)* 0.0065);
	}
	public int getSal_ins_sum() {
		return sal_ins_sum;
	}
	public void setSal_ins_sum(int sal_emp_sal,int sal_position_rank) {
		this.sal_ins_sum =  (int) ((sal_emp_sal + sal_position_rank*100000)* 0.0738);
	}
	public int getSal_position_sal() {
		return sal_position_sal;
	}
	public void setSal_position_sal(int sal_position_sal) {
		this.sal_position_sal = sal_position_sal * 100000;
	}
	public int getSal_sum() {
		return sal_sum;
	}
	public void setSal_sum(int sal_emp_sal,int sal_position_rank ) {
		this.sal_sum = sal_emp_sal + sal_position_rank*100000 + 100000;
	}
	public String getSal_start_date() {
		return sal_start_date;
	}
	public void setSal_start_date(String sal_start_date) {
		this.sal_start_date = sal_start_date;
	}
	public String getSal_end_date() {
		return sal_end_date;
	}
	public void setSal_end_date(String sal_end_date) {
		this.sal_end_date = sal_end_date;
	}
	public String getSal_start_year() {
		return sal_start_year;
	}
	public void setSal_start_year(String sal_start_year) {
		this.sal_start_year = sal_start_year;
	}
	public String getSal_start_month() {
		return sal_start_month;
	}
	public void setSal_start_month(String sal_start_month) {
		this.sal_start_month = sal_start_month;
	}
	public String getSal_end_year() {
		return sal_end_year;
	}
	public void setSal_end_year(String sal_end_year) {
		this.sal_end_year = sal_end_year;
	}
	public String getSal_end_month() {
		return sal_end_month;
	}
	public void setSal_end_month(String sal_end_month) {
		this.sal_end_month = sal_end_month;
	}
	public int getSal_emp_no() {
		return sal_emp_no;
	}
	public void setSal_emp_no(int sal_emp_no) {
		this.sal_emp_no = sal_emp_no;
	}
	public int getSal_position_rank() {
		return sal_position_rank;
	}
	public void setSal_position_rank(int sal_position_rank) {
		this.sal_position_rank = sal_position_rank;
	}
	public int getSal_emp_sal() {
		return sal_emp_sal;
	}
	public void setSal_emp_sal(int sal_emp_sal) {
		this.sal_emp_sal = sal_emp_sal;
	}
}
