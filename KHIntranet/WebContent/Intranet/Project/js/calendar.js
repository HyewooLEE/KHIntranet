function writeSave() {
	if (document.eventForm.eventTitle.value == "") {
		alert("일정을 입력해주세요..");
		document.eventForm.eventTitle.focus();
		return false;
	} else if (document.eventForm.eventStartDate.value == "") {
		alert("시작 일자를 선택해주세요.");
		document.eventForm.eventStartDate.focus();
		return false;
	} else if (document.eventForm.eventEndDate.value == "") {
		alert("종료 일자를 입력해주세요.");
		document.eventForm.eventEndDate.focus();
		return false;
	}
}

$(document).ready(function() {
	var emp_no = document.getElementById("emp_no").value;
	$('#calendar').fullCalendar({
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'insertButton'
		},
		titleFormat : 'YYYY년 MM월',
		monthNames : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ], 
		monthNamesShort : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
		dayNames : [ "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" ],
		dayNamesShort : [ "일", "월", "화", "수", "목", "금", "토" ],
		buttonText : {
			today : "오늘",
			month : "월별",
			week : "주별"
		},
		customButtons : {
			insertButton : {
				text : '등록',
				click : function() {
					insertEvent();
				}
			}
		},
		events : {
			url : '/KHIntranet/calendarList.ajax',
			type : 'POST',
			data : {
				emp_no : emp_no
			},
			error : function() {
				alert('오류났어 확인해!!!');
			},
		},
		eventClick : function(calEvent, jsEvent, view) {
			var dt_title = calEvent.title;
			var dt_start = moment(calEvent.start).format('YYYY-MM-DD');
			var dt_end = moment(calEvent.end).format('YYYY-MM-DD');

			updateEvent(dt_title, dt_start, dt_end);
		}
	});
});

function insertEvent() {
	document.getElementById("eventTitle").value = "";
	document.getElementById("eventStartDate").value = "";
	document.getElementById("eventEndDate").value = "";
	document.getElementById("cal_ud").value = 0;
	
	$("#pcp_delete").css("display", "none");
	$("#insertEvent").modal("show");

	$('#eventStartDate').datepicker({
		format : "yyyy-mm-dd",
		autoclose : true,
		todayHighlight : true,
		daysOfWeekDisabled : [ 0, 6 ]
	});

	$('#eventEndDate').datepicker({
		format : "yyyy-mm-dd",
		autoclose : true,
		todayHighlight : true,
		daysOfWeekDisabled : [ 0, 6 ]
	});

	$("#pcp_submit").click(function() {
		document.eventForm.submit();
	});
};

function updateEvent(dt_title, dt_start, dt_end) {
	document.getElementById("eventTitle").value = dt_title;
	document.getElementById("eventStartDate").value = dt_start;
	document.getElementById("eventEndDate").value = dt_end;
	document.getElementById("dt_title").value = dt_title;
	document.getElementById("dt_start").value = dt_start;
	document.getElementById("dt_end").value = dt_end;
	document.getElementById("cal_ud").value = 1;
	
	$("#pcp_delete").css("display", "block");
	
	$("#insertEvent").modal("show");

	$('#eventStartDate').datepicker({
		format : "yyyy-mm-dd",
		autoclose : true,
		todayHighlight : true,
		daysOfWeekDisabled : [ 0, 6 ]
	});

	$('#eventEndDate').datepicker({
		format : "yyyy-mm-dd",
		autoclose : true,
		todayHighlight : true,
		daysOfWeekDisabled : [ 0, 6 ]
	});

	$("#pcp_submit").click(function() {
		writeSave();
		document.eventForm.submit();
	});
};

function deleteEvent() {
	document.getElementById("cal_ud").value = 2;
	document.eventForm.submit();
}