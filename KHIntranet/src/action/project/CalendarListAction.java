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
        result.append("[");  //������ �����ؼ�
        
        for(int i=0; i< calendarList.size(); i++) {
           result.append("{\"title\": \"" + calendarList.get(i).getEventTitle() + "\","); // ���� ����
           result.append("\"start\": \"" + calendarList.get(i).getEventStartDate() + "\",");  // ���� ���� ��¥
           result.append("\"end\": \"" + calendarList.get(i).getEventEndDate() + "\"}"); // ���� ���� ��¥ 
           if(i != calendarList.size() -1) result.append(","); 
        }               
          
        result.append("]");
       
        return result.toString();
	} 	
}