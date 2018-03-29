package action.employee;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.department.DepartmentDBBean;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.position.PositionDBBean;

public class EmployeeChange implements CommandAction{ //�۳��� ó��
	
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse)throws Throwable{
		
		request.setCharacterEncoding("UTF-8"); //�ѱ� ���ڵ�
		
		EmployeeAllDataBean emp_all = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		
		int i = 0;
		if(request.getParameter("i") == null) {
		
		}else {
		i = Integer.parseInt(request.getParameter("i"));
		}
		
		List deptList = null;
		List positionList = null;
		List empAll = null;
		
		EmployeeAllDBBean dbPro = EmployeeAllDBBean.getInstance(); //DBó��
		DepartmentDBBean dbdept =DepartmentDBBean.getInstance();
		PositionDBBean dbpostion = PositionDBBean.getInstance();
		
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