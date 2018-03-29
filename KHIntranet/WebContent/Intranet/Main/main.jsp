<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import ="model.employee.EmployeeAllDataBean" %>
<%@ page import ="model.main.ConnectionDBBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	EmployeeAllDataBean emp_all = (EmployeeAllDataBean) session.getAttribute("emp_all");
%>
<html lang="ko">
  <head>
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Intranet/Public/css/main.css">
    <title>MAIN</title>
	<script type="text/javascript">
	//실시간 접속자
	function findEmp(){
	
		var emp_name= "<%=emp_all.getEmp_name() %>";
		var position_name= "<%=emp_all.getPosition_name() %>";
		var emp_no="<%=emp_all.getEmp_no() %>";
		
		$.ajax({
			url : "/KHIntranet/Connection.ajax",  //web.xml에 지정
			type : "post",
			data : {
						emp_name : emp_name,  //list형태로 보내고 list형태로 받는다.
						position_name : position_name,
						emp_no : emp_no
					},
				success : function(data){ 
					document.getElementById('usernumber').innerHTML=data;
					//alert(data);
				}
		});
	}
	
	</script>
	
	<script type="text/javascript">  <%-- ajax를 이용한 전송 방식 --%>
		var lastID =0;
	
		function massgeFunction(){  <%-- 입력하는 기능 --%>
			var chatContent = $('#chatContent').val();
			var chatNo = "<%=emp_all.getEmp_no() %>";
			var chatName = "<%= emp_all.getEmp_name()%>"; //id랑 조회해서 가져올 name값 

	$.ajax({
				url : "/KHIntranet/Chatinsert.ajax",   /* web.xml에 지정 */  /* 위치때문에 여기서는 . 2번 */
				type : "post",
				data : {chatNo : chatNo,
							chatContent : encodeURIComponent(chatContent),
							chatName : chatName
						},

				success : function(result){  /* 성공시 */
					if(result == 1){
						/*  alert('전송 성공')  */
					}else if(result ==0){
						alert('내용을 입력하세요');
					}else{
						alert('테이터베이스 오류가 발생');
					}
				}
				
			});
			$('#chatContent').val('');
		}
		

		<%-- 불러오는 기능 값 가져오기 --%>
		<%-- list로 값 가져 오기 --%>
		function chatListFunction(type){
			var emp_no = "<%=emp_all.getEmp_no() %>";
			
			$.ajax({
				url : "/KHIntranet/ChatList.ajax",  
				type : "post",
				data : {
							listType : type, 
							emp_no : emp_no
						},
					success : function(data){ 
					
						if(data=="") return;    <%-- 결과가 없거나 오류 --%>

						var parsed = JSON.parse(data);  <%-- JSON으로 값 가져오기 --%>
						var result = parsed.result;   <%-- chatListServlet에 있는 result = "{\"result\":[" --%>
						
						for(var i = 0; i < result.length; i++){
							addChat(result[i][0].value, result[i][1].value, result[i][2].value,result[i][3].value,result[i][4].value,result[i][5].value); //출력
					}
						lastID = Number(parsed.last);
				}
			});
		}
		
		/* 보여줄것 view 구현 */
		function addChat(chatNo, chatName, chatContent,pt_addr,pt_name,chatTime){  //출력시주는 원하는 위치에 생성되게끔 해준다.
				var way = "";
				var c = "";
		
		var emp_no =  "<%=emp_all.getEmp_no() %>"
		var name = "<%=emp_all.getEmp_name() %>"
		
		
	if(chatNo == emp_no){
		  way = "right"; 
				 c = "left";
			ctm = "message me"
			
				 $('#chatList').append(
						 	'<div class="clearfix"></div>'+
							'<div class="'+ctm+' row">'+
							 
							'<div class="col-md-8" style="text-align:right" >' +
							
							'<div class="'+ctm+' row"  style="margin-right:0px; margin-left:0px;" >'+
							'<div >'+
					        '<p class="info pull-right" style="margin:0px; word-break:break-word;">'+
					        chatContent +
							'</p>'+
							'</div>'+
							'</div>'+
							
							'<div class="'+ctm+' row"  style=" margin:0px; text-align:right">'+
							"<p class='small pull-right'>" +
							chatTime + 
							'</p>' +
							'</div>' +
							
							'</div>' +
							
							'</div>'
						 
						);  //추가
			
			

	}else {
		way = "left"; 
		c = "right";
		ctm = "message"
		
		
			 $('#chatList').append(
						
						'<div class="'+ctm+' row">'+
						'<div class="col-md-2">' +
						'<div class="row" style="margin-right:0px; margin-left:0px;">'+
						'<img src="'+pt_addr+pt_name+'" style="width:48px; height:48px;">'+
						'</div>'+
						'<div class="row" style="text-align:center;margin-right:0px; margin-left:0px;">'+
						chatName+
						'</div>' +
						'</div>' +
						'<div class="col-md-8">' +
						'<div class="row">'+
				        '<p class="info" style="margin-top:20px; word-break:break-word;">'+
				        chatContent +
						'</p>'+
						'</div>' +
						'<div class="row">'+
						"<p class='small pull-"+c+"'>" +
						chatTime + 
						'</p>' +
						'</div>' +
						'</div>' +
						'</div>'
					 
					);  //추가
	}
				$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
	}


		
		//실시간
 		function getInfiniteChat(){
			setInterval(function(){  //반복수행
				findEmp();
				chatListFunction(lastID); 
				todayWork();
			}, 500);  //0.5초마다 실행
		}
			 
	</script>
    <script>
		function todayWork() {
			var emp_no= document.getElementById('emp_no').value;
			$.ajax({
				url : "/KHIntranet/chkCalendar.ajax",  //web.xml에 지정
				type : "post",
				data : {
							emp_no : emp_no
						},
				success : function(data){
					var parsed = JSON.parse(data);  <%-- JSON으로 값 가져오기 --%>
	               	var result = parsed.result;
	               	
					document.getElementById('todayWork').innerHTML = result[0][0].value +"개";
					//alert(data);
				}	
			});
		};
	</script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
  </head>
  
  <!-- 건드릴 것 없음//////////////////////////////////////////////////////////////////////  -->


    
    
    
    <main class="app-content" >
      <div class="app-title">
        <div>
          <h1><i class="fa fa-home"></i> 메인페이지</h1> <!-- 페이지에 따라 아이콘및 제목 변경 수정 필요  -->
          <p></p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"><a href="#">Blank Page</a></li>
        </ul>  
      </div>
           <div class="row">
           <div class="col-md-7" >
           <div class="row">
           <div class="col-md-12">
           <div class="bs-component" style="box-shadow:0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2)">
              <div class="jumbotron" style=" width:100%; background-image: url(${pageContext.request.contextPath}/Intranet/Public/img/jumbotron.jpg); ">
                <p style="color:#007bff;">&nbsp;</p>
                <p style="color:#007bff;">&nbsp;</p>
                <p style="color:#007bff;">&nbsp;</p>
                <p style="text-align:right;" ><a class="btn btn-primary btn-lg" style=" margin-bottom:-60px;" href="#">더보기</a></p>
              </div> 
            </div>
           </div>
            </div>
                  <div class="row">
      	<c:if test="${ notice_count > 0 }">
		<div class="col-md-12">
			<div class="tile">
				<h3 class="tile-title">공지사항</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Title</th>
							<th>Content</th>
							<th>Writer</th>
							<th>Write Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="notice" items="${ noticeList }">
							<tr>
								<td onclick="javascript:location.assign('${pageContext.request.contextPath}/writeNoticeForm.do?notice_no=${notice.notice_no}');">${ notice.notice_title }</td>
								<td onclick="javascript:location.assign('${pageContext.request.contextPath}/writeNoticeForm.do?notice_no=${notice.notice_no}');">${ notice.notice_content }</td>
								<td onclick="javascript:location.assign('${pageContext.request.contextPath}/writeNoticeForm.do?notice_no=${notice.notice_no}');">${ notice.emp_name }</td>
								<td onclick="javascript:location.assign('${pageContext.request.contextPath}/writeNoticeForm.do?notice_no=${notice.notice_no}');">${ notice.notice_date }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
	
