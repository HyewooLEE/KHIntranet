<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>

<style>
input:focus {
	outline: none;
}

#photo_content {
	font-size: 1.1rem;
}

#writer_img {
	width: 40px;
	height: 40px;
	margin-right: 10px;
	border-radius: 50% !important;
}
</style>
<script>
	$(function() {
		$("#comentBtn").click(
				function() {
					var photo_no = ${article.photo_no};
					var writer = $("#writer").val();
					var password = $("#password").val();
					var photo_content = $("#photo_content").val();

					if (photo_content == "") {
						alert("내용을 입력하세요.");
						return;
					}

					$.ajax({
						url : "comment.ajax", //commandAjaxURI.properties 등록한 URL
						type : "post",
						data : {
							writer : writer,
							password : password,
							photo_content : photo_content,
							photo_no : photo_no
						},
						success : function(data) {
							$("#comment").append(
									"<div class='col-md-12'>" + "<p>" + "<b>"
											+ writer + "</b>&nbsp;" + "-"
											+ "&nbsp;<span>" + photo_content
											+ "</span>" + "</p>" + "</div>");
							$("#photo_content").val("");
							$('#comment').collapse('show');
						},
						error : function(e) {
							alert(e.responseText);
						}
					});
				});
	});

	$(function() {
		$(".like_btn")
				.click(
						function() {
							var like_emp_no = ${empBean.emp_no};
							var like_photo_no = ${article.photo_no};
							var check = $('.like_btn').attr('id');
							$
									.ajax({
										url : "photoLike.ajax", //commandAjaxURI.properties 등록한 URL
										type : "post",
										data : {
											like_emp_no : like_emp_no,
											like_photo_no : like_photo_no,
											check : check
										},
										success : function(data) {
											var parsed = JSON.parse(data);
											var result = parsed.result;
											var count = result[0][0].value;
											var check = result[0][1].value;
											$('#like_count').html(count);
											if (check == '1') {
												$('.like_btn')
														.attr('id', check);
												$('.like_btn').css('color',
														'red');
												$('.like_btn')
														.html(
																'<i class="fa fa-fw fa-2x fa-heart" style="margin-right: 10px;"></i>');
											} else {
												$('.like_btn')
														.attr('id', check);
												$('.like_btn').css('color',
														'#6c757d');
												$('.like_btn')
														.html(
																'<i class="fa fa-fw fa-2x fa-heart-o" style="margin-right: 10px;"></i>');

											}
										},
										error : function(e) {
											alert(e.responseText);
										}
									});
						});
	});
</script>

<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>사진첩</title>
</head>
<jsp:include page="PhotoModal.jsp" flush="false" />
<main class="app-content">
<div class="app-title">
	<div>
		<h1>
			<i class="fa fa-th-list"></i> 사진첩
		</h1>
	</div>
	<ul class="app-breadcrumb breadcrumb side">
		<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
		<li class="breadcrumb-item active"><a href="#">사진첩</a></li>
	</ul>
