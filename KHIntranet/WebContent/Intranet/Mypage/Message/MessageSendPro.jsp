<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=utf-8");
	String check = (String) request.getSession().getAttribute("check");
	if(check.equals("-1")||check.equals("0")){
		response.sendRedirect("/KHIntranet/messageSendForm.do");
	}else{
		response.sendRedirect("/KHIntranet/messageList.do?&status=1");
	}
	%>

