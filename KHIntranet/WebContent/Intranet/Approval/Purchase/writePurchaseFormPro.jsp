<%@ page contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>

<head>
	<title>물품구매신청 완료</title>
</head>

<body>
	<script>
	var conURL = confirm("신청완료되었습니다.\n마이페이지로 이동합니다.");
	if(conURL == true){
		location.replace("/KHIntranet/myApproval.do");
	}
	else if(conURL == false){
		history.go(-1);
	} 
	</script>
</body>

