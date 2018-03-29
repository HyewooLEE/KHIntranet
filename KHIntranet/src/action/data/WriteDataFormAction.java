package action.data;
    
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import model.data.DataDBBean;
import model.data.DataDataBean;

public class WriteDataFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int data_no = 0;
		
		DataDataBean ddb = new DataDataBean();
		DataDBBean data_db = DataDBBean.getInstance();
		
		if(request.getParameter("data_no") != null) {
			data_no = Integer.parseInt(request.getParameter("data_no"));
			ddb = data_db.getData(data_no);
			
			String parti = (ddb.getData_file_name()).trim();
			StringTokenizer st = new StringTokenizer(parti, ",");
			List<String> file_name = new ArrayList<String>();  
					
			while(st.hasMoreTokens()) {
				file_name.add(st.nextToken());
				
			}
			
			request.setAttribute("data_no", ddb.getData_no());
			request.setAttribute("data_emp_no", ddb.getData_emp_no());
			request.setAttribute("data_title", ddb.getData_title());
			request.setAttribute("data_content", ddb.getData_content());
			request.setAttribute("data_date", ddb.getData_date());
			request.setAttribute("data_file_name", file_name);
			request.setAttribute("data_file_addr", ddb.getData_file_addr());
			request.setAttribute("data_ud", "1");
		} else {
			request.setAttribute("data_ud", "0");
			request.setAttribute("data_no", "0");
		}
		
		return "Intranet/Data/writeDataForm.jsp";
	}
}