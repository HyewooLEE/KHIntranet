package action.mypage.certificate;
        
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDataBean;
import model.mypage.certificate.CareerDBBean;
import model.mypage.certificate.CareerDataBean;

public class CertificateCareerAction implements CommandAction{
	

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {

    	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
    	EmployeeAllDataBean empBean = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
    	int emp_no = empBean.getEmp_no();
    	
    	CareerDBBean carPro = CareerDBBean.getInstance();
    	ArrayList<CareerDataBean>  carBean = carPro.getCareers(emp_no);
    	
    	request.setAttribute("empBean", empBean);
    	request.setAttribute("carBean", carBean);
    	request.setAttribute("emp_no", emp_no);
    	
        return "Intranet/Mypage/Certificate/Certificate_Career_Print.jsp";
    }

}
