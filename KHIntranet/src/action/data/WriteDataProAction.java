package action.data;
    
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import model.data.DataDBBean;
import model.data.DataDataBean;
import model.employee.EmployeeAllDataBean;

public class WriteDataProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		EmployeeAllDataBean empPro  = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = empPro.getEmp_no();
		if(request.getParameter("data_del") != null ) {
			DataDBBean ddb = DataDBBean.getInstance();
					
			int data_no = Integer.parseInt(request.getParameter("data_no"));
			DataDataBean data_db = ddb.getData(data_no);
			
			File file = new File(data_db.getData_file_addr() +"/"+ data_db.getData_file_name());
			if(file.exists()) {
				file.delete();
			}
						
			ddb.deleteData(data_no);
			
		} else {
			int data_no = 0;
			
			String data_file_name = "", data_file_addr, data_date;
			
			String path = "D:/fileUpload";
			int size = 1024 * 1024 * 10;
			
			DataDBBean data_db = DataDBBean.getInstance();
			DataDataBean ddb = null;
			
			MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
						
			if(multi.getParameter("data_no") != null) {
				data_no = Integer.parseInt(multi.getParameter("data_no"));
			} 
			
			if(multi.getFilesystemName("data_file_name") == null) {
				ddb = data_db.getData(data_no);
				
				File file = new File(ddb.getData_file_addr() +"/"+ ddb.getData_file_name());
				if(file.exists()) {
					file.delete();
				}
				
				data_file_name = null;
			} else {
				Enumeration files = multi.getFileNames();
				/*String str = (String)files.nextElement();
				data_file_name = multi.getFilesystemName(str);
				*/
				while(files.hasMoreElements()) {
					String str = (String)files.nextElement();
					data_file_name += multi.getFilesystemName(str);
									
					data_file_name += ",";
					
				}
			}
				
			if(multi.getParameter("data_file_addr") == null || multi.getParameter("data_file_addr").equals("")) {
				data_file_addr = path;
			
			} else { data_file_addr = multi.getParameter("data_file_addr"); }
			
			
			Date dt = new Date();
			DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
			data_date = dtf.format(dt);
			
			ddb = new DataDataBean();
			
			ddb.setData_no(data_no);
			ddb.setData_emp_no(emp_no);
			ddb.setData_title(multi.getParameter("data_title"));
			ddb.setData_content(multi.getParameter("data_content"));
			ddb.setData_date(data_date);
			ddb.setData_file_name(data_file_name);
			ddb.setData_file_addr(data_file_addr);
			
			if(data_no > 0) {
				data_db.updateData(ddb);
			} else {
				data_db.insertData(ddb);
			}
			
		}
		
		return "Intranet/Data/writeDataPro.jsp";
	}
}