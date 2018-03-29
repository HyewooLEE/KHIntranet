package action.photo;
    
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.photo.PhotoCommentDataBean;
import model.photo.PhotoDBBean;

public class CommentAction implements CommandAction{
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		String result = "ok";
			  
		String writer = request.getParameter("writer");
		String photoContent = request.getParameter("photo_content");
		String password = request.getParameter("password");
		String photo_no = request.getParameter("photo_no");
		
		PhotoCommentDataBean photo_comment = new PhotoCommentDataBean();
		photo_comment.setComm_writer(writer); 
	    photo_comment.setComm_content(photoContent);
	    photo_comment.setComm_password(password);
		photo_comment.setPhoto_no(Integer.parseInt(photo_no));
		photo_comment.setComm_date(new Timestamp(System.currentTimeMillis()));

        PhotoDBBean dbPro = PhotoDBBean.getInstance();
        dbPro.insertPhoto_comment(photo_comment);
        
		return result;
	}
	
}
