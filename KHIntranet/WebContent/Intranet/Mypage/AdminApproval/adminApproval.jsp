<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="model.employee.EmployeeAllDataBean" %>
<%@page import="model.employee.EmployeeAllDBBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%request.setCharacterEncoding("utf-8"); %>

<%
	EmployeeAllDataBean emp_all = (EmployeeAllDataBean) session.getAttribute("emp_all");
	String myEmp_no = String.valueOf(emp_all.getEmp_no());
%>

<html>
<head>
<title>결재 승인 관리</title>
</head>
<body>
	
	
	
	<main class="app-content">
		 <div class="app-title">
	        <div>
	          <h1><i class="fa fa-th-list"></i>결재 승인 요청문서</h1>
	        </div>
	        <ul class="app-breadcrumb breadcrumb side">
	          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
	          <li class="breadcrumb-item">마이페이지</li>
	          <li class="breadcrumb-item active"><a href="/KHIntranet/adminApproval.do">결재 승인 요청문서</a></li>
	        </ul>
	      </div>
	      
	      <div class="row">
	      
	      	<!--문서구분 tab -->
			<div class="col-md-3">
				<div class="tile">
					<h4 class="line-head">결재문서 구분</h4>
					<div class="tile-body">
						<ul class="nav nav-pills flex-column mail-nav">
							<c:forEach var="DocList" items="${DocList }">
	                			<li class="nav-item" style="cursor:pointer;">
	                				<a class="nav-link" id="doc_${DocList.doc_no }">${DocList.doc_name }</a>
	                			</li>
	                		</c:forEach>
						</ul>
					</div>
				</div>
			</div>	 
	      
	      	<!-- 결재승인 대기문서 테이블 -->
	        <div class="col-md-9">
	        	<div class="tab-content">
	        
	        		<!------------------------------------------ 근태신청관리 테이블-------------------------------------------->
					<div class="tab-pane active" id="docTable_1">
					<div class="tile">
						<h4 class="line-head">근태신청 결재대기문서</h4>
						<div class="tile-body">
							<c:if test="${countA  == 0}">
								<div class="col-md-12" style="text-align: center; margin-bottom: 30px;">
									<h5 style="margin-top: 15px;">요청받은 결재글이 존재하지 않습니다.</h5>
								</div>
							</c:if>
							<c:if test="${countA > 0}">
								<div align="right">글목록(전체글:${countA})</div>
								<table class="table table-hover table-bordered" id="adminAttendTable">
									<thead>
										<tr>
											<th>발신자</th>
											<th>결재분류</th>
											<th>내용</th>
											<th>작성일</th>
											<th>승인</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="AttendList" items="${AttendList}">
											<c:set var="atdReceiver" value="${AttendList.atd_receiver }" /> <!-- 수신자 -->
											<c:set var="myEmpNo" value="<%=myEmp_no%>" /> <!-- 나의 사원번호. 결재하는사람  -->
											<c:set var="atdEmpno" value="${AttendList.atd_emp_no }+''" /> <!-- 발신자 -->
											<c:set var="fnAtdReceive" value="${fn:substringAfter(atdReceiver, ',')}+''" />

											<form name="formAttend" id="formAttend" action="${pageContext.request.contextPath}/adminAttendPro.do" method="post">
											<input type="hidden" value="${fn:substringAfter(atdReceiver, ',')}" name="atd_receiver" />
											<input type="hidden" value="${AttendList.atd_no}" name="atd_no" />
											<c:if test="${fnAtdReceive.equals(atdEmpno)}">
												<input type="hidden" value="결재완료" name="atd_status_ny">
											</c:if>

											<c:if test="${fn:substringBefore(atdReceiver, ',') == myEmpNo }">
												<tr>
													<c:forEach var="EmpList" items="${EmpList}">
														<c:if test="${AttendList.atd_emp_no == EmpList.emp_no }">
															<td style="cursor: pointer;" onclick="location.href='/KHIntranet/adminAttendContent.do?atd_no=${AttendList.atd_no}'">${EmpList.emp_name}</td>
														</c:if>
													</c:forEach>
													<td style="cursor: pointer;" onclick="location.href='/KHIntranet/adminAttendContent.do?atd_no=${AttendList.atd_no}'">${AttendList.atd_div }</td>
													<td style="cursor: pointer;" onclick="location.href='/KHIntranet/adminAttendContent.do?atd_no=${AttendList.atd_no}'">${AttendList.atd_note }</td>
													<td style="cursor: pointer;" onclick="location.href='/KHIntranet/adminAttendContent.do?atd_no=${AttendList.atd_no}'">${AttendList.atd_date }</td>
													<td width="150px">
														<button type="submit" class="btn btn-outline-info" onClick="return chkAttendYForm();">승인</button>
														<input type="button" value="반려" class="btn btn-outline-danger" onclick="chkAttendNForm('${AttendList.atd_no}','${AttendList.atd_emp_no }');">
													</td>
												</tr>
											</c:if>
											</form>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
						</div>
						
						<!-- 페이징 -->
						<c:if test="${countA > 0}">
							<c:set var="pageCount" value="${countA / pageSizeA + ( countA % pageSizeA == 0 ? 0 : 1)}" />
							<c:set var="pageBlock" value="${5}" />
							<fmt:parseNumber var="result" value="${currentPageA / 5}" integerOnly="true" />
							<c:set var="startPage" value="${result * 5 + 1}" />
							<c:set var="endPage" value="${startPage + pageBlock-1}" />
							<c:if test="${endPage > pageCount}">
								<c:set var="endPage" value="${pageCount}" />
							</c:if>
							<div class="tile-footer" >
								<div class="col-md-12">
									<div class="row" style="text-align: center;">
										<div class="col-md-3"></div>
										<div class="col-md-6" style="text-align: center;">
											<ul class="pagination" style="text-align: center;">
												<c:if test="${startPage > 10}">
													<li class="page-item disabled"><a class="page-link" href="/KHIntranet/adminApproval.do?pageNumA=${startPage-10 }">이전</a></li>
												</c:if>
												<c:forEach var="i" begin="${startPage}" end="${endPage}">
													<li class="page-item" style="display: inline-block;">
														<a class="page-link" href="/KHIntranet/adminApproval.do?pageNumA=${i}">${i}</a>
													</li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</c:if>
						
					</div><!-- tile 끝 -->
				</div>



				<!------------------------------------------ 물품구매신청관리 테이블-------------------------------------------->
		          <div class="tab-pane fade" id="docTable_2">
	        		  <div class="tile">
	          			<h4 class="line-head">물품구매신청 결재대기문서</h4>
	            		<div class="tile-body">
	            		<c:if test="${countP  == 0}">
							<div class="col-md-12" style="text-align: center; margin-bottom: 30px;">
								<h5 style="margin-top: 15px;">요청받은 결재글이 존재하지 않습니다.</h5>
							</div>
						</c:if>
						<c:if test="${countP > 0}">
		                  <div align="right">글목록(전체글:${countP})</div>
			              <table class="table table-hover table-bordered" id="adminPurchaseTable">
			                <thead>
			                  <tr>
			                    <th>발신자</th>
			                    <th>제목</th>   
			                    <th>품명</th>
			                    <th>수량</th>
			                    <th>추정금액</th>
			                    <th>내용</th>
			                    <th>작성일</th>
			                    <th>승인</th>
			                  </tr>	                                    
			                </thead>
			                <tbody>
			                	<c:forEach var="PurchaseList" items="${PurchaseList}" >	                
			                	
				                <c:set var="purReceiver" value="${PurchaseList.pur_receiver }" /><!-- 수신자 -->
				                <c:set var="purEmpno" value="${PurchaseList.pur_emp_no }+''"	 /><!-- 발신자 -->
				                <c:set var="fnPurReceive" value="${fn:substringAfter(purReceiver, ',')}+''" />             
			                    
			                    <form name="formPurchase" class="formPurchase" id="formPurchase" action="${pageContext.request.contextPath}/adminPurchasePro.do" method="post">
				                    <input type="hidden" value="${fn:substringAfter(purReceiver, ',')}" name="pur_receiver" />
				                    <input type="hidden" value="${PurchaseList.pur_no}" name="pur_no" />
				                    <c:if test="${fnPurReceive == purEmpno}">
				                    <input type="hidden" value="결재완료" name="pur_status_ny">
				                    </c:if>
			                    
				                    <c:if test="${fn:substringBefore(purReceiver, ',') == myEmpNo }"> 
					                    <tr>
					                    	<c:forEach var="EmpList" items="${EmpList}" >
										 		<c:if test="${PurchaseList.pur_emp_no == EmpList.emp_no }">   
					                    			<td style="cursor:pointer;" onclick="location.href='/KHIntranet/adminPurchaseContent.do?pur_no=${PurchaseList.pur_no}'">${EmpList.emp_name}</td>
					                    		</c:if>
					                   		</c:forEach>
						                    <td style="cursor:pointer;" onclick="location.href='/KHIntranet/adminPurchaseContent.do?pur_no=${PurchaseList.pur_no}'">${PurchaseList.pur_title }</td>
						                    <td style="cursor:pointer;" onclick="location.href='/KHIntranet/adminPurchaseContent.do?pur_no=${PurchaseList.pur_no}'">${PurchaseList.pur_name }</td>
						                    <td style="cursor:pointer;" onclick="location.href='/KHIntranet/adminPurchaseContent.do?pur_no=${PurchaseList.pur_no}'"><fmt:formatNumber value="${PurchaseList.pur_quan}" groupingUsed="true" type="number"/></td>
						                    <td style="cursor:pointer;" onclick="location.href='/KHIntranet/adminPurchaseContent.do?pur_no=${PurchaseList.pur_no}'"><fmt:formatNumber value="${PurchaseList.pur_sum}" groupingUsed="true" type="number"/></td>
						                    <td style="cursor:pointer;" onclick="location.href='/KHIntranet/adminPurchaseContent.do?pur_no=${PurchaseList.pur_no}'">${PurchaseList.pur_note }</td>
						                    <td style="cursor:pointer;" onclick="location.href='/KHIntranet/adminPurchaseContent.do?pur_no=${PurchaseList.pur_no}'">${PurchaseList.pur_date }</td>
					                    	
						                    <td width="150px">
						                    	<button type="submit" class="btn btn-outline-info" onclick="return updatePurYForm();">승인</button>
						                    	<input type="button" value="반려" class="btn btn-outline-danger" onclick="updatePurNForm('${PurchaseList.pur_no}','${PurchaseList.pur_emp_no }');">
						                  	</td>
					                  	</tr>
				                  	</c:if>
			                  	</form>
								</c:forEach>
			                </tbody>
			              </table>
			             </c:if>
	            		</div>
	            		
	            		<!-- 페이징 -->
	            		<c:if test="${countP > 0}">
							<c:set var="pageCount" value="${countP / pageSizeP + ( countP % pageSizeP == 0 ? 0 : 1)}" />
							<c:set var="pageBlock" value="${5}" />
							<fmt:parseNumber var="result" value="${currentPageP / 5}" integerOnly="true" />
							<c:set var="startPage" value="${result * 5 + 1}" />
							<c:set var="endPage" value="${startPage + pageBlock-1}" />
							<c:if test="${endPage > pageCount}">
								<c:set var="endPage" value="${pageCount}" />
							</c:if>
							<div class="tile-footer" >
								<div class="col-md-12">
									<div class="row" style="text-align: center;">
										<div class="col-md-3"></div>
										<div class="col-md-6" style="text-align: center;">
											<ul class="pagination" style="text-align: center;">
												<c:if test="${startPage > 10}">
													<li class="page-item disabled"><a class="page-link" href="/KHIntranet/adminApproval.do?pageNumP=${startPage-10 }">이전</a></li>
												</c:if>
												<c:forEach var="i" begin="${startPage}" end="${endPage}">
													<li class="page-item" style="display: inline-block;">
														<a class="page-link" href="/KHIntranet/adminApproval.do?pageNumP=${i}">${i}</a>
													</li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</c:if>
	            		
	          		 </div><!-- tile 끝 -->
	        	</div>
	        	
	        </div><!-- tab-content 끝 -->
	      </div>
		</div><!-- row 끝 -->
		
	</main>
	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/bootstrap-datepicker.min.js"></script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Mypage/AdminApproval/js/adminApproval_1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Mypage/AdminApproval/js/adminApproval_2.js"></script>
    
</body>
</html>