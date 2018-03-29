package action.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.project.CalendarDBBean;
import model.project.CalendarDataBean;
  
public class CalenadarProAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		CalendarDBBean db = CalendarDBBean.getInstance();
		CalendarDataBean cdb = new CalendarDataBean();
		
		cdb.setEmp_no(Integer.parseInt(request.getParameter("emp_no")));
		cdb.setEventTitle(request.getParameter("eventTitle"));
		cdb.setEventStartDate(request.getParameter("eventStartDate"));
		cdb.setEventEndDate(request.getParameter("eventEndDate"));
		int cal_ud = Integer.parseInt(request.getParameter("cal_ud")) ;
		
	
		if(cal_ud == 0) {
			db.insertEvent(cdb);
		} else if(cal_ud == 1) {
			String dt_title = request.getParameter("dt_title");
			String dt_start = request.getParameter("dt_start");
			String dt_end = request.getParameter("dt_end");
			db.updateEvent(cdb, dt_title, dt_start, dt_end);
		} else if(cal_ud == 2) {
			db.deleteEvent(cdb);
		}	
		return "Intranet/Project/calendarPro.jsp";
	}
}