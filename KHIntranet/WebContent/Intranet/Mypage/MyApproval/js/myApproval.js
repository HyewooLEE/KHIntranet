$(document).ready(function(){
	
    $("#Ndoc").mouseover(function(){
        $("#Ndoc").css("background-color", "#007bff");
        $("#Ndoc").css("color", "#FFF");
    });
    $("#Ndoc").mouseout(function(){
        $("#Ndoc").css("background-color", "#FFF");
        $("#Ndoc").css("color", "#000");
    });
    
    $("#Ydoc").mouseover(function(){
        $("#Ydoc").css("background-color", "#007bff");
        $("#Ydoc").css("color", "#FFF");
    });
    $("#Ydoc").mouseout(function(){
        $("#Ydoc").css("background-color", "#FFF");
        $("#Ydoc").css("color", "#000");
    });
    
    $("#Rdoc").mouseover(function(){
        $("#Rdoc").css("background-color", "#007bff");
        $("#Rdoc").css("color", "#FFF");
    });
    $("#Rdoc").mouseout(function(){
        $("#Rdoc").css("background-color", "#FFF");
        $("#Rdoc").css("color", "#000");
    });
    
});


$(function(){ 
	
	 $('#Ndoc').on('click',function(){ 
		NApproval.style.display = "inline";
		YApproval.style.display = "none";
		RApproval.style.display = "none";
	 });
	  
	 $('#Ydoc').on('click',function(){ 
		NApproval.style.display = "none";
		YApproval.style.display = "inline";
		RApproval.style.display = "none";
	 });
	
	 $('#Rdoc').on('click',function(){ 
		NApproval.style.display = "none";
		YApproval.style.display = "none";
		RApproval.style.display = "inline";
	 });
	
});


function changeDoc(docNo,docStatus){
		
	  if(docStatus=="미결재"){ 
		  if(docNo == 1){ //1
		  	NattendDrop.style.display = "inline"
		  	NpurchaseDrop.style.display = "none"
	  	  }else{ //2
		  	NattendDrop.style.display = "none"
		  	NpurchaseDrop.style.display = "inline"
	  	  }
	  }
	  
	  if(docStatus=="결재완료"){
		  if(docNo == 1){ //1
		 	YattendDrop.style.display = "inline"
		 	YpurchaseDrop.style.display = "none"
	  	  }else{ //2
		 	YattendDrop.style.display = "none"
		 	YpurchaseDrop.style.display = "inline"
	  	  }
	  }
	
	  if(docStatus=="반려"){
		  if(docNo == 1){ //1
			RattendDrop.style.display = "inline"
			RpurchaseDrop.style.display = "none"
	  	  }else{ //2
		  	RattendDrop.style.display = "none"
		  	RpurchaseDrop.style.display = "inline"
	  	  }
	  }
}