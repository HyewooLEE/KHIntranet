package action.notice;
  
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import model.employee.EmployeeAllDataBean;
import model.notice.NoticeDBBean;
import model.notice.NoticeDataBean;
import model.project.ProjectDBBean;
import model.project.ProjectDataBean;

public class WriteNoticeProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		EmployeeAllDataBean empPro  = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = empPro.getEmp_no();
		if(request.getParameter("notice_del") != null ) {
			int notice_no = Integer.parseInt(request.getParameter("notice_no"));
			
			NoticeDBBean ndb = NoticeDBBean.getInstance();
			ndb.deleteNotice(notice_no);
		} else {
			int notice_no = 0;
			
			String notice_file_name, notice_file_addr, notice_date;
			
			String path = "D:/fileUpload";
			int size = 1024 * 1024 * 10;
			
			MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
			
			if(multi.getParameter("notice_no") != null || multi.getParameter("notice_no").equals("") == false) {
				notice_no = Integer.parseInt(multi.getParameter("notice_no"));
			} 
			
			if(multi.getFilesystemName("notice_file_name") == null) {
				notice_file_name = null;
			} else {
				Enumeration files = multi.getFileNames();
				String str = (String)files.nextElement();
				notice_file_name = multi.getFilesystemName(str);
			}
				
			if(multi.getParameter("notice_file_addr") == null || multi.getParameter("notice_file_addr").equals("")) {
				notice_file_addr = path;
			
			} else { notice_file_addr = multi.getParameter("notice_file_addr"); }
			
			
			Date dt = new Date();
			DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
			notice_date = dtf.format(dt);
			
			NoticeDBBean noti_db = NoticeDBBean.getInstance();
			NoticeDataBean ndb = new NoticeDataBean();
			
			ndb.setNotice_no(notice_no);
			ndb.setNotice_emp_no(emp_no);
			ndb.setNotice_title(multi.getParameter("notice_title"));
			ndb.setNotice_content(multi.getParameter("notice_content"));
			ndb.setNotice_date(notice_date);
			ndb.setNotice_file_name(notice_file_name);
			ndb.setNotice_file_addr(notice_file_addr);
			
			if(notice_no > 0) {
				noti_db.updateWork(ndb);
			} else {
				noti_db.insertNotice(ndb);
			}
			
		}
		
		return "Intranet/Notice/writeNoticePro.jsp";
	}
}