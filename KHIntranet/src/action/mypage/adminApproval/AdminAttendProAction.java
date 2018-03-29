package action.mypage.adminApproval;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.attendance.AttendanceDataBean;
import model.mypage.approval.ApprovalDBBean;

public class AdminAttendProAction  implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");				
		
		String atd_receiver = request.getParameter("atd_receiver");		
		String atd_status_ny = request.getParameter("atd_status_ny");
		int atd_no = Integer.parseInt(request.getParameter("atd_no"));
		
		if(atd_status_ny == null) {
			atd_status_ny = "πÃ∞·¿Á";
		}
		
		try{
			
			AttendanceDataBean article = new AttendanceDataBean();
			
			article.setAtd_emp_no(atd_no);
			article.setAtd_status_ny(atd_status_ny);
			article.setAtd_div(atd_receiver);
			
			ApprovalDBBean approvalDao = ApprovalDBBean.getInstance();
			approvalDao.updateAttendApproval(atd_receiver, atd_status_ny, atd_no);

		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
		return "Intranet/Mypage/AdminApproval/adminApprovalPro.jsp";     
	}

}
