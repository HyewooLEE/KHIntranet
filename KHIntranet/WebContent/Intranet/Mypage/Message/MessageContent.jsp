<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<title>쪽지함</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Intranet/Mypage/Message/css/mailbox.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Mypage/Message/js/mailContent.js"></script>
</head>
<body>
	<main class="app-content">
	<div class="app-title">
		<div>
			<h1>
				<i class="fa fa-envelope-o"></i> 쪽지함
			</h1>
		</div>
		<ul class="app-breadcrumb breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
			<li class="breadcrumb-item"><a href="#">쪽지함</a></li>
		</ul>
	</div>
	<div class="row">
		<!-- 쪽지 리스트 -->
		<div class="col-md-3">
			<div class="tile">
				<h4 class="tile-title folder-head">쪽지함</h4>
				<div class="tile-body">
					<ul class="nav nav-pills flex-column mail-nav">
						<li class="nav-item active">
							<a class="nav-link" href="/KHIntranet/messageList.do?emp_id=${emp_id }&status=1">
								<i class="fa fa-inbox fa-fw"></i> 받은 쪽지함<span class="badge badge-pill badge-primary float-right">${unreadCount }</span>
							</a>
						</li>
						<li class="nav-item">
						<a class="nav-link" href="/KHIntranet/messageList.do?emp_id=${emp_id }&status=2">
							<i class="fa fa-envelope-o fa-fw"></i> 보낸 쪽지함</a></li>
						<li class="nav-item">
							<a class="nav-link" href="/KHIntranet/messageList.do?emp_id=${emp_id }&status=3"><i class="fa fa-trash-o fa-fw"></i> 휴지통</a>
						</li>
					</ul>
				</div>
			</div>
			<a class="mb-3 btn btn-primary btn-block" href="/KHIntranet/messageSendForm.do?status=${status }">쪽지 보내기</a>
		</div>
		<!-- 쪽지함  -->
		<div class="col-md-9">
			<div class="tile">
				<h5 class="tile-title" style="font-size: 1.3rem; display: inline;">${msg_fromname }님에게 온 쪽지</h5>
				<div class="btn-group" style="display: inline; float: right;">
					<input type="hidden" name="status" id="status" value="${status }">
					<button class="btn btn-primary btn-sm" id="${msg_no }" type="button" style="border-radius: 3px 0px 0px 3px;" onclick="deleteFunction2(this);"><i class="fa fa-trash-o"></i></button><a href="javascript:history.go(-1)"><button class="btn btn-primary btn-sm" type="button" style="border-radius: 0px 3px 3px 0px;"><i class="fa fa-reply"></i></button></a>
				</div>
				<div class="tilebody">
					<p style="font-size: 0.8rem; color: #6c757d; margin-top: 10px;">${msg_time }</p>
					<br>
					<p style="font-size: 1.1rem; word-break: break-all">${msg_content }</p>
					<br>
				</div>
				<div class="tile-footer" style="border-top: 0px;">
					<a href="/KHIntranet/messageSendForm.do?msg_fromname=${msg_fromname}&msg_fromno=${msg_fromno }">
						<button class="btn btn-primary btn-sm" type="button">
								<i class="fa fa-fw fa-lg fa-check-circle"></i>답장하기
						</button> 
					</a>
				</div>
			</div>
		</div>
	</div>
	</main>
	<!-- The javascript plugin to display page loading on top-->
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
		<%
			session.removeAttribute("messageContent");
				session.removeAttribute("messageType");
		%>
	</c:if>
</body>
</html>
