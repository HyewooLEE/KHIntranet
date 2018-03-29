package action.employee;
  
import java.util.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.department.DepartmentDBBean;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.position.PositionDBBean;

public class EmployeeChangePro implements CommandAction{ //글내용 처리
	
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse)throws Throwable{
		
		request.setCharacterEncoding("UTF-8"); //한글 인코딩
		
		EmployeeAllDataBean empAll = null;
		List deptList = null;
		List positionList = null;
		
		int emp_no = Integer.parseInt(request.getParameter("emp_no"));
		
		EmployeeAllDBBean dbPro = EmployeeAllDBBean.getInstance();
		DepartmentDBBean dbdept =DepartmentDBBean.getInstance();
		PositionDBBean dbpostion = PositionDBBean.getInstance();
		
		empAll = dbPro.getEmp_no(emp_no);
		positionList =  dbpostion.selectPosition();
		deptList = dbdept.selectDept();
		
		request.setAttribute("empAll", empAll);
		request.setAttribute("deptList", deptList);
		request.setAttribute("positionList", positionList);
		
		return "/Intranet/Employee/employeeChangeIn.jsp";
	}
}
