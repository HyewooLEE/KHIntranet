package action.project;
  
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import model.department.DepartmentDBBean;
import model.department.DepartmentDataBean;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.project.ProjectDBBean;
import model.project.ProjectDataBean;

public class WriteProjectFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int pro_no = 0;
		request.setAttribute("pro_ud", request.getParameter("pro_ud"));
		
		ProjectDBBean db = ProjectDBBean.getInstance();
		ProjectDataBean pdb = new ProjectDataBean();
		
		List<DepartmentDataBean> deptList = null;
	    List<EmployeeAllDataBean> empList = null;
	      
	    DepartmentDBBean deptDao = DepartmentDBBean.getInstance();
	    EmployeeAllDBBean empDao = EmployeeAllDBBean.getInstance();      
	             
	    deptList = deptDao.selectDept();
	    empList = empDao.selectAll();              
	      
	    
		if(request.getParameter("pro_no") != null) {
			pro_no = Integer.parseInt(request.getParameter("pro_no"));
			pdb = db.getProject(pro_no);
			
			request.setAttribute("pro_no", pdb.getPro_no());
			request.setAttribute("pro_emp_no", pdb.getPro_emp_no());
			request.setAttribute("pro_title", pdb.getPro_title());
			request.setAttribute("pro_content", pdb.getPro_content());
			request.setAttribute("pro_file_name", pdb.getPro_file_name());
			request.setAttribute("pro_file_addr", pdb.getPro_file_addr());
			request.setAttribute("pro_start_date", pdb.getPro_start_date());
			request.setAttribute("pro_end_date", pdb.getPro_end_date());
			request.setAttribute("pro_date", pdb.getPro_date());
			request.setAttribute("pro_percent", pdb.getPro_percent());
			
			String parti = (pdb.getPro_participant()).trim();
			StringTokenizer st = new StringTokenizer(parti, ",");
			String emp_name = "";
			String emp_no = "";
			int i =0;
			int stSize = st.countTokens();
			
			while(st.hasMoreTokens()) {
				EmployeeAllDBBean eadb = EmployeeAllDBBean.getInstance();
				EmployeeAllDataBean edb = eadb.getEmp_no(Integer.parseInt(st.nextToken()));
				
				emp_name += (edb.getEmp_name() +" - "+ edb.getPosition_name());
				emp_no += edb.getEmp_no();
				
				if(i < stSize -1) {
					emp_name += "/ ";
					emp_no += ", ";
				}
				i++;
			}
			request.setAttribute("emp_name", emp_name);
			request.setAttribute("emp_no", emp_no);
		}
		request.setAttribute("deptList", deptList);      
	    request.setAttribute("empList", empList);
		
		return "Intranet/Project/writeProjectForm.jsp";
	}
}