package action.login;
       
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDataBean;
import model.main.ConnectionDBBean;


public class ToLoginAction implements CommandAction{
	

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
        EmployeeAllDataBean empAll  = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
        if(empAll != null) {
            ConnectionDBBean cPro = ConnectionDBBean.getInstance();
            
            cPro.DeleteCon(empAll.getEmp_no());
            }
        return "Intranet/Login/login.jsp";
    }

}
