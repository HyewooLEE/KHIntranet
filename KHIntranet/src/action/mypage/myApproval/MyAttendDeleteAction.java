package action.mypage.myApproval;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.mypage.approval.ApprovalDBBean;

public class MyAttendDeleteAction  implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		int atd_no = Integer.parseInt(request.getParameter("atd_no"));
		
		ApprovalDBBean appDao = ApprovalDBBean.getInstance();
		appDao.deleteAttendArticle(atd_no);

		request.setAttribute("atd_no", new Integer(atd_no));
   
		return "Intranet/Mypage/MyApproval/myAttendDelete.jsp";
	}

}
