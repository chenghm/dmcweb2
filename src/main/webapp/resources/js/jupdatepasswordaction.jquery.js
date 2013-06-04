(function($) {

	jQuery.fn.jUpdatePasswordAction = function(options) {
		var allFields = $([]).add($("#currentPassword")).add($("#newPassword"))
				.add($("#confirmPassword"));
		var tips = $([]).add($("#error_current_password")).add(
				$("#error_new_password")).add($("#error_confirm_password"));

		$("#password-dialog").dialog(
				{
					autoOpen : false,
					height : 600,
					width : 650,
					modal : true,
					buttons : {
						"确认" : function() {
							tips.html("");
							$.ajax({
								type : 'post',
								url : "userMgtAction!modifyPassword",
								dataType : 'json',
								data : $("#password-form").serialize(),
								success : function(json) {
									if (json.actionStatus=="success") {
										
										$("#password-dialog").dialog("close");
										$("#message-content").html("修改密码成功！");
										$("#message-dialog").dialog("open");
									}else
									$.each(json.fieldErrors, function(index,
											obj) {
										$("#" + index).html(obj[0]);
									});
								},
								error : function(response, status, xhr) {
									$("#password-dialog").dialog("close");
									alert("发生错误,请重试:"+response.responseText);
									
									
									/*if (status == "timeout") {

										$("#dialog-form").empty().html(
												'<p>Plese Try Again</p>');
									} else if (status == "error") {
										// for Page Not
										// Found Invalid
										// URL
										if (xhr == "Not Found") {

										}
										// for server
										// error
										// which is from
										// controller
										if (xhr == "Internal Server Error") {

										}
									}*/
								}
							});

						},
						取消 : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
						allFields.val("");
						tips.html("");
					}
				});
		$("#update-password").bind('click', function(e) {
			e.preventDefault();
			$("#password-dialog").dialog("open");
		});
		$("#message-dialog").dialog({
			autoOpen : false,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});
	};

})(jQuery);