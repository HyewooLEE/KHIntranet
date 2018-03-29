package action.mypage.myApproval;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.attendance.AttendanceDBBean;
import model.approval.purchase.PurchaseDBBean;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.mypage.approval.DocumentDBBean;

public class MyApprovalAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		request.getSession();
		
		EmployeeAllDataBean emp_all = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");

		EmployeeAllDBBean empDao = EmployeeAllDBBean.getInstance();
		AttendanceDBBean attendDao = AttendanceDBBean.getInstance();
		PurchaseDBBean purchaseDao = PurchaseDBBean.getInstance();
		DocumentDBBean docDao = DocumentDBBean.getInstance();
		
        List EmpList  = null;
        List AttendList = null;
        List PurchaseList = null;
        List DocList = null;
        
        EmpList = empDao.selectAll();        
        AttendList = attendDao.attendDoc(emp_all);
        PurchaseList = purchaseDao.purchaseDoc(emp_all);
        DocList = docDao.listDoc();
        
        request.setAttribute("EmpList", EmpList);
        request.setAttribute("AttendList", AttendList);  
        request.setAttribute("PurchaseList", PurchaseList);
        request.setAttribute("DocList", DocList);
        
		
        return "Intranet/Mypage/MyApproval/myApproval.jsp";
	}

}
