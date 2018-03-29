package action.approval.attendance;
  
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import model.approval.attendance.AttendanceDBBean;
import model.approval.attendance.AttendanceDataBean;

public class WriteAttendFormProAction  implements CommandAction{
   
   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
      
      request.setCharacterEncoding("utf-8");
            
      int size = 1024 *1024 * 10; //10¸Þ°¡

      String savePath = "d://fileUpload";

      try{
         MultipartRequest multi = new MultipartRequest(request, savePath, size, "utf-8", new DefaultFileRenamePolicy());
         
         AttendanceDataBean article = new AttendanceDataBean();
         
         article.setAtd_emp_no(Integer.parseInt(multi.getParameter("atd_emp_no")));
         //article.setAtd_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         article.setAtd_date(sdf.format(new Timestamp(System.currentTimeMillis())));
         article.setAtd_receiver(multi.getParameter("atd_receiver").replaceAll("\\p{Z}", "")+','+Integer.parseInt(multi.getParameter("atd_emp_no")));
         article.setAtd_div(multi.getParameter("atd_div"));
         article.setAtd_start_date(multi.getParameter("atd_start_date"));
         article.setAtd_end_date(multi.getParameter("atd_end_date"));
         article.setAtd_note(multi.getParameter("atd_note"));
         article.setAtd_status_ny(multi.getParameter("atd_status_ny"));
         article.setAtd_file_name(multi.getFilesystemName("atd_file_name"));
         if(multi.getFilesystemName("atd_file_name") != null) {
            /*article.setAtd_file_path(savePath + "/" + multi.getFilesystemName("atd_file_name"));*/
            article.setAtd_file_path(savePath);
         }else {
            article.setAtd_file_path("null"); 
         }
         article.setDoc_no(Integer.parseInt(multi.getParameter("doc_no")));
         
         AttendanceDBBean attendDao = AttendanceDBBean.getInstance();
         attendDao.insertAttendance(article);
         
      }
      
      catch(Exception e){
         e.printStackTrace();
      }
      
      return "Intranet/Approval/Attendance/writeAttendFormPro.jsp";                                            
   }

}