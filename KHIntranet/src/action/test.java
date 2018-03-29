package action;
     
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeDBBean;
  
public class test implements CommandAction{  //±Û ÀÔ·Â ÆûÃ³¸®
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		
		
		return "/Intranet/Mypage/test.jsp"; //ÇØ´ç ºä
	}

}
