package action.photo;
    
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.photo.PhotoDBBean;
import model.photo.PhotoDataBean;

public class UpdateProAction implements CommandAction{
   
   public String requestPro( HttpServletRequest request,
           HttpServletResponse response) throws Throwable {

           request.setCharacterEncoding("UTF-8");
 
           String pageNum = request.getParameter("pageNum");
             
           PhotoDataBean article = new PhotoDataBean(); 
           article.setPhoto_no(Integer.parseInt(request.getParameter("photo_no")));
           article.setEmp_no(Integer.parseInt(request.getParameter("emp_no")));
           article.setPhoto_title(request.getParameter("photo_title"));
           article.setPhoto_content(request.getParameter("photo_content"));
           article.setPhoto_file_addr(request.getParameter("photo_file_addr"));
           article.setPhoto_file_nm(request.getParameter("photo_file_nm"));
           article.setPassword(request.getParameter("password"));
           
           PhotoDBBean dbPro = PhotoDBBean.getInstance();
           int check = dbPro.updateArticle(article);
           
           request.setAttribute("pageNum", new Integer(pageNum));
           request.setAttribute("check", new Integer(check));
           if(check ==1) {
				request.getSession().setAttribute("messageType", "성공!");
				request.getSession().setAttribute("messageContent", "글수정 성공하였습니다.");
			}else {
				request.getSession().setAttribute("messageType", "오류!");
				request.getSession().setAttribute("messageContent", "글수정 실패하였습니다.");
			}
           
           return "/Intranet/Photo/updatePro.jsp";
           
           
   }
}