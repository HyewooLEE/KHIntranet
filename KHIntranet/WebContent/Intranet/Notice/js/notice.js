function writeSave(){
	if(document.writeNoticeForm.notice_title.value==""){
		alert("제목을 작성 해주세요.");
		document.writeNoticeForm.notice_title.focus();
	  return false;
	} else if (document.writeNoticeForm.notice_content.value==""){
		alert("내용을 작성 해주세요.");
		document.writeNoticeForm.notice_content.focus();
	  return false;
	} else {
		document.writeNoticeForm.submit();
	}
}

function noticeFormCancel() {
	history.go(-1);
}
