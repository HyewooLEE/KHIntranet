package action.main;
  
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.websocket.Session;

import action.CommandAction;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.main.ConnectionDBBean;

public class Lognow  {
	public static int emp_no = 0;
//	public static String id = "";
	
	//������ ��ü�� �����ؼ� �̰͵� ���ǿ� ���� ��´�. �����ʶ�� �̸�����...
	public void setSession(HttpSession session, int emp_no) {
		
		session.setAttribute("listener", new CustomBindingListener());
		ConnectionDBBean ConPro = ConnectionDBBean.getInstance();
		ConPro.InsertCon(emp_no);  //�ʿ����
		setNowUser(emp_no);
	}
	
	//���� ������ ��
	public static void setNowUser(int x) {
		emp_no =x;

	}
	
	public int getNowUser() {
		return this.emp_no;
	}
}

//���⼭ ����
class CustomBindingListener implements HttpSessionBindingListener{
	ConnectionDBBean ConPro = ConnectionDBBean.getInstance();
	Lognow no = new Lognow();
	
	public void valueBound(HttpSessionBindingEvent event) {  //������ ������ �� ����

		//System.out.println("BOUND as " + event.getName() + " to " + event.getSession().getId());
	}
	
	public void valueUnbound(HttpSessionBindingEvent event) {  //������ ����Ǿ�����
		ConnectionDBBean ConPro = ConnectionDBBean.getInstance();
		int emp_no = no.getNowUser();//emp_no
		
		ConPro.DeleteCon(emp_no);
		
		//System.out.println("UNBOUND as " +event.getName() + " to " + event.getSession().getId());
		
	}
}

//HttpSessionBindingListener
/*valueBound() �� �����ʰ� ���ǿ� bind �ɶ� ȣ�� 
valueUnbound() �� �����ʰ� ���ǿ� unbind �ɶ� ȣ�� �˴ϴ�.*/