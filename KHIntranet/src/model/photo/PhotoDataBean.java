package model.photo;
    
import java.sql.Timestamp;

public class PhotoDataBean {
 
    private int photo_no;
    private int emp_no;
    private String photo_title;
    private String photo_content;
    private String photo_date;
    private String photo_file_nm;
    private String photo_file_addr;
    private String password;
    
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public int getPhoto_no() {
      return photo_no;
   }
   public void setPhoto_no(int photo_num) {
      this.photo_no = photo_num;
   }
   public int getEmp_no() {
      return emp_no;
   }
   public void setEmp_no(int emp_no) {
      this.emp_no = emp_no;
   }
   public String getPhoto_title() {
      return photo_title;
   }
   public void setPhoto_title(String photo_title) {
      this.photo_title = photo_title;
   }
   public String getPhoto_content() {
      return photo_content;
   }
   public void setPhoto_content(String photo_content) {
      this.photo_content = photo_content;
   }
   public String getPhoto_date() {
	return photo_date;
}
public void setPhoto_date(String photo_date) {
	this.photo_date = photo_date;
}
public String getPhoto_file_nm() {
      return photo_file_nm;
   }
   public void setPhoto_file_nm(String photo_file_nm) {
      this.photo_file_nm = photo_file_nm;
   }
   public String getPhoto_file_addr() {
      return photo_file_addr;
   }
   public void setPhoto_file_addr(String photo_file_addr) {
      this.photo_file_addr = photo_file_addr;
   }
}