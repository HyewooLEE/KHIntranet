	function chkAttendYForm(){
		
		var conURL = confirm("해당 결재건을 승인 하시겠습니까?");
		if(conURL == true){
			document.formAttend.submit();
		}
		else{
			return false;
		}
	}
	
	function chkAttendNForm(atd_no,atd_emp_no){
		
		var atd_status_ny = "반려";
		var conURL = confirm("해당 결재건을 반려 하시겠습니까?");
		
		if(conURL == true){
			location.replace("/KHIntranet/adminAttendReturnPro.do?atd_no="+atd_no+"&atd_receiver="+atd_emp_no+"&atd_status_ny="+atd_status_ny+"");
		}else if(conURL == false){
			return false;
		}
		
		/* if(conURL == true){
			var inputString = prompt('해당 결재건의 bu반려의 사유', ''); 
			if(inputString ==true){
				location.replace("/KHIntranet/adminAttendReturnPro.do?atd_no="+atd_no+"&atd_receiver="+atd_emp_no+"&atd_status_ny="+atd_status_ny+"");
			}
		}
		else if(conURL == false){
			return false;
		} */
		
	}
	
	function updatePurYForm(){
		
		var conURL = confirm("해당 결재건을 승인 하시겠습니까?");
		
		if(conURL == true){
			document.formPurchase.submit();
		}
		else if(conURL == false){
			return false;
		}
		
	}
	
	function updatePurNForm(pur_no,pur_emp_no){
		
		var pur_status_ny = "반려";
		var conURL = confirm("해당 결재건을 반려 하시겠습니까?");
		
		if(conURL == true){
			location.replace("/KHIntranet/adminPurchaseReturnPro.do?pur_no="+pur_no+"&pur_receiver="+pur_emp_no+"&pur_status_ny="+pur_status_ny+"");
		}else if(conURL == false){
			return false;
		}
		
	}
	
	
	