package action.notice;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDataBean;
import model.notice.NoticeDBBean;
import model.notice.NoticeDataBean;

public class ListNoticeAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		EmployeeAllDataBean empPro  = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = empPro.getEmp_no();
		
		NoticeDBBean noti_db = NoticeDBBean.getInstance();
		
		List<NoticeDataBean> noticeList = noti_db.getNoticeList();
		int notice_count = noti_db.getNoticeCount();
		
		request.setAttribute("notice_count", notice_count);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("emp_no", emp_no);
		
		return "Intranet/Notice/listNotice.jsp";
	}
}
