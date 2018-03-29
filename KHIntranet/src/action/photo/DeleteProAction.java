package action.photo;
      
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.photo.LikeDBBean;
import model.photo.PhotoCommentDBBean;
import model.photo.PhotoDBBean;

public class DeleteProAction implements CommandAction{
   
   public String requestPro(HttpServletRequest request,
         HttpServletResponse response) throws Throwable{
      request.setCharacterEncoding("UTF-8");
      int photo_no = Integer.parseInt(request.getParameter("photo_no"));
      int pageNum = Integer.parseInt(request.getParameter("pageNum"));
      String password = request.getParameter("password");
        
     PhotoDBBean dbPro = PhotoDBBean.getInstance();
      int check = dbPro.deleteArticle(photo_no, password);
      if (check == 1) {
			request.getSession().setAttribute("check", "1");
			request.getSession().setAttribute("messageType", "����!");
			request.getSession().setAttribute("messageContent", "���� �����Ͽ����ϴ�.");
		} else {
			request.getSession().setAttribute("check", "0");
			request.getSession().setAttribute("messageType", "����!");
			request.getSession().setAttribute("messageContent", "��й�ȣ�� �����ʽ��ϴ�.");
		}
      
      PhotoCommentDBBean commentPro = PhotoCommentDBBean.getInstance();
      int check2 = commentPro.deleteCommentAll(photo_no);
      
      LikeDBBean likePro = LikeDBBean.getInstance();
      int check3 = likePro.deleteLikeAll(photo_no);
      
      
      request.setAttribute("photo_no", photo_no);
      request.setAttribute("pageNum", new Integer(pageNum));
      request.setAttribute("check", new Integer(check));
      
      return "/Intranet/Photo/deletePro.jsp";
   }

}