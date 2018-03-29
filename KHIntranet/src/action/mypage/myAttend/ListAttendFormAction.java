package action.mypage.myAttend;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.attendance.AttendanceDBBean;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;

public class ListAttendFormAction implements CommandAction{
	
	public String requestPro (HttpServletRequest request, HttpServletResponse response) throws Throwable{
		
		request.setCharacterEncoding("utf-8");
		
		EmployeeAllDataBean emp_all = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int empAnnual = emp_all.getEmp_annual();
		int atd_emp_no = emp_all.getEmp_no();
		
		List AttendList = null;
		List EmpList  = null;
		int useAnnday = 0;
			
		AttendanceDBBean attendDao = AttendanceDBBean.getInstance(); 
		EmployeeAllDBBean empDao = EmployeeAllDBBean.getInstance();

		AttendList = attendDao.listAttendance(emp_all.getEmp_no());
		EmpList = empDao.selectAll();     
		useAnnday = attendDao.selectUseAnnDate(atd_emp_no);
        
        request.setAttribute("AttendList", AttendList); 
        request.setAttribute("EmpList", EmpList);
        request.setAttribute("empAnnual", empAnnual);
        request.setAttribute("useAnnday", useAnnday);
		
		return "Intranet/Mypage/MyAttend/listAttendForm.jsp";
	}
}
