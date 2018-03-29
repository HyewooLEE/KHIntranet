<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<main class="app-content">
	<div class="app-title">
		<div>
			<!-- content 내 상위 nav 시작 -->
			<h1>
				<i class="fa fa-home"></i>Calendar
			</h1>
			<!-- content 내 상위 nav 끝 -->
		</div>
		<ul class="app-breadcrumb breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
			<li class="breadcrumb-item"><a href="#">Project</a></li>
			<li class="breadcrumb-item"><a href="#">Calendar</a></li>
		</ul>
	</div>
	<div class="app-body">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="tile" id="calendar_tile" style="padding:50px;">
			 	<h5 class="tile-title">일정 관리</h5>
					<div id="calendar"></div>
				</div>
			</div>
			<div class="col-md-2"></div>	
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div id="insertEvent" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog">                  
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" id="close">×</button>
							</div>               
							<div class="modal-body" id="modal-body">
							<form method="post" action="/KHIntranet/calendarPro.do" name="eventForm" id="eventForm">
								<input type="hidden" name="emp_no" id="emp_no" value="${emp_no}">
								<input type="hidden" name="cal_ud" id="cal_ud" value="0">
								<input type="hidden" name="dt_title" id="dt_title" value="">
								<input type="hidden" name="dt_start" id="dt_start" value="">
								<input type="hidden" name="dt_end" id="dt_end" value="">
								<div class="col-md-8">일정명 : <input class="form-control" type="text" name="eventTitle" id="eventTitle" placeholder="Enter title"></div>
								<div class="col-md-8">시작일 : <input class="form-control" type="text" name="eventStartDate" id="eventStartDate" placeholder="Select Start Date"></div>
								<div class="col-md-8">종료일 : <input class="form-control" type="text" name="eventEndDate" id="eventEndDate" placeholder="Select End Date"></div>
							</form>
							</div>
							<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal" onclick="eventForm.reset();">취소</button>
							<button type="button" class="btn btn-primary" id="pcp_submit" onclick="writeSave();">등록</button>
							<button type="button" class="btn btn-primary" id="pcp_delete" onclick="deleteEvent();" style="display: none">삭제</button>
							</div>
						</div>
					</div>
				</div>					
			</div>
		</div>
	</div>
	<!-- Essential javascripts for application to work-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/moment.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/fullcalendar.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Project/js/calendar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Project/js/fullcalendar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Intranet/Public/js/plugins/bootstrap-datepicker.min.js"></script>
	
</body>
<!-- <style>
#calendar_tile{
	padding:70px;
}
@media (max-width:716px){
#calendar_tile{
	padding:10px;
}
}
</style> -->
</html>