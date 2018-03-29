package action.mypage.myApproval;
  
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.attendance.AttendanceDBBean;
import model.approval.attendance.AttendanceDataBean;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.main.AlarmDBBean;
import model.mypage.approval.ApprovalDBBean;

public class MyAttendContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		request.getSession();
		
		EmployeeAllDataBean emp_all = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = emp_all.getEmp_no();
		int atd_no = Integer.parseInt(request.getParameter("atd_no"));
		
		ApprovalDBBean appDao = ApprovalDBBean.getInstance();
		AttendanceDBBean attendDao = AttendanceDBBean.getInstance();
		EmployeeAllDBBean empDao = EmployeeAllDBBean.getInstance();
		AttendanceDataBean article = appDao.getAttendArticle(atd_no);
		
		List AttendList = null;
		List EmpList  = null;
		
		String parti = (article.getAtd_receiver()).trim();
        StringTokenizer st = new StringTokenizer(parti, ",");
        String emp_name = "";
      
        int i =0;
        int stSize = st.countTokens();
        
        while(st.hasMoreTokens()) {
        
           EmployeeAllDBBean eadb = EmployeeAllDBBean.getInstance();
           EmployeeAllDataBean edb = eadb.getEmp_no(Integer.parseInt(st.nextToken()));
           
           if(emp_no != edb.getEmp_no()) {
        	   emp_name += edb.getEmp_name();
           }
           
           if(i < stSize -2) {
              emp_name += " , ";
           }
           i++;
        }
        /*AlarmDBBean alrPro = AlarmDBBean.getInstance();
		alrPro.updateAtd(emp_no, atd_no);
		*/
		AttendList = attendDao.attendDoc();	
		EmpList = empDao.selectAll();  
		
		request.setAttribute("AttendList", AttendList);
		request.setAttribute("EmpList", EmpList);
		request.setAttribute("atd_no", atd_no);
		request.setAttribute("article", article);

        request.setAttribute("emp_name", emp_name);
        
		return "Intranet/Mypage/MyApproval/myAttendContent.jsp";
	}

}
