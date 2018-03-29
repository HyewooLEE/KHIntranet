<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check==1}">
<%
	response.sendRedirect("/KHIntranet/photolist.do");	
%>
</c:if>
<c:if test="${check==-1}">
<%
	response.sendRedirect("/KHIntranet/photowriteForm.do}");	
%>
</c:if>