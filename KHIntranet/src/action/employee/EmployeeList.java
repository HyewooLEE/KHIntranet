package action.employee;
  
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.main.ChatDBBean;
import model.main.ChatDTO;

public class EmployeeList implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		int type = Integer.parseInt(request.getParameter("type"));
		String Drop_name = request.getParameter("Drop_name");
		int type_no = Integer.parseInt(request.getParameter("type_no"));
		
		String empList ="";
		String x = "0";
		
		//�μ�
		if(type==2) {
			if(Drop_name.equals("����:")) {
				
				if(type_no == 0) {
					x = "1";
					return x;
				}
				empList =Dept_noList(type_no).toString();
			}else { //������ ��ü�� �ƴҶ�
				if(type_no == 0) {
					empList = Position_nameList(Drop_name).toString();
					//Drop_name
				}else {
				//PositionName
				empList = getPosition_Dept(Drop_name, type_no).toString();
				}
			}
		
		//����
		}else if(type ==1) {
			if(Drop_name.equals("�μ�:")) {	
				
				if(type_no == 0) {
					x = "1";
					return x;
				}
				empList =Position_noList(type_no).toString();	
			}else {//�μ��� ã��
				if(type_no == 0) {
						empList = Dept_nameList(Drop_name).toString();
						//Drop_name
					}else {
				//DeptName
						empList = getDept_Position(Drop_name, type_no).toString();
					}
			}
		}
		return empList;
	}
	
		//�μ��� ��ü
		public StringBuffer Dept_noList(int Dept_no) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //������ �����ؼ�
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.getDept_no(Dept_no);

			
			//ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(10);
			for(int i = 0; i < EmpList.size(); i++) {				
				result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no()+ "\"},"); //�̸��� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_id() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_gender() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_jumin1() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_email() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_pn() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_sal() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_date() + "\"}]");  //�ð�	
				
				if(i != EmpList.size() -1) result.append(",");
			}
			result.append("]}");

			return result;	
			
		}
		
		//�������� ��ü
		public StringBuffer Position_noList(int Position_rank) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //������ �����ؼ�
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.getPosition_rank(Position_rank);

			for(int i = 0; i < EmpList.size(); i++) {				
				result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no()+ "\"},"); //�̸��� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_id() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_gender() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_jumin1() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_email() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_pn() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_sal() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_date() + "\"}]");  //�ð�		
				
				if(i != EmpList.size() -1) result.append(",");
			}
			result.append("]}");

			return result;	
		}
		
		//dept_name���� ��ü
		public StringBuffer Dept_nameList(String dept_name) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //������ �����ؼ�
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.getDept_name(dept_name);

			for(int i = 0; i < EmpList.size(); i++) {				
				result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no()+ "\"},"); //�̸��� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_id() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_gender() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_jumin1() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_email() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_pn() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_sal() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_date() + "\"}]");  //�ð�	
				
				if(i != EmpList.size() -1) result.append(",");
			}
			result.append("]}");

			return result;	
		}
		
		//Position_name ��ü
		public StringBuffer Position_nameList(String Position_name) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //������ �����ؼ�
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.getPosition_name(Position_name);

			for(int i = 0; i < EmpList.size(); i++) {			
				System.out.println(EmpList.get(i).getEmp_name());
				result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no()+ "\"},"); //�̸��� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_id() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_gender() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_jumin1() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_email() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_pn() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_sal() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_date() + "\"}]");  //�ð�		
				
				if(i != EmpList.size() -1) result.append(",");
			}
			result.append("]}");

			return result;	
		}
		
		
		//������ name �μ��� no
		public StringBuffer getPosition_Dept(String Position_name, int dept_no) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //������ �����ؼ�
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.Position_DeptNo(Position_name, dept_no);

			for(int i = 0; i < EmpList.size(); i++) {				
				result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no()+ "\"},"); //�̸��� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_id() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_gender() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_jumin1() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_email() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_pn() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_sal() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_date() + "\"}]");  //�ð�	
				
				if(i != EmpList.size() -1) result.append(",");
			}
			result.append("]}");

			return result;	
		}
			
		
		
		//�μ��� name ������ no
		public StringBuffer getDept_Position(String dept_name, int Position_rank) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //������ �����ؼ�
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.Dept_PositionRank(dept_name, Position_rank);

			for(int i = 0; i < EmpList.size(); i++) {				
				result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no()+ "\"},"); //�̸��� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_id() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_gender() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_jumin1() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_email() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_pn() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_sal() + "\"},"); //���� ��������
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_date() + "\"}]");  //�ð�		
				
				if(i != EmpList.size() -1) result.append(",");
			}
			result.append("]}");

			return result;	
		}
}
