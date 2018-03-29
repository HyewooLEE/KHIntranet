package action.photo;
      
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.el.fmt.RequestEncodingTag;

import action.CommandAction;
import model.photo.LikeDBBean;

public class photoLikeAction implements CommandAction{
	@Override  
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
    	int like_photo_no = Integer.parseInt(request.getParameter("like_photo_no"));	
	    int like_emp_no = Integer.parseInt(request.getParameter("like_emp_no"));	
	    int check = Integer.parseInt(request.getParameter("check"));	
	    
	    LikeDBBean likePro =LikeDBBean.getInstance();
	    if(check==0) {
	    	likePro.updateLike(like_photo_no, like_emp_no);
	    	check = 1;
	    }else {
	    	likePro.deleteLike(like_photo_no, like_emp_no);
	    	check = 0;
	    }
	    String result = LikeEvent(like_photo_no, like_emp_no, check).toString();
	    
	    return result ;
    	
	}
	  
    	
    	public StringBuffer LikeEvent(int like_photo_no,int like_emp_no,int check) throws Exception {
			StringBuffer result = new StringBuffer("");
			
				LikeDBBean likePro =LikeDBBean.getInstance();
			    int count = likePro.getLikeCount(like_photo_no);
			    result.append("{\"result\":["); 
				result.append("[{\"value\": \"" + count+ "\"},");
				result.append("{\"value\": \"" + check + "\"}]");
				result.append("]}");
			
			return result;
    	}
}
    	
    	