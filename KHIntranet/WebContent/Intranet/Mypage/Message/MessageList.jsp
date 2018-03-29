<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html >
<html>
<head>
<title>쪽지함</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Intranet/Mypage/Message/css/mailbox.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Mypage/Message/js/mailBox.js"></script>
</head>
<body>
	<main class="app-content">
		<div class="app-title">
			<div>
				<h1><i class="fa fa-envelope-o"></i> 쪽지함</h1>
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
					<h4 class="tile-title folder-head"> 쪽지함</h4>
				<div class="tile-body">
					<ul class="nav nav-pills flex-column mail-nav">
						<li class="nav-item active"><a class="nav-link" href="/KHIntranet/messageList.do?status=1"><i class="fa fa-inbox fa-fw"></i> 받은 쪽지함<span class="badge badge-pill badge-primary float-right">${unreadCount }</span></a></li>
						<li class="nav-item"><a class="nav-link" href="/KHIntranet/messageList.do?status=2"><i class="fa fa-envelope-o fa-fw"></i> 보낸 쪽지함</a></li>
						<li class="nav-item"><a class="nav-link" href="/KHIntranet/messageList.do?status=3"><i class="fa fa-trash-o fa-fw"></i> 휴지통</a></li>
					</ul>
				</div>
				</div>
				<a class="mb-3 btn btn-primary btn-block" href="/KHIntranet/messageSendForm.do">쪽지 보내기</a>
			</div>
		
		<!-- 쪽지함  -->
		
		<!-- 상단 버튼 -->
			<div class="col-md-9">
				<div class="listBox">
					<div class="tile" >
					<c:choose>
						<c:when test="${status =='1' }">
							<h4 class="tile-title" style="font-size:1.2rem">받은쪽지함</h4>
						</c:when>
						<c:when test="${status =='2' }">
							<h4 class="tile-title" style="font-size:1.2rem">보낸쪽지함</h4>
						</c:when>
						<c:otherwise>
							<h4 class="tile-title" style="font-size:1.2rem">휴지통</h4>
						</c:otherwise>
					</c:choose>
						<input type="hidden" name="status" value="${status }" id="status">
				<div class="mailbox-controls">
					<div class="animated-checkbox" >
						<label>
							<input type="checkbox" name="selectAll"  id="selectAll"><span class="label-text"></span>
						</label>
					</div>
						<div class="btn-group">
							<button class="btn btn-primary btn-sm" type="button" onclick="deleteFunction();" style="border-radius:3px 0px 0px 3px;"><i class="fa fa-trash-o"></i></button>
							<c:if test="${status =='3' }">
							<button class="btn btn-primary btn-sm" type="button" onclick="restoreFunction();" style="border-radius:0px;"><i class="fa fa-reply"></i></button>
							</c:if>
							<a href="/KHIntranet/messageList.do?emp_id=${emp_id }&status=${status}">
							<button class="btn btn-primary btn-sm" type="button"  style="border-radius:0px 3px 3px 0px;"><i class="fa fa-refresh"></i></button></a>
						</div>
				</div>
				
		<!-- 쪽지 리스트 -->
			<c:if test ="${count ==0 }">
				<div class="table-responsive mailbox-messages">
					<table class="table table-hover">
						<tbody align="center">
							<tr align="center">
								<td width="100%" align="center">쪽지가 존재 하지 않습니다</td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:if>
	
	
	<c:if test="${count > 0 }">
	<c:if test="${status == '1' || status =='3'}">            
		<div class="table-responsive mailbox-messages">
			<table class="table table-hover">
				<tbody id="msg_load">
				<c:forEach var="msgList" items="${msgList}"> 
					<tr>
						<td width="3%">
							<div class="animated-checkbox">
								<label>
									<input type="checkbox" value="${msgList.msg_no }" name="msg_no"><span class="label-text"> </span>
								</label>
							</div>
						</td>
						<c:if test="${msgList.msg_important == '0' }">
						<td  width="3%"><a href="#" id="${msgList.msg_no }" onClick="return importantFunction(this);"><i class="fa fa-star-o"></i></a>
						</c:if>
						<c:if test="${msgList.msg_important == '1' }">
						<td  width="3%"><a href="#" id="${msgList.msg_no }" onClick="return importantFunction(this);"><i class="fa fa-star"></i></a>
						</c:if>
						<c:forEach var="empList" items="${empList }">
						<c:if test="${msgList.msg_fromno == empList.emp_no }">
							<td width="15%"><b>${empList.dept_name}부서 ${empList.emp_name}님</b></td>
						</c:if>
						</c:forEach>
						<c:if test="${msgList.msg_to_time == '0' }">
							<td class="mail-subject"  style="white-space:nowrap; overflow: hidden; width:60%;">
							<a class="vol" href="/KHIntranet/messageContent.do?msg_no=${msgList.msg_no }&status=${status}">
							<i class="fa fa-envelope-o"></i>&nbsp;&nbsp;${msgList.msg_content }</a></td>
						</c:if>
						<c:if test="${msgList.msg_to_time != '0' }">
							<td class="mail-subject"  style="white-space:nowrap; overflow: hidden; width:60%;">
							<a class="vol" href="/KHIntranet/messageContent.do?msg_no=${msgList.msg_no }&status=${status}" style="color:grey;">
							<i class="fa fa-envelope-open-o"></i>&nbsp;&nbsp;${msgList.msg_content }</a></td>
						</c:if>
							<td  width="18%" align="right" class="pull-right" style="margin-right:10px;">발신 시간&nbsp;:&nbsp; ${msgList.msg_from_time }</td>
					</tr>
				</c:forEach>           
					</tbody>
				</table>
		</div>          
	</c:if>
	<!-- 보낸 쪽지함 -->
	<c:if test="${status=='2'}">  
		<div class="table-responsive mailbox-messages">
			<table class="table table-hover">
				<tbody>   
					<c:forEach var="msgList" items="${msgList}">
					
						<tr>
							<td width="3%">
								<div class="animated-checkbox">
									<label>
										<input type="checkbox" value="${msgList.msg_no }" name="msg_no"><span class="label-text"> </span>
									</label>
								</div>
							</td> 
							<c:forEach var="empList" items="${empList }">
							<c:if test="${msgList.msg_tono == empList.emp_no }">
								<td width="20%">받는 사람 :&nbsp;<b>${empList.dept_name}부서 ${empList.emp_name}님</b></td>
							</c:if>
							</c:forEach>
							<c:if test ="${msgList.msg_to_time =='0' }">
								<td class="mail-subject"  style="white-space:nowrap; overflow: hidden; width:55%;">
								<a class="vol" href="/KHIntranet/messageContent.do?msg_no=${msgList.msg_no }&status=${status}">
								<i class="fa fa-envelope-o"></i>&nbsp;&nbsp;${msgList.msg_content }</a></td>
								<td width="20%"  class="pull-right" align="right" style="margin-right:10px;">수신 확인 : 읽지 않음</td>
							</c:if>
							<c:if test ="${msgList.msg_to_time != '0' }">
								<td class="mail-subject"  style="white-space:nowrap; overflow: hidden; width:55%;">
								<a class="vol" href="/KHIntranet/messageContent.do?msg_no=${msgList.msg_no }&status=${status}" style="color:grey;">
								<i class="fa fa-envelope-open-o"></i>&nbsp;&nbsp;${msgList.msg_content }</a></td>	
								<td width="18%" class="pull-right" align="right" style="margin-right:10px;">확인 시간 : ${msgList.msg_from_time }</td>
							</c:if>
						</tr>
					</c:forEach>           
				</tbody>
			</table>
		</div>
	</c:if> 
	</c:if>
	
	<!-- 하단 버튼  -->
	<div class="text-right">
		<div class="btn-group">
			<c:if test="${count > 10 }">              
			<c:if test="${pageNum < lastPage && pageNum > 1 }">
				<a href="/KHIntranet/messageList.do?emp_id=${emp_id }&status=${status}&pageNum=${pageNum-1}"><button class="btn btn-primary btn-sm" type="button"><i class="fa fa-chevron-left"></i></button></a>
				<a href="/KHIntranet/messageList.do?emp_id=${emp_id }&status=${status}&pageNum=${pageNum+1}"><button class="btn btn-primary btn-sm" type="button"><i class="fa fa-chevron-right"></i></button></a>
			</c:if>
			<c:if test="${pageNum == 1 }">
				<a href="/KHIntranet/messageList.do?emp_id=${emp_id }&status=${status}&pageNum=${pageNum+1}"><button class="btn btn-primary btn-sm" type="button"><i class="fa fa-chevron-right"></i></button></a>
			</c:if>
			<c:if test="${pageNum == lastPage }">
				<a href="/KHIntranet/messageList.do?emp_id=${emp_id }&status=${status}&pageNum=${pageNum-1}"><button class="btn btn-primary btn-sm" type="button"><i class="fa fa-chevron-left"></i></button></a>           
			</c:if>
			</c:if>	
		</div>
	</div>
	</div>
	</div>     
	</div>
</div>
    </main>
       <!-- The javascript plugin to display page loading on top-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
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
