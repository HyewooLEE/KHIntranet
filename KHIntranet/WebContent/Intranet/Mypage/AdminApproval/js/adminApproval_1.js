	$(function(){ 
		$("#doc_1").mouseover(function(){
	        $("#doc_1").css("background-color", "#007bff");
	        $("#doc_1").css("color", "#FFF");
	    });
	    $("#doc_1").mouseout(function(){
	        $("#doc_1").css("background-color", "#FFF");
	        $("#doc_1").css("color", "#000");
	    });
	    $("#doc_2").mouseover(function(){
	        $("#doc_2").css("background-color", "#007bff");
	        $("#doc_2").css("color", "#FFF");
	    });
	    $("#doc_2").mouseout(function(){
	        $("#doc_2").css("background-color", "#FFF");
	        $("#doc_2").css("color", "#000");
	    });
	});
	
	$(function(){ 
		 $('#doc_1').on('click',function(){ 
			 docTable_1.style.display = "inline";
			 docTable_2.style.display = "none";
		 });
		  
		 $('#doc_2').on('click',function(){ 
			 docTable_1.style.display = "none";
			 docTable_2.style.display = "inline";
		 });
	});