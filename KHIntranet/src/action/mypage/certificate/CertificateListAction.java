package action.mypage.certificate;
      
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDataBean;
import model.mypage.certificate.SalaryDBBean;
import model.mypage.certificate.SalaryDataBean;

public class CertificateListAction implements CommandAction{
	

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    		
    	EmployeeAllDataBean empBean = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
    	
    	
	    
    	int emp_no = empBean.getEmp_no();
    	SalaryDBBean salPro = SalaryDBBean.getInstance();
    	ArrayList<SalaryDataBean> salBean = salPro.getSalarys(emp_no);
    	
    	
    	request.getSession().setAttribute("empBean", empBean);
    	request.getSession().setAttribute("salBean", salBean);
        return "Intranet/Mypage/Certificate/Certificate_List.jsp";
    }

}
