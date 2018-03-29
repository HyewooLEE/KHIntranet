package action.project;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDataBean;
public class Calendar implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		EmployeeAllDataBean empPro  = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = empPro.getEmp_no();
		
		request.setAttribute("emp_no", emp_no);
		
		return "Intranet/Project/calendar.jsp";
	}
}