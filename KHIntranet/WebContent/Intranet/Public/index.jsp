<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.employee.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
   EmployeeAllDataBean emp_all = (EmployeeAllDataBean) session.getAttribute("emp_all");
%>
<title>KH Intranet</title>
</head>
<body>
	<%  if(emp_all == null){ 
   response.sendRedirect("/KHIntranet/tologin.do");
    }else{
   response.sendRedirect("/KHIntranet/main.do");
   } 
	
%>
</body>
</html>
