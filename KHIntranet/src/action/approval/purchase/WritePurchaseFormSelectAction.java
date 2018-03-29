package action.approval.purchase;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.department.DepartmentDBBean;
import model.employee.EmployeeAllDBBean;

public class WritePurchaseFormSelectAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");		   
		
		List deptList = null;
		List empList = null;
		
		DepartmentDBBean deptDao = DepartmentDBBean.getInstance();
		EmployeeAllDBBean empDao = EmployeeAllDBBean.getInstance();		
				 
		deptList = deptDao.selectDept();
		empList = empDao.selectAll();				  
		
		request.setAttribute("deptList", deptList);		
		request.setAttribute("empList", empList);
		
		return "Intranet/Approval/Purchase/writePurchaseForm.jsp";   
	}

}
