<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="now" value="<%=new java.util.Date() %>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title> 급여명세서 증명서 출력</title>
    <style>
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
          <h1><i class="fa fa-file-text-o"></i> 문서 출력</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"><a href="#"> 문서 출력</a></li>
          <li class="breadcrumb-item"><a href="#"> 급여명세서 출력</a></li>
        </ul>
      </div>
      <div class="row mb-5">
      	<div class="col-md-1"></div>
        <div class="col-md-10">
          <div class="tile">
            <section class="invoice">
              <div class="row" style="margin-top:50px; margin-bottom:50px;">
                <div class="col-12" style="text-align:center;">
                  <h2 class="page-header">${year }년 ${month }월 급여 명세서</h2>
                </div>
              </div>
              <div class="row mb-5">
              	<div class="col-2"></div>
                <div class="col-8 table-responsive">
                  <table class="table" > 
                  	<thead>
                  		<tr>
                  			<th colspan ="4" bgcolor="#e7e7e7"  style="border:1px solid #dee2e6;">인적사항</th>
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
                        <td>부서</td>
                        <td>${empBean.dept_name}</td>
                        <td>직급</td>
                        <td>${empBean.position_name}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="col-2"></div>
              </div>
              <div class="row mb-5">
              	<div class="col-2"></div>
                <div class="col-4 table-responsive">
                  <table class="table" >
                  	<thead>
                  		<tr>
                  			<th colspan ="2" bgcolor="#e7e7e7" style="border:1px solid #dee2e6;">지급 내역</th>
                  		</tr>
                  	</thead>
                    <tbody>
                      <tr>
                        <td>기본급</td>
                        <td><fmt:formatNumber value="${salBean.sal_emp_sal }" groupingUsed="true" type="number"/>원</td>
                      </tr>
                      <tr>
                      	<td>직책수당</td>
                      	<td><fmt:formatNumber value="${salBean.sal_position_sal}" groupingUsed="true" type="number"/>원</td>
                      </tr>
                      <tr>
                      	<td>식대</td>
                      	<td>100,000원</td>
                      </tr>
                      <tr>
                      	<td>지급 합계</td>
                      	<td><fmt:formatNumber value="${salBean.sal_sum }" groupingUsed="true" type="number"/>원</td>
                      </tr>
                    </tbody>
                  </table>
                  </div>
                  <div class="col-4 table-responsive">
                  <table class="table" >
                  	<thead>
                  		<tr>
                  			<th colspan ="2" bgcolor="#e7e7e7" style="border:1px solid #dee2e6;">공제 내역</th>
                  		</tr>
                  	</thead>
                    <tbody>
                      <tr>
                        <td>국민연금</td>
                        <td><fmt:formatNumber value="${salBean.sal_ins_first}" groupingUsed="true" type="number"/>원</td>
                      </tr>
                      <tr>
                      	<td>건강보험</td>
                      	<td><fmt:formatNumber value="${salBean.sal_ins_second}" groupingUsed="true" type="number"/>원</td>
                      </tr>
                      <tr>
                      	<td>고용보험</td>
                      	<td><fmt:formatNumber value="${salBean.sal_ins_third}" groupingUsed="true" type="number"/>원</td>
                      </tr>
                      <tr>
                      	<td>공제 합계</td>
                      	<td><fmt:formatNumber value="${salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="col-2"></div>
              </div>
              <div class="row" style="margin-bottom:100px;">
		      	<div class="col-7"></div>
			      	<div class="col-3 table-responsive">
	                  <table class="table" > 
	                  	<thead>
	                  		<tr>
	                  			<th colspan ="2" bgcolor="#e7e7e7"  style="border:1px solid #dee2e6;">실 수령액</th>
	                  		</tr>
	                  	</thead>
	                    <tbody>
	                      <tr>
	                        <td colspan="2"><fmt:formatNumber value="${salBean.sal_sum-salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원 </td>
	                      </tr>
	                    </tbody>
	                  </table>
			      	
		      	</div>
		      	<div class="col-2"></div>
              </div>
              <div class="row mb-5" >
				<div class="col-5"></div>              
              	<div class="col-5" align="right">
              		<p style="font-size:1.1rem;"><fmt:formatDate value="${now }" type="date" pattern="yyyy년 MM월 dd일" /><p>
              		<h4>인사관리 팀장 : 김주영 (인) <img src="${pageContext.request.contextPath}/Intranet/Mypage/Certificate/img/mark2.jpg"  width="100px;"></h4>
              		<h4>(주)KH Company 대표 : 나보스 (인)<img src="${pageContext.request.contextPath}/Intranet/Mypage/Certificate/img/mark1.jpg"  width="100px;"></h4>
              	</div>
              	<div class="col-2"></div>
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