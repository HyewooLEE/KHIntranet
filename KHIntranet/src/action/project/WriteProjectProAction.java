package action.project;
  
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
import model.project.ProjectDBBean;
import model.project.ProjectDataBean;

public class WriteProjectProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		EmployeeAllDataBean empPro  = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = empPro.getEmp_no();
		if(request.getParameter("pro_del") != null ) {
			int pro_no = Integer.parseInt(request.getParameter("pro_no"));
			
			ProjectDBBean db = ProjectDBBean.getInstance();
			
			db.deleteProject(pro_no);
		} else {
			int pro_no;
			String pro_file_name ,pro_date, pro_file_addr, pro_percent;
			
			// 파일 업로드 기능 구현
			String path = "D:/fileUpload";
			int size = 1024 * 1024 * 10;
			
			MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
			
			if(multi.getParameter("pro_no").equals("")) {
				pro_no = 0;
			} else {
				pro_no = Integer.parseInt(multi.getParameter("pro_no"));
			}
			if(multi.getParameter("pro_ud").equals("1")) {
				if(multi.getParameter("pro_file_name") == null || multi.getParameter("pro_file_name").equals("")) { 
					pro_file_name = null;
				} else { pro_file_name = multi.getParameter("pro_file_name"); }
				if(multi.getParameter("pro_file_addr") == null || multi.getParameter("pro_file_addr").equals("")) {
					pro_file_addr = null;
				} else { pro_file_addr = multi.getParameter("pro_file_addr"); }
			} else {
				if(multi.getParameter("pro_file_name") == null || multi.getParameter("pro_file_name").equals("")) {
					Enumeration files = multi.getFileNames();
					String str = (String)files.nextElement();
					pro_file_name = multi.getFilesystemName(str);
				} else { pro_file_name = multi.getParameter("pro_file_name"); }
				if(multi.getParameter("pro_file_addr") == null || multi.getParameter("pro_file_addr").equals("")) {
					pro_file_addr = path;
				} else { pro_file_addr = multi.getParameter("pro_file_addr"); }
			}
			
			if(multi.getParameter("pro_percent") == null || multi.getParameter("pro_percent").equals("")) {
				pro_percent = "0";
			} else {
				pro_percent = multi.getParameter("pro_percent");
			}
			Date dt = new Date();
			DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
			pro_date = dtf.format(dt);
					
			// 받은 값들 dto 처리
			ProjectDBBean db = ProjectDBBean.getInstance();
			ProjectDataBean pdb = new ProjectDataBean();
			
			pdb.setPro_no(pro_no);
			pdb.setPro_emp_no(emp_no);
			pdb.setPro_title(multi.getParameter("pro_title"));
			pdb.setPro_content(multi.getParameter("pro_content"));
			pdb.setPro_date(pro_date);
			pdb.setPro_percent(pro_percent);
			pdb.setPro_file_name(pro_file_name);
			pdb.setPro_file_addr(pro_file_addr);
			pdb.setPro_start_date(multi.getParameter("pro_start_date"));
			pdb.setPro_end_date(multi.getParameter("pro_end_date"));
			pdb.setPro_participant(multi.getParameter("lastSelector_hide"));
			
			System.out.println(multi.getParameter("lastSelector_hide"));
			
			if(pro_no == 0)
				db.insertProject(pdb);
			else if(pro_no > 1) 
				db.updateProject(pdb);
		}
		
		return "Intranet/Project/writeProjectPro.jsp";
	}
}