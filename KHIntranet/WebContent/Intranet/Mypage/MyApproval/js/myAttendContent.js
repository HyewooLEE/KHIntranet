function chkDeleteForm(){
	
	var conURL = confirm("해당 결재신청건을 삭제 하시겠습니까?");
	
	if(conURL == true){
		document.deleteForm.submit();
		return false;
	}
	else{
		return false;
	}
	
}