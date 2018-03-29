package action.photo;
    
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class DeleteFormAction implements CommandAction {
   
   public String requestPro(HttpServletRequest request,
         HttpServletResponse response) throws Throwable{
      
      int photo_no = Integer.parseInt(request.getParameter("photo_no"));
      String pageNum = request.getParameter("pageNum");
         
      request.setAttribute("photo_no", new Integer(photo_no));
      request.setAttribute("pageNum", new Integer(pageNum));
      
      return "/Intranet/Photo/deleteForm.jsp";
   }

}
