package action.mypage.certificate;
        
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDataBean;
import model.mypage.certificate.SalaryDBBean;
import model.mypage.certificate.SalaryDataBean;



public class CertificateSalaryAction implements CommandAction{
	

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
    	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		EmployeeAllDataBean empBean = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = empBean.getEmp_no();
		int salary = Integer.parseInt(request.getParameter("salary"));
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		
		SalaryDBBean salPro = SalaryDBBean.getInstance();
		SalaryDataBean salBean = salPro.getSalary(salary, emp_no);
		
		request.getSession().setAttribute("empBean", empBean);
    	request.getSession().setAttribute("salBean", salBean);
    	request.getSession().setAttribute("year", year);
    	request.getSession().setAttribute("month", month);
    	
    	
        return "Intranet/Mypage/Certificate/Certificate_Salary_Print.jsp";
    }

}
