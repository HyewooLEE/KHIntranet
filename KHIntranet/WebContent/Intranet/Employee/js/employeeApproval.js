
function update(emp_no,emp_name,dept_no1,position_rank1,sal1){
			var dept_no = document.getElementById(dept_no1).selectedIndex;
			var position_rank = document.getElementById(position_rank1).selectedIndex;
			var sal = document.getElementById(sal1).value;
			var dept_name = document.getElementById(dept_no1).options[dept_no].text;

			var position_name = document.getElementById(position_rank1).options[position_rank].text;
			
			if(dept_no==0){
				alert("부서를 선택해주세요");
				return false;
			}
			
			if(position_rank==0){
				alert("직급을 선택해주세요");
				return false;
			}
			
			
			if(sal==0){
				alert("급여를 선택해주세요");
				return false;
			}
			
			if(confirm(emp_name +"가입 승인하시는건가요?") == true){    //확인
			      $('.btn_del').unbind('click');
			  }else{   //취소
			      return false;
			  }
			
			$.ajax({
				
				url : "/KHIntranet/EmployeeUpdate.ajax",   /* web.xml에 지정 */
				type : "post",
				data : {emp_no : emp_no,
						   dept_no : dept_no,
						   position_rank : position_rank,
						   sal : sal,
						   position_name : position_name,
						   dept_name : dept_name
							},

				success : function(test){  /* 성공시 */
					location.reload(true);
					var parsed = JSON.parse(test);
					var result = parsed.result;

				},
				error: function(xhr, textStatus, errorThrown) {
					//$("div").html("<div>" + textStatus + " (HTTP-" + xhr.status + " / " + errorThrown + ")</div>" );
				alert(xhr.status + errorThrown );
				}
			});

		};
		
		
		//가입 거절
		function empDelete(emp_no,emp_name){
			
			if(confirm(emp_name+"님의 가입을 거절하시는건가요?") == true){    //확인
			      $('.btn_del').unbind('click');
			  }else{   //취소
			      return false;
			  }
			
$.ajax({
				
				url : "/KHIntranet/EmployeeDelect.ajax",   /* web.xml에 지정 */
				type : "post",
				data : {emp_no : emp_no	},

				success : function(test){  /* 성공시 */
					location.reload(true);
					var parsed = JSON.parse(test);
					var result = parsed.result;

				},
				error: function(xhr, textStatus, errorThrown) {
					//$("div").html("<div>" + textStatus + " (HTTP-" + xhr.status + " / " + errorThrown + ")</div>" );
				alert(xhr.status + errorThrown );
				}
			});

		};	
