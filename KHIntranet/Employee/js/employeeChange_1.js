$(document).ready(function(){
   
    $("#tabEmp").mouseover(function(){
        $("#tabEmp").css("background-color", "#007bff");
        $("#tabEmp").css("color", "#FFF");
    });
    $("#tabEmp").mouseout(function(){
        $("#tabEmp").css("background-color", "#FFF");
        $("#tabEmp").css("color", "#000");
    });
    
    $("#tabDept").mouseover(function(){
        $("#tabDept").css("background-color", "#007bff");
        $("#tabDept").css("color", "#FFF");
    });
    $("#tabDept").mouseout(function(){
        $("#tabDept").css("background-color", "#FFF");
        $("#tabDept").css("color", "#000");
    });
    
    $("#tabPosition").mouseover(function(){
        $("#tabPosition").css("background-color", "#007bff");
        $("#tabPosition").css("color", "#FFF");
    });
    $("#tabPosition").mouseout(function(){
        $("#tabPosition").css("background-color", "#FFF");
        $("#tabPosition").css("color", "#000");
    });
    
});


//페이지 돌아왔을때
function showTab(i){
   if(i==1){
      tableEmp.style.display = "none";
       tileFilter.style.display = "none";
       tableDept.style.display = "inline";
       tablePosi.style.display = "none";
       deptUp.style.display = "none";
       positionUp.style.display = "none";
   }else if(i==2){
      tableEmp.style.display = "none";
       tileFilter.style.display = "none";
       tableDept.style.display = "none";
       tablePosi.style.display = "inline";
       deptUp.style.display = "none";
       positionUp.style.display = "none";
   }else{
      tableEmp.style.display = "inline";
       tileFilter.style.display = "inline";
       tableDept.style.display = "none";
       tablePosi.style.display = "none";
       deptUp.style.display = "none";
       positionUp.style.display = "none";
   }
}


$(function(){ 
   
    //회원관리
    $('#tabEmp').on('click',function(){ 
       tableEmp.style.display = "inline";
       tileFilter.style.display = "inline";
       tableDept.style.display = "none";
       tablePosi.style.display = "none";
       deptUp.style.display = "none";
       positionUp.style.display = "none";
    });
     
    //부서관리
    $('#tabDept').on('click',function(){ 
       tableEmp.style.display = "none";
       tileFilter.style.display = "none";
       tableDept.style.display = "inline";
       tablePosi.style.display = "none";
       deptUp.style.display = "none";
       positionUp.style.display = "none";
    });
   
    //직급관리
    $('#tabPosition').on('click',function(){ 
       tableEmp.style.display = "none";
       tileFilter.style.display = "none";
       tableDept.style.display = "none";
       tablePosi.style.display = "inline";
       deptUp.style.display = "none";
       positionUp.style.display = "none";
    });
   
});



function deleteDept(){

      var checkboxValues = [];
   
       $("input[name='deptCheck']:checked").each(function(i) {
           checkboxValues.push($(this).val());
       });
       
       $('#deptCheckPlus').append(
          '<input type="hidden" name="checkbox" value="'+checkboxValues+'" />'  +
          '<input type="hidden" name="check" value="1" />'
       );
 
}

/*function deletePosition(){
    var checkboxValues = [];
 
    $("input[name='positionCheck']:checked").each(function(i) {
        checkboxValues.push($(this).val());
    });
     
     $('#positionPlus').append(
        '<input type="hidden" name="checkbox" value="'+checkboxValues+'" />'  +
        '<input type="hidden" name="check" value="2" />'
     );

}*/




function deletePosition(){
   var checkboxValues = [];
   
    $("input[name='positionCheck']:checked").each(function(i) {
        checkboxValues.push($(this).val());
    });
    
    $('#positionCheckPlus').append(
            '<input type="hidden" name="checkbox" value="'+checkboxValues+'" />'  +
            '<input type="hidden" name="check" value="2" />'
         );
    
}


//checkBox
$(function(){
   $("#selectAll1").click(function(){
   
      if($("#selectAll1").prop("checked")) {
           $(".deptBox > input[type=checkbox]").prop("checked",true);
         } else { 
            $(".deptBox > input[type=checkbox]").prop("checked",false);
            }
      })
      
      $("#selectAll2").click(function(){

      if($("#selectAll2").prop("checked")) {
          $(".positionBox > input[type=checkbox]").prop("checked",true);
         } else { 
          $(".positionBox > input[type=checkbox]").prop("checked",false);
            }
      })
    });