</div>
<!-- 테이블  -->
<div class="row">
	<div class="col-md-12">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<div class="tile p-0">
					<div class="bs-component">
						<div class="card">
							<div class="card-header" style="padding-left: 15px">
								<img id="writer_img" class="pull-left"
									src="${writerBean.emp_pt_addr}${writerBean.emp_pt_name}">
								<div class="pull-left">
									<h5 class="card-title" style="font-size: 1.1rem;">${writerBean.emp_name }</h5>
									<h6 class="card-subtitle text-muted">${article.photo_date }</h6>
								</div>
								<div class="nav-item dropdown pull-right"
									style="margin-top: 10px;">
									<a href="#" data-toggle="dropdown" role="button"
										style="color: gray" aria-haspopup="true" aria-expanded="false">
										<i class="fa fa-ellipsis-h fa-2x dropdown"></i>
									</a>
									<div class="dropdown-menu dropdown-menu-right">
									<c:if test="${me == 1}">
										<a class="dropdown-item" id="deleteBtn" data-toggle="modal"
											data-target="#PhotoModal">삭제</a>
									</c:if> 
									<%-- <a class="dropdown-item"
											id="deleteBtn"
											href="/KHIntranet/photoupdateForm.do?photo_no=${article.photo_no }&pageNum=${pageNum}">수정</a> --%>
										<a class="dropdown-item" id="deleteBtn"
											href="javascript:history.go(-1)">리스트로</a>
									</div>
								</div>
							</div>

							<div id="demo" class="carousel slide" data-ride="carousel">

								<!-- Indicators -->
								<ul class="carousel-indicators">
								<c:forEach var="i" begin="0" end="${length-1 }">
									<c:if test="${i =='0' }">
									<li data-target="#demo" data-slide-to="${i}" class="active"></li>
									</c:if>
									<c:if test="${i != '0' }">
									<li data-target="#demo" data-slide-to="${i}"></li>
									</c:if>
								</c:forEach>
								</ul>

								<!-- The slideshow -->
								<div class="carousel-inner">
									<c:forEach var="file_name" items="${file_name}" begin="0" end="${length-1 }" varStatus="status">
										<c:if test="${status.index == '0' }">
											<div class="carousel-item active">
												<img width=100%;
													src="${article.photo_file_addr }/${file_name}">
											</div>
										</c:if>
										<c:if test="${status.index != '0' }">
											<div class="carousel-item">
												<img width=100%;
													src="${article.photo_file_addr }/${file_name}">
											</div>
										</c:if>
									</c:forEach>
								</div>

								<!-- Left and right controls -->
								<a class="carousel-control-prev" href="#demo" data-slide="prev">
									<span class="carousel-control-prev-icon"></span>
								</a> <a class="carousel-control-next" href="#demo" data-slide="next">
									<span class="carousel-control-next-icon"></span>
								</a>
							</div>
							<div class="card-footer text-muted">
								<span id="check"> <c:if test="${check == '0' }">
										<a class="like_btn" style="color: #6c757d; cursor: pointer;"
											id="${check }"><i class="fa fa-fw fa-2x fa-heart-o"
											style="margin-right: 10px;"></i></a>
									</c:if> <c:if test="${check == '1' }">
										<a class="like_btn" style="color: red; cursor: pointer;"
											id="${check }"><i class="fa fa-fw fa-2x fa-heart"
											style="margin-right: 10px;"></i></a>
									</c:if>
								</span> <a style="color: #6c757d"><i
									class="fa fa-fw fa-2x fa-comment-o" style="margin-right: 10px;"></i></a>

							</div>
							<div class="card-body">
								<p>
									<b>좋아요 <span id="like_count">${like_count }</span>개
									</b>
								</p>
								<p style="margin-bottom: 5px; margin-left: 15px;">
									<b>${article.photo_title}</b>&nbsp;-&nbsp;${article.photo_content}
								</p>
								<c:if test="${comment_count > 0 }">
									<a style="margin-left: 15px;" class="card-link" href="#"
										data-toggle="collapse" data-target="#comment">댓글 전체 보기..</a>
									<br>
									<br>
								</c:if>
								<div id="comment" class="collapse">
									<c:if test="${comment_count > 0 }">
										<c:forEach var="commentBean" items="${commentBean }">
											<div class='col-md-12'>
												<p style="margin-left: 10px;">
													<b>${commentBean.comm_writer }</b> - <span>${commentBean.comm_content }</span>
												</p>
											</div>
										</c:forEach>
									</c:if>
								</div>
							</div>
							<div class="card-body" style="border-top: 1px solid #e7e7e7">
								<input id="writer" type="hidden" value="${emp_name }"> <input
									id="password" type="hidden" value="peco"> <input
									id="photo_content" type="text" style="border: 0px"
									placeholder="댓글을 입력해주세요." maxlength="60" height="auto"
									size="30"> <a id="comentBtn" style="cursor: pointer;">
									<i class="fa fa-paper-plane-o fa-2x pull-right"></i>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%-- <label class="control-label"><b>사진파일 다운로드</b></label>
						<div class="col-2">
							<div class="bs-component">
								<a href="${article.photo_file_addr}/${article.photo_file_nm}" download>${article.photo_file_nm}</a>
							</div>
						</div> --%>
	</div>
</div>
</main>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<c:if test="${messageType !=null }">
	<c:if test="${messageType =='성공!' }">
		<script>
			swal.getState();
			swal("${messageType}", "${messageContent}", "success", {
				buttons : "닫기"
			});
		</script>
	</c:if>
	<c:if test="${messageType =='오류!' }">
		<script>
			swal.getState();
			swal("${messageType}", "${messageContent}", "warning", {
				buttons : "닫기"
			});
		</script>
	</c:if>
</c:if>
<%
	session.removeAttribute("messageContent");
	session.removeAttribute("messageType");
%>
</body>
</html>

