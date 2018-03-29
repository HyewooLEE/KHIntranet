package action.photo;
  
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDBBean;
import model.photo.PhotoDBBean;

public class ListAction implements CommandAction{
   
   public String requestPro(HttpServletRequest request,
           HttpServletResponse response)throws Throwable {
      
      String pageNum = request.getParameter("pageNum");
      
      EmployeeAllDBBean empPro = EmployeeAllDBBean.getInstance();
      List empBean =  empPro.selectAll();
         
      if(pageNum == null) {
         pageNum = "1";
      }
      int pageSize = 8;
      int currentPage = Integer.parseInt(pageNum);
      int startRow = (currentPage - 1) * pageSize + 1;
      int endRow = currentPage * pageSize;
      int count = 0; //ÃÑ°³¼ö 
      int number = 0; 
      
      List articleList = null;
      PhotoDBBean dbPro = PhotoDBBean.getInstance();
      count = dbPro.getArticleCount();
      
      if(count > 0) {
         articleList = dbPro.getArticles(startRow, endRow);
      }else {
         articleList = Collections.EMPTY_LIST;
      }
      number = count - (currentPage-1) * pageSize; 
      
      
      request.setAttribute("currentPage", new Integer(currentPage));
      request.setAttribute("startRow", new Integer(startRow));
      request.setAttribute("endRow", new Integer(endRow));
      request.setAttribute("count", new Integer(count));
      request.setAttribute("pageSize", new Integer(pageSize));
      request.setAttribute("number", new Integer(number));
      request.setAttribute("articleList", articleList);
      request.setAttribute("empBean", empBean);

        
        return "/Intranet/Photo/list.jsp";
        
        
      
   }

}