// 자바 스크립트 구현 부.
function writeSave(){
	/*alert("여기 오긴하냐?");*/
	if(document.writeProjectForm.pro_title.value ==""){
		alert("프로젝트 이름을 입력해주세요..");	
		document.writeProjectForm.pro_title.focus();
	  return false;
	} else if (document.writeProjectForm.lastSelector.value==""){
	  alert("프로젝트 참가자를 선택해주세요.");
	  document.writeProjectForm.lastSelector.focus();
	  return false;
	} else if (document.writeProjectForm.pro_content.value==""){
	  alert("프로젝트 내용을 입력해주세요.");
	  document.writeProjectForm.pro_content.focus();
	  return false;
	} else {
		document.writeProjectForm.submit();
	}
}

function projectFormCancel() {
	history.go(-1);
}

// Jquery 구현 부.
$(function(){
	$("#btn1").click(function(){
		var chooser = document.writeProjectForm.chooser;
		var selector = document.writeProjectForm.selector;
		
		if(chooser.value != null && chooser.value != "") {
			
			var cinput = chooser.value;
			var sinput = selector.value;
			var sinputText = document.writeProjectForm.chooser.options[document.writeProjectForm.chooser.selectedIndex].text;
			$("#selector").append("<option value='"+ cinput +"' selected='selected'>"+ sinputText +"</option>");
			$("#chooser > option[value='"+ cinput +"']").remove();
		} else {
			alert("프로젝트 참가자를 선택해주세요.");
		}
	});

	$("#btn2").click(function(){
		var chooser = document.writeProjectForm.chooser;
		var selector = document.writeProjectForm.selector;
		
		if(selector.value != null && selector.value != "") {
			var cinput = chooser.value;
			var sinput = selector.value;
			var cinputText = document.writeProjectForm.selector.options[document.writeProjectForm.selector.selectedIndex].text;
			$("#chooser").append("<option value='"+ sinput +"'>"+ cinputText +"</option>");
			$("#selector > option[value='"+ sinput +"']").remove();
		} else {
			alert("프로젝트 참가자를 선택해주세요.");
		}
	});
	
	$("#pcp_submit").click(function(){
		var arr = new Array();
		var obj = document.writeProjectForm.selector.options;
		
		for (var i=0; i<obj.length; i++) {
	        if(obj[i].selected){
	            arr.push(obj[i].text);
	         }
	     }
	    
	    if(arr != null){
			var selector = $("#selector > option");	
			var sinput = arr

			for(var i=0; i<arr.length; i++)
			document.getElementById("lastSelector").value += arr[i] +" / ";
				
			$("#participant").modal("hide");
		} else{
			alert("프로젝트 참가자를 선택해주세요.");
		}
	    
	    var selector = document.writeProjectForm.selector;               
        document.getElementById("lastSelector").value = "";
        document.getElementById("lastSelector_hide").value = "";
           for(var i=0; i<selector.length; i++){                      
              var selectorValue_hide = document.writeProjectForm.selector[i].value;
              var selectorValue_show = document.writeProjectForm.selector[i].text; 
              
              document.getElementById("lastSelector_hide").value += selectorValue_hide;
              document.getElementById("lastSelector").value += selectorValue_show;   
              
              if(i != selector.length -1) {
                 var inputhidden_hide = document.getElementById("lastSelector_hide").value;
                 var inputhidden_show = document.getElementById("lastSelector").value;
                 
                 document.getElementById("lastSelector_hide").value +=  ",";
                 document.getElementById("lastSelector").value += " / ";       
              }
              
              $("#myModal").modal('hide');
           }               
	});
	
	$('#pro_start_date').datepicker({
      	format: "yyyy/mm/dd",
      	autoclose: true,
      	todayHighlight: true
      });
	
	$('#pro_end_date').datepicker({
      	format: "yyyy/mm/dd",
      	autoclose: true,
      	todayHighlight: true
      });
	
});