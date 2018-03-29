<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Intranet/Public/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Intranet/Login/css/login.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>KH인트라넷 로그인</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Login/js/login.js"></script>
   
<style>
::-webkit-scrollbar {display:none;}
.login-content .login-box.flipped {
    min-width: 500px; 
    min-height: 760px;
}

 .login-content .login-box.flipped {
  min-width:1000px;
  min-height: 650px;

}
</style>

  </head>
  <body style="-ms-overflow-style: none; " >
  <div id="fakeLoader"></div>
 
    <section class="material-half-bg">
      <div class="cover"></div>
    </section>

    <section class="login-content">
      <div class="logo">
        <h1><img src="${pageContext.request.contextPath}/Intranet/Public/img/symbol_blue.png"  width="80px;"></h1>
      </div>
      <div class="login-box">
        <form class="login-form" action="/KHIntranet/login.do" method="post" name="myform" onSubmit="return check()">
          <h3 class="login-head">로그인</h3>
          <div class="form-group">
            <label class="control-label">아이디</label>
            <input  class="form-control"  type="text"  name="emp_id" placeholder="아이디를 입력해주세요." autofocus>
          </div>
          <div class="form-group">
            <label class="control-label">비밀번호</label>
            <input  class="form-control"  type="password" name="emp_pw" placeholder="비밀번호를 입력해주세요.">
          </div>
          <div class="form-group">
            <div class="utility mb-2" style="border-bottom:1px solid #ddd;">
              <div class="animated-checkbox">
                <label>
                  <input type="checkbox"><span class="label-text">로그인 상태 유지</span>
                </label>
              </div>
                <p class="semibold-text mb-2"><a href="#">아이디/ 비번 찾기</a></p>
            </div>
            <div class="utility">
            <p class="semibold-text mb-2" style="margin:0 auto;" ><a href="#" data-toggle="flip">회원가입</a></p>
            </div>
          </div>
          <div class="form-group btn-container">
            <button class="btn btn-primary btn-block"><i class="fa fa-sign-in fa-lg fa-fw"></i>로그인하기</button>
          </div>
        </form>
        
        <!-- 회원가입 폼 -->
        <form class="forget-form" action="/KHIntranet/inputPro.do" name="userinput" onSubmit="return checkIt()" method="post">
        <div class="row mb-3 mt-3"  >
        	<div class="col-md-1"></div>
        	<div class="col-md-11" >
        		<h3>회원가입</h3>
        	</div>
        </div>
        <div class="row mb-5">  
        <div class="col-md-1"></div>
        <div class="col-md-4" >
          <div class="form-group">
                  <label class="control-label">아이디</label>
                    <input class="form-control" name="emp_id" type="text" maxlength="20"  placeholder="아이디를 입력하시오" Onblur="changeId();">
                    <b id="idfind"  style="color: red; "></b>
            		<input type="hidden" name="idcb" value="">
           </div>
           <div class="form-group">
                  <label class="control-label">비밀번호</label>
                    <input class="form-control" name="emp_pw1" type="password" maxlength="20"  placeholder="비밀번호를 입력하시오">
           </div>
           <div class="form-group">
                  <label class="control-label">비밀번호 확인</label>
                    <input class="form-control" name="emp_pw2" type="password" maxlength="20"  placeholder="비밀번호를 확인해주세요">
           </div>
           <div class="form-group">
           	<div class="row">
           		<div class="col-md-7">
                  <label class="control-label">이름</label>
                    <input class="form-control" name="emp_name" type="text" maxlength="10"  placeholder="이름을 입력하시오">
                   </div>
                   <div class="col-md-5">
                    <label class="control-label">성별</label>
                    <div class="animated-radio-button">
                      <label style="margin-right:5px;">
                        <input type="radio" name="optionsRadios" value="남성" checked=""><span class="label-text">남</span>  
                      </label>
                      <label>
                      <input type="radio" name="optionsRadios" value="여성" ><span class="label-text">여</span>
                    </label>
                    </div>
                    </div>
                    </div>
          </div>
           <div class="form-group" >
                  <label class="control-label">생년월일</label>
                  	<input  class="form-control" id="datepicker1"  name="emp_jumin1" type="text" maxlength="20"  >
           </div>
           </div>
           <div class="col-md-1" style="border-right:1px solid #e7e7e7;"></div>
           <div class="col-md-1"></div>
           <div class="col-md-4">
           <div class="form-group">
                  <label class="control-label">이메일</label>
                  	<input  class="form-control" name="emp_email"  type="email" maxlength="25"  placeholder="이메일 입력하시오">
           </div>
           <div class="form-group ">
                  <label class="control-label">전화번호</label>
                  	<input  class="form-control" name="emp_pn" type="text" maxlength="20"  placeholder="전화번호를 입력하시오">
           </div>
           <div class="form-group">
                  <label class="control-label">우편번호</label>
                  	<div class="input-group"> 
                  	<input  class="form-control" id="sample3_postcode" name="emp_addr1"  type="text" maxlength="20" placeholder="주소를 입력하시오" readonly>
                  	<span class="input-group-btn">
                 		<button class="btn btn-primary"  type="button" onclick="return kume1()">검색</button>
                   </span>
                  	</div>
           </div>
           <div class="form-group" >
                  <label class="control-label">주소</label>
                  	<input  class="form-control" id="sample3_address" name="emp_addr2" type="text" maxlength="50"  placeholder="주소를 입력하시오" readonly>
           </div>
           <div class="form-group">
                  <label class="control-label">상세 주소</label>
                  	<input  class="form-control" name="emp_addr3" type="text" maxlength="50"  placeholder="상세주소를 입력하시오.">
           </div>
           </div>
           <div class="col-md-1"></div>
           </div>
           
            
           
            
          <div class="row" align="center">
          <div class="col-md-12">
          <div class="form-group btn-container">
          <button class="btn btn-primary"  type="submit" style="margin-right:10px;"><i class="fa fa-sign-in fa-lg fa-fw"></i>가입하기</button>
           <a href="#" data-toggle="flip"> <button class="btn btn-secondary"  type="button"><i class="fa fa-angle-left fa-fw"></i> 이전으로</button></a>
          </div>
          </div>
          </div>
        </form>
      </div>
      
      
