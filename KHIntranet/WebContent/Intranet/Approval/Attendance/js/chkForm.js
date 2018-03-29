function chkForm(){

	if(document.attendForm.atd_receiver.value==""){
	  alert("결재라인을 선택하십시오.");
	  document.attendForm.atd_receiver.focus();
	  return false;
	}
	
	if(document.attendForm.atd_div.value==""){
	  alert("근태구분을 선택하십시오.");
	  document.attendForm.atd_div.focus();
	  return false;
	}
	
	if(document.attendForm.atd_start_date.value==""){
	  alert("시작일을 입력하십시오.");
	  document.attendForm.atd_start_date.focus();
	  return false;
	}

}