package action.mypage.adminApproval;
  
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.attendance.AttendanceDBBean;
import model.approval.purchase.PurchaseDBBean;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.mypage.approval.DocumentDBBean;

public class AdminApprovalAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		request.getSession();
		
		EmployeeAllDataBean emp_all = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		 int emp_no = emp_all.getEmp_no();
		
		String pageNumA = request.getParameter("pageNumA");
		String pageNumP = request.getParameter("pageNumP");
		
		AttendanceDBBean attendDao = AttendanceDBBean.getInstance();
		EmployeeAllDBBean empDao = EmployeeAllDBBean.getInstance();
		DocumentDBBean docDao = DocumentDBBean.getInstance();
		PurchaseDBBean purchaseDao = PurchaseDBBean.getInstance();
        
        List AttendList = null;
        List PurchaseList = null;
        List EmpList  = null;
        List DocList = null;
        
        EmpList = empDao.selectAll();      
        DocList = docDao.listDoc();

        
        //AttendList
        if(pageNumA == null) {
			pageNumA = "1";
        }
        int pageSizeA = 5;
        int currentPageA = Integer.parseInt(pageNumA);
        int startRowA = (currentPageA - 1) * pageSizeA + 1;
        int endRowA = currentPageA * pageSizeA;
        int countA = 0; 
        int numberA = 0;
        
        countA = attendDao.getArticleCount(emp_no);
        
        if(countA > 0) {
        	AttendList = attendDao.getArticles(emp_no,startRowA, endRowA);
        }else {
        	AttendList = Collections.EMPTY_LIST;
        }
        numberA = countA - (currentPageA-1) * pageSizeA; 
        
        request.setAttribute("currentPageA", new Integer(currentPageA));
        request.setAttribute("startRowA", new Integer(startRowA));
        request.setAttribute("endRowA", new Integer(endRowA));
        request.setAttribute("countA", new Integer(countA));
        request.setAttribute("pageSizeA", new Integer(pageSizeA));
        request.setAttribute("numberA", new Integer(numberA));
        
        
        //PurchaseList
        if(pageNumP == null) {
	         pageNumP = "1";
       }
        int pageSizeP = 5;
        int currentPageP = Integer.parseInt(pageNumP);
        int startRowP = (currentPageP - 1) * pageSizeP + 1;
        int endRowP = currentPageP * pageSizeP;
        int countP = 0; 
        int numberP = 0;
      
        countP = purchaseDao.getArticleCount(emp_no);
        if(countP > 0) {
        	PurchaseList = purchaseDao.getArticles(emp_no,startRowP, endRowP);
        }else {
        	PurchaseList = Collections.EMPTY_LIST;
        }
        numberP = countP - (currentPageP-1) * pageSizeP; 
        
        request.setAttribute("currentPageP", new Integer(currentPageP));
        request.setAttribute("startRowP", new Integer(startRowP));
        request.setAttribute("endRowP", new Integer(endRowP));
        request.setAttribute("countP", new Integer(countP));
        request.setAttribute("pageSizeP", new Integer(pageSizeP));
        request.setAttribute("numberP", new Integer(numberP));
        
        request.setAttribute("AttendList", AttendList);
        request.setAttribute("EmpList", EmpList);
        request.setAttribute("DocList", DocList);
        request.setAttribute("PurchaseList", PurchaseList);

		
		return "Intranet/Mypage/AdminApproval/adminApproval.jsp";
	}

}
