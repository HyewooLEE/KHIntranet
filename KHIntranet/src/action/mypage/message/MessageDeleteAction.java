package action.mypage.message;
        
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.mypage.message.MessageDBBean;


public class MessageDeleteAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
    	MessageDBBean msgPro = MessageDBBean.getInstance();
    
    	int x = 0;
    	String status = request.getParameter("status");	
    	
    	if(request.getParameterValues("msg_no[]") != null) {
    	if(status.equals("1") ) {
    	String[] Values = request.getParameterValues("msg_no[]");
    	for(String Value : Values) {
    		int msg_no = Integer.parseInt(Value);
    		x =msgPro.deleteMessage(msg_no);
    	}
    	}else if(status.equals("2")) {
    		String[] Values = request.getParameterValues("msg_no[]");
        	for(String Value : Values) {
        		int msg_no = Integer.parseInt(Value);
        		x =msgPro.deleteSendMessage(msg_no);
        	}
    	}else {
    		String[] Values = request.getParameterValues("msg_no[]");
        	for(String Value : Values) {
        		int msg_no = Integer.parseInt(Value);
        		x =msgPro.deleteReceiveMessage(msg_no);
        	}
    	}
    	}else {
    		int msg_no = Integer.parseInt(request.getParameter("msg_no"));
    		if(status.equals("1")) {
    			x =msgPro.deleteMessage(msg_no);
    		}else if( status.equals("2")) {
    			x =msgPro.deleteSendMessage(msg_no);
    		}else {
    			x = msgPro.deleteReceiveMessage(msg_no);
    		}
    	}
    	
    	request.getSession().setAttribute("messageType", "성공!");
		request.getSession().setAttribute("messageContent", "메세지를 삭제하였습니다.");
    	
    	 String result = x +  "";
    	
        return result ;
    }
}
