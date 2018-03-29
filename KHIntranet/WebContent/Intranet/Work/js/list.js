// 자바 스크립트 구현 부.
function deleteWork(work_no){
	var work_del = 1;
	location.assign("/KHIntranet/writeWorkPro.do?work_no="+ work_no +"&work_del="+ work_del);
}