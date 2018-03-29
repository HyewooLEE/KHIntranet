package action.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

import model.employee.EmployeeAllDataBean;
import model.project.ProjectDBBean;
import model.project.ProjectDataBean;

public class ListProjectAction implements CommandAction {
   public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
      EmployeeAllDataBean empPro = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
      int emp_no = empPro.getEmp_no();

      ProjectDBBean pdb = ProjectDBBean.getInstance();
      int count = pdb.getCount(emp_no);

      if (count > 0) {
         String rowNum = request.getParameter("rowNum");
         int curRow = 0;
         int startRow = 0;
         int endRow = 0;
         List<ProjectDataBean> projectPercentList = null;
         
         if (rowNum == null) {
               rowNum = "1";
         }
         
         curRow = Integer.parseInt(rowNum);
         startRow = (curRow - 1) * 4 + 1;
         endRow = startRow + 3;
         
         projectPercentList = pdb.getProjectList(emp_no, startRow, endRow);  
         
         
         request.setAttribute("curRow", curRow);
         request.setAttribute("startRow", startRow);
         request.setAttribute("endRow", endRow);
         request.setAttribute("projectPercentList", projectPercentList);
         request.setAttribute("count", count);
         
         List<ProjectDataBean> projectList = pdb.getProjectList(emp_no);
         request.setAttribute("articleList", projectList);
      }
      request.setAttribute("emp_no", emp_no);
      request.setAttribute("count", count);

      return "Intranet/Project/listProject.jsp";
   }
}