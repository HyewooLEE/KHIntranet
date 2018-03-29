package action.employee;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.department.DepartmentDBBean;
import model.employee.*;
import model.position.PositionDBBean;


public class EmployeeApprovalPro implements CommandAction{ //글내용 처리
	
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse)throws Throwable{
		
		request.setCharacterEncoding("UTF-8"); //한글 인코딩
		
		EmployeeAllDataBean emp_all = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		
		List deptList = null;
		List positionList = null;
		List empAll = null;

		EmployeeAllDBBean dbPro = EmployeeAllDBBean.getInstance(); //DB처리
		DepartmentDBBean dbdept =DepartmentDBBean.getInstance();
		PositionDBBean dbpostion = PositionDBBean.getInstance();
		
		empAll = dbPro.selectAll(); //해당 회원정보
		positionList =  dbpostion.selectPosition();
		deptList = dbdept.selectDept();//부서 여러개니 List로 받아라!!!!!
				
		//해당 뷰에서 사용할 속성
		request.setAttribute("empAll", empAll);  //값 1개만 가져온다.
		request.setAttribute("deptList", deptList);
		request.setAttribute("positionList", positionList);
		
		return "/Intranet/Employee/employeeApproval.jsp"; //해당 뷰
		
	}
}
