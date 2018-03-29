package action.mypage.adminApproval;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.attendance.AttendanceDBBean;
import model.approval.attendance.AttendanceDataBean;
import model.employee.EmployeeAllDBBean;
import model.mypage.approval.ApprovalDBBean;

public class AdminAttendContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.getSession();
		
		request.setCharacterEncoding("utf-8");
		int atd_no = Integer.parseInt(request.getParameter("atd_no"));
		
		ApprovalDBBean appDao = ApprovalDBBean.getInstance();
		AttendanceDBBean attendDao = AttendanceDBBean.getInstance();
		EmployeeAllDBBean empDao = EmployeeAllDBBean.getInstance();
		AttendanceDataBean article = appDao.getAttendArticle(atd_no);
		
		List AttendList = null;
		List EmpList  = null;
		
		AttendList = attendDao.attendDoc();	
		EmpList = empDao.selectAll();  
		
		request.setAttribute("AttendList", AttendList);
		request.setAttribute("EmpList", EmpList);
		request.setAttribute("atd_no", atd_no);
		request.setAttribute("article", article);
		
		return "Intranet/Mypage/AdminApproval/adminAttendContent.jsp";
	}

}