<!--       <form class="forget-form" action="index.html">
          <h3 class="login-head"><i class="fa fa-lg fa-fw fa-lock"></i>Forgot Password ?</h3>
          <div class="form-group">
            <label class="control-label">EMAIL</label>
            <input  class="form-control form-control-sm" id="inputSmall"  type="text" placeholder="Email">
          </div>
          <div class="form-group btn-container">
            <button class="btn btn-primary btn-block"><i class="fa fa-unlock fa-lg fa-fw"></i>RESET</button>
          </div>
          <div class="form-group mt-3">
            <p class="semibold-text mb-0"><a href="#" data-toggle="flip"><i class="fa fa-angle-left fa-fw"></i> Back to Login</a></p>
          </div>
        </form> -->
    </section>
  </body>

  <!-- Essential javascripts for application to work-->
  <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/popper.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
  <!-- The javascript plugin to display page loading on top-->
  <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/bootstrap-datepicker.min.js"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</html>
<script type="text/javascript">
function logingo(check){
	if(check==1){
		swal.getState();	
		swal("오류!","가입 승인을 받지 못하였습니다.","warning", {buttons: "닫기"});
		
		return false;
	}else if(check==0){
		swal.getState();	
		swal("오류!","비밀번호가 맞지 않습니다.","warning", {buttons: "닫기"});
		return false;
	}else{
		swal.getState();	
		swal("오류!","아이디가 존재 하지않습니다.","warning", {buttons: "닫기"});
		return false;
	}
};
  // Login Page Flipbox control
  $('.login-content [data-toggle="flip"]').click(function() {
     $('.login-box').toggleClass('flipped');
     return false;
  });
  //달력
  $(function(){  
       $('#datepicker1').datepicker({
         format: "yyyy-mm-dd",
            autoclose: true,
            todayHighlight: true,
          //buttonImage: "/img/calendar.gif"
    });
   });
</script>

     <c:if test="${check1 ==0 }" >
   <script>
   logingo(${check});
   </script>
    </c:if>