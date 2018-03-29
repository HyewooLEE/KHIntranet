package action.employee;
  
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeDBBean;
import model.employee.EmployeeDataBean;

public class InputProAction implements CommandAction{  //글 입력 폼처리
			
	String no ="";
 
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		
		request.setCharacterEncoding("UTF-8"); //한글 인코딩
				
		no = reRandom();

		//찾기
		/*find_no(no)*/
		
		//제목글과 답변글의 구분	
		EmployeeDataBean article = new EmployeeDataBean(); //데이터처리 빈
		
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
		article.setEmp_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); //현재시간 입력
		article.setDept_no(0);
		article.setEmp_annual(15);
		
		EmployeeDBBean dbPro = EmployeeDBBean.getInstance(); //DB처리
		dbPro.insertEmployee(article);
		
		return "/Intranet/Join/inputPro.jsp"; //해당 뷰
	}
	
	
		//사원번호 중복검사
		public String reRandom() throws NumberFormatException, Exception{
		EmployeeDBBean dbPro = EmployeeDBBean.getInstance(); //DB처리	
		
		int num = 0; //랜덤값
		int x = 0;
		
		
		//가입신청년도
		no = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(2,4);
		
		//random값
		num += (Math.random()*9)+1;

		no +=num;
		/*num= Integer.parseInt(dam + (Math.random()*999));*/
		
		/*Integer.parseInt*/
		x = dbPro.userEmp_no(Integer.parseInt(no));
		
		
		//no를 가져가서 존재여부 확인후 다시 돌린다.
		if(x ==0) {
			reRandom();
		}

		 return no;
	}	
}