<!--         <div class="clearfix"></div> -->

   </div>
          	<div class="row">
	<c:if test="${ count > 0 }">
		<div class="col-md-12">
			<div class="tile">
				<div class="row">
					<div class="col-lg-12">
						<div class="page-header">
							<h2 class="mb-3 line-head">프로젝트 진행도</h2>
						</div>
						<c:forEach var="projectPercentList" items="${ projectPercentList }">
							<h6 id="progress-basic">${ projectPercentList.pro_title }</h6>
							<div class="progress mb-3">
								<div class="progress-bar" role="progressbar" style="width:${ projectPercentList.pro_percent }%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	</div>
           </div>
        <div class="col-md-5">
        <div class="row">
        <div class="col-md-6">
			<div class="widget-small info coloured-icon">
				<i class="icon fa fa-users fa-3x"></i>
				<div class="info">
					<h4>휴가자</h4>
					<p>
						<b>${countHoliday}명</b>
					</p>
				</div>
			</div>
        </div>
        <div class="col-md-6">
           <div class="widget-small danger coloured-icon"><i class="icon fa fa-rss fa-3x"></i>
            <div class="info">
              <h4>접속자</h4>
              <p><b id="usernumber"></b></p> 
            </div>
          </div>
        </div>
      </div>
            <div class="row">
      
        <div class="col-md-12">
          <div class="widget-small warning coloured-icon">
			<i class="icon fa fa-clipboard fa-3x"></i>
			<div class="info">
				<h4>해야할 일</h4>
				<p>
					<b id="todayWork"></b>
				</p>
			</div>
		</div>
        </div>
      
      </div>
      <div class="row">
         <div class="col-md-12">
           <div class="tile">
            <h3 class="tile-title">메신저</h3>
            <div class="messanger">
              <div class="messages" id="chatList">
              
              </div>
              <div class="sender">
                <input type="text" placeholder="Send Message" id="chatContent">
                <button class="btn btn-primary" type="button" onclick="return massgeFunction();"><i class="fa fa-lg fa-fw fa-paper-plane"></i></button>
              </div>
            </div>
          </div>
        </div>
      
      </div>
      <div class="row">
      		<div class="col-md-12">
			<div class="tile">
				<input type="hidden" id="emp_no" name="emp_no" value="${emp_no}">
				<div id="calendar" onclick="javascript:location.assign('${pageContext.request.contextPath}/calendar.do')"></div>
			</div>
		</div>
      </div>
        </div>
      </div>



    

     


    </main>
    <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script> --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
    <!-- Page specific javascripts-->
     <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/moment.min.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Project/js/fullcalendar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Main/js/main.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <c:if test="${messageType !=null }">           
	<c:if test="${messageType =='성공!' }">           
	<script>
		swal.getState();	
		swal("${messageType}","${messageContent}","success", {buttons: "닫기"});
	</script>
	</c:if>
	<c:if test="${messageType =='오류!' }">           
	<script>
		swal.getState();	
		swal("${messageType}","${messageContent}","warning", {buttons: "닫기"});
	</script>
	</c:if>
	<%
		session.removeAttribute("messageContent");
		session.removeAttribute("messageType");	
	 %>
	</c:if>
  </body>
</html>