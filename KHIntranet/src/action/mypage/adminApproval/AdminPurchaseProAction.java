package action.mypage.adminApproval;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.purchase.PurchaseDataBean;
import model.mypage.approval.ApprovalDBBean;

public class AdminPurchaseProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");				
		
		String pur_receiver = request.getParameter("pur_receiver");		
		String pur_status_ny = request.getParameter("pur_status_ny");
		int pur_no = Integer.parseInt(request.getParameter("pur_no"));
		
		if(pur_status_ny == null) {
			pur_status_ny = "πÃ∞·¿Á";
		}
		
		try{
			
			PurchaseDataBean article = new PurchaseDataBean();
			
			article.setPur_emp_no(pur_no);
			article.setPur_status_ny(pur_status_ny);
			article.setPur_receiver(pur_receiver);
			
			ApprovalDBBean approvalDao = ApprovalDBBean.getInstance();
			approvalDao.updatePurchaseApproval(pur_receiver, pur_status_ny, pur_no);

		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
		return "Intranet/Mypage/AdminApproval/adminApprovalPro.jsp";    
	}

}
