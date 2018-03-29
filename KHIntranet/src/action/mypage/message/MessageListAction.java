package action.mypage.message;
      
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.*;
import model.mypage.message.MessageDBBean; 

public class MessageListAction implements CommandAction{
	

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
    	
    	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
    	EmployeeAllDataBean empBean = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
    	int emp_no = empBean.getEmp_no();
    	String pageNum = request.getParameter("pageNum");
    	String status = null;
    	 if (request.getParameter("status") == null) {
    		 status = "1";
         }else {
        	 status = request.getParameter("status");
         }
        if (pageNum == null) {
            pageNum = "1";
        }
        int pageSize = 10;
        int currentPage = Integer.parseInt(pageNum);
        int startRow = (currentPage - 1) * pageSize + 1;
        int endRow = currentPage * pageSize;
        int count = 0;
        int unreadCount = 0;
        
        List empList = null;
        List msgList = null;
        
        EmployeeAllDBBean empPro = EmployeeAllDBBean.getInstance();
        MessageDBBean msgPro = MessageDBBean.getInstance();
        empList = empPro.selectAll();
        unreadCount = msgPro.getUnreadMessageTotalCount(emp_no);
        
        
        
        if(status.equals("1")){        	
        	count = msgPro.getReceivedMessageTotalCount(emp_no);      	
	        if (count > 0) {
	            msgList = msgPro.getMessages(emp_no, startRow, endRow);
	        } else {
	            msgList = Collections.EMPTY_LIST;
	        }        	
        }else if(status.equals("2")){
        	count = msgPro.getSendMessageTotalCount(emp_no);
            if (count > 0) {
                msgList = msgPro.getSendMessages(emp_no, startRow, endRow);
            } else {
                msgList = Collections.EMPTY_LIST;
            }
            	
        }else if(status.equals("3")) {
        	count = msgPro.getDeletedMessageTotalCount(emp_no);  	
        	if (count > 0) {
                msgList = msgPro.getDeletedMessages(emp_no, startRow, endRow);
            } else {
                msgList = Collections.EMPTY_LIST;
            }
        	
        } 
        
        int lastPage = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
        request.setAttribute("pageNum", new Integer(currentPage));
        request.setAttribute("count", new Integer(count));
        request.setAttribute("pageSize", new Integer(pageSize));
        request.setAttribute("lastPage", new Integer(lastPage));
        request.setAttribute("unreadCount", new Integer(unreadCount));
        request.setAttribute("status", new Integer(status));
        request.setAttribute("msgList", msgList);
        request.setAttribute("empList", empList);
        
       

        return "Intranet/Mypage/Message/MessageList.jsp";
    }

}
