package model.main;

public class AlarmDataBean {
	int LastID;
	int	emp_no;
	String Countent;
	String Time;
	int type;
	
	public int getLastID() {
		return LastID;
	}
	public void setLastID(int lastID) {
		LastID = lastID;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public String getCountent() {
		return Countent;
	}
	public void setCountent(String countent) {
		Countent = countent;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
