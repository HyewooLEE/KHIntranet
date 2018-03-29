package action.main;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.approval.attendance.AttendanceDataBean;
import model.approval.purchase.PurchaseDataBean;
import model.employee.EmployeeAllDBBean;
import model.main.AlarmDBBean;
import model.mypage.message.MessageDataBean;

public class AlarmList implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");  //한글 받아오기
		response.setContentType("text/html;charset=UTF-8");
		
		int emp_no = Integer.parseInt(request.getParameter("emp_no"));
		int type = Integer.parseInt(request.getParameter("type"));
		int ID = Integer.parseInt(request.getParameter("ID"));
		
		//1메시지, 2물품, 3근
		
		String AlramList ="";
		
		
		AlramList = AlarmList(emp_no, type, ID).toString();
		
		return AlramList;
	}
	
	
	//알람 처음
	public StringBuffer AlarmList(int emp_no, int type, int ID) throws Exception {  
		StringBuffer result = new StringBuffer("");
		
		EmployeeAllDBBean dbPro = EmployeeAllDBBean.getInstance();		
		AlarmDBBean AlarmPro = AlarmDBBean.getInstance();
		
		AlarmPro.countChat(emp_no);

		if(type==1) {
		ArrayList<MessageDataBean> messageList = (ArrayList<MessageDataBean>) AlarmPro.messageList(emp_no, ID);
		
		result.append("{\"result\":[");  //변수를 지정해서
		
		if(messageList != null) {
		
		for(int i = 0; i < messageList.size(); i++) {
			result.append("[{\"value\": \"" + messageList.get(i).getMsg_no() + "\"},"); //기존 글번호
			result.append("{\"value\": \"" + dbPro.getEmp_no(messageList.get(i).getMsg_fromno()).getEmp_name() + "\"},"); //발신자
			result.append("{\"value\": \"" + messageList.get(i).getMsg_content() + "\"},"); //내용 가져오기
			result.append("{\"value\": \"" + messageList.get(i).getMsg_from_time() + "\"}]");  //시간
			
			if(i != messageList.size() -1) result.append(",");
			
			}
		}
		
		}else if(type ==3) {
		
		//근태관리
		ArrayList<AttendanceDataBean> attendanceList = (ArrayList<AttendanceDataBean>) AlarmPro.attendanceList(emp_no, ID);
		
		result.append("{\"result\":[");

		if(attendanceList !=null) {
		
		for(int i = 0; i < attendanceList.size(); i++) {
			result.append("[{\"value\": \"" + attendanceList.get(i).getAtd_no() + "\"},"); //기존 글번호
			result.append("{\"value\": \"" + dbPro.getEmp_no(attendanceList.get(i).getAtd_emp_no()).getEmp_name() + "\"},"); //발신자
			result.append("{\"value\": \"" + attendanceList.get(i).getAtd_div() + "\"},"); //내용 가져오기
			result.append("{\"value\": \"" + attendanceList.get(i).getAtd_date().substring(0, 10) + "\"}]");  //시간
			
			if(i != attendanceList.size() -1) result.append(",");	
			}	
		}
		
		}else{
		//물품
		ArrayList<PurchaseDataBean> purchaseList = (ArrayList<PurchaseDataBean>) AlarmPro.PurchaseList(emp_no, ID);
				
		result.append("{\"result\":[");
				
		if(purchaseList != null) {
			
		for(int i = 0; i < purchaseList.size(); i++) {
			result.append("[{\"value\": \"" + purchaseList.get(i).getPur_no() + "\"},"); //기존 글번호
			result.append("{\"value\": \"" + dbPro.getEmp_no(purchaseList.get(i).getPur_emp_no()).getEmp_name() + "\"},"); //발신자
			result.append("{\"value\": \"" + purchaseList.get(i).getPur_title() + "\"},"); //내용 가져오기
			result.append("{\"value\": \"" + purchaseList.get(i).getPur_date() + "\"}]");  //시간
			
			if(i != purchaseList.size() -1) result.append(",");
				}
		}
		}
		
		result.append("], \"MessageLast\":\"" + AlarmPro.messageLast(emp_no) + "\""); 
		result.append(", \"AttendLast\":\"" + AlarmPro.attLast(emp_no) + "\""); 
		result.append(", \"PurchaseLast\":\"" + AlarmPro.purLast(emp_no) + "\""); 
		result.append(", \"type\":\"" + type + "\"");
		//채팅수
		result.append(", \"Last\":\"" + AlarmPro.countChat(emp_no) + "\"}"); 

		return result;
	}
}
