<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title> 증명서 출력</title>
    <style>
    #SalaryTable_filter{
    display:none;
    }
    #SalaryTable_length{
    display:none;
    }
    #SalaryTable_info{
    display:none;
    }
    </style>
</head>
   <main class="app-content">
      <div class="app-title"> 
        <div>
          <h1><i class="fa fa-print"></i> 증명서 출력</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"> 증명서 출력</li>
          <li class="breadcrumb-item active"><a href="#"> 급여대장</a></li>
        </ul>
      </div>
      <!-- 테이블  -->
      <div class="row">
      	<div class="col-md-2 mb-3">
           <div class="bs-component" style="box-shadow:0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2)">
             <div class="list-group">
             	<a class="list-group-item list-group-item-action" href="/KHIntranet/certificateList.do">급여명세서 출력</a>
             	<a class="list-group-item list-group-item-action" href="/KHIntranet/certificateCareerPrint.do">경력증명서 출력</a></div>
            </div> 
          </div>
        <div class="col-md-10">
          <div class="tile">
           <h3 class="tile-title" style="font-size:1.3rem;"> ${empBean.emp_name }님의 급여대장</h3>
            <div class="tile-body">
	            <div class="col-md-12">
	              <table class="table table-hover table-bordered" id="SalaryTable">
	                <thead>
	                  <tr>
	                    <th bgcolor="#AFE1FF">해당월</th>
	                    <th>기본급</th>
	                    <th>직책수당</th>
	                    <th>식대</th>
	                    <th bgcolor="#AFE1FF">급여계</th>
	                    <th>국민연금</th>
	                    <th>건강보험</th>
	                    <th>고용보험</th>
	                    <th bgcolor="#AFE1FF">공제합계</th>
	                    <th bgcolor="#AFE1FF">총합</th>
	                    <th>출력</th>
	                  </tr>
	                </thead>
	                
	                <tbody>
		                <c:forEach var="salBean" items="${salBean }">
			                <c:forEach var = "i2" begin ="${salBean.sal_start_year }" end="${salBean.sal_end_year }" >
			                	<c:if test="${salBean.sal_end_year - salBean.sal_start_year == 0 }">
			                		<c:forEach var = "i" begin ="${salBean.sal_start_month+1 }" end="${salBean.sal_end_month }">
												<tr>
													<td bgcolor="#AFE1FF">${i2}년 ${i}월 </td>
													<td><fmt:formatNumber value="${salBean.sal_emp_sal }" groupingUsed="true" type="number"/>원</td>
													<td><fmt:formatNumber value="${salBean.sal_position_sal}" groupingUsed="true" type="number"/>원</td>
													<td><fmt:formatNumber value="100000" groupingUsed="true" type="number"/>원</td>
													<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_sum }" groupingUsed="true" type="number"/>원</td>
													<td><fmt:formatNumber value="${salBean.sal_ins_first}" groupingUsed="true" type="number"/>원</td>
													<td><fmt:formatNumber value="${salBean.sal_ins_second}" groupingUsed="true" type="number"/>원</td>
													<td><fmt:formatNumber value="${salBean.sal_ins_third}" groupingUsed="true" type="number"/>원</td>
													<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
													<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_sum-salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
													<td align="center" style="font-size:1rem; color:#007bff"><a href="/KHIntranet/certificateSalaryPrint.do?salary=${salBean.sal_emp_sal}&year=${i2}&month=${i}"><i class="fa fa-print"></i></a></td>
												</tr>
										</c:forEach>
			                	</c:if>
			                	<c:if test="${salBean.sal_end_year - salBean.sal_start_year == 1 }">
			                		<c:if test="${i2 == salBean.sal_start_year }">
				                		<c:forEach var = "i" begin ="${salBean.sal_start_month+1 }" end="12">
													<tr>
														<td bgcolor="#AFE1FF">${i2}년 ${i}월 </td>
														<td><fmt:formatNumber value="${salBean.sal_emp_sal }" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_position_sal}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="100000" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_sum }" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_first}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_second}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_third}" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_sum-salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
														<td align="center" style="font-size:1rem; color:#007bff"><a href="/KHIntranet/certificateSalaryPrint.do?salary=${salBean.sal_emp_sal}&year=${i2}&month=${i}"><i class="fa fa-print"></i></a></td>
													</tr>
											</c:forEach>
										</c:if>
			                		<c:if test="${i2 == salBean.sal_end_year }">
				                		<c:forEach var = "i" begin ="1" end="${salBean.sal_end_month }">
													<tr>
														<td bgcolor="#AFE1FF">${i2}년 ${i}월 </td>
														<td><fmt:formatNumber value="${salBean.sal_emp_sal }" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_position_sal}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="100000" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_sum }" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_first}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_second}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_third}" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_sum-salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
														<td align="center" style="font-size:1rem; color:#007bff"><a href="/KHIntranet/certificateSalaryPrint.do?salary=${salBean.sal_emp_sal}&year=${i2}&month=${i}"><i class="fa fa-print"></i></a></td>													</tr>
											</c:forEach>
										</c:if>
			                	</c:if>
			                	<c:if test="${salBean.sal_end_year - salBean.sal_start_year > 1 }">
			                		<c:if test="${i2 == salBean.sal_start_year }">
				                		<c:forEach var = "i" begin ="${salBean.sal_start_month+1 }" end="12">
													<tr>
														<td bgcolor="#AFE1FF">${i2}년 ${i}월 </td>
														<td><fmt:formatNumber value="${salBean.sal_emp_sal }" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_position_sal}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="100000" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_sum }" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_first}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_second}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_third}" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_sum-salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
														<td align="center" style="font-size:1rem; color:#007bff"><a href="/KHIntranet/certificateSalaryPrint.do?salary=${salBean.sal_emp_sal}&year=${i2}&month=${i}"><i class="fa fa-print"></i></a></td>
													</tr>
											</c:forEach>
										</c:if>
									<c:if test="${i2 < salBean.sal_end_year && i2 > salBean.sal_start_year}">
				                		<c:forEach var = "i" begin ="1" end="12">
													<tr>
														<td bgcolor="#AFE1FF">${i2}년 ${i}월 </td>
														<td><fmt:formatNumber value="${salBean.sal_emp_sal }" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_position_sal}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="100000" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_sum }" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_first}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_second}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_third}" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_sum-salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
														<td align="center" style="font-size:1rem; color:#007bff"><a href="/KHIntranet/certificateSalaryPrint.do?salary=${salBean.sal_emp_sal}&year=${i2}&month=${i}"><i class="fa fa-print"></i></a></td>
													</tr>
											</c:forEach>
									</c:if>	
			                		<c:if test="${i2 == salBean.sal_end_year }">
				                		<c:forEach var = "i" begin ="1" end="${salBean.sal_end_month }">
													<tr>
														<td bgcolor="#AFE1FF">${i2}년 ${i}월 </td>
														<td><fmt:formatNumber value="${salBean.sal_emp_sal }" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_position_sal}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="100000" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_sum }" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_first}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_second}" groupingUsed="true" type="number"/>원</td>
														<td><fmt:formatNumber value="${salBean.sal_ins_third}" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
														<td bgcolor="#AFE1FF"><fmt:formatNumber value="${salBean.sal_sum-salBean.sal_ins_sum}" groupingUsed="true" type="number"/>원</td>
														<td align="center" style="font-size:1rem; color:#007bff"><a href="/KHIntranet/certificateSalaryPrint.do?salary=${salBean.sal_emp_sal}&year=${i2}&month=${i}"><i class="fa fa-print"></i></a></td>
													</tr>
											</c:forEach>
										</c:if>
			                	</c:if>
					         </c:forEach>
					    </c:forEach>
	                </tbody>
	               <!--  <tfoot>
	                	<tr>
	                		<th>합계</th>
	                		<td></td>
	                		<td></td>
	                		<td></td>
	                		<td></td>
	                		<td></td>
	                		<td></td>
	                		<td></td>
	                		<td></td>
	                		<td></td>
	                		<td></td>
	                	</tr>
	                </tfoot> -->
	              </table>
	              </div>
	            </div>
            </div>
          </div>
        </div>
    </main>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>  
    <!-- 페이지 특정 플러그인 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/dataTables.bootstrap.min.js"></script>
    <script>
    $('#SalaryTable').dataTable( {"order": [ 0, 'desc' ]} , {"columnDefs": [   {"type": "numeric", "targets": 0 }] } );
    </script>
  </body>
</html>