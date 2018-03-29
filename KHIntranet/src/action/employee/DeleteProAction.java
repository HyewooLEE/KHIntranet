package action.employee;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeDBBean;

public class DeleteProAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String re = "";
	 	
		int emp_no = Integer.parseInt(request.getParameter("emp_no"));
		
		EmployeeDBBean dbPro = EmployeeDBBean.getInstance(); //DBÃ³¸®
		
		dbPro.deleteMember(emp_no);
		
		return re; //ÇØ´ç ºä
	}
}
