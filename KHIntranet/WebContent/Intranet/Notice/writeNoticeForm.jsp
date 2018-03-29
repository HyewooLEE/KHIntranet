<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>WriteForm</title>
</head>
<body>
	<main class="app-content">
	<div class="app-title">
		<h1>
			<i class="fa fa-home"></i> Write Notice
		</h1>
		<ul class="app-breadcrumb breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
			<li class="breadcrumb-item"><a href="/KHIntranet/listNotice.do">Notice
					List</a></li>
			<li class="breadcrumb-item"><a href="#">Write Notice</a></li>
		</ul>
	</div>
	<form method="post" action="${pageContext.request.contextPath}/writeNoticePro.do" name="writeNoticeForm" enctype="multipart/form-data">
		<input type="hidden" name="notice_ud" value="${ notice_ud }">
		<input type="hidden" name="notice_no" value="${ notice_no }">
		<div class="row">
		<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="tile" style="padding:50px;">
				<h5 class="line-head"> 공지사항</h5>
					<div class="form-group">
						<label class="title" ><b>제목</b></label> 
						<input class="form-control" type="text" name="notice_title" id="notice_title" value="${ notice_title }" placeholder="Enter Notice Title..">
					</div>
					<div class="form-group">
						<label class="control-label"><b>내용</b></label>
						<textarea class="form-control" id="notice_content" name="notice_content" rows="5" placeholder="Enter Notice Content">${ notice_content }</textarea>
					</div>
					
					<div class="form-group">
						<c:if test="${ notice_file_name != null }">
							<label class="control-label"><b>첨부파일</b></label>
							<div class="col-2">
								<div class="bs-component">
									<div class="alert alert-dismissible alert-info">
										<button class="close" type="button" data-dismiss="alert" id="delete_btn">×</button>
										<input type="button" name="notice_file_name" id="notice_file_name" onclick="javascript:window.location.assign('${pageContext.request.contextPath}/Intranet/Notice/fileDownload.jsp?notice_no=${notice_no}&notice_file_name=${notice_file_name}&notice_file_addr=${notice_file_addr}')" value="${notice_file_name}" style="clear: none; border: 0px none; background-color: transparent; cursor: pointer;">
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${ notice_file_name == null }">
							<label class="control-label">첨부파일</label>
							<input class="form-control" type="file" name="notice_file_name">
						</c:if>
					</div>
					<div class="tile-footer" align="center">
						<button class="btn btn-primary" type="button" onclick="writeSave();">
										<i class="fa fa-fw fa-lg fa-check-circle"></i>등록
									</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn btn-secondary" type="button"  onclick="noticeFormCancel();">
							<i class="fa fa-fw fa-lg fa-times-circle"></i>리스트로
						</button>
						
					</div>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
	</form>
	</main>
	<!-- Essential javascripts for application to work-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Notice/js/notice.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/bootstrap-datepicker.min.js"></script>
</body>
</html>