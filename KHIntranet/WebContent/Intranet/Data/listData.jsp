<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Project</title>
</head>

<body>
	<main class="app-content">
	<!-- content 내 상위 nav 시작 -->
		<div class="app-title">
			<h1><i class="fa fa-home"></i>Data</h1>
			<ul class="app-breadcrumb breadcrumb">
				<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
				<li class="breadcrumb-item"><a href="#">Data List</a></li>
			</ul>
		</div>
		<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
          <div class="tile" style="padding:50px;">
          <h4 class="line-head"> 자료실</h4>
          	<c:if test="${ data_count == 0 }">
				<div class="page-error">
	      			<h1><i class="fa fa-exclamation-circle"></i>등록한 자료가 없습니다.</h1>
	      				<p>자료를 등록 하시려면 아래 글자를 클릭해주세요.</p>
	      			<p><a href="javascript:window.location.assign('${pageContext.request.contextPath}/writeDataForm.do')">Go to Write Data</a></p>
   				</div>
			</c:if>
         	<c:if test="${ data_count > 0 }">
            <div class="table-responsive">
              <table class="table" id="dataTable">
                <thead>
                  <tr>
                    <th>제목</th>
					<th>내용</th>
					<th>작성자</th>
					<th>등록일자</th>
					<th></th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="data" items="${ dataList }">
                  <tr>
                    <td onclick="javascript:location.assign('${pageContext.request.contextPath}/writeDataForm.do?data_no=${data.data_no}');">${ data.data_title }</td>
					<td onclick="javascript:location.assign('${pageContext.request.contextPath}/writeDataForm.do?data_no=${data.data_no}');">${ data.data_content }</td>
					<td onclick="javascript:location.assign('${pageContext.request.contextPath}/writeDataForm.do?data_no=${data.data_no}');">${ data.emp_name }</td>
					<td onclick="javascript:location.assign('${pageContext.request.contextPath}/writeDataForm.do?data_no=${data.data_no}');">${ data.data_date }</td>
					<td><i class="fas fa-trash-alt" onclick="javascript:deleteData(${data.data_no});"></i></td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
              <p class="bs-component" align="right"><button class="btn btn-primary" type="button" onclick="javascript:window.location.assign('${pageContext.request.contextPath}/writeDataForm.do')" style="cursor:pointer;">등록</button></p>
            </div>
            </c:if>
          </div>
        </div>
        </div>
	</main>
	<!-- content 끝 --> 

	<!-- Essential javascripts for application to work-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Data/js/dataList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/dataTables.bootstrap.min.js"></script>
	<script>$('#dataTable').DataTable();</script>
</body>
</html>