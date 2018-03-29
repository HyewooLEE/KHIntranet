		function Empdate(emp_no, emp_name, dept_no1, position_rank1, sal1) {
	var dept_no = document.getElementById(dept_no1).selectedIndex;
	var position_rank = document.getElementById(position_rank1).selectedIndex;
	var sal = document.getElementById(sal1).value;


	if (dept_no == 0) {
		alert("부서를 선택해주세요");
		return false;
	}

	if (position_rank == 0) {
		alert("직급을 선택해주세요");
		return false;
	}

	if (sal == 0) {
		alert("급여를 선택해주세요");
		return false;
	}

	if (confirm(emp_name + "가입 승인하시는건가요?") == true) { // 확인
		$('.btn_del').unbind('click');
	} else { // 취소
		return false;
	}
}