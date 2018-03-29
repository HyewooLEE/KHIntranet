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
	
	//리스너 객체를 생성해서 이것도 세션에 같이 담는다. 리스너라는 이름으로...
	public void setSession(HttpSession session, int emp_no) {
		
		session.setAttribute("listener", new CustomBindingListener());
		ConnectionDBBean ConPro = ConnectionDBBean.getInstance();
		ConPro.InsertCon(emp_no);  //필요없음
		setNowUser(emp_no);
	}
	
	//현재 접속자 수
	public static void setNowUser(int x) {
		emp_no =x;

	}
	
	public int getNowUser() {
		return this.emp_no;
	}
}

//여기서 구현
class CustomBindingListener implements HttpSessionBindingListener{
	ConnectionDBBean ConPro = ConnectionDBBean.getInstance();
	Lognow no = new Lognow();
	
	public void valueBound(HttpSessionBindingEvent event) {  //세션이 생겼을 때 내용

		//System.out.println("BOUND as " + event.getName() + " to " + event.getSession().getId());
	}
	
	public void valueUnbound(HttpSessionBindingEvent event) {  //세션이 종료되었을때
		ConnectionDBBean ConPro = ConnectionDBBean.getInstance();
		int emp_no = no.getNowUser();//emp_no
		
		ConPro.DeleteCon(emp_no);
		
		//System.out.println("UNBOUND as " +event.getName() + " to " + event.getSession().getId());
		
	}
}

//HttpSessionBindingListener
/*valueBound() 는 리스너가 세션에 bind 될때 호출 
valueUnbound() 는 리스너가 세션에 unbind 될때 호출 됩니다.*/