  function deleteFunction2(e){
	  var status = $("#status").val();	   
	  var msg_no = e.getAttribute('id');
		$.ajax({
			type: 'GET',
			url: 'messageDelete.ajax',
			data : 
			{ 
				msg_no : msg_no,
				status : status		
			},
			success : function(result){

				location.replace('/KHIntranet/mailbox.do?emp_id=&status=1');

			}
		});
	}
 

