package action.main;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.main.ConnectionDBBean;

public class ConnectionAction implements CommandAction{
	
	public static int emp_no = 0;
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		ConnectionDBBean ConPro = ConnectionDBBean.getInstance(); 
		int emp_no = Integer.parseInt(request.getParameter("emp_no"));
	//	String emp_name = request.getParameter("emp_name");

	//	System.out.println(emp_name);
		
		//String emp_all = getEmpAll_id(emp_id).toString();

		String i = ConPro.nowConnection()+"";
		
		return i;
	}
	//접속자 내용?
		/*public StringBuffer getEmpAll_id(String emp_id) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //변수를 지정해서
			
			EmployeeAllDataBean EmpList = empAll.getEmp_id(emp_id);

				result.append("[{\"value\": \"" + EmpList.getEmp_id()+ "\"},"); //이름값 가져오기
				result.append("{\"value\": \"" + EmpList.getPosition_rank() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.getEmp_name() + "\"}]");  //시간
				
				result.append("]}");
				
			return result;
		}*/
}
