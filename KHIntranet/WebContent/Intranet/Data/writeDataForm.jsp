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
			<i class="fa fa-home"></i> Write Data
		</h1>
		<ul class="app-breadcrumb breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
			<li class="breadcrumb-item"><a href="/KHIntranet/listNotice.do">Data List</a></li>
			<li class="breadcrumb-item"><a href="#">Write Data</a></li>
		</ul>
	</div>
	<!-- <form method="get" action="" name="writeDataForm" enctype="multipart/form-data"> -->
	<form method="post" action="${pageContext.request.contextPath}/writeDataPro.do" name="writeDataForm" enctype="multipart/form-data">
		<input type="hidden" name="data_ud" value="${ data_ud }">
		<input type="hidden" name="data_no" value="${ data_no }">
		<div class="row">
		<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="tile" style="padding:50px;">
				<h5 class="line-head"> 자료실</h5>
					<div class="form-group">
						<label class="title"><b> 제목</b></label> 
						<input class="form-control" type="text" name="data_title" id="data_title" value="${ data_title }" placeholder="Enter Data Title..">
					</div>
					<div class="form-group">
						<label class="control-label"><b> 내용</b></label>
						<textarea class="form-control" id="data_content" name="data_content" rows="5" placeholder="Enter Data Content">${ data_content }</textarea>
					</div>
					
					
						<c:if test="${ data_file_name != null }">
						
							<div class="form-group">
							<label class="control-label"><b>첨부파일</b></label>
							<div class="row">
							<c:forEach var="file_name" items="${ data_file_name }">
								<div class="col-4" style="width:100%">
									<div class="bs-component">
										<div class="alert alert-dismissible alert-info">
											<button class="close" type="button" data-dismiss="alert" id="delete_btn">×</button>
											<input type="button" name="data_file_name" id="data_file_name" onclick="javascript:window.location.assign('${pageContext.request.contextPath}/Intranet/Data/fileDownload.jsp?data_no=${data_no}&data_file_name=${file_name}&data_file_addr=${data_file_addr}')" value="${file_name}" style="clear: none; border: 0px none; background-color: transparent; cursor: pointer;">
										</div>
									</div>	
								</div>
							</c:forEach>
							</div>
							</div>						
						</c:if>
					
					
						<c:if test="${ data_file_name == null }">
							<div class="form-group">
								<div class="row">
									<div class="col-md-12">
										<label class="control-label">첨부파일</label>
										<div class="row" id="inputFileForm">
											<div class="col-10">
												<input class="form-control" type="file" name="data_file_name" id="data_file_name">
											</div>
											<div class="col-2">											
												<input type="button" class="btn btn-default" type="button" value="+" id="addFile">
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					
					<div class="tile-footer" align="center">
						<button class="btn btn-primary" type="button" onclick="writeSave();">
							<i class="fa fa-fw fa-lg fa-check-circle"></i>Register
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="btn btn-secondary" type="button" onclick="dataFormCancel();">
							<i class="fa fa-fw fa-lg fa-times-circle"></i>Cancel
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Data/js/data.js"></script>
</body>
</html>