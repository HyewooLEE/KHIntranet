package action.mypage.myApproval;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.mypage.approval.ApprovalDBBean;

public class MyPurchaseDeleteAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int pur_no = Integer.parseInt(request.getParameter("pur_no"));
		
		ApprovalDBBean appDao = ApprovalDBBean.getInstance();
		appDao.deletePurchaseArticle(pur_no);

		request.setAttribute("pur_no", new Integer(pur_no));
		
		return "Intranet/Mypage/MyApproval/myPurchaseDelete.jsp";
	}

}
