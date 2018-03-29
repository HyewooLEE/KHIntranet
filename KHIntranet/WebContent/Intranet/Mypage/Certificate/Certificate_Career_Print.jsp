<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="now" value="<%=new java.util.Date() %>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title> 경력 증명서 출력</title>
    <style>
    th{
    vertical-align:middle;
    border:1px solid #dee2e6;
    width:inherit;
    }
    td {
    text-align:center;
    vertical-align:middle;
    border:1px solid #dee2e6;
    width:inherit;
    }
    </style>
</head>
    <main class="app-content">
      <div class="app-title">
        <div>
          <h1><i class="fa fa-file-text-o"></i> 증명서 출력</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"><a href="#"> 증명서 출력</a></li>
          <li class="breadcrumb-item"><a href="#"> 경력증명서 출력</a></li>
        </ul>
      </div>
      <div class="row mb-5">
      	<div class="col-md-1"></div>
        <div class="col-md-10">
          <div class="tile">
            <section class="invoice">
              <div class="row" style="margin-top:50px; margin-bottom:50px;">
                <div class="col-12" style="text-align:center;">
                  <h2 class="page-header">경력증명서</h2>
                </div>
              </div>
              <div class="row mb-5">
              	<div class="col-1"></div>
                <div class="col-10 table-responsive">
                  <table class="table" > 
                  	<thead>
                  		<tr>
                  			<th colspan ="4" bgcolor="#e7e7e7">인적사항</th>
                  		</tr>
                  	</thead>
                    <tbody>
                      <tr>
                        <td>이름</td>
                        <td>${empBean.emp_name }</td>
                        <td>생년월일</td>
                        <td>${empBean.emp_jumin1}</td>
                      </tr>
                      <tr>
                        <td>전화번호</td>
                        <td>${empBean.emp_pn}</td>
                        <td>본적</td>
                        <td>${empBean.emp_addr2}&nbsp;${empBean.emp_addr3}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="col-1"></div>
              </div>
              <div class="row mb-5">
              	<div class="col-1"></div>
                <div class="col-10 table-responsive">
                  <table class="table" >
                  	<thead>
                  		<tr>
                  			<th colspan ="4" bgcolor="#e7e7e7">경력사항</th>
                  		</tr>
                  	</thead>
                    <tbody>
                      <tr>
                        <td>회사명</td>
                        <td>KH Company</td>
                        <td>주소</td>
                        <td>서울 특별시 종로구 대하빌딩 2층</td>
                      </tr>
                      <c:forEach var="carBean" items="${carBean}">
                      <tr>
                      	<td>부서 / 직급</td>
                      	<td>${carBean.car_dept_name}&nbsp;/&nbsp; ${carBean.car_position_name}</td>
                      	<td>경력기간</td>
                      	<td>${carBean.car_start_date }&nbsp;~&nbsp;${carBean.car_end_date } </td>
                      </tr>
                     </c:forEach>
                    </tbody>
                  </table>
                </div>
                <div class="col-1"></div>
              </div>
              <div class="row mb-5" >
				<div class="col-3"></div>              
              	<div class="col-6" align="center">
              		<br><br>
              		위 사람은 KH Company 에서 근무하였음을 위와 같이 증명 합니다.
              		<br><br><br><br><br><br><br><br><br><br><br>
              		<p style="font-size:1.1rem;"><fmt:formatDate value="${now }" type="date" pattern="yyyy년 MM월 dd일" /><p>
              		<h4>인사관리 팀장 : 김주영 (인) <img src="${pageContext.request.contextPath}/Intranet/Mypage/Certificate/img/mark2.jpg"  width="100px;"></h4>
              		<h4>(주)KH Company 대표 : 나보스 (인) <img src="${pageContext.request.contextPath}/Intranet/Mypage/Certificate/img/mark1.jpg"  width="100px;"></h4>
              	</div>
              	<div class="col-3"></div>
              </div>
              <div class="row d-print-none mt-2">
                <div class="col-12 text-right"><a class="btn btn-primary" href="javascript:window.print();" target="_blank"><i class="fa fa-print"></i> 프린트</a>
                <a class="btn btn-secondary" href="javascript:history.go(-1)"><i class="fa fa-fw fa-lg fa-times-circle"></i> 취소</a></div>
              </div>
            </section>
          </div>
        </div>
        <div class="col-md-1"></div>
      </div>
    </main>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>  
  </body>
</html>