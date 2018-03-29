function changeId(){
		
		var userinput = eval("document.userinput");		
		
		if(userinput.emp_id.value =="")  {//userinput안의 id의 value값 다 name이다.
			return;
		}else{
			//url과 사용자 입력 id를 조합합니다.
			var emp_id = userinput.emp_id.value; //name= id의값
			
				var pat = /[ㄱ-ㅎ||ㅏ-ㅣ||가-힣]/i    //한글 안되게
				 var st = "한글 ㄴㄴ"
				if(emp_id.match(pat)){ //match 비교 하는 것 
					document.getElementById("modal_message").innerHTML="";
					document.getElementById("modal_message").innerHTML="한글ㄴㄴ";
					$('#myModal').modal('show');
					userinput.emp_id.value ="";  //값을 다시 없애줌
					userinput.emp_id.focus();
					return;
				}
			
	$.ajax({
		url : "/KHIntranet/confind.do",   /* web.xml에 지정 */
    	dataType : "xml",
		type : "post",
		data : {emp_id : emp_id	},

		success : function(test){  /* 성공시 */
			var title = $(test).find("title").text();

			if(title == 0){
				
				document.getElementById("idfind").innerHTML="멋진 아이디입니다."
				document.getElementById("idfind").style="color : blue"
				document.userinput.idcb.value="x";
			}else{
				
				document.getElementById("idfind").innerHTML="존재하는 아이디입니다."
				document.getElementById("idfind").style="color : red"
				document.userinput.idcb.value="";
			} 
		},
		error: function(xhr, textStatus, errorThrown) {
			//$("div").html("<div>" + textStatus + " (HTTP-" + xhr.status + " / " + errorThrown + ")</div>" );
		alert(xhr.status + errorThrown );
		}
		
	});
}
}

	function checkIt(){
		var userinput = eval("document.userinput");
		
		if(!userinput.emp_id.value){
			document.getElementById("modal_message").innerHTML="";
			document.getElementById("modal_message").innerHTML="ID를 입력하세요";
			$('#myModal').modal('show');
			return false;
		}
		
		if(!userinput.emp_pw1.value){
			document.getElementById("modal_message").innerHTML="";
			document.getElementById("modal_message").innerHTML="비밀번호를 입력하세요";
			$('#myModal').modal('show');
			return false;
		}
		
		if(userinput.emp_pw1.value != userinput.emp_pw2.value){
			document.getElementById("modal_message").innerHTML="";
			document.getElementById("modal_message").innerHTML="비밀번호를 동일하게 입력하세요";
			$('#myModal').modal('show');
			return false;
		}
		if(!userinput.emp_name.value){
			document.getElementById("modal_message").innerHTML="";
			document.getElementById("modal_message").innerHTML="사용자 이름을 입력하세요";
			$('#myModal').modal('show');
			return false;
		}
 		if(!userinput.emp_jumin1.value){
 			document.getElementById("modal_message").innerHTML="";
			document.getElementById("modal_message").innerHTML="생년월일을 입력하세요";
			$('#myModal').modal('show');
			return false;
		}
 		
 		if(!userinput.emp_email.value){
 			document.getElementById("modal_message").innerHTML="";
			document.getElementById("modal_message").innerHTML="이메일을 입력하세요";
			$('#myModal').modal('show');
			return false;
		}
 		
 		if(!userinput.emp_pn.value){
 			document.getElementById("modal_message").innerHTML="";
			document.getElementById("modal_message").innerHTML="전화번호를 입력하세요";
			$('#myModal').modal('show');
			return false;
		}

		if(!userinput.idcb.value){
			document.getElementById("modal_message").innerHTML="";
			document.getElementById("modal_message").innerHTML="중복 검사를 하세요...";
			$('#myModal').modal('show');
			return false;
		} 
		
		if(!userinput.emp_addr1.value){
			document.getElementById("modal_message").innerHTML="";
			document.getElementById("modal_message").innerHTML="우편 번호를 입력하세요";
			$('#myModal').modal('show');
			return false;
		}
		
		if(!userinput.emp_addr3.value){
			document.getElementById("modal_message").innerHTML="";
			document.getElementById("modal_message").innerHTML="상세 주소를 입력하세요";
			$('#myModal').modal('show');
			return false;
		} 

	}
	
function check(){
	if(!document.myform.emp_id.value){
		document.getElementById("modal_message").innerHTML="";
		document.getElementById("modal_message").innerHTML="아이디를 입력하지 않으셨습니다.";
		$('#myModal').modal('show');
		document.myform.emp_id.focus();
		return false;
	}
	if(!document.myform.emp_pw.value){
		document.getElementById("modal_message").innerHTML="";
		document.getElementById("modal_message").innerHTML="비밀번호를 입력하지 않으셨습니다.";
		$('#myModal').modal('show');
		document.myform.emp_pw.focus();
		return false;
	}
}

//주소
function kume1() {
    new daum.Postcode({
        oncomplete: function(data) {
           
        	  // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = data.address; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 기본 주소가 도로명 타입일때 조합한다.
            if(data.addressType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample3_postcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('sample3_address').value = fullAddr;

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            return false;

            // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
            document.body.scrollTop = currentScroll;
        },
        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
        onresize : function(size) {
            element_wrap.style.height = size.height+'px';
        },
        width : '100%',
        height : '100%'
    }).open();
    
}



//로그인시 
function logingo(check){
	if(check==1){
		document.getElementById("modal_message").innerHTML="";
		document.getElementById("modal_message").innerHTML="가입승인을 받지 못했습니다.";
		$('#myModal').modal('show');
		
		return false;
	}else if(check==0){
		document.getElementById("modal_message").innerHTML="";
		document.getElementById("modal_message").innerHTML="비밀번호가 맞지 않습니다.";
		$('#myModal').modal('show');
		return false;
	}else{
		document.getElementById("modal_message").innerHTML="";
		document.getElementById("modal_message").innerHTML="아이디가 맞지 않습니다..";
		$('#myModal').modal('show');
		return false;
	}

};

//Caps Lock
function caps_lock(e) {
    var keyCode = 0;
    var shiftKey = false;
    keyCode = e.keyCode;
    shiftKey = e.shiftKey;
    if (((keyCode >= 65 && keyCode <= 90) && !shiftKey)
            || ((keyCode >= 97 && keyCode <= 122) && shiftKey)) {
        show_caps_lock();
        setTimeout("hide_caps_lock()", 3500);
    } else {
        hide_caps_lock();
    }
}

function show_caps_lock() {
 $("#capslock").show();
}

function hide_caps_lock() {
 $("#capslock").hide();
}

