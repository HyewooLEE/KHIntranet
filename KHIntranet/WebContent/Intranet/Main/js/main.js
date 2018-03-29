$(document).ready(function() {
	chatListFunction('ten');
	getInfiniteChat();

	var emp_no = document.getElementById("emp_no").value;
	$('#calendar').fullCalendar({
		header : {
			right : '',
			center : 'title',
			left : ''
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
		events : {
			url : '/KHIntranet/calendarList.ajax',
			type : 'POST',
			data : {
				emp_no : emp_no
			},
		}
	});
});