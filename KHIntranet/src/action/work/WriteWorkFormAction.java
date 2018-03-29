package action.work;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import model.employee.EmployeeAllDataBean;
import model.project.ProjectDBBean;
import model.project.ProjectDataBean;
import model.work.WorkDBBean;
import model.work.WorkDataBean;


public class WriteWorkFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		EmployeeAllDataBean empPro  = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = empPro.getEmp_no();
		String emp_name = empPro.getEmp_name();
		WorkDBBean db = WorkDBBean.getInstance();
		WorkDataBean wdb = new WorkDataBean();
		System.out.println(request.getParameter("work_no"));
		System.out.println(request.getParameter("work_ud"));

		if(request.getParameter("work_no") != null) {
			int work_no = Integer.parseInt(request.getParameter("work_no"));
			wdb = db.getWork(work_no);
			request.setAttribute("work_no", work_no);
			request.setAttribute("work_ct_pb", wdb.getWork_ct_pb());
			request.setAttribute("work_ct_sg", wdb.getWork_ct_sg());
			request.setAttribute("work_date", wdb.getWork_date());
			request.setAttribute("work_file_name", wdb.getWork_file_name());
			request.setAttribute("work_file_addr", wdb.getWork_file_addr());
			request.setAttribute("pro_title", wdb.getPro_title());
			request.setAttribute("pro_percent", wdb.getPro_percent());
			request.setAttribute("pro_no", wdb.getPro_no());
			request.setAttribute("work_ud", request.getParameter("work_ud"));
			request.setAttribute("pro_participant", wdb.getPro_participant());
		} else {
			ProjectDBBean pjdb = ProjectDBBean.getInstance();
			List<ProjectDataBean> projects = pjdb.getProjectList(emp_no);
			request.setAttribute("projects", projects);
			
		}
		request.setAttribute("emp_name", emp_name);
		request.setAttribute("work_emp_no", emp_no);
		/*
		 * µð¹ö±ë
		 * for(int i=0; i<projects.size(); i++) {
			System.out.println(projects.get(i).getPro_title());
		}*/
		
							
		return "Intranet/Work/writeWorkForm.jsp";
	}
	
	
}

