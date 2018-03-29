package action.mypage.message;
      
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;

public class MessageSearchAction implements CommandAction{
	@Override  
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
    	String emp_name = request.getParameter("emp_name");	
	    String SearchList = getSearchList(emp_name).toString();
	    
	    return SearchList ;
    	
	}
	  
    	
    	public StringBuffer getSearchList(String emp_name ) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean EmpAllPro = EmployeeAllDBBean.getInstance();
			
			
			List<EmployeeAllDataBean> EmpAll = EmpAllPro.getEmp_nameList(emp_name);
			if(EmpAll.size() == 0 ) {
				result.append("0");
					return result;
				}
			result.append("{\"result\":["); 
			for(int i = 0 ; i < EmpAll.size(); i++) {
				result.append("[{\"value\": \"" + EmpAll.get(i).getDept_name()+ "\"},");
				result.append("{\"value\": \"" + EmpAll.get(i).getPosition_name() + "\"},");
				result.append("{\"value\": \"" + EmpAll.get(i).getEmp_name() + "\"},");
				result.append("{\"value\": \"" + EmpAll.get(i).getEmp_no() + "\"}]");  
				if(i != EmpAll.size() -1) result.append(",");
			}
			result.append("]}");
			
			return result;
		}
    	
    	
      
    
}
