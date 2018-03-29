<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html lang="ko">
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Intranet/Mypage/css/modifyForm.css">
<title>개인정보 수정</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/jquery.form.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Mypage/js/modifyForm.js"></script>
</head>
<main class="app-content">
<div class="app-title">
	<div>
		<h1>
			<i class="fa fa-edit"></i>개인정보 수정
		</h1>
	</div>
	<ul class="app-breadcrumb breadcrumb">
		<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
		<li class="breadcrumb-item"><a href="#"> 개인정보 수정</a></li>
	</ul>
</div>
<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<form class="form-horizontal"
			action="/KHIntranet/EmployeeUpdateChange.do" name="modify"
			method="post">
			<div class="tile" style="padding: 50px;">
				<div class="row">
					<div class="col-md-8">
						<h4 class="mb-4 line-head" id="buttons">인적 사항</h4>
						<div class="row">
							<div class="col-md-5">
								<div class="form-group" style="text-align: center;">
									<img id="user-img" class="user-img"
										src="${emp_all.emp_pt_addr}${emp_all.emp_pt_name}"
										width="150px" height="150px"
										style="border-radius: 50%; width: 200px; height: 200px; margin-bottom: 20px; margin-top: 30px;">
									<p style="margin-bottom: 30px;">
										<b>프로필 사진</b>
									</p>
								</div>
							</div>
							<div class="col-md-7">
								<div class="row">
									<div class="col-md-7">
										<div class="form-group">
											<label class="control-label">이름</label> <input
												class="form-control" name="emp_name" type="text"
												value="${empAll.emp_name }" readonly>
										</div>
									</div>
									<div class="col-md-5">
										<div class="form-group">
											<label class="control-label">성별</label> <input
												class="form-control" name="emp_gender" type="text"
												value="${empAll.emp_gender }" readonly>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label">생년월일</label> <input
												class="form-control" name="emp_jumin1" type="text"
												value="${empAll.emp_jumin1 }" readonly>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label">전화번호</label> <input
												class="form-control" name="emp_pn" type="text"
												maxlength="20" value="${empAll.emp_pn }" readonly>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label">이메일</label> <input
												class="form-control" name="emp_email" type="email"
												maxlength="25" value="${empAll.emp_email }" readonly>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label">주소지</label> <input
												class="form-control" name="emp_addr2" type="text"
												maxlength="50"
												value="${empAll.emp_addr2 }${empAll.emp_addr3 }"
												id="sample3_address" readonly>
										</div>
									</div>
								</div>
							</div>
						</div>


					</div>
					<div class="col-md-4">
						<h4 class="mb-4 line-head" id="buttons">사원 정보 수정</h4>
						<div class="form-group">
							<label class="control-label">아이디</label> <input
								class="form-control" name="emp_id" type="text"
								value="${empAll.emp_id }" readonly>
						</div>
						<div class="form-group">
							<label class="control-label">사원번호</label> <input
								class="form-control" name="emp_no" type="text"
								value="${empAll.emp_no }" readonly>
						</div>
						<div class="form-group">
							<label class="control-label">부서</label> <select
								class="form-control" name="dept_no" id="dept_no${empAll.emp_no}">
								<c:forEach var="deptList" items="${deptList}">
								<c:if test="${deptList.dept_name != '대기' }">
									<option value="${deptList.dept_no}">${deptList.dept_name}</option>
								</c:if>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label class="control-label">직급</label> <select
								class="form-control" name="position_rank"
								id="position_rank${empAll.emp_no}">
								<c:forEach var="positionList" items="${positionList}">
								<c:if test="${positionList.position_rank != 0}">
									<option value="${positionList.position_rank}">${positionList.position_name}</option>
								</c:if>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label class="control-label">급여</label> <input
								class="form-control" name="sal" type="text" maxlength="25"
								value="${empAll.emp_sal }">
						</div>

					</div>
				</div>
				<div class="tile-footer">
					<div class="row" style="text-align: right">
						<!-- align="right" -->
						<div class="col-md-12">
							<button class="btn btn-primary" type="submit">
								<i class="fa fa-fw fa-lg fa-check-circle"
									onClick="Empdate('${empAll.emp_no}','${empAll.emp_name}','dept_no${empAll.emp_no}','position_rank${empAll.emp_no}','sal${empAll.emp_no}')"></i>변경
							</button>
							&nbsp;&nbsp;&nbsp; <a class="btn btn-secondary"
								href="/KHIntranet/Intranet/EmployeeChange.do"><i
								class="fa fa-fw fa-lg fa-times-circle"></i>취소</a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

</main>
<!-- The javascript plugin to display page loading on top-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Employee/js/employeeChange.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>
</html>