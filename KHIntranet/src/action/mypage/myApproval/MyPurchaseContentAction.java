package action.mypage.myApproval;
  
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.purchase.PurchaseDBBean;
import model.approval.purchase.PurchaseDataBean;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.main.AlarmDBBean;
import model.mypage.approval.ApprovalDBBean;

public class MyPurchaseContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.getSession();
		request.setCharacterEncoding("utf-8");
		
		EmployeeAllDataBean emp_all = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = emp_all.getEmp_no();
		int pur_no = Integer.parseInt(request.getParameter("pur_no"));
		System.out.println(emp_no);
		System.out.println(pur_no);
		
		ApprovalDBBean appDao = ApprovalDBBean.getInstance();
		PurchaseDBBean purDao = PurchaseDBBean.getInstance();
		EmployeeAllDBBean empDao = EmployeeAllDBBean.getInstance();
		PurchaseDataBean article = appDao.getPurArticle(pur_no);
		
		List PurchaseList = null;
		List EmpList  = null;
		
		String parti = (article.getPur_receiver()).trim();
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
		alrPro.updatePur(emp_no, pur_no);*/
        
		PurchaseList = purDao.purchaseDoc();
		EmpList = empDao.selectAll();  
		
		request.setAttribute("PurchaseList", PurchaseList);
		request.setAttribute("EmpList", EmpList);
		request.setAttribute("pur_no", pur_no);
		request.setAttribute("article", article);
		
		request.setAttribute("emp_name", emp_name);
		
		return "Intranet/Mypage/MyApproval/myPurchaseContent.jsp";
	}

}
