package action.mypage.profile;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import model.employee.EmployeeAllDataBean;
import model.employee.EmployeeDBBean;
  
public class UploadProfileAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
//		String path = "D:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/KHIntranet/Intranet/Mypage/upload";
		String path = "D:/hw/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/KHIntranet/Intranet/Mypage/Profile/upload";
//		String path= "D:/profileUpload";
		String addr = "http://localhost:8850/KHIntranet/Intranet/Mypage/Profile/upload/";
//		String addr= "D:/profileUpload/";
        int size = 1024 * 1024 * 10;
        MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
        String fileRealName = multi.getFilesystemName("file");
	    EmployeeAllDataBean empBean = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
    	int emp_no = empBean.getEmp_no();
    	
        EmployeeDBBean empPro = EmployeeDBBean.getInstance();
        int check =empPro.uploadProfile(fileRealName, addr,emp_no);
        
        empBean.setEmp_pt_name(fileRealName);
        empBean.setEmp_pt_addr(addr);
        
        
        return URLEncoder.encode(fileRealName, "UTF-8"); 
        
    }
}
