

$(function() {
		$('#comentBtn').click(function() {
					/*		var photo_no = ${article.photo_no};*/
							var writer = $("#writer").val();
							var password = $("#password").val();
							var photo_content = $("#photo_content").val();

							if (photo_content == "") {
								alert("내용을 입력하세요.");
								return;
							}

							$.ajax({
										url : "comment.ajax", //commandAjaxURI.properties 등록한 URL
										type : "post",
										data : {
											writer : writer,
											password : password,
											photo_content : photo_content,
											photo_no : photo_no
										},
										success : function(data) {
											$("#comment")
													.append(
															"<div class='col-md-12'>"+
															"<p>" +
															"<b>" +
															writer +
															"</b>&nbsp;" +
															"-"+
															"&nbsp;<span>"+
															photo_content+
															"</span>"+
															"</p>"+
															"</div>");
											$("#photo_content").val("");
										},
										error : function(e) {
											alert(e.responseText);
										}
									});
						});
	});