function writeSave(){
	if(document.writeDataForm.data_title.value==""){
		alert("제목을 작성해 주세요.");
		document.writeDataForm.data_title.focus();
	  return false;
	} else if (document.writeDataForm.data_content.value==""){
		alert("내용을 작성해주세요.");
		document.writeDataForm.data_content.focus();
	  return false;
	} else {
		document.writeDataForm.submit();
	}
}

function dataFormCancel() {
	history.go(-1);
}
$(function(){
	var count = 1;
	$("#addFile").click(function(){
		for(var i=0; i<count; i++) {
			var addFileInput = 	"<div class='col-10'>" +
			"<input class='form-control' type='file' name='data_file_name"+i+"' id='data_file_name"+i+"'>" +
			"</div>" +
			"<div class='col-2'>" +
			"</div>";
		}
		$("#inputFileForm").append(addFileInput);
		count++;
	});
});