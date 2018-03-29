<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>WriteForm</title>
</head>
<body>

	<main class="app-content">
	<div class="app-title">
		<h1>
			<i class="fa fa-home"></i> Write Project
		</h1>
		<ul class="app-breadcrumb breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
			<li class="breadcrumb-item"><a href="/KHIntranet/listProject.do?emp_no='1111'">Project List</a></li>
			<li class="breadcrumb-item"><a href="#">Write Project</a></li>
		</ul>
	</div>
	<form method="post" action="/KHIntranet/writeProjectPro.do" name="writeProjectForm" enctype="multipart/form-data">
	<input type="hidden" name="pro_no" value="${ pro_no }">
	<input type="hidden" name="pro_ud" value="${ pro_ud }">
	<input type="hidden" name="pro_percent" value="${ pro_percent }">
 				<div class="row">
 					<div class="col-md-2"></div>
						<div class="col-md-8">
							<div class="tile" style="padding:50px;">
							<h4 class="line-head">프로젝트 등록</h4>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="title"><b>프로젝트 명</b></label>
											<input class="form-control" type="text" name="pro_title" id="pro_title" value="${ pro_title }" placeholder="Enter Project Title..">
										</div>
										<div class="form-group">
												<label for="lastSelector"><b>프로젝트 참가자</b></label>
												<div class="input-group">
													<input class="form-control" type="text" id="lastSelector" name="lastSelector" readonly="readonly" value="${emp_name}">
													<input class="form-control" type="hidden" id="lastSelector_hide" name="lastSelector_hide" value="${emp_no}">
													<a data-toggle="modal" data-target="#participant" role="button" id="myBtn"><span class="input-group-btn"><button class="btn btn-default" type="button">add</button></span></a>
												</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group date">
			              					<label class="control-label"><b>시작일자</b></label>
			              					<div class="row">
				              					<div class="col-md-10">
						              					<input class="form-control" id="pro_start_date" name="pro_start_date" type="text" value="${ pro_start_date }" placeholder="Select Date" style="display:inline;width:85%;"><i class="fa fa-calendar fa-lg" style="margin-left:10px"></i>
						              			</div>
					              			</div>
			            				</div>
										<div class="form-group date">
			              					<label class="control-label"><b>목표일자</b></label>
			              					<div class="row">
				              					<div class="col-md-10">
						              					<input class="form-control" id="pro_end_date" name="pro_end_date" type="text" value="${ pro_end_date }" placeholder="Select Date" style="display:inline;width:85%;"><i class="fa fa-calendar fa-lg" style="margin-left:10px"></i>
						              			</div>
					              			</div>
			            				</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label"><b>프로젝트 내용</b></label>
											<textarea class="form-control" id="pro_content" name="pro_content" rows="8" placeholder="Enter Project Content">${ pro_content }</textarea>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-12">
										<div class="form-group">
											<c:if test="${ pro_file_name != null }">
												<label class="control-label"><b>첨부파일</b></label>
												<%-- <a href="/KHIntranet/writeProjectForm.do?pro_no=${pro_no}&pro_file_name=${ pro_file_name }"><input class="form-control" type="text" name="pro_file_name" value="${ pro_file_name }"> --%>
												<div class="col-2">
										            <div class="bs-component">
										              <div class="alert alert-dismissible alert-info">
										                <button class="close" type="button" data-dismiss="alert" id="delete_btn">×</button>
										                <input type="button" name="pro_file_name" id="pro_file_name" onclick="javascript:window.location.assign('${pageContext.request.contextPath}/Intranet/Project/fileDownload.jsp?pro_no=${pro_no}&pro_file_name=${pro_file_name}&pro_file_addr=${pro_file_addr}')" value="${pro_file_name}" style="clear:none; border:0px none; background-color:transparent; cursor:pointer;">
										              </div>
										            </div>
										        </div>
												<!-- <button class="btn btn-secondary" type="button">다운로드</button></a> -->
											</c:if>
											<c:if test="${ pro_file_name == null }">
												<label class="control-label">첨부파일</label>
												<input class="form-control" type="file" name="pro_file_name">
											</c:if>
										</div>
									</div>
								</div>
								<div class="tile-footer" align="center">
									<button class="btn btn-primary" type="button" onclick="writeSave();">
										<i class="fa fa-fw fa-lg fa-check-circle"></i>등록
									</button>
									&nbsp;&nbsp;&nbsp;
									<button class="btn btn-secondary" type="button"
										onclick="projectFormCancel();">
										<i class="fa fa-fw fa-lg fa-times-circle"></i>취소
									</button>
								</div>
							</div>
						</div>
				</div> 
					<div id="participant" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog">                  
				      <div class="modal-dialog modal-lg">
				          <div class="modal-content">
				            <div class="modal-header">
				                 <button type="button" class="close" data-dismiss="modal" id="close">×</button>
				            </div>               
					        <div class="modal-body" id="modal-body">
								<div class="form-group row">
									<div class="bs-component col-5">
										<ul class="nav nav-tabs">  
				                           <li class="nav-item dropdown">
				                              <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" id="selectDept">부서를 선택해주세요</a>                                       
				                                <div class="dropdown-menu">
				                                   <!-- <a class="dropdown-item" id="dept_all" class="depNo">전체보기</a> -->   
				                                   <c:forEach var="deptList" items="${deptList}">                  
				                                      <a class="dropdown-item" id="dept_${deptList.dept_no }" class="depNo">${deptList.dept_name }</a>   
				                                   </c:forEach>
				                                </div>                                                                                                                        
				                           </li>
				                        </ul>
										<div class="tab-content" id="myTabContent">
											<div class="tab-pane fade active show">
												<select class="form-control" id="chooser" name="chooser" multiple="multiple">
													<c:forEach var="empList" items="${empList }">                                                                             
	                                    				<option value="${empList.emp_no }">${empList.emp_name } - ${empList.position_name }</option>                                          
	                                 				</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-2" style="margin: auto; text-align: center; padding-top: 25px">
										<button class="mb-2" type="button" id="btn1">-></button><br>
										<button class="mb-2" type="button" id="btn2"><-</button>
									</div>
									<div class="bs-component col-5">
										<ul class="nav nav-tabs">
											<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#selector">참가자</a></li>
										</ul>
										<div class="tab-content" id="myTabContent">
											<div class="tab-pane fade active show">
												<select class="form-control" id="selector" name="selector" multiple="multiple"></select>
											</div>
										</div>
									</div>
								</div>
							</div>
					        <div class="modal-footer">
					          <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					          <button type="button" class="btn btn-primary" id="pcp_submit">선택완료</button>
					        </div>
				          </div>
				       </div>
					</div>					

	</form>
	

	</main>
	
	<!-- content 끝 -->

	<!-- Essential javascripts for application to work-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Project/js/project.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/bootstrap-datepicker.min.js"></script>

		<c:forEach var="deptList" items="${deptList}">
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
		                    
		                	  if(chkEmp == result[i][3].value){
		                        appendHTML(result[i][0].value, result[i][1].value, result[i][2].value, result[i][3].value); //출력
		                     }
		                  }
		              },
		              error : function(xhr, textStatus, errorThrown) {
		                  alert(xhr.status + errorThrown );                   
		              }
		        });                    
		    });
		             
		 });
		 
		 function appendHTML(emp_no, emp_name, position_name, dept_name){
		   /* $('#chooser').append("<option value=' "+ emp_name+"-"+position_name + " '>"+ emp_name+"-"+position_name+"</option>") ; */
			 $('#chooser').append("<option value='"+ emp_no +"'>"+ emp_name +"-"+ position_name +"</option>") ;
		}
		              
		</script>      
	</c:forEach>
</body>
</html>