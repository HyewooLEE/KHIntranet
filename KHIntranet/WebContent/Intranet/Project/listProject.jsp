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
		<h1><i class="fa fa-home"></i>Project</h1>
		<ul class="app-breadcrumb breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
			<li class="breadcrumb-item"><a href="#">Project List</a></li>
		</ul>
	</div>
	<!-- content 내 상위 nav 끝 -->
	<!-- content 시작 -->
	
		<div class="row">
				<c:if test="${ count > 0 }">
				<div class="col-md-4 ">
					<div class="tile" style="padding:30px">
						<h5 class="line-head mb-3">프로젝트 진행 상황</h5>
						<c:forEach var="article" items="${ projectPercentList }">
                     <h6 id="progress-basic">${ article.pro_title }</h6>
                     <div class="progress mb-3">
                        <div class="progress-bar" role="progressbar" style="width:${ article.pro_percent }%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                     	</div>
                  		</c:forEach>
                  <c:if test="${curRow > 1 }">
                     <a href="/KHIntranet/listProject.do?rowNum=${curRow - 1}">[이전]</a>
                  </c:if>
                  <c:if test="${curRow < count / 4 }">
                     <a href="/KHIntranet/listProject.do?rowNum=${curRow + 1}">[다음]</a>
                  </c:if>
					</div>
				</div>
				</c:if>
			<div class="col-md-8">
				<div class="tile" style="padding:30px;">
				<h5 class="line-head mb-3" >프로젝트 진행 리스트</h5>
				<!-- 프로젝트가 없을 경우  -->
				<c:if test="${ count == 0 }">
					<div class="page-error">
	      				<h1><i class="fa fa-exclamation-circle"></i>등록한 프로젝트가 없습니다.</h1>
	      					<p>프로젝트를 등록 하시려면 아래 글자를 클릭해주세요.</p>
	      				<p><a href="${pageContext.request.contextPath}/writeProjectForm.do">Go to Write Project</a></p>
   					</div>
				</c:if>
				<!-- 프로젝트가 있을 경우 프로젝트 -->
				<c:forEach var="article" items="${articleList}">
				</c:forEach>
				<c:if test="${ count > 0 }">
					<table class="table table-hover table-bordered" id="projectTable">
						<thead>
							<tr>
								<th>프로젝트</th>
								<th>설명</th>
								<th>참가자</th>
								<th>등록일자</th>
								<th>시작일자</th>
								<th>목표일자</th>
								<th>진행도</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="article" items="${articleList}">
							<tr>
								<td onclick="javascript:location.assign('/KHIntranet/writeProjectForm.do?pro_no=${article.pro_no}&pro_ud=1');">${ article.pro_title }</td>
								<td onclick="javascript:location.assign('/KHIntranet/writeProjectForm.do?pro_no=${article.pro_no}&pro_ud=1');">${ article.pro_content }</td>
								<td onclick="javascript:location.assign('/KHIntranet/writeProjectForm.do?pro_no=${article.pro_no}&pro_ud=1');">${ article.emp_name }</td>
								<td onclick="javascript:location.assign('/KHIntranet/writeProjectForm.do?pro_no=${article.pro_no}&pro_ud=1');">${ article.pro_date }</td>
								<td onclick="javascript:location.assign('/KHIntranet/writeProjectForm.do?pro_no=${article.pro_no}&pro_ud=1');">${ article.pro_start_date }</td>
								<td onclick="javascript:location.assign('/KHIntranet/writeProjectForm.do?pro_no=${article.pro_no}&pro_ud=1');">${ article.pro_end_date }</td>
								<td onclick="javascript:location.assign('/KHIntranet/writeProjectForm.do?pro_no=${article.pro_no}&pro_ud=1');">${ article.pro_percent }%</td>
								<td><i class="fa fa-trash" onclick="javascript:deleteProject(${article.pro_no});"></i></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<p class="bs-component" align="right"><button class="btn btn-primary" type="button" onclick="javascript:window.location.assign('/KHIntranet/writeProjectForm.do?pro_ud=0')">등록</button></p>
				</c:if>
				</div>
			</div>
		</div>
	</main>
	<!-- content 끝 --> 

	<!-- Essential javascripts for application to work-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Project/js/list.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/dataTables.bootstrap.min.js"></script>
	<script>$('#projectTable').DataTable();</script>
</body>
</html>