		$(function(){  
         $('#datepicker1,#datepicker2').datepicker({
        	  format: "yyyy-mm-dd",
              autoclose: true,
              todayHighlight: true
          });
       });
    
       $(function(){
          $("#btn1").click(function(){
             var chooser = document.selectEmpForm.chooser;
             var selector = document.selectEmpForm.selector;                          
             
             if(chooser.value != null && chooser.value != "") {
                var cinput = chooser.value;
                var sinput = selector.value;
                var chtml = $("#chooser  option:selected").text();
                
                $("#selector").append("<option value=' "+ cinput +"'>"+ chtml +"</option>");
                $("#chooser > option[value='"+ cinput +"']").remove();
                
             } else {
                alert("결재라인을 지정해주세요.");
             }
          });
       });
   
       
       $(function(){
          $("#btn2").click(function(){
             var chooser = document.selectEmpForm.chooser;
             var selector = document.selectEmpForm.selector;            
             
             if(selector.value != null && selector.value != "") {
                var cinput = chooser.value;
                var sinput = selector.value;
                var shtml = $("#selector  option:selected").text();
                
                $("#chooser").append("<option value='"+ sinput +"'>"+ shtml +"</option>");
                $("#selector > option[value='"+ sinput +"']").remove();              
                 
             } else {
                alert("결재라인을 지정해주세요.");
             }
          });
       });
       
       
      $(function(){                        
          $('#submit').on('click',function(){
            var selector = document.selectEmpForm.selector; 
            document.getElementById("readOnlyInput_hide").value= "";
            document.getElementById("readOnlyInput").value = "";
            
               for(var i=0; i<selector.length; i++){                      
                  var selectorValue_hide = document.selectEmpForm.selector[i].value;
                  var selectorValue_show = document.selectEmpForm.selector[i].text; 
                  
                  document.getElementById("readOnlyInput_hide").value += selectorValue_hide; //value사원번호
                  document.getElementById("readOnlyInput").value += selectorValue_show;   //value사원이름
                  
                  if(i != selector.length -1) {
                     var inputhidden_hide = document.getElementById("readOnlyInput_hide").value;
                     var inputhidden_show = document.getElementById("readOnlyInput").value;
                     
                     document.getElementById("readOnlyInput_hide").value +=  ",";
                     document.getElementById("readOnlyInput").value += ",";       
                  }
                  
                  $("#myModal").modal('hide');
               }               
               
          });          
       });