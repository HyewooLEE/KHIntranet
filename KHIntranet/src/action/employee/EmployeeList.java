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
		
		//부서
		if(type==2) {
			if(Drop_name.equals("직급:")) {
				
				if(type_no == 0) {
					x = "1";
					return x;
				}
				empList =Dept_noList(type_no).toString();
			}else { //직급이 전체가 아닐때
				if(type_no == 0) {
					empList = Position_nameList(Drop_name).toString();
					//Drop_name
				}else {
				//PositionName
				empList = getPosition_Dept(Drop_name, type_no).toString();
				}
			}
		
		//직급
		}else if(type ==1) {
			if(Drop_name.equals("부서:")) {	
				
				if(type_no == 0) {
					x = "1";
					return x;
				}
				empList =Position_noList(type_no).toString();	
			}else {//부서로 찾기
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
	
		//부서로 전체
		public StringBuffer Dept_noList(int Dept_no) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //변수를 지정해서
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.getDept_no(Dept_no);

			
			//ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(10);
			for(int i = 0; i < EmpList.size(); i++) {				
				result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no()+ "\"},"); //이름값 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_id() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_gender() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_jumin1() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_email() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_pn() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_sal() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_date() + "\"}]");  //시간	
				
				if(i != EmpList.size() -1) result.append(",");
			}
			result.append("]}");

			return result;	
			
		}
		
		//직급으로 전체
		public StringBuffer Position_noList(int Position_rank) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //변수를 지정해서
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.getPosition_rank(Position_rank);

			for(int i = 0; i < EmpList.size(); i++) {				
				result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no()+ "\"},"); //이름값 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_id() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_gender() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_jumin1() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_email() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_pn() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_sal() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_date() + "\"}]");  //시간		
				
				if(i != EmpList.size() -1) result.append(",");
			}
			result.append("]}");

			return result;	
		}
		
		//dept_name으로 전체
		public StringBuffer Dept_nameList(String dept_name) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //변수를 지정해서
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.getDept_name(dept_name);

			for(int i = 0; i < EmpList.size(); i++) {				
				result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no()+ "\"},"); //이름값 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_id() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_gender() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_jumin1() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_email() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_pn() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_sal() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_date() + "\"}]");  //시간	
				
				if(i != EmpList.size() -1) result.append(",");
			}
			result.append("]}");

			return result;	
		}
		
		//Position_name 전체
		public StringBuffer Position_nameList(String Position_name) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //변수를 지정해서
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.getPosition_name(Position_name);

			for(int i = 0; i < EmpList.size(); i++) {			
				System.out.println(EmpList.get(i).getEmp_name());
				result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no()+ "\"},"); //이름값 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_id() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_gender() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_jumin1() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_email() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_pn() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_sal() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_date() + "\"}]");  //시간		
				
				if(i != EmpList.size() -1) result.append(",");
			}
			result.append("]}");

			return result;	
		}
		
		
		//직급이 name 부서는 no
		public StringBuffer getPosition_Dept(String Position_name, int dept_no) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //변수를 지정해서
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.Position_DeptNo(Position_name, dept_no);

			for(int i = 0; i < EmpList.size(); i++) {				
				result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no()+ "\"},"); //이름값 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_id() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_gender() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_jumin1() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_email() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_pn() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_sal() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_date() + "\"}]");  //시간	
				
				if(i != EmpList.size() -1) result.append(",");
			}
			result.append("]}");

			return result;	
		}
			
		
		
		//부서는 name 직급이 no
		public StringBuffer getDept_Position(String dept_name, int Position_rank) throws Exception {
			StringBuffer result = new StringBuffer("");
			EmployeeAllDBBean empAll = EmployeeAllDBBean.getInstance();
			
			result.append("{\"result\":[");  //변수를 지정해서
			
			ArrayList<EmployeeAllDataBean> EmpList = (ArrayList<EmployeeAllDataBean>) empAll.Dept_PositionRank(dept_name, Position_rank);

			for(int i = 0; i < EmpList.size(); i++) {				
				result.append("[{\"value\": \"" + EmpList.get(i).getEmp_no()+ "\"},"); //이름값 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_id() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_gender() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_jumin1() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_email() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_pn() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getDept_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getPosition_name() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_sal() + "\"},"); //내용 가져오기
				result.append("{\"value\": \"" + EmpList.get(i).getEmp_date() + "\"}]");  //시간		
				
				if(i != EmpList.size() -1) result.append(",");
			}
			result.append("]}");

			return result;	
		}
}