function Dropdown1(){

   selectDocName1.style.display ="inline";
   selectDocName2.style.display ="none";
}

function Dropdown2(){

   selectDocName1.style.display ="none";
   selectDocName2.style.display ="inline";
}


//불러오는 기능 값 가져오기
//부서
function changeDo2(val, text){  //empAllList
   document.getElementById("empAllList").style.display ="none"; 
   var type = "2";
   var Drop_name = document.getElementById("selectDoc1").innerHTML;
   
   document.getElementById("selectDoc2").innerHTML="";
   document.getElementById("selectDoc2").innerHTML=text;
   
   $("#empAllList2 > tr").remove();
   //list로 값 가져 오기
   $.ajax({
      url : "/KHIntranet/EmployeeList.ajax",  //web.xml에 지정
      type : "post",
      data : {
               type : type,
               Drop_name : Drop_name,
               type_no : val
            },
      //성공시
      success : function(data){ 
         
         if(data ==1){
            document.getElementById("empAllList").style.display="table-row-group"
         }

         var parsed = JSON.parse(data);  //JSON으로 값 가져오기
         var result = parsed.result;   //chatListServlet에 있는 result = "{\"result\":[" 
         
         for(var i = 0; i < result.length; i++){
            addList(result[i][0].value, result[i][1].value, result[i][2].value,result[i][3].value,result[i][4].value,result[i][5].value,result[i][6].value,result[i][7].value,result[i][8].value,result[i][9].value,result[i][10].value); //출력
         }
            
      }
   });
}
   
//직급
function changeDo1(val, text){

   document.getElementById("empAllList").style.display ="none"; 
   var type = "1";
   var Drop_name = document.getElementById("selectDoc2").innerHTML;
   
   document.getElementById("selectDoc1").innerHTML="";
   document.getElementById("selectDoc1").innerHTML=text;
   
   $("#empAllList2 > tr").remove();
   //list로 값 가져 오기
   $.ajax({
      url : "/KHIntranet/EmployeeList.ajax", 
      type : "post",
      data : {
               type : type,
               Drop_name : Drop_name,
               type_no : val
            },
      //성공시
      success : function(data){ 
      
         if(data ==1){
            document.getElementById("empAllList").style.display="table-row-group"
         }
         var parsed = JSON.parse(data);
         var result = parsed.result;   
         
         for(var i = 0; i < result.length; i++){
            addList(result[i][0].value, result[i][1].value, result[i][2].value,result[i][3].value,result[i][4].value,result[i][5].value,result[i][6].value,result[i][7].value,result[i][8].value,result[i][9].value,result[i][10].value); //출력
         }
      }
   });
}   


   
   
//보여줄것 view 구현
function addList(Emp_no, Emp_id, Emp_name, Emp_gender, Emp_jumin1, Emp_email, Emp_pn, Dept_name, Position_name, Emp_sal, Emp_date){  //출력시주는 원하는 위치에 생성되게끔 해준다.

    $('#empAllList2').append(
         '<tr>'+
           '<td>'+Emp_no +'</td>'+
           '<td>'+Emp_id +'</td>'+
           '<td>'+Emp_name +'</td>'+
           '<td>'+Emp_gender +'</td>'+
           '<td>'+Emp_email +'</td>'+
           '<td>'+Emp_pn +'</td>'+
           '<td>'+Dept_name +'</td>'+
           '<td>'+Position_name +'</td>'+
           '<td>'+Emp_date +'</td>'+
           '</tr>'
         );  //추가
}
            
//dept 추가 취소 버튼
function deptUpshow(){

   var show = deptUp.style.display;
   if(show == "none"){
   deptUp.style.display ="inline";
   return false;
   }else{
      document.getElementById("dept_no").value ="";
      document.getElementById("dept_name").value="";
      deptUp.style.display ="none";
      return false;
   }
      
   
}

//position 추가 취소 버튼
function positionUpshow(){


   var show = positionUp.style.display;
   if(show == "none"){
      positionUp.style.display ="inline";
      return false;
   }else{
      document.getElementById("position_rank").value ="";
      document.getElementById("position_name").value="";
      positionUp.style.display ="none";
      return false;
   }
}