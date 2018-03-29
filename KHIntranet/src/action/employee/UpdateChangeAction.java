package action.employee;
  
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.department.DepartmentDBBean;
import model.employee.EmployeeDBBean;
import model.employee.EmployeeDataBean;
import model.mypage.certificate.CareerDBBean;
import model.mypage.certificate.SalaryDBBean;
import model.position.PositionDBBean;

public class UpdateChangeAction implements CommandAction{  //글 입력 폼처리
	

	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		
		request.setCharacterEncoding("UTF-8"); //한글 인코딩
		int emp_no = Integer.parseInt(request.getParameter("emp_no"));
		int dept_no = Integer.parseInt(request.getParameter("dept_no"));
		int rank = Integer.parseInt(request.getParameter("position_rank"));
		int sal = Integer.parseInt(request.getParameter("sal"));
		
		EmployeeDataBean article = new EmployeeDataBean(); //데이터처리 빈
		
		article.setEmp_no(emp_no);
		article.setPosition_rank(rank);
		article.setEmp_sal(sal);
		article.setDept_no(dept_no);
		
		EmployeeDBBean dbPro = EmployeeDBBean.getInstance(); //DB처리
		PositionDBBean pdbPro = PositionDBBean.getInstance();
		DepartmentDBBean ddbPro = DepartmentDBBean.getInstance();
		SalaryDBBean salPro = SalaryDBBean.getInstance();
		CareerDBBean carPro = CareerDBBean.getInstance();
		
		String position_name = pdbPro.getPosition_name(rank);
		String dept_name = ddbPro.getDept_name(dept_no);
		System.out.println(position_name);
		System.out.println(dept_name);
		
		
		carPro.updateCareer(emp_no,  new SimpleDateFormat("yy/MM/dd").format(new Date()));
		salPro.updateSalary(emp_no,  new SimpleDateFormat("yy/MM/dd").format(new Date()));
		carPro.insertCareer(emp_no, dept_name, position_name, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		salPro.insertSalary(emp_no, rank, sal, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
	
		
		
		
		
		
		dbPro.upDateChange(article);  //업데이트
		
		request.setAttribute("pageNum", 0);
		//Intranet/Employee
		return "/Intranet/Employee/employeeHome.jsp"; //해당 뷰
	}

}
