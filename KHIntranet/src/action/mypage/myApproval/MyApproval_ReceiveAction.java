package action.mypage.myApproval;
  
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.attendance.AttendanceDBBean;
import model.approval.attendance.AttendanceDataBean;

public class MyApproval_ReceiveAction  implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");		                                                    
		response.setContentType("text/html; charset=UTF-8");
		
		String chkDoc = request.getParameter("chkDoc");
		String attendData = getAttendData().toString();
					
		return attendData;
		
	}
	
	public StringBuffer getAttendData() throws Exception {
		StringBuffer result = new StringBuffer("");
		AttendanceDBBean attendDao = AttendanceDBBean.getInstance();
		
		result.append("{\"result\":[");  //변수를 지정해서
		
		ArrayList<AttendanceDataBean> AttendList = (ArrayList<AttendanceDataBean>) attendDao.attendDoc();
		
	   for(int i = 0; i < AttendList.size(); i++) {
            result.append("[{\"value\": \"" + AttendList.get(i).getAtd_no() + "\"},"); 
            result.append("{\"value\": \"" + AttendList.get(i).getAtd_emp_no() + "\"},"); 
            result.append("{\"value\": \"" + AttendList.get(i).getAtd_date() + "\"},"); 
            result.append("{\"value\": \"" + AttendList.get(i).getAtd_receiver() + "\"},"); 
            result.append("{\"value\": \"" + AttendList.get(i).getAtd_div() + "\"},"); 
            result.append("{\"value\": \"" + AttendList.get(i).getAtd_start_date() + "\"},"); 
            result.append("{\"value\": \"" + AttendList.get(i).getAtd_end_date() + "\"},"); 
            result.append("{\"value\": \"" + AttendList.get(i).getAtd_note() + "\"},"); 
            result.append("{\"value\": \"" + AttendList.get(i).getAtd_status_ny() + "\"}"); 
            result.append("{\"value\": \"" + AttendList.get(i).getDoc_name() + "\"}]");
            
            if(i != AttendList.size() -1) result.append(",");

	   }
	   result.append("]}");
	   
		return result;
	}

}
