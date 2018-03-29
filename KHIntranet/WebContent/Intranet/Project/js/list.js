// 자바 스크립트 구현 부.
function deleteProject(pro_no){
	var pro_del = 1;
	location.assign("/KHIntranet/writeProjectPro.do?pro_no="+ pro_no +"&pro_del="+ pro_del);
}