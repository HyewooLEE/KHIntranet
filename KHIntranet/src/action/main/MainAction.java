package action.main;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.attendance.AttendanceDBBean;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.employee.EmployeeDBBean;
import model.notice.NoticeDBBean;
import model.notice.NoticeDataBean;
import model.project.ProjectDBBean;
import model.project.ProjectDataBean;


public class MainAction implements CommandAction{
	

    public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable {

    	request.setCharacterEncoding("UTF-8"); //한글 인코딩
    	EmployeeAllDataBean empPro  = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = empPro.getEmp_no();
    	
		request.getSession(true);
    	
		String emp_id= (String) request.getAttribute("emp_id");
		
		
		EmployeeDBBean dbPr = EmployeeDBBean.getInstance();
		EmployeeAllDBBean dbPro = EmployeeAllDBBean.getInstance();
		
		//휴가자 출력
	      int countHoliday = 0;
	     AttendanceDBBean attDao = AttendanceDBBean.getInstance();
	      countHoliday = attDao.countHoliyday();
	      request.setAttribute("countHoliday",countHoliday);
		
		// 공지사항 action
		NoticeDBBean noti_db = NoticeDBBean.getInstance();
		
		List<NoticeDataBean> noticeList = noti_db.getNoticeMainList();
		int notice_count = noti_db.getNoticeCount();
		
		request.setAttribute("notice_count", notice_count);
		request.setAttribute("noticeList", noticeList);
		
		// 프로젝트 action
		  ProjectDBBean pdb = ProjectDBBean.getInstance();
	           
	      int count = pdb.getCount(emp_no);
	      List<ProjectDataBean> projectPercentList = null;
	      
	      
	      if(count > 0) {
	    	  projectPercentList = pdb.getProjectList(emp_no);  
	      }
	      
	      request.setAttribute("projectPercentList", projectPercentList);
	      request.setAttribute("count", count);
	      
		request.setAttribute("emp_no", emp_no);
		
        return "Intranet/Main/main.jsp";
    }

}