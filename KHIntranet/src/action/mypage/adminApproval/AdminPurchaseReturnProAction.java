package action.mypage.adminApproval;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.mypage.approval.ApprovalDBBean;

public class AdminPurchaseReturnProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		request.getSession();
		
		int pur_no = Integer.parseInt(request.getParameter("pur_no"));
		String pur_receiver = request.getParameter("pur_receiver");
		String pur_status_ny = request.getParameter("pur_status_ny");
		
		ApprovalDBBean appDao = ApprovalDBBean.getInstance();
		appDao.updatePurchaseApproval(pur_receiver, pur_status_ny, pur_no);


		return "/Intranet/Mypage/AdminApproval/adminPurchaseReturnPro.jsp";
	
	}

}
