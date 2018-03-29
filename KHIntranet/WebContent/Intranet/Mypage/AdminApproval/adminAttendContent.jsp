<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>근태신청 상세페이지</title>
</head>
<body>

	<main class="app-content">
		<div class="app-title">
	        <div>
	          <h1><i class="fa fa-th-list"></i>근태신청 상세</h1>
	        </div>
	        <ul class="app-breadcrumb breadcrumb side">
	          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
	          <li class="breadcrumb-item">마이페이지</li>
	          <li class="breadcrumb-item active"><a href="/KHIntranet/adminAttendContent.do">근태신청서</a></li>
	        </ul>
	     </div>
	     
	      <div class="row">
	      <div class="col-md-1"></div>
	        <div class="col-md-10">
	          <div class="tile">
	            <div class="tile-body">
	            	 <div class="col-12 table-responsive">
	            	  	  <p align="right">
		                  작성일 : ${article.atd_date}<br/>
		                  문서번호 : 근태-${article.atd_no}
		                  </p>
		                  <table class="table table-bordered">
		                     <thead>
		                        <tr>
		                           <th colspan ="4" bgcolor="#e7e7e7"><h4  align="center" >근태 신청서</h4></th>
		                        </tr>
		                     </thead>
		                    <tbody>
		                      <tr>
		                        <td>사원명</td>
		                        <c:forEach var="EmpList" items="${EmpList}" >
							 		<c:if test="${article.atd_emp_no == EmpList.emp_no }">   
		                    			<td>${EmpList.emp_name}</td>
		                    		</c:if>
		                   		</c:forEach>
		                        <td>근태구분</td>
		                        <td>${article.atd_div}</td>
		                      </tr>
		                      <tr>
		                        <td>일자</td>
		                        <td colspan="3">${article.atd_start_date}&nbsp;~&nbsp;${empBean.atd_end_date}</td>
		                      </tr>
		                      <tr>
		                        <td>비고</td>
		                        <td colspan="3">${article.atd_note}</td>
		                      </tr>
		                      <tr>
		                        <td>첨부파일</td>
		                        <td colspan="3">${article.atd_file_name}</td>
		                      </tr>
		                    </tbody>
		                  </table>
		             </div>
		             <div class="d-print-none col-12 text-right">
		             	<c:if test="${article.atd_file_name != null }">
		             	<button class="btn btn-secondary" type="button" onClick="location.href='/KHIntranet/Intranet/Mypage/MyApproval/fileAtdDownload.jsp?atd_no=${article.atd_no}&atd_file_name=${article.atd_file_name}&atd_file_path=${article.atd_file_path}'">파일다운로드</button>
		             	</c:if>
		             	<a class="btn btn-primary" href="javascript:window.print();" target="_blank"><i class="fa fa-print"></i> 프린트</a>
		             	<button class="btn btn-primary" type="button" onClick="location.href='/KHIntranet/adminApproval.do'"><i class="fa fa-th-list"></i>목록보기</button>
		             </div>
	            </div>
	          </div>
	        </div>
	      </div>
	      <div class="col-md-1"></div>
	     </div>
	  </main>

	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
	
</body>
</html>