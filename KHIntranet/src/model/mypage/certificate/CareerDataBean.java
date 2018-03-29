package model.mypage.certificate;
  
import java.sql.Timestamp;

public class CareerDataBean {
	private int car_emp_no ;
	private String car_dept_name;
	private String car_position_name;
	private String car_start_date;
	private String car_end_date;
	public int getCar_emp_no() {
		return car_emp_no;
	}
	public void setCar_emp_no(int car_emp_no) {
		this.car_emp_no = car_emp_no;
	}
	public String getCar_dept_name() {
		return car_dept_name;
	}
	public void setCar_dept_name(String car_dept_name) {
		this.car_dept_name = car_dept_name;
	}
	public String getCar_position_name() {
		return car_position_name;
	}
	public void setCar_position_name(String car_position_name) {
		this.car_position_name = car_position_name;
	}
	public String getCar_start_date() {
		return car_start_date;
	}
	public void setCar_start_date(String car_start_date) {
		this.car_start_date = car_start_date;
	}
	public String getCar_end_date() {
		return car_end_date;
	}
	public void setCar_end_date(String car_end_date) {
		this.car_end_date = car_end_date;
	}

}