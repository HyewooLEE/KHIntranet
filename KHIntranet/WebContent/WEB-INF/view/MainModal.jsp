<%@ page contentType="text/html; charset=UTF-8"%>
<div id="modifyModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="테스트정보 등록" aria-describedby="테스트 모달">                  
   <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      <form action="${pageContext.request.contextPath}/profileModifyform.do" method="post">
         <div class="modal-header" style="border:0px;">
            <h4 class="modal-title">비밀번호 확인</h4>
            <button type="button" class="close" data-dismiss="modal">×</button>
         </div>
         <div class="modal-body" style="text-align:center;">
         	<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
	         		<input class="form-control" type="password" name="password" placeholder="비밀번호를 입력해주세요">
				</div>         	
				<div class="col-md-2"></div>
         	</div>
         </div>
         <div class="modal-footer" style="border:0px;">
         	<button type="submit" class="btn btn-primary" >확인</button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="return">닫기</button>
         </div> 
         </form>                    
      </div>
   </div>
</div>
