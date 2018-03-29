package action.work;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDataBean;
import model.work.WorkDBBean;
import model.work.WorkDataBean;

public class ListWorkAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		EmployeeAllDataBean empPro  = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = empPro.getEmp_no();
		String emp_name = empPro.getEmp_name();
		WorkDBBean db = WorkDBBean.getInstance();
		
		List<WorkDataBean> workList = db.getWorkList(emp_no);
		int workCount = db.getWorkCount(emp_no);
		
		request.setAttribute("emp_name", emp_name);
		request.setAttribute("workCount", workCount);
		request.setAttribute("workList", workList);
				
		return "Intranet/Work/listWork.jsp";
	}
}
