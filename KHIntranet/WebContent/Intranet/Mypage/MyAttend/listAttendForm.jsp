<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>근태관리 리스트</title>
</head>

<body>
	<main class="app-content">
		<div class="app-title">
	        <div>
	          <h1><i class="fa fa-th-list"></i>나의 근태현황</h1>
	        </div>
	        <ul class="app-breadcrumb breadcrumb side">
	          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
	          <li class="breadcrumb-item">마이페이지</li>
	          <li class="breadcrumb-item active"><a href="/KHIntranet/listAttendForm.do">나의 근태현황</a></li>
	        </ul>
	    </div>
	    
      <div class="row">
      
        <div class="col-md-3">
          <div class="tile" style="height:auto;">
          <h5 class="line-head">현재 나의 근태현황</h5>
            <div class="tile-body">
              <div class="row" align="center">
                <div class="form-group col-md-12">
                  <label class="control-label col-md-8">총연차일수</label><span class="control-label col-md-4">${empAnnual }</span>
                </div>	    
                <div class="form-group col-md-12">
                  <label class="control-label col-md-8">사용일</label><span class="control-label col-md-4" style="color:red;">${useAnnday }</span>
                </div>	
                <div class="form-group col-md-12">
                  <label class="control-label col-md-8">가용일</label><span class="control-label col-md-4" style="color:blue;">${empAnnual - useAnnday }</span>
                </div>                
              </div>
            </div>
          </div>
        </div>
        
        <div class="col-md-9">  
          <div class="tile">
          <h5 class="line-head">나의 근태신청 현황</h5>
            <div class="tile-body">
              <table class="table table-hover table-bordered" id="attendListTable">
                <thead>
                  <tr>
                    <th>No.</th> 
                    <th>근태구분</th>
                    <th>기간</th>            
                    <th>내용</th>
                    <th>작성일</th>
                    <th>승인여부</th>
                  </tr>
                </thead>
                
                <tbody>           
                  <c:forEach var="AttendList" items="${AttendList}" >
                  	<tr>
                  		<td>${AttendList.atd_no}</td>
                  		<td>${AttendList.atd_div}</td>
                  		<td>${AttendList.atd_start_date} ~ ${AttendList.atd_end_date}</td>
                  		<td>${AttendList.atd_note}</td>
                  		<td>${AttendList.atd_date}</td>
                  		<td>
                  		<c:if test="${AttendList.atd_status_ny=='결재완료'}">
                  		<span class="badge badge-primary">${AttendList.atd_status_ny}</span>
                  		</c:if>
                  		<c:if test="${AttendList.atd_status_ny=='미결재'}">
                  		<span class="badge badge-danger">${AttendList.atd_status_ny}</span>
                  		</c:if>
                  		<c:if test="${AttendList.atd_status_ny=='반려'}">
                  		<span class="badge badge-warning">${AttendList.atd_status_ny}</span>
                  		</c:if>
                  		</td>
                  	</tr>
				  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
		
	</main>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript">$('#attendListTable').DataTable();</script>

</body>
</html>