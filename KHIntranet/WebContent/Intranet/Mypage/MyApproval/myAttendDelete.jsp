<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>근태신청 삭제</title>
</head>
<body>
	<script>
	alert("삭제완료되었습니다.\n나의 결재관리로 이동합니다.");
	window.location.href="/KHIntranet/myApproval.do";
	 
	/* var conURL = confirm("삭제완료되었습니다.\n나의 결재관리로 이동합니다.");
	
	if(conURL == true){
		location.replace("/KHIntranet/myApproval.do");
	}
	else if(conURL == false){
		history.go(-1);
	}  */
	</script>
</body>
</html>