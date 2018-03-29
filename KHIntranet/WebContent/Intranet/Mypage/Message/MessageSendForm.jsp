<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
<title>쪽지함</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Intranet/Mypage/Message/css/mailForm.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Mypage/Message/js/mailForm.js"></script>
</head>
<body>
    <main class="app-content">   
      <div class="app-title">
        <div>
          <h1><i class="fa fa-envelope-o"></i> 쪽지함</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"><a href="#">Mailbox</a></li>
        </ul>
      </div>
      <div class="row">
      <div class="col-md-3">
      	<div class="row">
      	<div class="col-md-12">       
          <div class="tile">
            <h4 class="tile-title folder-head"> 쪽지함</h4>
            <div class="tile-body">
              <ul class="nav nav-pills flex-column mail-nav">
                <li class="nav-item active"><a class="nav-link" href="/KHIntranet/messageList.do?emp_id=${emp_id }&status=1"><i class="fa fa-inbox fa-fw"></i> 받은 쪽지함<span class="badge badge-pill badge-primary float-right">${unreadCount }</span></a></li>
                <li class="nav-item"><a class="nav-link" href="/KHIntranet/messageList.do?emp_id=${emp_id }&status=2"><i class="fa fa-envelope-o fa-fw"></i> 보낸 쪽지함</a></li>
                <li class="nav-item"><a class="nav-link" href="/KHIntranet/messageList.do?emp_id=${emp_id }&status=3"><i class="fa fa-trash-o fa-fw"></i> 휴지통</a></li>
              </ul>
            </div>
          </div>
          </div>
        </div>
        <div class="row panel-collapse collapse" id="tono_box" > 
        <div class="col-md-12">      
          <div class="tile"  >
            <h5 class="tile-title folder-head"> 수신자 설정</h5>
            <div class="tile-body">
              <ul class="nav nav-pills flex-column mail-nav" id="searchList">
              </ul>
            </div>
          </div>
          </div>
        </div>
        </div>
        <div class="col-md-9">
        <div class="formBox" >
          <div class="tile">
          <h4 class="tile-title line-head">메시지 보내기</h4>
            <div class="tile-body">
           <form  action="/KHIntranet/messageSendPro.do" method="get">
           <div class="form-group">
           <label class="control-label "><b> 받는사람</b></label>
                <div class="input-group">
                  <input type="hidden" name="msg_fromno" value="${emp_no }" >
                	<c:if test="${msg_fromno eq null}">
                	<input type="hidden" name="msg_tono" value="none" id="input_tono">                
                  </c:if>
                  <c:if test="${msg_fromno ne null }">
                  <input type="hidden" name="msg_tono" value="${msg_fromno }" id="input_tono">               
                  </c:if>
                  
                  
                  <c:if test="${msg_fromno ne null}">
                  <input class="form-control col-md-4" type="text" name="emp_name" value="${msg_fromname }"  id="searchInput"  readonly>
                  <span class="input-group-btn">
                 <a href="#tono_box" ><button class="btn btn-secondary" id="searchBtn" onclick="return searchFunction();" disabled> 검색</button></a>
                   </span>
                  </c:if>
                  <c:if test="${msg_fromno eq null}">
                   <input class="form-control col-md-4" type="text" name="emp_name" placeholder="받는 분의 이름으로 검색해주세요"  id="searchInput" >
                  <span class="input-group-btn">
                 <a href="#tono_box" ><button class="btn btn-primary" id="searchBtn" onclick="return searchFunction();" > 검색</button></a>
                   </span>
                   </c:if>
                </div>
                </div>
                <div class="form-group">
                  <label class="control-label"><b>내용</b></label>
                  <textarea class="form-control" rows="15" placeholder="내용을 입력하세요" name="msg_content" id="msg_content"></textarea>
                </div>
                <div class="tile-footer">
              <button class="btn btn-primary" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>보내기</button>
            </div>
              </form>
                </div>
          </div>
        </div>
        </div>     
      </div>

      
    </main>
       <!-- The javascript plugin to display page loading on top-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/pace.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/bootstrap-notify.min.js"></script>
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
	</c:if>
	<%
		session.removeAttribute("check");
		session.removeAttribute("messageContent");
		session.removeAttribute("messageType");	
	 %>
</body>
</html>
