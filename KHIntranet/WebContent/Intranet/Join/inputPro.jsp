<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==1}">
<meta http-equiv="Refresh" content="0;url=/ELEx/inputForm.do" >
</c:if>
<c:if test="${check==0}">
신청이 불가능합니다.
<br>
<a href="javascript:history.go(-1)">[회원가입으로 돌아가기]</a>
</c:if>
<% response.sendRedirect("/KHIntranet/tologin.do"); %>