<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import ="model.employee.EmployeeAllDataBean" %>

<%
	EmployeeAllDataBean emp_all = (EmployeeAllDataBean) session.getAttribute("emp_all");
%>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Vali Admin</title>
  </head>
  <body>
    <div class="page-error">
      <h1><i class="fa fa-exclamation-circle"></i>해당 관리자만 들어갈 수 있습니다.</h1>
      <p>The page you have requested is not found.</p>
      <p><a href="/KHIntranet/main.do">Go back to Main page</a></p>
    </div>
  </body> 
  <!-- Essential javascripts for application to work-->
  <script src="js/jquery-3.2.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/main.js"></script>
  <!-- The javascript plugin to display page loading on top-->
  <script src="js/plugins/pace.min.js"></script>
</html>