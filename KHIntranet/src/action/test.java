package action;
     
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeDBBean;
  
public class test implements CommandAction{  //�� �Է� ��ó��
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		
		
		return "/Intranet/Mypage/test.jsp"; //�ش� ��
	}

}
