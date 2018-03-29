<%@ page contentType="text/xml; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	request.setCharacterEncoding("UTF-8"); 

	/* Integer check = (Integer)request.getAttribute("check"); */    //keyword라는 이름에 web을 가져온다.

%>
<?xml version="1.0" encoding="utf-8" ?>
<title>
${check}
<%-- <%=check %> --%>
</title>