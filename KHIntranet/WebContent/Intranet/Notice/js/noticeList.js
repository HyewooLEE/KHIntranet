// 자바 스크립트 구현 부.
function deleteNotice(notice_no){
	var notice_del = 1;
	location.assign("/KHIntranet/writeNoticePro.do?notice_no="+ notice_no +"&notice_del="+ notice_del);
}