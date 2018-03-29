package action.approval.attendance;

import  java.util.ArrayList;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;

public class WriteForm_ReceiveAction implements CommandAction{

		@Override
		public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

			request.setCharacterEncoding("utf-8");		                                                    
			response.setContentType("text/html; charset=UTF-8");
			
			String chkEmp = request.getParameter("chkEmp");
			String appEmp = getEmpData().toString();
			
			return appEmp;
			
		}
		
		
		public StringBuffer getEmpData() throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //변수를 지정해서
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.selectAll();
			
		   for(int i = 0; i < EmpList.size(); i++) {
			    result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no() + "\"},"); 
	            result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); 
	            result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},");
	            result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"}]");            
	            
	            if(i != EmpList.size() -1) result.append(",");
	
		   }
		   result.append("]}");	
			return result;
		}

}
