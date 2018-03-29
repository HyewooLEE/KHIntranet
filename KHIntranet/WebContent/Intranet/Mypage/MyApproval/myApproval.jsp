<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import ="model.employee.EmployeeAllDataBean" %>
<%@ page import ="model.approval.attendance.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%request.setCharacterEncoding("utf-8"); %>

<%
	EmployeeAllDataBean emp_all = (EmployeeAllDataBean) session.getAttribute("emp_all");
	String myEmp_no = String.valueOf(emp_all.getEmp_no()); //나의 사원번호
%>

<html>
<head>
<title>나의 결재함</title>
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
	          <li class="breadcrumb-item active"><a href="/KHIntranet/myApproval.do">나의결재함</a></li>
	        </ul>
	     </div>
	     
	  	 <div class="row">
	  	 
	  	 	<!-- 나의 결재 문서함 tab -->
			<div class="col-md-3">
				<div class="tile">
					<h4 class="line-head">나의 결재함</h4>
				<div class="tile-body">
					<ul class="nav nav-pills flex-column mail-nav">
						<li class="nav-item"><a class="nav-link" id="Ndoc" style="cursor:pointer;">미결재 문서함</a></li>
						<li class="nav-item"><a class="nav-link" id="Ydoc" style="cursor:pointer;">결재완료 문서함</a></li>
						<li class="nav-item"><a class="nav-link" id="Rdoc" style="cursor:pointer;">반려 문서함</a></li>
					</ul>
				</div>
				</div>
			</div>	  		  	
        
	        <!--문서함 tab-content  -->
	        <div class="col-md-9">
	          <div class="tab-content">
          
	          	<!-- ###미결재문서 : NApproval-->
	            <div class="tab-pane" id="NApproval" style="display:inline;">
	              <div class="tile">
	                <h4 class="line-head">미결재 문서</h4>
	                <a class="nav-link dropdown-toggle" style="color:red;"  id="selectDocName1" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">분류</a>
	               		<div class="dropdown-menu">
	                		<c:forEach var="DocList" items="${DocList }">
	                			<a class="dropdown-item" id="docN_${DocList.doc_no }" onClick="changeDoc('${DocList.doc_no }','미결재')" >${DocList.doc_name }</a>
	                		</c:forEach>
	               		</div>
		                
	                	<!-- 미결재문서 > 근태신청관리 -->
	                	<div id="NattendDrop">
		                	<table class="table table-hover table-bordered" id="nattendTable">
				                <thead>
				                  <tr>
				                    <th>결재분류</th>          
				                    <th>수신자</th>
				                    <th>근태신청일자</th>
				                    <th>내용</th>
				                    <th>작성일</th>
				                  </tr>	                                    	             
				              	</thead>
							                
				                <tbody>	                	
				                	<c:forEach var="AttendList" items="${AttendList}" >	       
				                	
				                	<c:if test="${AttendList.atd_status_ny == '미결재' }">
				                	                        	
					                <c:set var="str1" value="${AttendList.atd_receiver }" />
					                <c:set var="str2" value="<%=myEmp_no %>" />			               
				                    
				                   	<c:if test="${AttendList.atd_emp_no == str2}">
					                    <tr style="cursor:pointer;" onclick="location.href='/KHIntranet/myAttendContent.do?atd_no=${AttendList.atd_no}'">
						                    <td>${AttendList.atd_div}</td>
						                    <td>${AttendList.atd_names}</td>
						                    <td>${AttendList.atd_start_date} ~ ${AttendList.atd_end_date}</td>
						                    <td>${AttendList.atd_note}</td>
						                    <td>${AttendList.atd_date}</td>
					                    </tr>
				                    </c:if>                    	                  	                                        
				                   
				                   </c:if>
									</c:forEach>						
				                </tbody>
				            </table>
	                	</div>
               		
               			<!-- 미결재문서 > 물품구매신청관리 -->
	                	<div id="NpurchaseDrop" style="display:none;">
	                		<table class="table table-hover table-bordered" id="npurchaseTable">
				                <thead>
				                  <tr>
				                    <th>수신자</th>
				                    <th>품명</th>
				                    <th>수량</th>
				                    <th>추정금액</th>
				                    <th>내용</th>
				                    <th>작성일</th>
				                  </tr>	                                    	             
				              	</thead>
						                
				                <tbody>	                	
				                	<c:forEach var="PurchaseList" items="${PurchaseList}" >	
				                	
				                	<c:if test="${PurchaseList.pur_status_ny == '미결재' }">
				                	                      	
						                <c:set var="str1" value="${PurchaseList.pur_receiver }" />
						                <c:set var="str2" value="<%=myEmp_no %>" />	               
					                    
					                   	<c:if test="${PurchaseList.pur_emp_no == str2}">
						                    <tr style="cursor:pointer;" onclick="location.href='/KHIntranet/myPurchaseContent.do?pur_no=${PurchaseList.pur_no}'">
						                    	<td>${PurchaseList.pur_names}</td>
							                   	<td>${PurchaseList.pur_name}</td>
							                   	<td>${PurchaseList.pur_quan}</td>
							                   	<td><fmt:formatNumber value="${PurchaseList.pur_sum}" groupingUsed="true" type="number"/></td>
							                    <td>${PurchaseList.pur_note}</td>
							                    <td>${PurchaseList.pur_date}</td>
						                    </tr>
					                    </c:if>                    	                  	                                        
				                   
				                   	</c:if>
									</c:forEach>
				                </tbody>
			              	</table>	
	                	</div>
		                	
	              	</div><!-- <div class="tile"> 끝 -->
              	</div><!-- 미결재문서 끝: NApproval-->            
            
           
	            <!-- ###결재완료 문서 : YApproval -->
	            <div class="tab-pane" id="YApproval">
	              <div class="tile">
	                <h4 class="line-head">결재완료 문서</h4>
	                <a class="nav-link dropdown-toggle" style="color:red;" id="selectDocName2" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">분류</a>
	               		<div class="dropdown-menu">
	                		<c:forEach var="DocList" items="${DocList }">
	                			<a class="dropdown-item" id="docY_${DocList.doc_no }" onClick="changeDoc('${DocList.doc_no }','결재완료')">${DocList.doc_name }</a>
	                		</c:forEach>
	               		</div>
	                
	                	<!-- 결재완료문서 > 근태신청 관리 -->
	                	<div class="tab-pane"  id="YattendDrop">
		                	<table class="table table-hover table-bordered" id="yattendTable">
				                <thead>
				                  <tr>
				                    <th>결재분류</th> 
				                    <th>근태신청일자</th>         
				                    <th>내용</th>
				                    <th>작성일</th>
				                  </tr>	                                    	             
				                </thead>
						                
				                <tbody>	                	
				                	<c:forEach var="AttendList" items="${AttendList}" >	       
				                	<c:if test="${AttendList.atd_status_ny == '결재완료' }">   
				                	                     	
						                <c:set var="str1" value="${AttendList.atd_receiver }" />
						                <c:set var="str2" value="<%=myEmp_no %>" /><!-- 나의 사원번호  -->
					                    
					                   	<c:if test="${AttendList.atd_emp_no == str2}">
						                    <tr style="cursor:pointer;" onclick="location.href='/KHIntranet/myAttendContent.do?atd_no=${AttendList.atd_no}'">
							                    <td>${AttendList.atd_div }</td>
							                    <td>${AttendList.atd_start_date} ~ ${AttendList.atd_end_date}</td>
							                    <td>${AttendList.atd_note}</td>
							                    <td>${AttendList.atd_date}</td>
						                    </tr>
					                    </c:if>
					                </c:if>                    	                  	                                        
									</c:forEach>						
				                </tbody>
			              	</table>
		              	</div><!-- 결재완료문서 > 근태신청 관리 끝 -->
		        
			              
			            <!-- 결재완료문서 > 물품구매신청관리 -->
	                	<div class="tab-pane" id="YpurchaseDrop"  style="display:none;">
	                		<table class="table table-hover table-bordered" id="ypurchaseTable">
				                <thead>
				                  <tr>
				                    <th>품명</th>
				                    <th>수량</th>
				                    <th>추정금액</th>
				                    <th>내용</th>
				                    <th>작성일</th>
				                  </tr>	                                    	             
				              	</thead>
						                
				                <tbody>	                	
				                	<c:forEach var="PurchaseList" items="${PurchaseList}" >	
				                	
				                	<c:if test="${PurchaseList.pur_status_ny == '결재완료' }">
				                	                      	
						                <c:set var="str1" value="${PurchaseList.pur_receiver }" />
						                <c:set var="str2" value="<%=myEmp_no %>" />	               
					                    
					                   	<c:if test="${PurchaseList.pur_emp_no == str2}">
						                    <tr style="cursor:pointer;" onclick="location.href='/KHIntranet/myPurchaseContent.do?pur_no=${PurchaseList.pur_no}'">
							                   	<td>${PurchaseList.pur_name}</td>
							                   	<td>${PurchaseList.pur_quan}</td>
							                   	<td><fmt:formatNumber value="${PurchaseList.pur_sum}" groupingUsed="true" type="number"/></td>
							                    <td>${PurchaseList.pur_note}</td>
							                    <td>${PurchaseList.pur_date}</td>
						                    </tr>
					                    </c:if>                    	                  	                                        
				                   
				                   	</c:if>
									</c:forEach>
				                </tbody>
			              	</table>
	                	</div><!-- 결재완료문서 > 물품구매신청관리 끝 -->
	            	</div><!-- tile 끝 -->
          		</div><!-- 결재완료 문서 끝 : YApproval -->
          		
          		
          		<!-- ###반려 문서 : RApproval -->
	            <div class="tab-pane" id="RApproval">
	              <div class="tile">
	                <h4 class="line-head">반려 문서</h4>
	                <a class="nav-link dropdown-toggle" style="color:red;" id="selectDocName3" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">분류</a>
	               		<div class="dropdown-menu">
	                		<c:forEach var="DocList" items="${DocList }">
	                			<a class="dropdown-item" id="docR_${DocList.doc_no }" onClick="changeDoc('${DocList.doc_no }','반려')">${DocList.doc_name }</a>
	                		</c:forEach>
	               		</div>
	                
	                	<!-- 반려문서 > 근태신청 관리 -->
	                	<div class="tab-pane"  id="RattendDrop">
		                	<table class="table table-hover table-bordered" id="rattendTable">
				                <thead>
				                  <tr>
				                    <th>결재분류</th>   
				                    <th>근태신청일자</th>       
				                    <th>내용</th>
				                    <th>작성일</th>
				                  </tr>	                                    	             
				                </thead>
						                
				                <tbody>	                	
				                	<c:forEach var="AttendList" items="${AttendList}" >	       
				                	<c:if test="${AttendList.atd_status_ny == '반려' }">   
				                	                     	
						                <c:set var="str1" value="${AttendList.atd_receiver }" />
						                <c:set var="str2" value="<%=myEmp_no %>" /><!-- 나의 사원번호  -->
					                    
					                   	<c:if test="${AttendList.atd_emp_no == str2}">
						                    <tr style="cursor:pointer;" onclick="location.href='/KHIntranet/myAttendContent.do?atd_no=${AttendList.atd_no}'">
							                    <td>${AttendList.atd_div }</td>
							                    <td>${AttendList.atd_start_date} ~ ${AttendList.atd_end_date}</td>
							                    <td>${AttendList.atd_note}</td>
							                    <td>${AttendList.atd_date}</td>
						                    </tr>
					                    </c:if>
					                </c:if>                    	                  	                                        
									</c:forEach>						
				                </tbody>
			              	</table>
		              	</div><!-- 반려문서 > 근태신청 관리 끝 -->
		        
			              
			            <!-- 반려문서 > 물품구매신청관리 -->
	                	<div class="tab-pane" id="RpurchaseDrop"  style="display:none;">
	                		<table class="table table-hover table-bordered" id="rpurchaseTable">
				                <thead>
				                  <tr>
				                    <th>품명</th>
				                    <th>수량</th>
				                    <th>추정금액</th>
				                    <th>내용</th>
				                    <th>작성일</th>
				                  </tr>	                                    	             
				              	</thead>
						                
				                <tbody>	                	
				                	<c:forEach var="PurchaseList" items="${PurchaseList}" >	
				                	
				                	<c:if test="${PurchaseList.pur_status_ny == '반려' }">
				                	                      	
						                <c:set var="str1" value="${PurchaseList.pur_receiver }" />
						                <c:set var="str2" value="<%=myEmp_no %>" />	               
					                    
					                   	<c:if test="${PurchaseList.pur_emp_no == str2}">
						                    <tr style="cursor:pointer;" onclick="location.href='/KHIntranet/myPurchaseContent.do?pur_no=${PurchaseList.pur_no}'">
							                   	<td>${PurchaseList.pur_name}</td>
							                   	<td>${PurchaseList.pur_quan}</td>
							                   	<td><fmt:formatNumber value="${PurchaseList.pur_sum}" groupingUsed="true" type="number"/></td>
							                    <td>${PurchaseList.pur_note}</td>
							                    <td>${PurchaseList.pur_date}</td>
						                    </tr>
					                    </c:if>                    	                  	                                        
				                   
				                   	</c:if>
									</c:forEach>
				                </tbody>
			              	</table>
	                	</div><!-- 반려문서 > 물품구매신청관리 끝 -->
	            	</div><!-- tile 끝 -->
          		</div><!-- 반려 문서 끝 : RApproval -->
          		
          		
          </div><!-- tab-content 끝 -->
      	</div>
	  </div>   
	</main>


	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript">$('#nattendTable').DataTable();</script>
    <script type="text/javascript">$('#npurchaseTable').DataTable();</script>
    <script type="text/javascript">$('#yattendTable').DataTable();</script>
    <script type="text/javascript">$('#ypurchaseTable').DataTable();</script>
    <script type="text/javascript">$('#rattendTable').DataTable();</script>
    <script type="text/javascript">$('#rpurchaseTable').DataTable();</script>
    
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Mypage/MyApproval/js/myApproval.js"></script>
	<c:forEach var="DocList" items="${DocList }">
	<script>
	$(function(){  
		
	   	  $('#docN_${DocList.doc_no }').on('click',function(){ 
	   		  var chkDoc1 = document.getElementById("docN_${DocList.doc_no }").innerHTML;
	   		  document.getElementById("selectDocName1").innerHTML = chkDoc1 ;  
	   	  });
	   	  
	   	  $('#docY_${DocList.doc_no }').on('click',function(){
	   		  var chkDoc2 = document.getElementById("docY_${DocList.doc_no }").innerHTML;
	   		  document.getElementById("selectDocName2").innerHTML = chkDoc2 ;
	   	  });	
	   	  
	   	  $('#docR_${DocList.doc_no }').on('click',function(){
	   		  var chkDoc3 = document.getElementById("docR_${DocList.doc_no }").innerHTML;
	   		  document.getElementById("selectDocName3").innerHTML = chkDoc3 ;
	   	  });
	    	  
	});
	</script>
	</c:forEach>
	
</body>
</html>