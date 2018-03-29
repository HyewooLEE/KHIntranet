package action.main;
  
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import model.project.CalendarDBBean;

public class CheckCalendarListAction implements CommandAction{

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
       request.setCharacterEncoding("utf-8");                                                          
       response.setContentType("text/html; charset=UTF-8");
       
       int emp_no = Integer.parseInt(request.getParameter("emp_no"));
       String appEmp = getEmpData(emp_no).toString();
      
       return appEmp;
       
    }
    
    
    public StringBuffer getEmpData(int emp_no) throws Exception {
    	StringBuffer result = new StringBuffer("");
       CalendarDBBean calendar = CalendarDBBean.getInstance();
       
       Date dt = new Date();
       DateFormat dtf = new SimpleDateFormat("yyyyMMdd");
       int pro_date = Integer.parseInt(dtf.format(dt));
     
       int eventCount = calendar.getEventList(emp_no, pro_date);
              
       result.append("{\"result\":[");  //변수를 지정해서
       result.append("[{\"value\": \"" + eventCount + "\"}]"); 
       result.append("]}");   
       return result;
    }
    

}