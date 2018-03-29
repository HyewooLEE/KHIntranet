package action.mypage.profile;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDataBean;
import model.employee.EmployeeDBBean;

public class ModifyFormAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String password = request.getParameter("password");
		EmployeeAllDataBean empBean = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = empBean.getEmp_no();
		String emp_id = empBean.getEmp_id();
		EmployeeDBBean empPro = EmployeeDBBean.getInstance();
		int check = empPro.userCheck(emp_id, password);
		System.out.println(check);
		if (check == 2) {
			request.setAttribute("empBean", empBean);
			request.setAttribute("emp_no", emp_no);
			return "Intranet/Mypage/Profile/modifyForm.jsp";
		} else {
			request.getSession().setAttribute("messageType", "오류!");
			request.getSession().setAttribute("messageContent", "비밀번호가 틀렸습니다.");
			return "/main.do";   
		}
	}

}
