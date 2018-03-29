package action.data;
    
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.data.DataDBBean;
import model.data.DataDataBean;
import model.employee.EmployeeAllDataBean;

public class ListDataAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		EmployeeAllDataBean empPro  = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		int emp_no = empPro.getEmp_no();
		
		DataDBBean data_db = DataDBBean.getInstance();
		
		List<DataDataBean> dataList = data_db.getDataList();
		int data_count = data_db.getDataCount();
		
		request.setAttribute("data_count", data_count);
		request.setAttribute("dataList", dataList);
		request.setAttribute("emp_no", emp_no);
		
		return "Intranet/Data/listData.jsp";
	}
}
