package action.work;
   
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;

import model.project.ProjectDataBean;
import model.work.WorkDBBean;
import model.work.WorkDataBean;

public class WriteWorkProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if(request.getParameter("work_del") != null ) {
			
			int work_no = Integer.parseInt(request.getParameter("work_no"));
			WorkDBBean db = WorkDBBean.getInstance();
			
			db.deleteWork(work_no);
		} else {
			EmployeeAllDataBean empPro  = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
			int emp_no = empPro.getEmp_no();
			String emp_email = empPro.getEmp_email();
			String emp_password = empPro.getEmp_pw();
			
			int work_no, pro_no = 0;
			String work_file_name , work_date, work_file_addr, pro_percent;
			
			// 파일 업로드 기능 구현
			String path = "D:/fileUpload";
			int size = 1024 * 1024 * 10;
			
			MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
			if(multi.getParameter("work_no") == null || multi.getParameter("work_no").equals("")) {
				work_no = 0;
			} else {
				work_no = Integer.parseInt(multi.getParameter("work_no"));
			}
			if(multi.getParameter("pro_no") != null || !multi.getParameter("pro_no").equals("")) {
				pro_no = Integer.parseInt(multi.getParameter("pro_no"));
			}
			if(multi.getParameter("work_ud").equals("1")) {
				if(multi.getParameter("work_file_name") == null || multi.getParameter("work_file_name").equals("")) { 
					work_file_name = null;
				} else { work_file_name = multi.getParameter("work_file_name"); }
				if(multi.getParameter("work_file_addr") == null || multi.getParameter("work_file_addr").equals("")) {
					work_file_addr = null;
				} else { work_file_addr = multi.getParameter("work_file_addr"); }
			} else {
				if(multi.getParameter("work_file_name") == null || multi.getParameter("work_file_name").equals("")) {
					Enumeration files = multi.getFileNames();
					String str = (String)files.nextElement();
					work_file_name = multi.getFilesystemName(str);
				} else { work_file_name = multi.getParameter("work_file_name"); }
				if(multi.getParameter("work_file_addr") == null || multi.getParameter("work_file_addr").equals("")) {
					work_file_addr = path;
				} else { work_file_addr = multi.getParameter("work_file_addr"); }
			}
			
			if(multi.getParameter("pro_percent") == null || multi.getParameter("pro_percent").equals("")) {
				pro_percent = "0";
			} else {
				pro_percent = multi.getParameter("pro_percent");
			}
			/*System.out.println("work_no : "+ multi.getParameter("work_no"));
			System.out.println("work_ct_sg : "+ multi.getParameter("work_ct_sg"));
			System.out.println("work_ct_pb : "+ multi.getParameter("work_ct_pb"));
			System.out.println("work_file_name : "+ multi.getParameter("work_file_name"));
			System.out.println("work_file_addr : "+ multi.getParameter("work_file_addr"));
			System.out.println("work_date : "+ multi.getParameter("work_date"));
			System.out.println("pro_percent : "+ multi.getParameter("pro_percent"));
			System.out.println("work_ud : "+ multi.getParameter("work_ud"));
			System.out.println("pro_no : "+ multi.getParameter("pro_no"));
			System.out.println("---------------------------- multi -------------------");*/
			Date dt = new Date();
			SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
			work_date = dtf.format(dt);
					
			WorkDBBean db = WorkDBBean.getInstance();
			WorkDataBean wdb = new WorkDataBean();
			ProjectDataBean pdb = new ProjectDataBean();
						
			String emp_participant = db.getEmailList(pro_no);
			
			String parti = emp_participant.trim();
			StringTokenizer st = new StringTokenizer(parti, ",");
			EmployeeAllDataBean pro_emp_email;	
			while(st.hasMoreTokens()) {
				EmployeeAllDBBean edb = EmployeeAllDBBean.getInstance();
				pro_emp_email = edb.getEmp_no(Integer.parseInt(st.nextToken()));
				
				String d_uname = "destiny85548@gmail.com";
				String d_password = "fkaus445!@#";
				String d_host = "smtp.gmail.com";
				int d_port = 465;
				
				String m_to = pro_emp_email.getEmp_email();
				String m_from = emp_email;
				String m_subject = work_date +" / "+ pro_emp_email.getEmp_name() +"- 업무보고 : "+ multi.getParameter("work_ct_sg");
				String m_text = multi.getParameter("work_ct_pb");
				
				Properties props = new Properties();
				SMTPAuthenticator auth = new SMTPAuthenticator();
				Session ses = Session.getInstance(props, auth);

				MimeMessage msg = new MimeMessage(ses);
				msg.setContent(m_text, "text/html; charset=UTF-8");
				msg.setSubject(m_subject);
				msg.setFrom(new InternetAddress(m_from));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
				
				try {
					Transport transport = ses.getTransport("smtps");
					transport.connect(d_host, d_port, d_uname, d_password);
					transport.sendMessage(msg, msg.getAllRecipients());
				
					transport.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			wdb.setWork_no(work_no);
			wdb.setWork_emp_no(emp_no);
			wdb.setWork_ct_pb(multi.getParameter("work_ct_pb"));
			wdb.setWork_ct_sg(multi.getParameter("work_ct_sg"));
			wdb.setWork_date(work_date);
			wdb.setPro_percent(pro_percent);
			wdb.setWork_file_name(work_file_name);
			wdb.setWork_file_addr(work_file_addr);
			pdb.setPro_percent(pro_percent);
			pdb.setPro_no(pro_no);
		
			if(work_no == 0)
				db.insertWork(wdb, pdb);
			else if(work_no > 1) 
				db.updateWork(wdb ,pdb);
		}
		return "Intranet/Work/writeWorkPro.jsp";
	}
}
