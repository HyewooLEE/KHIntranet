package action.mypage.adminApproval;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.mypage.approval.ApprovalDBBean;

public class AdminAttendReturnProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		request.getSession();
		
		int atd_no = Integer.parseInt(request.getParameter("atd_no"));
		String atd_receiver = request.getParameter("atd_receiver");
		String atd_status_ny = request.getParameter("atd_status_ny");
		
		ApprovalDBBean appDao = ApprovalDBBean.getInstance();
		appDao.updateAttendApproval(atd_receiver, atd_status_ny, atd_no);


		return "/Intranet/Mypage/AdminApproval/adminAttendReturnPro.jsp";
	}

}
