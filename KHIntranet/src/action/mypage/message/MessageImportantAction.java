package action.mypage.message;
      
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.el.fmt.RequestEncodingTag;

import action.CommandAction;
import model.mypage.message.MessageDBBean;
import model.photo.LikeDBBean;

public class MessageImportantAction implements CommandAction{
	@Override  
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
    	int msg_no = Integer.parseInt(request.getParameter("msg_no"));	
	    
	    MessageDBBean msgPro = MessageDBBean.getInstance();
	    int x = msgPro.updateImportant(msg_no);
	    
	    String check = x+"";
	    
	    return check ;
    	
	}
	  
}
    	
    	