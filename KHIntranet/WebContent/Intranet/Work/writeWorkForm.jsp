<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>WriteForm</title>
</head>

<body>
	<main class="app-content">
	<!-- content 내 상위 nav 시작 -->
	<div class="app-title">
		<h1><i class="fa fa-home"></i> Work</h1>
		<ul class="app-breadcrumb breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
			<li class="breadcrumb-item"><a href="#">Work</a></li>
			<li class="breadcrumb-item"><a href="#">Write Work</a></li>
		</ul>
	</div>
	<!-- content 내 상위 nav 끝 -->
	<!-- content1 시작 -->
	<form method="post" action="/KHIntranet/writeWorkPro.do" name="writeWorkForm" enctype="multipart/form-data">
	<input type="hidden" name="pro_participant" value="${pro_participant}">
	<input type="hidden" name="work_no" value="${work_no}">
	<input type="hidden" name="work_ud" value="${ work_ud }">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<div class="tile" style="padding:50px;">
			<h4 class="line-head mb-3">업무보고</h4>
				<div class="row mb-3">
					<div class="col-md-6">
						<div class="form-group">
							<label for="pro_title"><b>프로젝트</b></label>
							<select class="form-control" id="pro_no" name="pro_no">
								<c:if test="${ work_no == null }">
								<c:forEach var="project" items="${projects}">
									<option value="${ project.pro_no }">${ project.pro_title }</option>
				                </c:forEach>
				                </c:if>
				                <c:if test="${ work_no != null }">
				                	<option value="${ pro_no }">${ pro_title }</option>
						        </c:if>
							</select>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="emp_no"><b>작성자</b></label>
							<input class="form-control" id="emp_name" name="emp_name" type="text" value="${emp_name}">
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group" style="margin-bottom:30px;">
							<label for="work_percent"><b>진행 률</b></label>
				           	<select class="form-control" id="pro_percent" name="pro_percent">
						        <c:forEach var="i" begin="0" end="10" step="1">
					    	      	<option value="${ i * 10 }">${ i * 10 }%</option>
					            </c:forEach>
				        	</select>
						</div>
						<div class="form-group">
							<h6 id="progress-basic">진행 그래프</h6>
							<div class="progress mb-3">
								<div class="progress-bar" role="progressbar" id="pro_progress" style="width:${pro_percent}%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-md-12">
						<div class="bs-component">
							<ul class="nav nav-tabs">
								<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#work_ct_pb">문제점</a></li>
							</ul>
							<div class="tab-content" id="myTabContent">
								<div class="tab-pane fade active show">
									<textarea class="form-control" id="work_ct_pb" name="work_ct_pb" rows="7">${ work_ct_pb }</textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-md-12">
						<div class="bs-component">
							<ul class="nav nav-tabs">
								<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#work_ct_sg">제안</a></li>
							</ul>
							<div class="tab-content" id="myTabContent">
								<div class="tab-pane fade active show">
									<textarea class="form-control" id="work_ct_sg" name="work_ct_sg" rows="7">${ work_ct_sg }</textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
				        	<c:if test="${ work_file_name != null }">
								<label class="control-label"><b>첨부파일</b></label>
								<div class="col-2">
						            <div class="bs-component">
						              <div class="alert alert-dismissible alert-info">
						                <button class="close" type="button" data-dismiss="alert" id="delete_btn">×</button>
						                <input type="button" name="work_file_name" id="work_file_name" onclick="javascript:window.location.assign('${pageContext.request.contextPath}/Intranet/Work/fileDownload.jsp?work_no=${work_no}&work_file_name=${work_file_name}&work_file_addr=${work_file_addr}')" value="${work_file_name}" style="clear:none; border:0px none; background-color:transparent; cursor:pointer;">
						              </div>
						            </div>
						        </div>
							</c:if>
							<c:if test="${ work_file_name == null }">
								<label class="control-label">첨부파일</label>
								<input class="form-control" type="file" name="work_file_name">
							</c:if>
				        </div>
					</div>
				</div>
				<div class="tile-footer" align="center">
					<button class="btn btn-primary" type="button" onclick="writeSave();">
						<i class="fa fa-fw fa-lg fa-check-circle"></i>등록
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="btn btn-secondary" type="button" onclick="workFormReset();">
						<i class="fa fa-fw fa-lg fa-times-circle"></i>취소
					</button>
				</div>
			</div>
		</div>
	</div>
	</form>
	</main>
	<!-- content2 끝-->

	<!-- Essential javascripts for application to work-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Work/js/work.js"></script>
</body>
</html>