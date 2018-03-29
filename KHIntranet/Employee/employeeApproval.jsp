<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.employee.EmployeeAllDataBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	EmployeeAllDataBean emp_all = (EmployeeAllDataBean) session.getAttribute("emp_all");
	if (emp_all.getDept_name().equals("인사")) {
	} else {
		response.sendRedirect("Employee/employee-error.jsp"); //다음에 여기 에러 페이지로 넘어가는 곳
	}
	///KHIntranet/Intranet/EmployeeDelete.do
%>

<html lang="ko">
<head>
<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<title>회원가입 승인</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Employee/js/employeeApproval.js"></script>

</head>
<!-- 건드릴 것 없음//////////////////////////////////////////////////////////////////////  -->
<main class="app-content">
	<div class="app-title">
		<div>
			<h1>
				<i class="fa fa-th-list"></i>회원가입 승인 대기자
			</h1>
		</div>
		<ul class="app-breadcrumb breadcrumb side">
			<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
			<li class="breadcrumb-item">Tables</li>
			<li class="breadcrumb-item active"><a href="#">Data Table</a></li>
		</ul>
	</div>
	
	<!-- 필터자리 --> <!-- <div class="row">
	        	<div class="col-md-12">
	          		<div class="tile">
	          		<h4 class="tile-title"> 필터</h4>
	          		<div class="tile-body">	
	          		</div>
	      			</div>
	      		</div>
	      </div> --> <!-- 테이블  -->
	<div class="row">
		<!-- <div class="col-md-1"></div> -->
		<div class="col-md-12">
			<div class="tile" style="padding:50px;">
				<h3 class="tile-title">회원승인 리스트</h3>
				<div class="tile-body">
					<table class="table table-hover table-bordered" id="sampleTable">
						<thead>
							<tr>
								<th>가입신청일</th>
								<th>사원번호</th>
								<th>아이디</th>
								<th>이름</th>
								<th>성별</th>
								<th>생년월일</th>
								<th>이메일</th>
								<th>전화번호</th>
								<th>부서</th>
								<th>직급</th>
								<th>급여</th>
								<th>승인</th>
							</tr>
						</thead>
						<tbody>
							<!--  -->
							<c:forEach var="empAll" items="${empAll}">
								<form method="post" name="userinput" id="userinput">
									<c:if test="${empAll.position_name == '대기' }">
										<tr>
											<td>${empAll.emp_date}</td>
											<td>${empAll.emp_no}</td>
											<td>${empAll.emp_id }</td>
											<td>${empAll.emp_name}</td>
											<td>${empAll.emp_gender}</td>
											<td>${empAll.emp_jumin1}</td>
											<td>${empAll.emp_email}</td>
											<td>${empAll.emp_pn}</td>
											<td><select class="form-control" name="dept_no${empAll.emp_no}"
												id="dept_no${empAll.emp_no}">
													<c:forEach var="deptList" items="${deptList}">
														<option value="${deptList.dept_no}">${deptList.dept_name}</option>
													</c:forEach>
											</select></td>
											<td><select class="form-control" name="position_rank${empAll.emp_no}"
												id="position_rank${empAll.emp_no}">
													<c:forEach var="positionList" items="${positionList}">
														<option value="${positionList.position_rank}">${positionList.position_name}</option>
													</c:forEach>
											</select></td>
											<td><input class="form-control" type="text" name="sal" value="0" size="9"
												id="sal${empAll.emp_no}"></td>
											<td><input type="submit" value="승인"
												class="btn btn-outline-info"
												onClick="update('${empAll.emp_no}','${empAll.emp_name}','dept_no${empAll.emp_no}','position_rank${empAll.emp_no}','sal${empAll.emp_no}')">
												<input type="button" value="거절"
												class="btn btn-outline-danger"
												onClick="empDelete('${empAll.emp_no}','${empAll.emp_name}');">
											</td>
										</tr>
									</c:if>
								</form>
							</c:forEach>
	
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</main>

<!-- Essential javascripts for application to work-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Public/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
<!-- Data table plugin-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript">
	$('#sampleTable').DataTable();
</script>

</body>
</html>
