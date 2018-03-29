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
	          <h1><i class="fa fa-th-list"></i>물품구매신청 상세</h1>
	        </div>
	        <ul class="app-breadcrumb breadcrumb side">
	          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
	          <li class="breadcrumb-item">마이페이지</li>
	          <li class="breadcrumb-item active"><a href="/KHIntranet/adminPurchaseContent.do">물품구매 신청서</a></li>
	        </ul>
	     </div>
	     
	      <div class="row">
	      <div class="col-md-1"></div>
	        <div class="col-md-10">
	          <div class="tile">
	            <div class="tile-body">
	            	 <div class="col-12 table-responsive">
		                  <table class="table table-bordered">
		                  <div align="right">
		                  작성일 : ${article.pur_date}<br/>
		                  문서번호 : 물품구매-${article.pur_no}<p>
		                  </div>
		                     <thead>
		                        <tr>
		                           <th colspan ="4" bgcolor="#e7e7e7"><h4  align="center" >물품구매 신청서</h4></th>
		                        </tr>
		                     </thead>
		                    <tbody>
		                      <tr>
		                        <td>사원명</td>
		                        <c:forEach var="EmpList" items="${EmpList}" >
							 		<c:if test="${article.pur_emp_no == EmpList.emp_no }">   
		                    			<td colspan="3">${EmpList.emp_name}</td>
		                    		</c:if>
		                   		</c:forEach>
		                      </tr>
		                      <tr>
		                        <td>글제목</td>
		                        <td colspan="3">${article.pur_title}</td>
		                      </tr>
		                      <tr>
		                        <td>품명</td>
		                        <td colspan="3">${article.pur_name}</td>
		                      </tr>
		                      <tr>
		                        <td>규격</td>
		                        <td>${article.pur_standard}</td>
		                        <td>수량</td>
		                        <td><fmt:formatNumber value="${article.pur_quan}" groupingUsed="true" type="number"/></td>
		                      </tr>
		                      <tr>
		                        <td>추정단가</td>
		                        <td><fmt:formatNumber value="${article.pur_price}" groupingUsed="true" type="number"/></td>
		                        <td>추정금액</td>
		                        <td><fmt:formatNumber value="${article.pur_sum}" groupingUsed="true" type="number"/></td>
		                      </tr>
		                      <tr>
		                        <td>용도</td>
		                        <td colspan="3">${article.pur_use}</td>
		                      </tr>
		                      <tr>
		                        <td>비고</td>
		                        <td colspan="3">${article.pur_note}</td>
		                      </tr>
		                       <tr>
		                        <td>첨부파일</td>
		                        <td colspan="3">${article.pur_file_name}</td>
		                      </tr>
		                    </tbody>
		                  </table>
		             </div>
		             <div class="d-print-none col-12 text-right">
		             	<c:if test="${article.pur_file_name != null }">
		             	<button class="btn btn-secondary" type="button" onClick="location.href='/KHIntranet/Intranet/Mypage/MyApproval/filePurDownload.jsp?pur_no=${article.pur_no}&pur_file_name=${article.pur_file_name}&pur_file_path=${article.pur_file_path}'">파일다운로드</button>
		             	</c:if>
		             	<a class="btn btn-primary" href="javascript:window.print();" target="_blank"><i class="fa fa-print"></i> 프린트</a>
		             	<button class="btn btn-primary" type="button" onClick="location.href='/KHIntranet/adminApproval.do'"><i class="fa fa-th-list"></i>목록보기</button>
		             </div>
	            </div>
	          </div>
	        </div>
	        <div class="col-md-1"></div>
	      </div>
	  </main>

	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
	
	<!-- Page specific javascripts-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/bootstrap-datepicker.min.js"></script>

</body>
</html>