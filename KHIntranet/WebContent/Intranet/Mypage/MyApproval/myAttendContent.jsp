<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="model.employee.EmployeeAllDataBean" %>
<%@ page import="model.employee.EmployeeAllDBBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8"); %>

<%
	EmployeeAllDataBean emp_all = (EmployeeAllDataBean) session.getAttribute("emp_all");
	String myEmp_name = String.valueOf(emp_all.getEmp_name());
%>

<html>
<head>
<title>나의 근태신청 결재관리 문서</title>
</head>
<body>

	<main class="app-content">
	
		<div class="app-title">
	        <div>
	          <h1><i class="fa fa-th-list"></i>나의 결재함</h1>
	        </div>
	        <ul class="app-breadcrumb breadcrumb side">
	          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
	          <li class="breadcrumb-item">마이페이지</li>
	          <li class="breadcrumb-item active"><a href="/KHIntranet/myApproval.do">나의 결재함</a></li>
	        </ul>
	    </div>
	     
	    <div class="row">
	    <div class="col-md-1"></div>
	        <div class="col-md-10">
	          <div class="tab-content">
	          	<div class="tile">
	            	<div class="tile-body">
	            
		       			<!-- 미결재문서 > 근태신청관리 -->
		               	<form method="POST" name="deleteForm" action="${pageContext.request.contextPath}/myAttendDelete.do">
		               		<input type="hidden" value="${article.atd_no }" name="atd_no"/>
		               		
		               		<div class="col-12">
		               			<p align="right">
		               			작성일 : ${article.atd_date}<br/>
		               			문서번호 : 근태-${article.atd_no}
		               			</p>
		               		
			                	<table class="table table-bordered">
					                <thead>
					                  <tr>
			                             <th colspan ="4" bgcolor="#e7e7e7" ><h4 align="center">근태신청서</h4></th>
			                          </tr>                               	             
					              	</thead>
								                
					                <tbody>	     
					                	<tr>           	
					                	  <td>작성자</td>
					                   	  <td><%=myEmp_name %></td>
					                    </tr>
					                	<tr >           	
					                	  <td>근태구분</td>
					                      <td>${article.atd_div}</td>	
					                    </tr>
					                    <tr>           	
					                	  <td>수신자</td>
					                   	  <td>${emp_name }</td>
					                    </tr>
					                    <tr>           	
					                	  <td>일자</td>
					                      <td>${article.atd_start_date}&nbsp;~&nbsp;${article.atd_end_date}</td>	
					                    </tr>
					                    <tr>           	
					                	  <td>비고</td>
					                      <td>${article.atd_note}</td>	
					                    </tr>		
					                    <tr>
					                      <td>첨부파일</td>
					                      <td><a href="/KHIntranet/Intranet/Mypage/MyApproval/fileAtdDownload.jsp?atd_no=${article.atd_no}&atd_file_name=${article.atd_file_name}&atd_file_path=${article.atd_file_path}'">${article.atd_file_name}</a></td>	
					                    </tr>
					                </tbody>
					                
					            </table>
					            
				            <div class="d-print-none col-12 text-right">
				                <button class="btn btn-secondary" type="submit" onClick="return chkDeleteForm();"><i class="fa fa-fw fa-lg fa-times-circle"></i>삭제</button>
				                <a class="btn btn-primary" href="javascript:window.print();" target="_blank"><i class="fa fa-print"></i> 프린트</a>
				                <button class="btn btn-primary" type="button" onClick="location.href='/KHIntranet/myApproval.do'"><i class="fa fa-fw fa-lg fa-check-circle"></i>목록</button>
				            </div>
				            
				           </div>
				     	</form>
               		</div>
               </div>
	        </div>
       	  </div>
       </div><!-- row 끝 -->
    </main>
	 
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Mypage/MyApproval/js/myAttendContent.js"></script>
	
</body>
</html>