package action.mypage.message;
      
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.main.AlarmDBBean;
import model.mypage.message.MessageDBBean;
import model.mypage.message.MessageDataBean;

public class MessageLoadAction implements CommandAction{
	@Override  
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		
		EmployeeAllDataBean empBean = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
    	int emp_no = empBean.getEmp_no();
		int msg_no = Integer.parseInt(request.getParameter("msg_no"));
		 
		/*AlarmDBBean alrPro = AlarmDBBean.getInstance();
		alrPro.updateMsg(emp_no, msg_no);
		*/
		String status = request.getParameter("status");
		MessageDBBean msgPro = MessageDBBean.getInstance();
		MessageDataBean msgBean = msgPro.getMessage(msg_no,status);
		int unreadCount = msgPro.getUnreadMessageTotalCount(emp_no);
		
		
		
		
		request.setAttribute("msg_no", msg_no);
		request.setAttribute("status", status);
		request.setAttribute("msg_fromname", msgPro.getEMP_NAME(msgBean.getMsg_fromno()));
		request.setAttribute("msg_fromno", (msgBean.getMsg_fromno()));
		request.setAttribute("msg_tono", msgBean.getMsg_tono());
		request.setAttribute("msg_content", msgBean.getMsg_content());
		request.setAttribute("msg_time", msgBean.getMsg_from_time());
		request.setAttribute("unreadCount", new Integer(unreadCount));
		
    	return "Intranet/Mypage/Message/MessageContent.jsp" ;
	}
}
