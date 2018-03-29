<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="model.employee.EmployeeAllDataBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
   EmployeeAllDataBean emp_all = (EmployeeAllDataBean) session.getAttribute("emp_all");
   if(emp_all.getDept_name().equals("인사")){
       }else{  
          response.sendRedirect("Employee/employee-error.jsp");  //다음에 여기 에러 페이지로 넘어가는 곳
       }
   
      int i =0;
   ///KHIntranet/Intranet/EmployeeDelete.do
%>

<html>
<head>
   <title>회원관리</title>
</head>
<body>

   <main class="app-content">
      <div class="app-title">
           <div>
             <h1><i class="fa fa-th-list"></i>총 회원관리</h1>
           </div>
           <ul class="app-breadcrumb breadcrumb side">
             <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
             <li class="breadcrumb-item">인사관리</li>
             <li class="breadcrumb-item active"><a href="/KHIntranet/DeptPositionDelete.do">회원검색</a></li>
           </ul>
      </div>
   
      
      <div class="row">
      
         <div class="col-md-3">
         
            <div class="tile">
         <h4 class="line-head">관리 구분</h4>
            <div class="tile-body">
               <ul class="nav nav-pills flex-column mail-nav">
                  <li class="nav-item" style="cursor:pointer;">
                           <a class="nav-link" id="tabEmp">회원관리</a>
                           <a class="nav-link" id="tabDept">부서관리</a>
                           <a class="nav-link" id="tabPosition">직급관리</a>
                        </li>
                 </ul>
             </div>
             </div>
             
             <div class="tab-pane active" id="tileFilter">
                <div class="tile">
                <h4 class="line-head"> 필터</h4>
                <div class="tile-body">
                   <a class="nav-link dropdown-toggle" id="selectDoc2" data-toggle="dropdown" href="#2" role="button" aria-haspopup="true" aria-expanded="false" onclick="Dropdown2();">부서:</a>
                   <a class="nav-link dropdown-toggle" id="selectDoc1" data-toggle="dropdown" href="#1" role="button" aria-haspopup="true" aria-expanded="false" onClick="Dropdown1();">직급:</a>
                   <div class="dropdown-menu" >
                      <div id="selectDocName1" style="display:none" >
                         <a class="dropdown-item" onClick="changeDo1('0','직급')">전체</a>
                             <c:forEach var="positionList" items="${positionList}">
                                <c:if test="${positionList.position_rank != 0}">
                                    <a class="dropdown-item"  onClick="changeDo1('${positionList.position_rank}','${positionList.position_name}')">${positionList.position_name}</a>
                                  </c:if>
                            </c:forEach>
                     </div>
                   
                      <div id="selectDocName2" style="display:none" >
                          <a class="dropdown-item" onClick="changeDo2('0','부서')">전체</a>
                              <c:forEach var="deptList" items="${deptList}">
                                 <c:if test="${deptList.dept_name != '대기' }">
                                    <a class="dropdown-item" onClick="changeDo2('${deptList.dept_no}','${deptList.dept_name}')">${deptList.dept_name}</a>
                                 </c:if>
                              </c:forEach>  
                      </div>
                  </div>  
               
                </div>
               </div>
            </div><!-- 필터 tab 끝 -->
            
         </div>
         
         <div class="col-md-9">
         
            <div class="tab-content">
            
               <!--  회원리스트 table  -->
               <div class="tab-pane active" id="tableEmp">
               <div class="tile">
                  <h4 class="line-head">회원 리스트</h4>
                  <div class="tile-body">
                     <table class="table table-hover table-bordered" id="sampleTable">
                            <thead>
                              <tr>
                                <th>사원번호</th>
                                <th>아이디</th>
                                <th>이름</th>
                                <th>성별</th>
                                <th>이메일</th>
                                <th>전화번호</th>
                                <th>부서</th>
                                <th>직급</th>
                                <th>입사일</th>
                              </tr>
                            </thead>
                            <tbody id="empAllList"  style="display :table-row-group;">
                              <c:forEach var="empAll" items="${empAll}" >
                              <c:if test="${empAll.position_name != '대기' }">
                                 <tr style="cursor:pointer;" onclick="javascript:location.assign('/KHIntranet/EmployeeChangeIn.do?emp_no=${empAll.emp_no}');">
                                 <td>${empAll.emp_no}</td>
                                 <td>${empAll.emp_id }</td>
                                 <td>${empAll.emp_name}</td>
                                 <td>${empAll.emp_gender}</td>
                                 <td>${empAll.emp_email}</td>
                                 <td>${empAll.emp_pn}</td>
                                 <td>${empAll.dept_name }</td>
                                 <td>${empAll.position_name }</td>
                                 <td>${empAll.emp_date}</td>
                                 </tr>
                              </c:if>
                        </c:forEach>
                            </tbody>  
                          <tbody id="empAllList2">
                
               			 </tbody> 
                          </table>
                  </div>
               </div>
            </div><!-- 회원리스트 table 끝 -->   
               
               
            <!-- 부서리스트 table -->
            <div class="tab-pane fade" id="tableDept">
               <div class="tile">
                   <h4 class="line-head">부서 리스트</h4>
                    <!-- <div class="animated-checkbox" >
                        <label><input type="checkbox" name="selectAll1"  id="selectAll1"></label>
                     </div> -->
                     
                     <div class="btn-group">
                       <!-- <button class="btn btn-primary btn-sm" type="submit" onclick="return deleteDept();" style="border-radius:3px 0px 0px 3px;"><i class="fa fa-trash-o"></i></button>
                       <button class="btn btn-primary btn-sm" type="button"  onclick="return deptUpshow();" style="border-radius:0px 3px 3px 0px;"><i class="fa fa-download"></i></button> -->
                     </div>
                     
                     <form action="/KHIntranet/DeptPositionDelete.do" method="post">
                        <div>
                         <table class="table table-hover table-bordered">
                            <thead>
                              <tr>
                                 <th>
                                 <div class="animated-checkbox" >
                                    <label>
                                    <input type="checkbox" name="selectAll1"  id="selectAll1">
                                    <span class="label-text">전체선택</span>
                                    </label>
                                 </div>
                                 </th> 
                                <th>부서번호</th>          
                                <th>부서이름</th>
                                <th>사원수</th>
                              </tr>                                                       
                             </thead>
                                     
                            <tbody>
                                <c:forEach var="deptList" items="${deptList}">
                               <c:if test="${deptList.dept_name != '대기' }">
                                   <% i = 0; %>
                                      <c:forEach var="empAll" items="${empAll}" >
                                         <c:if test="${deptList.dept_no == empAll.dept_no}">
                                            <% i += 1; %>
                                         </c:if>
                                      </c:forEach>     
                                         
                                <tr class="deptBox" id="deptBox">
                                <% if(i != 0){ %>
                                   <td><input type="checkbox" value="${deptList.dept_no}" id ="deptCheck" name="" disabled></td>
                                   <td class="label-text">${deptList.dept_no}</td>
                                   <td>${deptList.dept_name}</td>
                                   <td><%=i %>명</td>
                               <% }else{ %>     
                                   <td><label class="deptBox" id="deptBox"><input type="checkbox" value="${deptList.dept_no}" id ="deptCheck" name="deptCheck"></label></td>
                                   <td class="label-text">${deptList.dept_no}</td>
                                   <td>${deptList.dept_name}</td>
                                   <td><%=i %>명</td>
                               <%} %>
                                </tr>  
                                
                                </c:if>
                               </c:forEach> 
                            </tbody>
                        </table>
                      </div>
                      <div id="deptCheckPlus">
                      
                      </div>
                      <div class="text-right">
                         <button class="btn btn-primary" type="submit" onclick="return deptUpshow();"><i class="fa fa-plus-square"></i></button>&nbsp;&nbsp;&nbsp;
                            <button class="btn btn-secondary" onclick="return deleteDept();"><i class="fa fa-trash-o"></i>부서삭제</button>
                         </div>
                      
                   </form>
                   
                   </div>
                </div><!-- 부서리스트 table 끝 -->
                
                
             
                <!-- 직급리스트 table -->
                <div class="tab-pane fade" id="tablePosi">
                   <div class="tile">
                   <h4 class="line-head">직급 리스트</h4>
                   
                     <form action="/KHIntranet/DeptPositionDelete.do" method="post">
                        <div>
                         <table class="table table-hover table-bordered">
                            <thead>
                              <tr>
                                 <th><div class="animated-checkbox" >
                                    <label><input type="checkbox" name="selectAll2"  id="selectAll2"><span class="label-text">전체 선택</span></label>
                                 </div></th>
                                <th>직급번호</th>          
                                <th>직급이름</th>
                                <th>사원수</th>
                              </tr>                                                       
                             </thead>
                                     
                            <tbody>
                                  <c:forEach var="positionList" items="${positionList}">
                                   <c:if test="${positionList.position_rank != 0}">
                                      <% i = 0; %>
                                      <c:forEach var="empAll" items="${empAll}" >
                                         <c:if test="${positionList.position_rank == empAll.position_rank}">
                                            <% i += 1; %>
                                         </c:if>
                                      </c:forEach>                        
                                <tr>
                                 
                                   <% if(i != 0){ %>
                                   <td ><input type="checkbox" value="${positionList.position_rank}" name="" disabled></td>
                                   <td>${positionList.position_rank} </td>
                                   <td>${positionList.position_name} </td>
                                   <td><%=i %>명 </td>
                                   <% }else{ %>
                                   <td><label id="positionBox" class="positionBox"><input type="checkbox" value="${positionList.position_rank}" name="positionCheck"></label></td>
                                   <td>${positionList.position_rank}</td>
                                   <td>${positionList.position_name}</td>
                                   <td><%=i %>명 </td>
                                   <%} %>
                                   
                                </tr>
                                
                                   </c:if>
                                  </c:forEach> 
                            </tbody>
                        </table>
                      </div>
                     <div id="positionCheckPlus">
                      
                      </div>
                      <div class="tile-footer text-right">
                           <button class="btn btn-primary" type="submit" onclick="return positionUpshow();"><i class="fa fa-plus-square"></i></button>&nbsp;&nbsp;&nbsp;
                           <button class="btn btn-secondary"  onclick="return deletePosition();"><i class="fa fa-trash-o"></i>직급삭제</button>
                       </div>
                   </form>
                   </div>
            </div><!-- 직급리스트 table -->
            
            
            <!-- 부서추가 form -->
              <div class="tab-pane fade" id="deptUp" style="display:none">
                   <div class="tile">
                      <form action="/KHIntranet/DeptInsert.do" name="deptU" method="post">
                      <h4 class="line-head">부서 추가</h4>
                         <div class="tile-body">
                         
                         <div class="form-group">
                               <div class="row">
                                  <div class="col-md-6">
                                       <label class="control-label">부서번호</label>
                                         <input class="form-control" type="text" name="dept_no" id="dept_no">
                                  </div>     
                                  <div class="col-md-6">  
                                     <label class="control-label">부서이름</label>
                                            <input class="form-control" type="text" name="dept_name" id="dept_name">
                                            <input type="hidden" name="i" value="1">
                                     </div>
                                </div>
                            </div>
                         
                         </div>
                         
                         <div class="text-right">
                            <button class="btn btn-primary" type="submit"><i class="fa fa-download"></i>등록</button>&nbsp;&nbsp;&nbsp;
                              <button class="btn btn-secondary" type="button" onclick="deptUpshow()"><i class="fa fa-trash-o"></i>취소</button>
                         </div>

                      </form>
                   </div>
              </div>
              
              
              <!-- 직급추가 form -->
              <div class="tab-pane fade" id="positionUp" style="display:none">
                   <div class="tile">
                      <form action="/KHIntranet/PositionInsert.do" method="post">
                      <h4 class="line-head">직급 추가</h4>
                         <div class="tile-body">
                         
                         <div class="form-group">
                               <div class="row">
                                  <div class="col-md-6">
                                       <label class="control-label">직급번호</label>
                                         <input class="form-control" type="text" name="position_rank" id="position_rank"  Onblur="find()">
                                  </div>     
                                  <div class="col-md-6">  
                                     <label class="control-label">직급이름</label>
                                            <input class="form-control" type="text" name="position_name" id="position_name">
                                            <input type="hidden" name="i" value="2">
                                     </div>
                                </div>
                            </div>
                         
                         </div>
                         
                         <div class="text-right">
                            <button class="btn btn-primary" type="submit"><i class="fa fa-download"></i>등록</button>&nbsp;&nbsp;&nbsp;
                              <button class="btn btn-secondary" type="button" onclick=" return positionUpshow()"><i class="fa fa-trash-o"></i>취소</button>
                         </div>

                      </form>
                   
                   </div>
               </div>
            
           </div>
           
         </div>
         
     </div><!-- row 끝 -->
      
      
      
      
      
        

      
         
      
    </main>
     
    <!-- Essential javascripts for application to work-->
     <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>  
     <!-- Data table plugin-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript">$('#sampleTable').DataTable();</script>
    
   <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Employee/js/employeeChange_1.js"></script>
    
    <script>
    $(document).ready(function(){    /* 최근 10개 가져오기 */
      showTab(${i}) 
   });
    </script>
    
    </main>
  </body>
</html>


<!--
박스
//-->

