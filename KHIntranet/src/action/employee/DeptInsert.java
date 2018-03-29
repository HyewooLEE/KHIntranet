package action.employee;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.department.DepartmentDBBean;
import model.employee.EmployeeAllDBBean;
import model.position.PositionDBBean;

public class DeptInsert implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		 
		EmployeeAllDBBean dbPro = EmployeeAllDBBean.getInstance(); //DB처리
		DepartmentDBBean dbdept =DepartmentDBBean.getInstance();
		PositionDBBean dbpostion = PositionDBBean.getInstance();
		
		List deptList = null;
		List positionList = null;
		List empAll = null;
		
		int i = Integer.parseInt(request.getParameter("i"));
		
		
		int dept_no = Integer.parseInt(request.getParameter("dept_no"));
		String dpet_name = request.getParameter("dept_name");
		
		if(dpet_name == null) {
			dbdept.deleteDept(dept_no);
		}else {
			dbdept.insertDept(dept_no, dpet_name);
		}

		
		empAll = dbPro.selectAll(); //해당 회원정보
		positionList =  dbpostion.selectPosition();
		deptList = dbdept.selectDept();//부서 여러개니 List로 받아라!!!!!
		
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("empAll", empAll);  //값 1개만 가져온다.
		request.setAttribute("deptList", deptList);
		request.setAttribute("positionList", positionList);
		request.setAttribute("i", i);
		
		return "/Intranet/Employee/employeeChange.jsp"; //해당 뷰
		
	}
}
