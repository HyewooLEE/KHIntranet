package action.employee;
  
import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.employee.EmployeeDataBean;
import model.mypage.certificate.CareerDBBean;
import model.mypage.certificate.SalaryDBBean;
import model.employee.EmployeeDBBean;
import action.CommandAction;

public class UpdateProAction implements CommandAction{  //글 입력 폼처리
	

	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		 
		request.setCharacterEncoding("UTF-8"); //한글 인코딩
		
		
		int emp_no = Integer.parseInt(request.getParameter("emp_no"));
		int dept_no = Integer.parseInt(request.getParameter("dept_no"));
		int rank = Integer.parseInt(request.getParameter("position_rank"));
		int sal = Integer.parseInt(request.getParameter("sal"));
		String dept_name = request.getParameter("dept_name");
		String position_name = request.getParameter("position_name");
		

		EmployeeDataBean article = new EmployeeDataBean(); //데이터처리 빈
		
		article.setEmp_no(emp_no);
		article.setPosition_rank(rank);
		article.setEmp_sal(sal);
		article.setEmp_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); //현재시간 입력
		article.setDept_no(dept_no);
		
		
		
		CareerDBBean carPro = CareerDBBean.getInstance();
		carPro.insertCareer(emp_no, dept_name, position_name, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		SalaryDBBean salPro = SalaryDBBean.getInstance();
		salPro.insertSalary(emp_no, rank, sal, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		
		EmployeeDBBean dbPro = EmployeeDBBean.getInstance(); //DB처리
		dbPro.upDateJoin(article);  //업데이트
		
		request.setAttribute("pageNum", 0);
		//Intranet/Employee
		return "/Employee/employeeHome.jsp"; //해당 뷰
	}

}
