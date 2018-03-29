package action.mypage.adminApproval;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.purchase.PurchaseDBBean;
import model.approval.purchase.PurchaseDataBean;
import model.employee.EmployeeAllDBBean;
import model.mypage.approval.ApprovalDBBean;

public class AdminPurchaseContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.getSession();
		
		request.setCharacterEncoding("utf-8");
		int pur_no = Integer.parseInt(request.getParameter("pur_no"));
		
		ApprovalDBBean appDao = ApprovalDBBean.getInstance();
		PurchaseDBBean purchaseDao = PurchaseDBBean.getInstance();
		EmployeeAllDBBean empDao = EmployeeAllDBBean.getInstance();
		PurchaseDataBean article = appDao.getPurArticle(pur_no);
		
		List PurchaseList = null;
		List EmpList  = null;
		
		PurchaseList = purchaseDao.purchaseDoc();
		EmpList = empDao.selectAll();  
		
		request.setAttribute("PurchaseList", PurchaseList);
		request.setAttribute("EmpList", EmpList);
		request.setAttribute("pur_no", pur_no);
		request.setAttribute("article", article);
		
		return "/Intranet/Mypage/AdminApproval/adminPurchaseContent.jsp";
	}

}
