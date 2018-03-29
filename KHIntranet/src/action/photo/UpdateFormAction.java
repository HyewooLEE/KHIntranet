package action.photo;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDataBean;
import model.photo.PhotoDBBean;
import model.photo.PhotoDataBean;

public class UpdateFormAction implements CommandAction {
   
   public String requestPro(HttpServletRequest request,
           HttpServletResponse response) throws Throwable {
      
      int photo_no = Integer.parseInt(request.getParameter("photo_no"));
      String pageNum = request.getParameter("pageNum");
        
      EmployeeAllDataBean empBean = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
      request.setAttribute("empBean", empBean);
       
      PhotoDBBean dbPro = PhotoDBBean.getInstance();
      PhotoDataBean article = dbPro.updateGetArticle(photo_no);
      
      request.setAttribute("pageNum", new Integer(pageNum));
      request.setAttribute("article", article);

      return "/Intranet/Photo/updateForm.jsp";
   }

}