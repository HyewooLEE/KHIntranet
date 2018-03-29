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
<title>나의 물품구매신청 결재관리 문서</title>
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
	          
	          <!-- 미결재문서 : NApproval-->
              <div class="tab-pane active" id="NApproval" >
	          
	          	<div class="tile">
	            <div class="tile-body">
	            
	       			<!-- 미결재문서 > 근태신청관리 -->
	               	<form method="POST" name="deleteForm" action="${pageContext.request.contextPath}/myPurchaseDelete.do">
	               		<input type="hidden" value="${article.pur_no }" name="pur_no"/>
	               		
	               		<div class="col-12 ">
	               			<p align="right">
		               			작성일 : ${article.pur_date}<br/>
		               			문서번호 : 물품구매-${article.pur_no}
		               		</p>
	               			
	                		<table class="table table-bordered">
				                <thead>
				                  <tr>
		                             <th colspan ="4" bgcolor="#e7e7e7" ><h4 align="center">물품구매 신청서</h4></th>
		                          </tr>                               	             
				              	</thead>
						                
				                <tbody>
				                	<tr>           	
				                	  <td>작성자</td>
				                   	  <td colspan="3"><%=myEmp_name %></td>
				                    </tr>	     
				                	<tr >           	
				                	  <td>글제목</td>
				                      <td colspan="3">${article.pur_title }</td>	
				                    </tr>
				                    <tr>           	
				                	  <td>수신자</td>
				                	  <td colspan="3">${emp_name}</td>
				                    </tr>
				                    <tr>           	
				                	  <td>품명</td>
				                      <td colspan="3">${article.pur_name }</td>	
				                    </tr>
				                    <tr>           	
				                	  <td>규격</td>
				                      <td>${article.pur_standard }</td>	
				                      <td>수량</td>
				                      <td>${article.pur_quan }</td>	
				                    </tr>
				                    <tr>           	
				                	  <td>추정단가</td>
				                      <td>${article.pur_price }</td>	
				                      <td>추정금액</td>
				                      <td>${article.pur_sum }</td>	
				                    </tr>
				                    <tr>           	
				                	  <td>용도</td>
				                      <td colspan="3">${article.pur_use }</td>		
				                    </tr>
				                    <tr>           	
				                	  <td>비고</td>
				                      <td colspan="3">${article.pur_note }</td>		
				                    </tr>		
				                    <tr>           	
				                	  <td>첨부파일</td>
				                      <td colspan="3"><a href="/KHIntranet/Intranet/Mypage/MyApproval/filePurDownload.jsp?pur_no=${article.pur_no}&pur_file_name=${article.pur_file_name}&pur_file_path=${article.pur_file_path}'">${article.pur_file_name}</a></td>	
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
	     </div>
	   </div><!-- row 끝 -->
	   
	</main>
	 
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Mypage/MyApproval/js/myPurchaseContent.js"></script>
	
</body>
</html>