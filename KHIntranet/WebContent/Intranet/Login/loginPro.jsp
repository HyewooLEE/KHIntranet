<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import ="model.employee.EmployeeAllDataBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Login/js/login.js"></script>
<% request.setCharacterEncoding("utf-8"); %>

<%
   String emp_id = request.getParameter("emp_id");
   Integer check = (Integer)request.getAttribute("check");
   
   EmployeeAllDataBean emp_all = (EmployeeAllDataBean) request.getAttribute("article");

   if(check==2){ //2 로그인 가능

      session.setAttribute("emp_id", emp_id);  //memId라는 이름에 id값을 넣는다.
      session.setAttribute("emp_all", emp_all);
      
      response.sendRedirect("/KHIntranet/main.do");  //main.jsp로 보낸다.
   }
%>