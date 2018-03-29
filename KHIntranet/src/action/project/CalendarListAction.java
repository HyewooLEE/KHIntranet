package action.project;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.project.CalendarDBBean;
import model.project.CalendarDataBean;

public class CalendarListAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int emp_no = Integer.parseInt(request.getParameter("emp_no"));
		System.out.println(emp_no);
		StringBuffer result = new StringBuffer("");
        CalendarDBBean cal = CalendarDBBean.getInstance();
        List<CalendarDataBean> calendarList = cal.getEventList(emp_no);
        result.append("[");  //변수를 지정해서
        
        for(int i=0; i< calendarList.size(); i++) {
           result.append("{\"title\": \"" + calendarList.get(i).getEventTitle() + "\","); // 일정 제목
           result.append("\"start\": \"" + calendarList.get(i).getEventStartDate() + "\",");  // 일정 시작 날짜
           result.append("\"end\": \"" + calendarList.get(i).getEventEndDate() + "\"}"); // 일정 종료 날짜 
           if(i != calendarList.size() -1) result.append(","); 
        }               
          
        result.append("]");
       
        return result.toString();
	} 	
}