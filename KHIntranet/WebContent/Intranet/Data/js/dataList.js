// 자바 스크립트 구현 부.
function deleteData(data_no){
	var data_del = 1;
	location.assign("/KHIntranet/writeDataPro.do?data_no="+ data_no +"&data_del="+ data_del);
}