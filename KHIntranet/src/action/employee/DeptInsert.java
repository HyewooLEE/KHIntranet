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
		 
		EmployeeAllDBBean dbPro = EmployeeAllDBBean.getInstance(); //DBó��
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

		
		empAll = dbPro.selectAll(); //�ش� ȸ������
		positionList =  dbpostion.selectPosition();
		deptList = dbdept.selectDept();//�μ� �������� List�� �޾ƶ�!!!!!
		
		
		//�ش� �信�� ����� �Ӽ�
		request.setAttribute("empAll", empAll);  //�� 1���� �����´�.
		request.setAttribute("deptList", deptList);
		request.setAttribute("positionList", positionList);
		request.setAttribute("i", i);
		
		return "/Intranet/Employee/employeeChange.jsp"; //�ش� ��
		
	}
}
