<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>    
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Intranet/Mypage/Profile/css/modifyForm.css">
<title>개인정보 수정</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/jquery.form.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Mypage/Profile/js/modifyForm.js"></script>
</head>  
<body>
	<main class="app-content" >
			<div class="app-title">
				<div>
				<h1><i class="fa fa-edit"></i>개인정보 수정</h1>
				</div>
				<ul class="app-breadcrumb breadcrumb">
					<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
					<li class="breadcrumb-item"><a href="#"> 개인정보 수정</a></li>
				</ul>
			</div>
			<div class="row">
			<!-- 프로필 사진 부분 -->
				<div class="col-md-3" style="text-align:center;">
					<div class="tile">
						<form id="ajaxform" action="/KHIntranet/profileUpload.ajax" method="post" enctype="multipart/form-data">
							<h3 class="mb-3 line-head" id="buttons">프로필 사진</h3> 
							<img id="user-img" class="user-img" src="${empBean.emp_pt_addr}${empBean.emp_pt_name}" width="150px" height="150px">
							<h4>${empBean.emp_name}</h4>
							<p style="margin-bottom:30px;">${empBean.dept_name } / ${empBean.position_name }</p>
							<input type='file' id='file' name='file' onchange="sumbitFunction();"/>
							<button class="btn btn-primary"  id="btn-upload" onclick="return fileInput(this)" type="button">프로필 사진 변경</button>
						</form>
					</div>
				</div>
			 
			<!-- 수정 폼 부분 -->
				<div class="col-md-9">
					<form action="/KHIntranet/profileModifypro.do" name="modify">
						<div class="tile" style="padding:50px;">
						<div class="tile-body">
							<div class="row">
								<!-- 패스워드 변경 폼 -->
								<div class="col-md-3" style="border-right:1px solid #e7e7e7;">
										<h5 class="mb-4">패스워드 변경</h5>
										<input type="hidden"  name="emp_id" value="${empBean.emp_id}"> <!-- 수정할때 수정할 아이디 보내기 -->	
										<div class="form-group">
											<label class="control-label">비밀번호</label>
											<input class="form-control" name="emp_pw"  type="password"  maxlength="20" value="${empBean.emp_pw}">
										</div>
										<div class="form-group">
											<label class="control-label ">비밀번호 확인</label>
											<input class="form-control" name="emp_pw2"  type="password"  maxlength="20" value="${empBean.emp_pw}">                    
										</div>
								</div>
								
								<div class="col-md-9">
									<h5 class="mb-4">개인정보 수정</h5>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">이름</label>
													<input class="form-control" name="emp_name" type="text" value="${empBean.emp_name}">
												</div>
												<div class="form-group">
													<label class="control-label">전화번호</label>
													<input  class="form-control" name="emp_pn" type="text" maxlength="20" value="${empBean.emp_pn}">
												</div>        
												<div class="form-group">
													<label class="control-label">이메일</label>
													<input  class="form-control" name="emp_email" type="email" maxlength="25" value="${empBean.emp_email}">
												</div>
											</div>
											<div class="col-md-6">
											<div class="form-group">
												<label class="control-label">우편번호</label>
												<div class="input-group">
													<input  class="form-control" name="emp_addr1" type="text" maxlength="20" value="${empBean.emp_addr1}" id ="sample3_postcode" readonly>
													<span class="input-group-btn">
														<button type="button" class="btn btn-primary" style="border-radius:0px 3px 3px 0px;" onclick="return kume1();">검색</button>
													</span>                    
												</div>
											</div>
											<div class="form-group">
												<label class="control-label">기본 주소</label>
												<input  class="form-control" name="emp_addr2" type="text" maxlength="50" value="${empBean.emp_addr2}" id="sample3_address" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">상세 주소</label>
												<input  class="form-control" name="emp_addr3" type="text" maxlength="50" value="${empBean.emp_addr3}" id="sample4_address">
											</div>
											</div>
										</div>
								</div>
							</div>
							</div>
							<div class="tile-footer" align="center">
								<div class="row">
									<div class="col-md-12 col-md-offset-3">                
										<button class="btn btn-primary" type="submit" ><i class="fa fa-fw fa-lg fa-check-circle"></i>변경</button>
										 &nbsp;&nbsp;&nbsp;
										<a class="btn btn-secondary" href="javascript:location.reload()"><i class="fa fa-fw fa-lg fa-times-circle"></i>취소</a>
									</div>
								</div>
							</div>   
						</div>
					</form>
				</div>
				<!-- 수정 푸터 -->
			</div>		
	</main>
<!-- The javascript plugin to display page loading on top-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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
	     <%
		    session.removeAttribute("messageContent");
			session.removeAttribute("messageType");	
		 %>
		</c:if>
</body>
</html>