    function searchFunction(){
  	  var emp_name = $("#searchInput").val();
  	  if(emp_name == ""){
  		  $.notify({
  	      		title: "검색 실패: ",
  	      		message: "검색어를 입력해주세요!",
  	      		icon: 'fa fa-check' 
  	      	},{
  	      		type: "info",
  	      		allow_dismiss: true,
  	      		animate: {
  	      			enter: 'animated fadeInDown',
  	      			exit: 'animated fadeOutUp'
  	      		},
  	      		newest_on_top: true,
  	      		offset: 100
  	      		
  	      	});
  		  	return false;
  	  }else{
  		$.ajax({
  			type: 'post',
  			url: 'messageSearch.ajax',
  			data :    
  			{ 
  				emp_name : emp_name,	
  			},
  			success : function(data){
  				if(data ==0) {
  					$.notify({
  			      		title: "검색 실패: ",
  			      		message: "대상이 존재 하지 않습니다.",
  			      		icon: 'fa fa-check' 
  					},{
  			      		type: "info",
  			      		allow_dismiss: true,
  			      		animate: {
  			      			enter: 'animated fadeInDown',
  			      			exit: 'animated fadeOutUp'
  			      		},
  			      		newest_on_top: true,
  			      		offset: 100
  			      		
  			      	});
  				}else{
  				var parsed = JSON.parse(data);
  				var result = parsed.result;
  				$('#searchList').html(' ');
  				for(var i = 0 ; i < result.length; i++){
  					addList(result[i][0].value,result[i][1].value,result[i][2].value,result[i][3].value);
  				}
  				$("#tono_box").collapse('show');
  				}
  			}
  		}); 
  		return false;
  	  }
  	}
    function addList(dept_name,position_rank,emp_name,emp_no){
    	$('#tono_box').attr("display","block");
    	$('#searchList').append(
    			'<li class="nav-item">' +
    			'<a class="nav-link" href="javascript:" onclick="inputValue(this);" id="'+emp_no+'" title="'+emp_name +'">' +
    			'<i class="fa fa-user fa-lg"></i>' +
    			'&nbsp;&nbsp;&nbsp;&nbsp;' +
    			'<b>' +
    			dept_name + 
    			'부서' +
    			'</b>' +
    			'&nbsp;&nbsp;' + 
    			position_rank +
    			'&nbsp;&nbsp;' +
    			'<span class ="'+emp_name+'">' +
    			emp_name + 
    			' 님' +
    			'</span>'+
    			'</a>' +
    			'</li>'
    			);
    }

    function inputValue(e){
    	var test = e.getAttribute('id');
    	var test2 = e.getAttribute('title');
     	$("#tono_box").collapse('hide');
     	$('#msg_content').focus();
    	$('#searchInput').val(test2);
     	$('#input_tono').val(test);
    }