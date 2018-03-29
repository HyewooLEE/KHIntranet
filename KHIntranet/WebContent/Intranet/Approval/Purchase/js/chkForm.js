function chkForm(){

	if(document.purchaseForm.pur_receiver.value==""){
	  alert("결재라인을 선택하십시오.");
	  document.purchaseForm.pur_receiver.focus();
	  return false;
	}
	
	if(document.purchaseForm.pur_title.value==""){
	  alert("제목을 입력하십시오.");
	  document.purchaseForm.pur_title.focus();
	  return false;
	}
	
}