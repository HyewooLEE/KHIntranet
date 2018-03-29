package model.photo;
    
import java.sql.Timestamp;

public class PhotoCommentDataBean {
 
    // 코멘트 번호 
    private int comm_no;

    // 작성자 
    private String comm_writer;

    // 코멘트 내용 
    private String comm_content;

    // 비밀번호 
    private String comm_password;
    
    // 작성일
    private Timestamp comm_date;

    // 글번호 
    private int photo_no;

    public int getComm_no() {
        return comm_no;
    }

    public void setCommNo(int comm_no) {
        this.comm_no = comm_no;
    }

    public String getComm_writer() {
        return comm_writer;
    }

    public void setComm_writer(String comm_writer) {
        this.comm_writer = comm_writer;
    }

    public String getComm_content() {
        return comm_content;
    }

    public void setComm_content(String comm_content) {
        this.comm_content = comm_content;
    }

    public String getComm_password() {
        return comm_password;
    }

    public void setComm_password(String comm_password) {
        this.comm_password = comm_password;
    }

    public int getPhoto_no() {
        return photo_no;
    }

    public void setPhoto_no(int photo_no) {
        this.photo_no = photo_no;
    }

    public Timestamp getComm_date() {
      return comm_date;
   }

   public void setComm_date(Timestamp comm_date) {
      this.comm_date = comm_date;
   }

   // PhotoComment 모델 복사
    public void CopyData(PhotoCommentDataBean param)
    {
        this.comm_no = param.getComm_no();
        this.comm_writer = param.getComm_writer();
        this.comm_content = param.getComm_content();
        this.comm_password = param.getComm_password();
        this.photo_no = param.getPhoto_no();
    }
}