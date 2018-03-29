<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Project</title>
</head>

<body>
	<main class="app-content">
	<!-- content 내 상위 nav 시작 -->
	<div class="app-title">
		<h1><i class="fa fa-home"></i>Work</h1>
		<ul class="app-breadcrumb breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
			<li class="breadcrumb-item"><a href="#">Work List</a></li>
		</ul>
	</div>
	<!-- content 내 상위 nav 끝 -->
	<!-- content 시작 -->
	
	
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="tile" style="padding:50px;">
					<h4 class="line-head mb-3">업무 리스트</h4>
					<div class="tile-body">
					<!-- 프로젝트가 없을 경우  -->
					<c:if test="${ workCount == 0 }">
						<div class="page-error">
		      				<h1><i class="fa fa-exclamation-circle"></i>등록한 작업이 없습니다.</h1>
		      					<p>작업 하신 것을 등록 하시려면 아래 글자를 클릭해주세요.</p>
		      				<p><a href="javascript:window.location.assign('${pageContext.request.contextPath}/writeWorkForm.do')">Go to Write Work</a></p>
	   					</div>
					</c:if>  
					<!-- 프로젝트가 있을 경우 프로젝트 -->
					<c:forEach var="workList" items="${workList}">
					</c:forEach>
					<c:if test="${ workCount > 0 }">
						<table class="table table-hover table-bordered" id="projectTable">
							<thead>
								<tr>
									<th>프로젝트 명</th>
									<th>문제점</th>
									<th>해결내용</th>
									<th>처리자</th>
									<th>등록일자</th>
									<th>진행</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="workList" items="${workList}">
								<tr>
									<td onclick="javascript:location.assign('/KHIntranet/writeWorkForm.do?work_no=${workList.work_no}&work_ud=1');">${ workList.pro_title }</td>
									<td onclick="javascript:location.assign('/KHIntranet/writeWorkForm.do?work_no=${workList.work_no}&work_ud=1');">${ workList.work_ct_pb }</td>
									<td onclick="javascript:location.assign('/KHIntranet/writeWorkForm.do?work_no=${workList.work_no}&work_ud=1');">${ workList.work_ct_sg }</td>
									<td onclick="javascript:location.assign('/KHIntranet/writeWorkForm.do?work_no=${workList.work_no}&work_ud=1');">${ emp_name }</td>
									<td onclick="javascript:location.assign('/KHIntranet/writeWorkForm.do?work_no=${workList.work_no}&work_ud=1');">${ workList.work_date }</td>
									<td onclick="javascript:location.assign('/KHIntranet/writeWorkForm.do?work_no=${workList.work_no}&work_ud=1');">${ workList.pro_percent }%</td>
									<td><i class="fa fa-trash" onclick="javascript:deleteWork(${workList.work_no});"></i></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						<p class="bs-component" align="right"><button class="btn btn-primary" type="button" onclick="javascript:window.location.assign('/KHIntranet/writeWorkForm.do?work_ud=0')">등록</button></p>
					</c:if>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!-- content 끝 --> 

	<!-- Essential javascripts for application to work-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Work/js/list.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/dataTables.bootstrap.min.js"></script>
	<script>$('#projectTable').DataTable();</script>
</body>
</html>