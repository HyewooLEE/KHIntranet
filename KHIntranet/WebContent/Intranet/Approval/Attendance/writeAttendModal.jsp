<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="model.employee.EmployeeAllDataBean" %>
<%request.setCharacterEncoding("utf-8"); %>

<% 
	EmployeeAllDataBean emp_all = (EmployeeAllDataBean) session.getAttribute("emp_all");
	int myEmpNo = emp_all.getEmp_no(); 
	String myEmp_no = String.valueOf(emp_all.getEmp_no());
%>

<!-- Modal -->
    <div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog">	               
		<div class="modal-dialog modal-lg">
 			<div class="modal-content">
				<div class="modal-header">
		  			<button type="button" class="close" data-dismiss="modal">×</button>
				</div>					
			<div class="modal-body" id="modal-body">
	
			<form name="selectEmpForm">
				<div class="col-md-12">
			         <div class="form-group row" style="margin:auto; padding-top: 30px">
			            <div class="bs-component col-5">							            				           			                  
			                <!-- 부서명별 select tab -->
							<ul class="nav nav-tabs">  
			                  <li class="nav-item dropdown">
			                  	<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" id="selectDept">부서를 선택해주세요</a>						                  	
			                    	<div class="dropdown-menu">
			                    		<!-- <a class="dropdown-item" id="dept_all" class="depNo">전체보기</a> -->	
			                    		<c:forEach var="deptList" items="${deptList }">
			                    		<c:if test="${deptList.dept_name != '대기'}">					
				                    		<a class="dropdown-item" id="dept_${deptList.dept_no }" class="depNo">${deptList.dept_name }</a>
				                    	</c:if>	
				                    	</c:forEach>
			                    	</div>				                    								                       							                    
			                  </li>
			                </ul>
			                
			               <!-- 선택할 사원명 / 직책 list -->
			               <div class="tab-content" id="myTabContent">
			                  <div class="tab-pane fade active show">							                  	
			                     <select class="form-control" id="chooser" name="chooser" multiple="">	
			                    	<c:forEach var="empList" items="${empList }">
			                    	<c:set var="str1" value="${article.atd_receiver }" />
			                    	<c:set var="myEmpNo" value="<%=myEmp_no %>" />
			                    	<c:if test = "${empList.position_rank !=0  && myEmpNo != empList.emp_no}">					                    							                     
			                        	<option value="${empList.emp_no }">${empList.emp_name } - ${empList.position_name }</option>	
			                        </c:if>													
			                        </c:forEach>
			                     </select>						                    
			                  </div>
			               </div>							               
			            </div>
			            
			            <!--  -->
			            <div class="col-2" style="margin:auto; text-align:center; padding-top:25px">
			               <button class="mb-2" type="button" id="btn1" >-></button><br>   
			               <button class="mb-2" type="button" id="btn2" ><-</button>
			            </div>
			            
			            <!-- 선택된 사원명 / 직책 -->
			            <div class="bs-component col-5">
			               <ul class="nav nav-tabs">
			                  <li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#Suggest">결재라인</a></li>
			               </ul>
			               <div class="tab-content" id="myTabContent">
			                  <div class="tab-pane fade active show">
			                     <select class="form-control" id="selector" name="selector" multiple="">			                     
			                     
			                     </select>
			                  </div>
			               </div>
			            </div>   
			         </div>
			      </div>
		   	</form>		                   																
			</div>
	
			<div class="modal-footer">
			  <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
			  <button type="button" class="btn btn-primary" id="submit">선택완료</button>
			</div>
     					
   			</div>
    	</div>
    </div>
  	<!-- modal 끝 -->