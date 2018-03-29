package action.employee;
       
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeDBBean;

public class ConfindAction implements CommandAction{  //�� �Է� ��ó��
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		
		request.setCharacterEncoding("UTF-8"); //�ѱ� ���ڵ�

		String emp_id= request.getParameter("emp_id");
		
		EmployeeDBBean dbPro = EmployeeDBBean.getInstance();
		
		int i = dbPro.confirmId(emp_id);
		
		request.setAttribute("check", i);
		request.setAttribute("emp_id", emp_id);
		
		return "/Intranet/Join/confindPro.jsp"; //�ش� ��
	}

}
