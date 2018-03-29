package action.main;
  
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDBBean;
import model.main.AlarmDBBean;
import model.main.ChatDBBean;
import model.main.ChatDTO;


public class ChatListServlet implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");  //�ѱ� �޾ƿ���
		response.setContentType("text/html;charset=UTF-8");
		
		int emp_no = Integer.parseInt(request.getParameter("emp_no"));  //�˶� ����
		String listType = request.getParameter("listType");
  
		//String chatName="as";
		
		ChatDBBean dao = ChatDBBean.getInstance();
		AlarmDBBean APro = AlarmDBBean.getInstance();
		
		
		EmployeeAllDBBean allPro = EmployeeAllDBBean.getInstance();
		
		List allBean = allPro.selectAll();
		
		//String emp_all = getEmpAll_id(emp_id).toString();
		
		if(listType == null || listType.equals("")) {
			response.getWriter().write(""); //���� �߻���
		}else if (listType.equals("today")) {
			response.getWriter().write(getToday().toString()); //�޽��� ���� �־��ֱ� ���ڿ��� �ޱ� ����	
		}else if (listType.equals("ten")) {
			//request.setAttribute("chatName",chatName);
			response.getWriter().write(getTen().toString()); //�ֱ� 10�� ��������
		}else {  //chat ��������ȣ
			try {
				int chatID = Integer.parseInt(listType);
				APro.updateChat(emp_no, chatID);//�˶� ������Ʈ
				response.getWriter().write(getID(listType).toString());
			}catch(Exception e) {
				response.getWriter().write("");
			}
		}
		return "";
	}	
		
		//���� ����
		public StringBuffer getToday() throws Exception {
			StringBuffer result = new StringBuffer("");
			result.append("{\"result\":[");  //������ �����ؼ�
			
			ChatDBBean chatDAO = ChatDBBean.getInstance();
			EmployeeAllDBBean allPro = EmployeeAllDBBean.getInstance();
			
			ArrayList<ChatDTO> chatList = chatDAO.getChatList(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			for(int i = 0; i < chatList.size(); i++) {
				System.out.println(allPro.getEmp_no(chatList.get(i).getChat_emp_no()).getEmp_pt_addr());
				result.append("[{\"value\": \"" + chatList.get(i).getChat_emp_no() + "\"},"); //�̸��� ��������
				result.append("{\"value\": \"" + chatList.get(i).getChat_emp_name() + "\"},");
				result.append("{\"value\": \"" + chatList.get(i).getChat_content() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + allPro.getEmp_no(chatList.get(i).getChat_emp_no()).getEmp_pt_addr() + "\"},");
				result.append("{\"value\": \"" + allPro.getEmp_no(chatList.get(i).getChat_emp_no()).getEmp_pt_name() + "\"},");
				result.append("{\"value\": \"" + chatList.get(i).getChat_time() + "\"}]");  //�ð�
				
				if(i != chatList.size() -1) result.append(",");
				
			}
			result.append("], \"last\":\"" + chatList.get(chatList.size() -1).getChat_no() + "\"}");
			return result;
		}
		
		
		//10�� ������ ��������
		public StringBuffer getTen() throws Exception {  
			StringBuffer result = new StringBuffer("");
			result.append("{\"result\":[");  //������ �����ؼ�
			
			ChatDBBean chatDAO = ChatDBBean.getInstance();
			EmployeeAllDBBean allPro = EmployeeAllDBBean.getInstance();
			
			
			ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(10);
			for(int i = 0; i < chatList.size(); i++) {				
				System.out.println(allPro.getEmp_no(chatList.get(i).getChat_emp_no()).getEmp_pt_addr());
				result.append("[{\"value\": \"" + chatList.get(i).getChat_emp_no() + "\"},"); //�̸��� ��������
				result.append("{\"value\": \"" + chatList.get(i).getChat_emp_name() + "\"},");
				result.append("{\"value\": \"" + chatList.get(i).getChat_content() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + allPro.getEmp_no(chatList.get(i).getChat_emp_no()).getEmp_pt_addr() + "\"},");
				result.append("{\"value\": \"" + allPro.getEmp_no(chatList.get(i).getChat_emp_no()).getEmp_pt_name() + "\"},");
				result.append("{\"value\": \"" + chatList.get(i).getChat_time() + "\"}]");  //�ð�
				
				if(i != chatList.size() -1) result.append(",");
				
			}
			result.append("], \"last\":\"" + chatList.get(chatList.size() -1).getChat_no() + "\"}");
			return result;
		}
		
		
		public StringBuffer getID(String chatID) throws Exception {  
			StringBuffer result = new StringBuffer("");
			result.append("{\"result\":[");  //������ �����ؼ�
			
			ChatDBBean chatDAO = ChatDBBean.getInstance();
			EmployeeAllDBBean allPro = EmployeeAllDBBean.getInstance();
			
			ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(chatID);
			if(chatList != null) {
			for(int i = 0; i < chatList.size(); i++) {
				System.out.println(allPro.getEmp_no(chatList.get(i).getChat_emp_no()).getEmp_pt_addr());
				result.append("[{\"value\": \"" + chatList.get(i).getChat_emp_no() + "\"},"); //�̸��� ��������
				result.append("{\"value\": \"" + chatList.get(i).getChat_emp_name() + "\"},");
				result.append("{\"value\": \"" + chatList.get(i).getChat_content() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + allPro.getEmp_no(chatList.get(i).getChat_emp_no()).getEmp_pt_addr() + "\"},");
				result.append("{\"value\": \"" + allPro.getEmp_no(chatList.get(i).getChat_emp_no()).getEmp_pt_name() + "\"},");				
				result.append("{\"value\": \"" + chatList.get(i).getChat_time() + "\"}]");  //�ð�
				
				if(i != chatList.size() -1) result.append(",");
				
			}
			result.append("], \"last\":\"" + chatList.get(chatList.size() -1).getChat_no() + "\"}");
			
		}else {
			result.append("]}");
		}

			return result;
		}

	}
//���̺� �� ��������
//ajax 