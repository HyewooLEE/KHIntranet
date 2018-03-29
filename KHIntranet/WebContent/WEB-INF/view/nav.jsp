<%@ page contentType="text/html; charset=utf-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import ="model.employee.EmployeeAllDataBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<!DOCTYPE html >
<html>  
<head>
<%
   EmployeeAllDataBean emp_all = (EmployeeAllDataBean) session.getAttribute("emp_all");
%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Intranet/Public/css/main.css">
    <!-- Essential javascripts for application to work-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/popper.min.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/bootstrap.min.js"></script>
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        
         <script>
   //알람 채팅
   var MessageID = 0;
   var AttendID = 0;
   var PurchaseID = 0;
   var ChatCount = 0;
   
   function AlarmListFunction(){
   
      var emp_no = "<%=emp_all.getEmp_no() %>";
      $.ajax({
         url : "/KHIntranet/AlarmAction.ajax",  
         type : "post",
         data : {
                  emp_no : emp_no  
               },
             
            success : function(data){ 

               var parsed = JSON.parse(data); 
               var result = parsed.result;   
               
                  for(var i = 0; i < result.length; i++){
                     if(result[i][4].value == 1){
                     MessageList(result[i][0].value, result[i][1].value, result[i][2].value,result[i][3].value,1); //출력
                     }else if(result[i][4].value == 2){

                        PurchaseList(result[i][0].value, result[i][1].value, result[i][2].value,result[i][3].value,1); //출력
                     }else{

                        AttendList(result[i][0].value, result[i][1].value, result[i][2].value,result[i][3].value,1); //출력   
                     }   
                  }
               
                  MessageID = Number(parsed.MessageLast); //메시지 마지막 번호 자신
                  AttendID = Number(parsed.AttendLast);
                  PurchaseID = Number(parsed.PurchaseLast);
               ChatCount = Number(parsed.Last);   //채팅 마지막 번호   자신
               document.getElementById("chatNumber").innerHTML=ChatCount+"개";
         }
      });
   }
   
    
   //계속
   function Allalarm(type, ID){
      var emp_no = "<%=emp_all.getEmp_no() %>";
      
      if(type == 1){
         
      $.ajax({
         url : "/KHIntranet/AlarmList.ajax",  
         type : "post",
         data : {
                  emp_no : emp_no,  
                  ID : ID,
                  type : type
               },
            success : function(data){ 
               var parsed = JSON.parse(data);  
               var result = parsed.result;  
               //메시지 번호, 보낸사람, 받는사람, 내용, 보낸시간
               //alert(result.length);
               for(var i = 0; i < result.length; i++){
                  MessageList(result[i][0].value, result[i][1].value, result[i][2].value,result[i][3].value,2); //출력   
               }
               MessageID = Number(parsed.MessageLast);
               
               ChatCount = Number(parsed.Last);   //채팅 마지막 번호   자신
               document.getElementById("chatNumber").innerHTML=ChatCount+"개";
            }
      });
      
      }else if(type ==2){
         
         $.ajax({
            url : "/KHIntranet/AlarmList.ajax",  
            type : "post",
            data : {
                     emp_no : emp_no,  
                     ID : ID,
                     type : type
                  },
                  <%-- 성공시 --%>
               success : function(data){ 
               
                  var parsed = JSON.parse(data);  
                  var result = parsed.result;   
                  //메시지 번호, 보낸사람, 받는사람, 내용, 보낸시간   
                     
                  for(var i = 0; i < result.length; i++){
                      PurchaseList(result[i][0].value, result[i][1].value, result[i][2].value,result[i][3].value, 2); //출력
                  }
                  PurchaseID = Number(parsed.PurchaseLast);


                  ChatCount = Number(parsed.Last);   //채팅 마지막 번호   자신
                  document.getElementById("chatNumber").innerHTML=ChatCount+"개";
               }
         });
         
      }else{
         
         $.ajax({
            url : "/KHIntranet/AlarmList.ajax",  
            type : "post",
            data : {
                     emp_no : emp_no,  
                     ID : ID,
                     type : type
                  },
               success : function(data){ 
                  
               
                  var parsed = JSON.parse(data);  
                  var result = parsed.result;    
                  //메시지 번호, 보낸사람, 받는사람, 내용, 보낸시간   

                  for(var i = 0; i < result.length; i++){   
                     
                     AttendList(result[i][0].value, result[i][1].value, result[i][2].value,result[i][3].value,2); //출력
                  }
                  AttendID = Number(parsed.AttendLast);
                  
                  
                  
                  ChatCount = Number(parsed.Last);   //채팅 마지막 번호   자신
                  document.getElementById("chatNumber").innerHTML=ChatCount+"개";
               }
         });   
            
      }
         
      //alert(MessageID);
      //alert(PurchaseID);
      //alert(AttendID);
         
         
   }
         
   //메시지 List
   function MessageList(No, Name, Content, Time,A){  //출력시주는 원하는 위치에 생성되게끔 해준다.
      
      if(A==1){
       $('#AlarmList').append(
             '<li class="messageAll"><a class="app-notification__item" href="/KHIntranet/messageContent.do?msg_no='+No+'&status=1" >'+
             '<span class="app-notification__icon"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-primary"></i><i class="fa fa-envelope fa-stack-1x fa-inverse"></i></span></span>'+
                 '<div>'+
                   '<p class="app-notification__message"><b>'+Name+'님에게 온 쪽지</b></p>'+
                   '<p class="app-notification__meta" id="chatNumber">'+Content+'</p>'+
                   '<p style="font-size:11px;margin-bottom:0px;">보낸 시간'+Time+'</p>'+
                 '</div></a></li>'
            );  //추가
         }else{
             $('#AlarmList').prepend(
                   '<li class="messageAll"><a class="app-notification__item" href="/KHIntranet/messageContent.do?msg_no='+No+'&status=1">'+
                   '<span class="app-notification__icon"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-primary"></i><i class="fa fa-envelope fa-stack-1x fa-inverse"></i></span></span>'+
                       '<div>'+
                         '<p class="app-notification__message"><b>'+Name+'님에게 온 쪽지</b></p>'+
                         '<p class="app-notification__meta" id="chatNumber">'+Content+'</p>'+
                         '<p style="font-size:11px;margin-bottom:0px;">보낸 시간'+Time+'</p>'+
                       '</div></a></li>'
               );  //추가
         }
   }
   
   
   //물품 List
   function PurchaseList(No, Name, Content, Time, A){
   
      if(A==1){
      $('#AlarmList').append(
             ' <li><a class="app-notification__item"href="/KHIntranet/adminPurchaseContent.do?pur_no='+No+'">'+
             '<span class="app-notification__icon"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-success"></i><i class="fa fa-money fa-stack-1x fa-inverse"></i></span></span>'+
                '<div>'+
                  '<p class="app-notification__message"><b>'+Name+'님이 물품결재 요청</b></p>'+
                  '<p class="app-notification__meta" id="chatNumber">'+Content+'</p>'+
                  '<p style="font-size:11px; margin-bottom:0px;">보낸 시간'+Time+'</p>'+
                '</div></a></li>'
            );  //추가
      }else{  
         $('#AlarmList').prepend(
                ' <li><a class="app-notification__item" href="/KHIntranet/adminPurchaseContent.do?pur_no='+No+'">'+
                '<span class="app-notification__icon"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-success"></i><i class="fa fa-money fa-stack-1x fa-inverse"></i></span></span>'+
                   '<div>'+
                     '<p class="app-notification__message"><b>'+Name+'님이 물품결재 요청</b></p>'+
                     '<p class="app-notification__meta" id="chatNumber">'+Content+'</p>'+
                     '<p style="font-size:11px;margin-bottom:0px;">보낸 시간'+Time+'</p>'+
                   '</div></a></li>'
            ); 
      }
   }
   
   
   //근태 List
   function AttendList(No, Name, Content, Time,A){
      
      if(A==1){
      $('#AlarmList').append(
             '<li><a class="app-notification__item" >'+
               '<span class="app-notification__icon"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-danger"></i><i class="fa fa-hdd-o fa-stack-1x fa-inverse"></i></span></span>'+
                  '<div>'+
                    '<p class="app-notification__message"><b>'+Name+'님이 근태 결재신청</b></p>'+
                    '<p class="app-notification__meta" id="chatNumber">'+Content+'</p>'+
                    '<p style="font-size:11px;margin-bottom:0px;">보낸 시간'+Time+'</p>'+
                  '</div></a></li>'
            );  //추가
         }else{ //href="/KHIntranet/adminPurchaseContent.do?pur_no='+NO+'"
            $('#AlarmList').prepend(
                   '<li><a class="app-notification__item">'+
                   '<span class="app-notification__icon" ><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-danger"></i><i class="fa fa-hdd-o fa-stack-1x fa-inverse"></i></span></span>'+
                        '<div>'+
                          '<p class="app-notification__message"><b>'+Name+'님이 근태 결재신청</b></p>'+
                          '<p class="app-notification__meta" id="chatNumber">'+Content+'</p>'+
                          '<p style="font-size:11px;margin-bottom:0px;">보낸 시간'+Time+'</p>'+
                        '</div></a></li>'
                  );  //추가
            
         }
      }
   
   
   
   //실시간
    function getInfiniteAlarm(){
   
      setInterval(function(){  //반복수행
         
         //alert("반복");
         //Allalarm(1,MessageID); 
       //  Allalarm(2,PurchaseID); 
     //    Allalarm(3,AttendID);
      }, 500);  //0.5초마다 실행
   }
   
    $(document).ready(function(){   
     //  AlarmListFunction();
      getInfiniteAlarm(); //ID 값을 가지고 값 가져 오기
      });
     
    </script> 
	<decorator:head />
	<title>KH <decorator:title/></title>
	</head>
	<body class="app sidebar-mini" onload="">
    <!-- Navbar-->
    <header class="app-header"><a class="app-header__logo" href="main.do" ><img src="${pageContext.request.contextPath}/Intranet/Public/img/symbol_trans.png"  width="25px;"style="margin-bottom:5.5px;"></a>
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"></a>
    <!-- Navbar Right Menu-->
    <ul class="app-nav">
    <!--Notification Menu--> <!-- 알림 테이블 구현후 수정 할 것 -->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown"><i class="fa fa-bell-o fa-lg"></i></a>
          <ul class="app-notification dropdown-menu dropdown-menu-right" id="alarm">
            <li class="app-notification__title">최신 알람입니다.</li>
            <li>
            		<a class="app-notification__item" href="main.do"><span class="app-notification__icon"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-primary"></i><i class="fa fa-weixin fa-stack-1x fa-inverse"></i></span></span>
                  <div>
                    <p class="app-notification__message">채팅</p>
                    <p class="app-notification__meta" id="chatNumber"></p>
                  </div></a>
            </li>
            <div class="app-notification__content" id="AlarmList">
              </div>
            </div>
          </ul>
        </li>
        <!-- User Menu-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown"><i class="fa fa-user fa-lg"></i></a>
          <ul class="dropdown-menu settings-menu dropdown-menu-right">
            <li><a class="dropdown-item" href="#"><i class="fa fa-cog fa-lg"></i> 환경 설정</a></li>
            <li><a class="dropdown-item" href="#"><i class="fa fa-user fa-lg"></i> 마이 페이지</a></li>
            <li><a class="dropdown-item" href="/KHIntranet/tologin.do"><i class="fa fa-sign-out fa-lg"></i> 로그아웃</a></li>
          </ul>
        </li>
      </ul>
    </header>
    
    
    <!-- Sidebar menu-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    <aside class="app-sidebar">
      <div class="app-sidebar__user">
      		<%// if(emp_all.getEmp_pt_addr().equals("1")){
      			%>
      			
      		<img class="app-sidebar__user-avatar" src="${pageContext.request.contextPath}/Intranet/Public/img/profile_default.jpg" alt="User Image" id="nav-user-img" width="40px" height="40px">
      		<%//}else{ %>
      		<img class="app-sidebar__user-avatar" src="${emp_all.emp_pt_addr}${emp_all.emp_pt_name}" alt="User Image" id="nav-user-img" width="40px" height="40px">
      		<%//} %>
      		<div>
            <p class="app-sidebar__user-name">${emp_all.emp_name}</p><!-- EMP_NAME -->
          <p class="app-sidebar__user-designation">${emp_all.dept_name}&nbsp;/&nbsp;${emp_all.position_name } </p><!-- DEPT_NAME / EMP_GRADE -->
        </div>
      </div>
      
      <ul class="app-menu">
        <li><a class="app-menu__item" href="/KHIntranet/main.do"><i class="app-menu__icon fa fa-home"></i><span class="app-menu__label">메인페이지</span></a></li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-edit"></i><span class="app-menu__label">마이페이지</span><i class="treeview-indicator fa fa-angle-right"></i></a>
          <ul class="treeview-menu">
            <li><a class="treeview-item" href="#" data-toggle="modal" data-target="#modifyModal"><i class="icon fa fa-circle-o"></i> 개인정보 수정</a></li>
             <li><a class="treeview-item" href="${pageContext.request.contextPath}/myApproval.do"><i class="icon fa fa-circle-o"></i> 나의결재함</a></li>
             <%//if(emp_all.getPosition_rank() >= 3){%>
             <li><a class="treeview-item" href="${pageContext.request.contextPath}/adminApproval.do"><i class="icon fa fa-circle-o"></i> 결재승인요청문서</a></li>
             <%//}%>
            <li><a class="treeview-item" href="${pageContext.request.contextPath}/certificateList.do"><i class="icon fa fa-circle-o"></i> 증명서 출력</a></li>
            <li><a class="treeview-item" href="${pageContext.request.contextPath}/messageList.do"><i class="icon fa fa-circle-o"></i> 쪽지함</a></li>
            <li><a class="treeview-item" href="${pageContext.request.contextPath}/listAttendForm.do"><i class="icon fa fa-circle-o"></i> 나의 근태 현황</a></li>
          </ul>
        </li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">전자결재</span><i class="treeview-indicator fa fa-angle-right"></i></a>
          <ul class="treeview-menu">
             <li><a class="treeview-item" href="${pageContext.request.contextPath}/writeAttendForm.do"><i class="icon fa fa-circle-o"></i> 근태신청</a></li>
            <li><a class="treeview-item" href="${pageContext.request.contextPath}/writePurchaseForm.do"><i class="icon fa fa-circle-o"></i> 구매신청</a></li>
          </ul>
        </li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">업무관리</span><i class="treeview-indicator fa fa-angle-right"></i></a>
         	<ul class="treeview-menu">
	            <li><a class="treeview-item" href="${pageContext.request.contextPath}/listWork.do"><i class="icon fa fa-circle-o"></i> 업무보고</a></li>
	            <li><a class="treeview-item" href="${pageContext.request.contextPath}/calendar.do"><i class="icon fa fa-circle-o"></i> 일정관리</a></li>
          </ul>
         </li>    
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-users"></i><span class="app-menu__label">인사관리</span><i class="treeview-indicator fa fa-angle-right"></i></a>
          <ul class="treeview-menu">
            <li><a class="treeview-item" href="${pageContext.request.contextPath}/EmployeeChange.do"><i class="icon fa fa-circle-o"></i> 회원 검색</a></li>
            <li><a class="treeview-item" href="${pageContext.request.contextPath}/EmployeeApprovalPro.do"><i class="icon fa fa-circle-o"></i> 가입 승인</a></li> <!-- 스크립트 써서 관리자 만 볼수 있게 해야함 (세션으로 값 받아서 if문으로 출력하도록 합시다잉)-->
          </ul>
        </li>
        <li><a class="app-menu__item" href="${pageContext.request.contextPath}/listProject.do"><i class="app-menu__icon fa fa-bar-chart"></i><span class="app-menu__label"> 프로젝트</span></a></li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-file-text"></i><span class="app-menu__label">커뮤니티</span><i class="treeview-indicator fa fa-angle-right"></i></a>
          <ul class="treeview-menu">
          	<li><a class="treeview-item" href="${pageContext.request.contextPath}/listNotice.do"><i class="icon fa fa-circle-o"></i> 공지 사항</a></li>
            <li><a class="treeview-item" href="${pageContext.request.contextPath}/listData.do"><i class="icon fa fa-circle-o"></i> 자료실</a></li>
            <li><a class="treeview-item" href="${pageContext.request.contextPath}/photolist.do"><i class="icon fa fa-circle-o"></i> 사진첩</a></li>
          </ul>
        </li>
      <!--   <li><a class="app-menu__item" href="main.jsp"><i class="app-menu__icon fa fa-weixin"></i><span class="app-menu__label"> 1:1 문의 게시판</span></a></li> -->
        
       <!--  <li><a class="app-menu__item" href="main.jsp"><i class="app-menu__icon fa fa-cogs"></i><span class="app-menu__label"> 시스템 관리</span></a></li> -->
      </ul>
    </aside>
    <jsp:include page="MainModal.jsp" flush="false" />
    <decorator:body /> 
    </body>