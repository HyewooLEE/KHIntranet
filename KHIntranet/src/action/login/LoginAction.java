package action.login;
    
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.employee.EmployeeDBBean;
import model.main.ConnectionDBBean;

public class LoginAction implements CommandAction{  //글 입력 폼처리
   
   public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable{
      
      request.setCharacterEncoding("UTF-8"); //한글 인코딩
      
      String emp_id= request.getParameter("emp_id");
      String emp_pw = request.getParameter("emp_pw");
      int emp_no = 0;
      String emp_name = "";
      int check1 = 0;
      
      EmployeeDBBean dbPr = EmployeeDBBean.getInstance();
      EmployeeAllDBBean dbPro = EmployeeAllDBBean.getInstance();
      ConnectionDBBean cPro = ConnectionDBBean.getInstance();
      int check = dbPr.userCheck(emp_id,emp_pw);
      if(check==2) {
         EmployeeAllDataBean article = dbPro.getEmp_id(emp_id); //해당 회원정보
         
         emp_no = article.getEmp_no();
         emp_name = article.getEmp_name();
         cPro.InsertCon(emp_no);    //LoginAction
         request.setAttribute("check", check);
         request.setAttribute("article", article);
         
         //?emp_name="+emp_name+"&position_name="+article.getPosition_name()+"&emp_no="+emp_no+"&dept_name="+article.getDept_name()
         
         return "Intranet/Login/loginPro.jsp";
      }
      
      request.setAttribute("check", check);
      request.setAttribute("check1", check1);
      
      return "Intranet/Login/login.jsp"; //해당 뷰
   }
}
