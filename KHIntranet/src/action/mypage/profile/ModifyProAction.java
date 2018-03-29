package action.mypage.profile;
      
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.employee.EmployeeDBBean;


public class ModifyProAction implements CommandAction{
	

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
    	EmployeeAllDataBean empBean = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
    	String emp_id = empBean.getEmp_id();
    	String emp_name = (String) request.getParameter("emp_name");
    	String emp_pw = (String) request.getParameter("emp_pw");
    	String emp_pw2 = (String) request.getParameter("emp_pw2");
    	String emp_pn = (String) request.getParameter("emp_pn");
    	String emp_email = (String) request.getParameter("emp_email");
    	String emp_addr1 = (String) request.getParameter("emp_addr1");
    	String emp_addr2 = (String) request.getParameter("emp_addr2");
    	String emp_addr3 = (String) request.getParameter("emp_addr3");
    	if(emp_id == null || emp_id.equals("") || emp_name ==null || emp_name.equals("")
			||	emp_pw == null || emp_pw.equals("") ||	emp_pw2 == null || emp_pw2.equals("") 
			|| emp_pn ==null || emp_pn.equals("")
			|| emp_email == null || emp_email.equals("") || emp_addr1 ==null || emp_addr1.equals("")
			|| emp_addr2 == null || emp_addr2.equals("") || emp_addr3 == null || emp_addr3.equals("") 
				) {
			request.getSession().setAttribute("messageType", "오류!");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			return "Intranet/Mypage/Profile/modifyPro.jsp";
    	}else if(emp_pw.equals(emp_pw2)==false) {
    		request.getSession().setAttribute("messageType", "오류!");
			request.getSession().setAttribute("messageContent", "비밀번호가 일치하지 않습니다.");
			return "Intranet/Mypage/Profile/modifyPro.jsp";
    	}else {
    	EmployeeAllDBBean dbPr = EmployeeAllDBBean.getInstance();
    	EmployeeDBBean dbPro = EmployeeDBBean.getInstance();
    	int check = dbPro.updateMember(emp_id,emp_name,emp_pw,emp_pn,emp_email,emp_addr1,emp_addr2,emp_addr3);
    	empBean = dbPr.getEmp_id(emp_id); 
    	if(check ==1) {
    		request.setAttribute("check", check);
    		request.getSession().setAttribute("emp_all", empBean);
			request.getSession().setAttribute("messageType", "성공!");
			request.getSession().setAttribute("messageContent", "수정 완료 되었습니다.");
		}else {
			request.getSession().setAttribute("messageType", "오류!");
			request.getSession().setAttribute("messageContent", "수정 실패하였습니다.");
		}
        return "Intranet/Mypage/Profile/modifyPro.jsp";   
    	}
    }

}
