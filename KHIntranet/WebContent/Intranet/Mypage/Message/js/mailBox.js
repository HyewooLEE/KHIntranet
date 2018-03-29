function reloadFunction(){
	location.reload(true);
} 
  function deleteFunction(){
	  var status = $("#status").val();	
	  var checkboxValues = [];
		$("input[name='msg_no']:checked").each(function(i) {
   		 checkboxValues.push($(this).val());
	  });
		$.ajax({
			type: 'GET',
			url: 'messageDelete.ajax',
			data : 
			{ 
				msg_no : checkboxValues,
				status : status		
			},
			success : function(result){
				location.reload(true);
				

				    
				
			}
		}); 
	}
 
  function restoreFunction(){
  	  var status = $("#status").val();	
  	  var checkboxValues = [];
  		$("input[name='msg_no']:checked").each(function(i) {
   		 checkboxValues.push($(this).val());
  	  });
  		$.ajax({
  			type: 'GET',
  			url: 'messageRestore.ajax',
  			data : 
  			{ 
  				msg_no : checkboxValues,
  				status : status		
  			},
  			success : function(result){
  				location.reload(true);
  			}
  		}); 
  	}

  $(function(){
	  $("#selectAll").click(function(){
		  if($("#selectAll").prop("checked")) {
			  $("input[type=checkbox]").prop("checked",true);
			  } else { 
				  $("input[type=checkbox]").prop("checked",false);
				  }
		  })
		})

		
	function importantFunction(e) {
		var msg_no =  e.getAttribute('id');
		 $.ajax({
			url : "messageImportant.ajax", //commandAjaxURI.properties 등록한 URL
			type : "post",
			data : {
				msg_no : msg_no
			},
			success : function(data) {
				if(data == 1){
					e.innerHTML ='<i class="fa fa-star"></i>';
				}else if(data ==0){
					e.innerHTML ='<i class="fa fa-star-o"></i>';
				}else{
					return false;
				}
			},
			error : function(e) {
				alert(e.responseText);
			}
		});  
	};
		
		
