<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==1}">
<meta http-equiv="Refresh" content="0;url=/KHIntranet/photolist.do?pageNum=${pageNum}" >
</c:if>
<c:if test="${check==0}">
<meta http-equiv="Refresh" content="0;url=/KHIntranet/photocontent.do?photo_no=${photo_no }&pageNum=${pageNum}" >
</c:if>

