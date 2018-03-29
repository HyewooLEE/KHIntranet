<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import ="model.employee.EmployeeAllDataBean" %>

<%request.setCharacterEncoding("utf-8"); %>
<%
   EmployeeAllDataBean emp_all = (EmployeeAllDataBean) session.getAttribute("emp_all");
%>
<head>  
   <title>물품구매신청</title>   
</head>

<body>

   <jsp:include page="../Attendance/writeAttendModal.jsp" flush="false" />

   <main class="app-content">
   
      <div class="app-title">      
           <div class="div">
             <h1><i class="fa fa-laptop line-head"></i> 물품구매신청</h1>
           </div>
           <ul class="app-breadcrumb breadcrumb">
             <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
             <li class="breadcrumb-item">전자결재</li>
             <li class="breadcrumb-item"><a href="/KHIntranet/myApproval.do">물품구매신청</a></li>
           </ul>
      </div>
         
      <form name="purchaseForm" action="${pageContext.request.contextPath}/writePurchaseFormPro.do" method="post"  enctype="multipart/form-data" onSubmit="return chkForm()">
	      <input type="hidden" value="${emp_all.getEmp_no() }" name="pur_emp_no"><!-- 작성자 -->
	      <input type="hidden" value="2" name="doc_no"><!-- 문서번호: 물품구매신청=2 -->
	      <input type="hidden" value="미결재" name="pur_status_ny"><!-- 결재상태 -->

                      
	      <div class="row">
	         <div class="col-md-2"></div>
	         <div class="col-md-8">
	         <div class="row">
	         <div class="col-md-12">
	             <div class="tile md-3 " style="padding:50px;">
	               <h5 class="mb-3 line-head" id="navs">구매 기안서</h5>
	               <div class="tile-body">
	                  <div class="row">
	                   <div class="col-md-6"  style="border-right:1px solid #e7e7e7">
	                      <div class="form-group">
	                        <label class="control-label">결재라인 선택</label>
	                           <div class="input-group">
	                                <input class="form-control" id="readOnlyInput" name="pur_receiver_text" type="text" readonly="" />
	                                <input class="form-control" id="readOnlyInput_hide" name="pur_receiver" type="text" readonly="" style="display:none;">
	                                <span class="input-group-btn">
	                                   <button class="btn btn-primary " data-toggle="modal" data-target="#myModal" id="myBtn" type="button" onclick="return" style="border-radius:0px 3px 3px 0px;">결재선 선택</button>
	                             	</span>
	                           </div>
	                     </div>
	                     <div class="form-group">
	                        <label class="control-label">품명</label>
	                          <input class="form-control" type="text" name="pur_name">
	                     </div>
	                     <div class="form-group">
	                         <div class="row">
	                            <div class="col-md-6">
	                                 <label class="control-label">수량</label>
	                                   <input class="form-control" type="text" name="pur_quan">
	                            </div>     
	                            <div class="col-md-6">  
	                               <label class="control-label">규격</label>
	                                      <input class="form-control" type="text" name="pur_standard">
	                               </div>
	                          </div>
	                      </div>
	                      <div class="form-group">
	                         <div class="row">
	                            <div class="col-md-6">
	                              <label class="control-label">추정단가</label>
	                                <input class="form-control" type="number" name="pur_price" placeholder="숫자를 입력하세요">
	                             </div>
	                             <div class="col-md-6">
	                                <label class="control-label">추정금액</label>
	                                <input class="form-control" type="number" name="pur_sum" placeholder="숫자를 입력하세요">
	                             </div>
	                         </div>
	                      </div>
	                      <div class="form-group">
	                        <label class="control-label">용도</label>
	                          <input class="form-control" type="text" name="pur_use">
	                      </div> 
	                  </div>
	                  
	                  <div class="col-md-6">
	                      <div class="form-group">
	                        <label class="control-label">제목</label>
	                          <input class="form-control" type="text" name="pur_title">
	                      </div>
	                      <div class="form-group">
	                        <label class="control-label">비고</label>
	                          <textarea class="form-control" rows="8" name="pur_note"></textarea>
	                      </div>
	                      <div class="form-group">
	                        <label class="control-label">첨부파일</label>
	                           <input class="form-control" type="file" name="pur_file_name">
	                      </div>
	                   </div>
	                   
	               </div>
	               </div>
	               
	               <div class="tile-footer">
	                 <div class="row">
	                   <div class="col-md-offset-3 col-md-8">
	                     <button class="btn btn-primary" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>신청</button>&nbsp;&nbsp;&nbsp;
	                     <a class="btn btn-secondary" href="#"><i class="fa fa-fw fa-lg fa-times-circle"></i>취소</a>
	                   </div>
	                 </div>
	               </div>
	               
	             </div>
	           </div>
	        </div>
	        </div>
	        </div>
      </form>
   </main>

<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Approval/Purchase/js/chkForm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Approval/Purchase/js/writePurchaseForm.js"></script>
<c:forEach var="deptList" items="${deptList }">
    <script>
      $(function(){             
         //ajaxController로 '사원명 / 부서명' 불러오기
        $('#dept_${deptList.dept_no }').on('click',function(){ 

           $('#chooser option').remove(); //초기화
           
            var chkEmp = document.getElementById("dept_${deptList.dept_no }").innerHTML; //선택된 부서명
            document.getElementById("selectDept").innerHTML = chkEmp ;                
            
            $.ajax({
                   url : "/KHIntranet/writeForm_Receive.ajax", 
                   type : "post",
                   data : {chkEmp : chkEmp},
                   
                   success : function(test){ 
                      var parsed = JSON.parse(test);
                       var result = parsed.result;                       
                       
                       for(var i = 0; i < result.length; i++){
                          if(chkEmp==result[i][3].value){
                             appendHTML(result[i][0].value,result[i][1].value,result[i][2].value,result[i][3].value); //출력
                          }
                       }
                                                                                                          
                   },
                   error : function(xhr, textStatus, errorThrown) {
                       alert(xhr.status + errorThrown );                   
                   }
             });                    
         });
                  
      });
      
      function appendHTML(emp_no,emp_name,position_name,dept_name){
         $('#chooser').append("<option value=' "+ emp_no+ " '>"+ emp_name+"-"+position_name+"</option>") ;   
        
      }
                   
    </script>      
    </c:forEach>

</body>
</html>