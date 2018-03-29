function writeSave(){
	//console.log(document.writeWorkForm.title.value);
	if(document.writeWorkForm.pro_no.value==""){
		alert("프로젝트를 선택해주세요.");
		document.writeWorkForm.pro_no.focus();
	  return false;
	} else if (document.writeWorkForm.work_ct_pb.value==""){
	  alert("프로젝트 문제점을 작성해주세요.");
	  document.writeWorkForm.work_ct_pb.focus();
	  return false;
	} else if (document.writeWorkForm.work_ct_sg.value==""){
	  alert("프로젝트 처리/제안을 작성해주세요.");
	  document.writeWorkForm.work_ct_sg.focus();
	  return false;
	} else {
		document.writeWorkForm.submit();
	}
}

function workFormReset() {
	history.go(-1);
}

$(function(){
	$("#pro_percent").change(function(){
		var percent = this.value;
		$("#pro_progress").css("width", percent+"%");
	});
});