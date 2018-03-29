<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<script>
	$(function() {
		$("#photo_file_nm").change(function() {
			var ext = $("input:file").val().split(".").pop().toLowerCase();
			if(ext.length > 0){
				if($.inArray(ext, ["gif","png","jpg","jpeg"]) == -1) { 
					$('#photo_file_nm').val('');
					swal.getState();	
					swal("오류!","사진 형식의 파일만 업로드가 가능합니다.","warning", {buttons: "닫기"});
									return false;
				}
			}else {
			var form = $('form')[0];
			var formData = new FormData(form);
			var filePath = 'http://localhost:8328/KHIntranet/Intranet/Photo/upload';
			$.ajax({
				url : "uploadImage.ajax", 
				processData: false,
                contentType: false,
				type : "post",
				data : formData,
				success : function(data) {
					$("#fileDisplay").html("<div class='col-md-12'>"+
												"<img src='"+filePath+"/sm_"+data+"'>"+
											 "</div>");
				},
				error : function(e) {
					alert(e.responseText);
				}
			});
			}
		});
	});
	function checkSubmit(){
		var file = $('#photo_file_nm').val();
		if (file ==''){
			swal.getState();	
			swal("오류!","사진을 선택해주세요.","warning", {buttons: "닫기"});
			return false;
		}else{
			$("#form").submit();
		}
		}
</script>
<title>사진첩</title>
</head>
<body>
	<main class="app-content">
	<div class="app-title">
		<div>
			<h1>
				<i class="fa fa-th-list"></i> 사진첩
			</h1>
		</div>

		<ul class="app-breadcrumb breadcrumb side">
			<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
			<li class="breadcrumb-item">커뮤니티</li>
			<li class="breadcrumb-item">사진첩</li>
			<li class="breadcrumb-item active"><a href="#"> 수정하기</a></li>
		</ul>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<div class="tile" style="padding:50px;">
				<h3 class="mb-4 line-head" id="buttons">수정하기</h3>
				<div class="tile-body">
					<form id="form" class="form-horizontal" action="/KHIntranet/photowritePro.do" method="post" name="writeform" enctype="multipart/form-data">
						<input type="hidden" name="emp_no" value="${empBean.emp_no }">
						<div class="row">
							<div class="col-md-8">
								<div class="form-group" >
										<label class="control-label">제목</label>
										<input class="form-control" name="photo_title" type="text" placeholder="제목을 입력하세요." value="${article.photo_title }">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label">비밀번호</label>
										<input class="form-control" name="password" type="password" maxlength="20" placeholder="비밀번호를 입력하세요"  value="${article.password }">
								</div>
							</div>
						</div>
						<div class="form-group">
								<label class="control-label">내용</label>
								<textarea class="form-control" rows="15" name="photo_content" maxlength="200" placeholder="내용을 입력하세요" >${article.photo_content }</textarea>
						</div>
						<div class="form-group">
							<label class="control-label"><b>사진선택</b></label>
							<input class="form-control" type="file" id="photo_file_nm" name="photo_file_nm"  value="">
						</div>
						<div class="row" id="fileDisplay">
							<div class='col-md-12'>
								<img src='${article.photo_file_addr}/sm_${article.photo_file_nm}'>
							</div>
						</div>
						<div class="tile-footer">
							<div class="row">
								<div class="col-md-8 col-md-offset-3">
									<button class="btn btn-primary" type="button" onClick="return checkSubmit();">
										<i class="fa fa-fw fa-lg fa-check-circle"> </i>수정
									</button>
									&nbsp;&nbsp;&nbsp;
									<a class="btn btn-secondary" href="javascript:history.go(-1)"><i	class="fa fa-fw fa-lg fa-times-circle"></i>취소</a>
								</div>
							</div>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
	</main>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <c:if test="${messageType !=null }">           
	<c:if test="${messageType =='성공!' }">           
	<script>
		swal.getState();	
		swal("${messageType}","${messageContent}","success", {buttons: "닫기"});
	</script>
	</c:if>
	<c:if test="${messageType =='오류!' }">           
	<script>
		swal.getState();	
		swal("${messageType}","${messageContent}","warning", {buttons: "닫기"});
	</script>
	</c:if>
	</c:if>
	<%
		session.removeAttribute("messageContent");
		session.removeAttribute("messageType");	
	 %>
</body>
</html>