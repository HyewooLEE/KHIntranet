package action.main;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.attendance.AttendanceDataBean;
import model.approval.purchase.PurchaseDataBean;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.main.AlarmDBBean;
import model.main.AlarmDataBean;
import model.mypage.message.MessageDataBean;

public class AlarmAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");  //�ѱ� �޾ƿ���
		response.setContentType("text/html;charset=UTF-8");
		
		int emp_no = Integer.parseInt(request.getParameter("emp_no"));
	
		String AlramList ="";	
		
		AlramList = AllList(emp_no).toString();
		
		return AlramList;
	}
	
	public StringBuffer AllList(int emp_no) throws Exception {  
		StringBuffer result = new StringBuffer("");
		
		EmployeeAllDBBean dbPro = EmployeeAllDBBean.getInstance();		
		AlarmDBBean AlarmPro = AlarmDBBean.getInstance();

		
		ArrayList<AlarmDataBean> AlramList = (ArrayList<AlarmDataBean>) AlarmPro.AlarmSList(emp_no);
		
		result.append("{\"result\":[");  //������ �����ؼ�
		
		if(AlramList != null) {
		
		for(int i = 0; i < AlramList.size(); i++) {
			result.append("[{\"value\": \"" + AlramList.get(i).getLastID() + "\"},"); //���� �۹�ȣ
			result.append("{\"value\": \"" + dbPro.getEmp_no(AlramList.get(i).getEmp_no()).getEmp_name() + "\"},"); //�߽���
			result.append("{\"value\": \"" + AlramList.get(i).getCountent() + "\"},"); //���� ��������
			result.append("{\"value\": \"" + AlramList.get(i).getTime() + "\"},"); //���� ��������
			result.append("{\"value\": \"" + AlramList.get(i).getType() + "\"}]");  //�ð�
			
			if(i != AlramList.size() -1) result.append(",");
			}
		} 
		result.append("], \"MessageLast\":\"" + AlarmPro.messageLast(emp_no) + "\""); 
		result.append(", \"AttendLast\":\"" + AlarmPro.attLast(emp_no) + "\""); 
		result.append(", \"PurchaseLast\":\"" + AlarmPro.purLast(emp_no) + "\""); 
		result.append(", \"Last\":\"" + AlarmPro.countChat(emp_no) + "\"}"); 
		return result;
	}
}