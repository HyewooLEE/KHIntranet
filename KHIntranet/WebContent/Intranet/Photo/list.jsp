<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="ko">
<head>
<title>사진첩</title>
<style>
.photo_box {
	text-align: center;
	/* border: 1px solid #e7e7e7;  */
	border-radius: 2px;
	padding: 20px;
	cursor: pointer;
	margin-top: 20px;
}

.photo_img {
	width: 100%;
	height: 250px;
}
</style>
</head>
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
		<li class="breadcrumb-item active"><a href="#"> 사진첩</a></li>
	</ul>
</div>
<!-- 테이블  -->
<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<div class="tile">
			<h4>사원 사진첩</h4>
			<div class="col-md-12 line-head" style="text-align: right;">글목록(전체글:${count})</div>
			<div class="tile-body">
				<div class="col-md-12">
					<c:if test="${count == 0}">
						<div class="row">
							<div class="col-md-12" style="text-align: center; margin-bottom: 30px;">
								<h5 style="font-size: 1.4rem; margin-top: 15px;">
									게시글이 없습니다.<br> 이쁜 사진을 올려주세요!!
								</h5>
							</div>
							<div class="col-md-12" style="text-align: right;">
								<a href="/KHIntranet/photowriteForm.do"><button
										class="btn btn-primary">글쓰기</button></a>
							</div>
						</div>
					</c:if>
					<c:if test="${count > 0}">
						<div class="row">
							<!-- 1개 컨텐츠 foreach로 돌리기 -->
							<c:forEach var="article" items="${articleList}">
								<div class="col-md-3 photo_box" onclick="javascript:location.assign('/KHIntranet/photocontent.do?photo_no=${article.photo_no}&pageNum=${currentPage}')">
									<br>
									<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-10">
									<div class="row">
										<div class="col-md-12">
											<img class="photo_img"
												src="${article.photo_file_addr}/${article.photo_file_nm}">
										</div>
									</div>
									<div class="row" style="text-align: left;">
										<div class="col-md-12">
											<h6 style="margin-top: 15px; margin-bottom:2px;">
												<c:out value="${number}" />
												<c:set var="number" value="${number - 1}" />
												. ${article.photo_title }
											</h6>
											<c:forEach var="empBean" items="${empBean }">
												<c:if test="${article.emp_no == empBean.emp_no }">
													<p>${empBean.dept_name}부서${empBean.emp_name }</p>
												</c:if>
											</c:forEach>
											<span  class="pull-right" style="font-size: 0.9rem;">${article.photo_date }</span>
										</div>
									</div>
									</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:if>
				</div>
			</div>
			<c:if test="${count > 0}">
				<c:set var="pageCount"
					value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
				<c:set var="pageBlock" value="${5}" />
				<fmt:parseNumber var="result" value="${currentPage / 5}"
					integerOnly="true" />
				<c:set var="startPage" value="${result * 5 + 1}" />
				<c:set var="endPage" value="${startPage + pageBlock-1}" />
				<c:if test="${endPage > pageCount}">
					<c:set var="endPage" value="${pageCount}" />
				</c:if>
				<div class="tile-footer">
					<div class="row" style="text-align: right;">
						<div class="col-md-12" style="text-align: right;">
							<ul class="pagination "
								style="display: inline; margin-right: 10px;">
								<c:if test="${startPage > 5}">
									<li class="page-item disabled"><a class="page-link"
										href="/KHIntranet/photolist.do?pageNum=${startPage-5 }">이전</a></li>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<li class="page-item" style="display: inline-block;"><a
										class="page-link" href="/KHIntranet/photolist.do?pageNum=${i}">${i}</a></li>
								</c:forEach>
							</ul>
							<a href="/KHIntranet/photowriteForm.do" class="pull-right">
								<button class="btn btn-primary">글쓰기</button>
							</a>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	<div class="col-md-1"></div>
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
