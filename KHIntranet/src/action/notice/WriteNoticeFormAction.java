package action.notice;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import model.notice.NoticeDBBean;
import model.notice.NoticeDataBean;

public class WriteNoticeFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int notice_no = 0;
		
		NoticeDataBean ndb = new NoticeDataBean();
		NoticeDBBean noti_db = NoticeDBBean.getInstance();
		
		if(request.getParameter("notice_no") != null) {
			notice_no = Integer.parseInt(request.getParameter("notice_no"));
			ndb = noti_db.getNotice(notice_no);
			
			request.setAttribute("notice_no", ndb.getNotice_no());
			request.setAttribute("notice_emp_no", ndb.getNotice_emp_no());
			request.setAttribute("notice_title", ndb.getNotice_title());
			request.setAttribute("notice_content", ndb.getNotice_content());
			request.setAttribute("notice_date", ndb.getNotice_date());
			request.setAttribute("notice_file_name", ndb.getNotice_file_name());
			request.setAttribute("notice_file_addr", ndb.getNotice_file_addr());
			request.setAttribute("notice_ud", "1");
		} else {
			request.setAttribute("notice_ud", "0");
			request.setAttribute("notice_no", "0");
		}
		
		return "Intranet/Notice/writeNoticeForm.jsp";
	}
}