package action.employee;
  
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeDBBean;
import model.employee.EmployeeDataBean;

public class InputProAction implements CommandAction{  //�� �Է� ��ó��
			
	String no ="";
 
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		
		request.setCharacterEncoding("UTF-8"); //�ѱ� ���ڵ�
				
		no = reRandom();

		//ã��
		/*find_no(no)*/
		
		//����۰� �亯���� ����	
		EmployeeDataBean article = new EmployeeDataBean(); //������ó�� ��
		
		article.setEmp_no(Integer.parseInt(no));
		article.setEmp_id(request.getParameter("emp_id"));
		article.setEmp_name(request.getParameter("emp_name"));
		article.setEmp_pw(request.getParameter("emp_pw1"));
		article.setEmp_gender(request.getParameter("optionsRadios"));
		article.setEmp_jumin1(request.getParameter("emp_jumin1"));
		article.setEmp_email(request.getParameter("emp_email"));
		article.setEmp_pn(request.getParameter("emp_pn"));
		article.setEmp_addr1(request.getParameter("emp_addr1"));
		article.setEmp_addr2(request.getParameter("emp_addr2"));
		article.setEmp_addr3(request.getParameter("emp_addr3"));
		article.setEmp_pt_addr("http://192.168.40.79:8328/KHIntranet/Intranet/Mypage/Profile/upload/");
		article.setEmp_pt_name("profile_default.jpg");
		article.setPosition_rank(0);
		article.setEmp_sal(0);
		article.setEmp_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); //����ð� �Է�
		article.setDept_no(0);
		article.setEmp_annual(15);
		
		EmployeeDBBean dbPro = EmployeeDBBean.getInstance(); //DBó��
		dbPro.insertEmployee(article);
		
		return "/Intranet/Join/inputPro.jsp"; //�ش� ��
	}
	
	
		//�����ȣ �ߺ��˻�
		public String reRandom() throws NumberFormatException, Exception{
		EmployeeDBBean dbPro = EmployeeDBBean.getInstance(); //DBó��	
		
		int num = 0; //������
		int x = 0;
		
		
		//���Խ�û�⵵
		no = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(2,4);
		
		//random��
		num += (Math.random()*9)+1;

		no +=num;
		/*num= Integer.parseInt(dam + (Math.random()*999));*/
		
		/*Integer.parseInt*/
		x = dbPro.userEmp_no(Integer.parseInt(no));
		
		
		//no�� �������� ���翩�� Ȯ���� �ٽ� ������.
		if(x ==0) {
			reRandom();
		}

		 return no;
	}	
}
