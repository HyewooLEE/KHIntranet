package action.mypage.message;
      
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDataBean;
import model.employee.EmployeeDBBean;
import model.employee.EmployeeDataBean;
import model.mypage.message.MessageDBBean;



public class MesseageFormAction implements CommandAction {

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {

    	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
    	
        EmployeeAllDataBean empBean = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
    	MessageDBBean msgPro = MessageDBBean.getInstance();
    	if(request.getParameter("msg_fromname") != null) {
    		String msg_fromname= request.getParameter("msg_fromname");
    		request.setAttribute("msg_fromname", msg_fromname);
    		
    	}
    	if(request.getParameter("msg_fromno")!=null) {
    		String msg_fromno= request.getParameter("msg_fromno");
    		request.setAttribute("msg_fromno", msg_fromno);
    	}
    	int emp_no = empBean.getEmp_no();
    	int unreadCount = msgPro.getUnreadMessageTotalCount(emp_no);
    	
    	request.setAttribute("emp_no", emp_no);
    	request.setAttribute("empBean", empBean);
    	request.setAttribute("unreadCount", unreadCount);
       
        return "Intranet/Mypage/Message/MessageSendForm.jsp";
    }
}
