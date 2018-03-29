package action.employee;
  
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.department.DepartmentDBBean;
import model.employee.EmployeeAllDBBean;
import model.position.PositionDBBean;

public class ChangeDelete implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		EmployeeAllDBBean dbPro = EmployeeAllDBBean.getInstance(); //DB처리
		DepartmentDBBean dbdept =DepartmentDBBean.getInstance();
		PositionDBBean dbpostion = PositionDBBean.getInstance();
		
		List empAll = null;
		List positionList = null;
		List deptList = null; 
		
		int check = Integer.parseInt(request.getParameter("check"));
		String checkbox = request.getParameter("checkbox");

		System.out.println(check);
		
		//String parti = (checkbox.getPro_participant()).trim();
        StringTokenizer st = new StringTokenizer(checkbox, ",");
        String emp_name = "";
        String emp_no = "";
        int i =0;
        int stSize = st.countTokens();
        

		if(check ==1) {  //부서 삭제
			while(st.hasMoreTokens()) {
				dbdept.deleteDept(Integer.parseInt(st.nextToken()));    
		        }
		}else {  //직급삭제
			while(st.hasMoreTokens()) {
				dbpostion.deletePosition(Integer.parseInt(st.nextToken()));    
		        }
		}
		
		empAll = dbPro.selectAll(); //해당 회원정보
		positionList =  dbpostion.selectPosition();
		deptList = dbdept.selectDept();//부서 여러개니 List로 받아라!!!!!
		
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("empAll", empAll);  //값 1개만 가져온다.
		request.setAttribute("deptList", deptList);
		request.setAttribute("positionList", positionList);
		request.setAttribute("i", check);
		
		return "/Intranet/Employee/employeeChange.jsp"; //해당 뷰
		
	}
}
