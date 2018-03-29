<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="model.employee.EmployeeAllDataBean" %>
<%request.setCharacterEncoding("utf-8"); %>
<%
   EmployeeAllDataBean emp_all = (EmployeeAllDataBean) session.getAttribute("emp_all");
%>
<head> 
   <title>근태신청</title>      
</head>
<style>
@media (min-width: 400px) {
   #datepicker1{
      width:90%;
   }
   #datepicker2{
      width:90%;
   }
}
</style>
<body>   
   
   <jsp:include page="writeAttendModal.jsp" flush="false" />   

   <main class="app-content" >
   
	   <form name="attendForm" action="${pageContext.request.contextPath}/writeAttendFormPro.do" method="post"  enctype="multipart/form-data" onSubmit="return chkForm()">
	        <input type="hidden" value="${emp_all.getEmp_no() }" name="atd_emp_no"><!-- 작성자 -->
	      	<input type="hidden" value="1" name="doc_no"><!-- 문서번호: 근태신청=1 -->
	      	<input type="hidden" value="미결재" name="atd_status_ny"><!-- 결재상태 -->
	   
	      	<div class="app-title">      
	           <div class="div">
	             <h1><i class="fa fa-th-list"></i> 근태신청</h1>
	           </div>
	           <ul class="app-breadcrumb breadcrumb">
	             <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
	             <li class="breadcrumb-item">전자결재</li>
	             <li class="breadcrumb-item"><a href="/KHIntranet/writeAttendForm.do">근태신청</a></li>
	           </ul>
	         </div>
	         
	       <div class="row">
	          <div class="col-md-2"></div>
	          <div class="col-md-8">      
	             <div class="tile" style="padding:50px;">               
	                 <div class="bs-component">
	                   
		                    <div class="page-header">
		                        <h5 class="mb-3 line-head">근태 신청서</h5>
		                    </div>
		                    
		                    <div class="col-md-12">
		                    
			                    <div class="row">
			                        <div class="col-md-9">
				                    <div class="form-group">
		                         		<label for="atd_div" class="control-label">결재선 선택</label>
				                          		<div class="input-group">
				                              		<input class="form-control" id="readOnlyInput" name="atd_receiver_text" type="text" readonly="" value="${emp_name}"/>
				                                	<input class="form-control" id="readOnlyInput_hide" name="atd_receiver" type="text" readonly="" value="${emp_no}" style="display:none;">
				                               		<span class="input-group-btn">
				                                  		<button class="btn btn-primary " data-toggle="modal" data-target="#myModal" id="myBtn" type="button" onclick="return" style="border-radius:0px 3px 3px 0px;">결재선 선택</button>
				                            		</span>
				                      			</div>
		                       		</div> 
	                       			</div>
	                       		
		                       		<div class="col-md-3">
		                       		<div class="form-group">
			                         	<label for="atd_div" class="control-label">분류</label>
			                         		<select class="form-control " id="atd_div" name="atd_div">
					                             <option value="연차">연차</option>
					                             <option value="반차">반차</option>
					                             <option value="지각">지각</option>
					                             <option value="조퇴">조퇴</option>
					                             <option value="외출">외출</option>
					                             <option value="야근">야근</option>
					                             <option value="출장">출장</option>
					                             <option value="연수">연수</option>                           
			                         		</select>
		                       		</div>
		                       		</div>
	                       		</div>
		                    
		                        <div class="row">
                          			<div class="col-md-6">
                                		<div class="form-group date">
                                   		<label class="control-label">시작일</label>
                                   		<div class="row">
                                      		<div class="col-md-12">
                                            	<input class="form-control col-md-9" id="datepicker1" name="atd_start_date" type="text" style="display:inline; width:85%" ><i class="fa fa-calendar fa-lg" style="margin-left:10px"></i>
                                      		</div>
                                   		</div>
                              			</div>
                              		</div>
                              		<div class="col-md-6">	
                              			<div class="form-group date">
                                   		<label class="control-label">종료일</label>
                                   		<div class="row">
	                                      	<div class="col-md-12">
	                                        	<input class="form-control col-md-9" id="datepicker2" name="atd_end_date" type="text" style="display:inline;width:85%"><i class="fa fa-calendar fa-lg" style="margin-left:10px"></i>
	                                      	</div>
                                   		</div>
                                		</div>
                                	</div>
		                     	</div>
	                     	
		                       <div class="form-group">
		                         <label class="control-label">내용</label>
		                         <textarea class="form-control" rows="8" name="atd_note" placeholder="사유 및 용무"></textarea>
		                       </div>                     
		                       <div class="form-group" style="margin-bottom:30px;">
		                         <label class="control-label">첨부파일</label>
		                         <input class="form-control" type="file" name="atd_file_name">
		                       </div>                                                                           
		                      <div class="tile-footer">
		                         <button class="btn btn-primary" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>신청</button>&nbsp;&nbsp;&nbsp;
		                         <a class="btn btn-secondary" href="#"><i class="fa fa-fw fa-lg fa-times-circle"></i>취소</a>
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
   <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/bootstrap-datepicker.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Approval/Attendance/js/chkForm.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Approval/Attendance/js/writeAttendForm.js"></script>
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
    
    
    <%-- <c:forEach var="empList" items="${empList }"> 
    <script>
    //★★★전체보기 ---> 구현하기!!!!!!!!!!!!
    $(function(){
       if($('#dept_all').click()){
             $('#chooser').append("<option value=' "+ ${empList.emp_name }+"-"+ ${empList.position_name } + " '>"+ ${empList.emp_name }+"-"+ ${empList.position_name }+"</option>") ;      
       }
    });

    </script>
    </c:forEach> --%> 
</body>
</html>