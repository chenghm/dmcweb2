<?xml version="1.0" encoding="utf-8" ?>
<%@ taglib prefix="p" uri="/WEB-INF/page.tld"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/start/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> -->
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/basic.js" /> --%>

<script type="text/javascript">
	$(function() {
		var username = $("#user\\.username"), password = $("#user\\.password"), roleId = $("#user\\.role\\.id"), error_username = $("#error_user_username"), error_password = $("#error_user_password"), error_roleId = $("#error_user_role_id");
		error_msg = $("#error_msg"), allFields = $([]).add(username).add(
				password).add(roleId);
		tips = $([]).add(error_username).add(error_password).add(error_roleId)
				.add(error_msg);

		$("#dialog-form").dialog(
				{
					autoOpen : false,
					height : 600,
					width : 650,
					modal : true,
					buttons : {
						"保存" : function() {
							tips.html("");
							$.ajax({
								type : 'post',
								url : url,
								dataType : 'json',
								data : $("#userForm").serialize(),
								success : function(json) {
									if (json.actionStatus=="success") {
										$("#dialog-form").dialog("close");
										$('.right_content').load(
												"userMgtAction!findAll");

									}else {
									$.each(json.actionMessages, function(index,
											obj) {
										$("#error_msg").html(obj);
									});
									$.each(json.fieldErrors, function(index,
											obj) {
												$("#"+index).html(obj);

									});
									}
									

								},
								error : function(response, status, xhr) {
									if (status == "timeout") {

										$("#dialog-form").empty().html(
												'<p>Plese Try Again</p>');
									} else if (status == "error") {
										//for Page Not Found Invalid URL
										if (xhr == "Not Found") {

										}
										//for server error which is from controller
										if (xhr == "Internal Server Error") {

										}
									}
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
		var url;
		$("#create-user").bind('click', function(e) {
			e.preventDefault();
			url = 'userMgtAction!add';//add
			$("#dialog-form").dialog("open");
		});
		$("a[name='editLink']").bind('click', function(e) {
			alert($("#elementOnLoginPage").length);
			e.preventDefault();
			url = 'userMgtAction!modify';//modify
			var thisHref = $(this).attr('href');
			$.getJSON(thisHref, function(data,status,xhr) {
				alert(status);
				$("#dialog-form").dialog("open");
				$("#user\\.id").val(data.user.id);
				$("#user\\.username").val(data.user.username);
				$("#user\\.password").val(data.user.password);
				$("#user\\.role\\.id").val(data.user.role.id);
				$("#user\\.password").bind('focus', function(e) {
					$("#user\\.password").val("");
				});

			});

		});

		$("#dialog-message").dialog({
			autoOpen : false,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});

		$("#delete-user").bind(
				'click',
				function(e) {
					e.preventDefault();
					var userIds = "";
					$("input[name='checkbox']:checked").each(function(i) {
						userIds += "," + $(this).val();
					});
					if (userIds == "") {
						$("#dialog-message").dialog("open");
					} else {
						//alert(userIds.substr(1));
						//$(this).attr("href","userMgtAction!batchDelete?userIds="+userIds.substr(1));
						var theOptions = jQuery.extend({
							question : "Are You Sure ?",
							yesAnswer : "Yes",
							cancelAnswer : "Cancel"
						});

						//thisHref	= $(this).attr('href');

						if ($(this).next('.question').length <= 0)
							$(this).after(
									'<div class="question" style="right:320px" >'
											+ theOptions.question
											+ '<br/> <span class="yes">'
											+ theOptions.yesAnswer
											+ '</span><span class="cancel">'
											+ theOptions.cancelAnswer
											+ '</span></div>');

						$(this).next('.question').animate({
							opacity : 1
						}, 300);

						$('.yes').bind(
								'click',
								function() {
									$('.right_content').load(
											"userMgtAction!batchDelete?userIds="
													+ userIds.substr(1));
								});

						$('.cancel').bind(
								'click',
								function() {
									$(this).parents('.question').fadeOut(300,
											function() {
												$(this).remove();
											});
								});

					}
				});
	});
	$(document).ready(function() {
		$('.ask').jConfirmLoadAction();
		$('a').jLoadAction();

		//全局的ajax访问，处理ajax清求时sesion超时
        $.ajaxSetup({ 
            contentType:"application/x-www-form-urlencoded;charset=utf-8", 
            complete:function(XMLHttpRequest,textStatus){ 
                    var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，
alert(sessionstatus);
                       if(sessionstatus=="timeout"){ 
                                //如果超时就处理 ，指定要跳转的页面
                                alert("超时");
                                        window.top.location.replace("login.jsp"); 
                                } 
                     } 
          });
	});
</script>
<title>用戶管理</title>
</head>
<body>
	<h2>用户信息</h2>


	<table id="rounded-corner" summary="2007 Major IT Companies' Profit">
		<thead>
			<tr>
				<th scope="col" class="rounded-company"></th>
				<th scope="col" class="rounded">序列</th>
				<th scope="col" class="rounded">用户名</th>
				<th scope="col" class="rounded">角色</th>
				<th scope="col" class="rounded">编辑</th>
				<th scope="col" class="rounded-q4">删除</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<td colspan="5" class="rounded-foot-left"><em></em></td>
				<td class="rounded-foot-right">&nbsp;</td>

			</tr>
		</tfoot>
		<tbody>
			<c:forEach items="${users}" var="user" varStatus="status">



				<tr>
					<sec:authentication property="principal.id" var="userId" />
					<c:set value="${user.id==userId}" var="disabled" />
					<td><c:choose>
							<c:when test="${disabled }">
								<input type="checkbox" name="checkbox" value="${user.id }"
									disabled="disabled" />
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="checkbox" value="${user.id }" />
							</c:otherwise>
						</c:choose></td>
					<td>${status.index+1 }</td>
					<td>${user.username }</td>
					<td>${user.role.descn }</td>

					<td><a
						href="${pageContext.request.contextPath}/userMgtAction!initModify?userId=${user.id}"
						name="editLink"><img
							src="${pageContext.request.contextPath}/resources/img/user_edit.png"
							alt="" title="" border="0" /></a></td>
					<td><a
						href="${pageContext.request.contextPath}/userMgtAction!delete?userId=${user.id}"
						class="ask"
						style="${disabled?'pointer-events: none; cursor: default;':''}"><img
							src="${pageContext.request.contextPath}/resources/img/trash.png"
							alt="" title="" border="0" /></a></td>
				</tr>

			</c:forEach>

		</tbody>
	</table>

	<a href="" class="bt_green" id="create-user"><span
		class="bt_green_lft"></span><strong>新增</strong><span
		class="bt_green_r"></span></a>
	<a href="" class="bt_red" id="delete-user"><span class="bt_red_lft"></span><strong>删除</strong><span
		class="bt_red_r"></span></a>

	<p:pages pageNo="${pageNo }" total="${pages }" />
	<div id="dialog-form" title="用户信息">
		<form id="userForm">
			<fieldset>
				<dl>
					<dd>
						<span style="color: red" id="error_msg"></span> <input
							type="hidden" name="user.id" id="user.id" />
					</dd>
				</dl>
				<dl>

					<dt>
						<label for="user.username">用户名:</label>
					</dt>
					<dd>
						<input type="text" name="user.username" id="user.username"
							size="32" maxlength="128" value="${user.username }"
							class="text ui-widget-content ui-corner-all" /> <font
							color="red">*</font> <span style="color: red"
							id="error_user_username"></span>
					</dd>
				</dl>
				<dl>
					<dt>
						<label for="user.password">密码:</label>
					</dt>
					<dd>
						<input type="password" name="user.password" id="user.password"
							size="32" maxlength="32"
							class="text ui-widget-content ui-corner-all" /> <font
							color="red">*</font> <span style="color: red"
							id="error_user_password"></span>
					</dd>
				</dl>
				<dl>

					<dt>
						<label for="user.role.id">角色:</label>
					</dt>
					<dd>
						<select size="1" name="user.role.id" id="user.role.id"
							class=" ui-widget-content ui-corner-all">
							<option value="0">-请选择-</option>
							<c:forEach items="${roles }" var="entry">
								<c:choose>
									<c:when test="${user.role.id==entry.key }">
										<option value="${entry.key }" selected="selected">${entry.value
											}</option>
									</c:when>
									<c:otherwise>
										<option value="${entry.key }">${entry.value }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select> <font color="red">*</font> <span style="color: red"
							id="error_user_role_id"></span>
					</dd>
				</dl>
			</fieldset>
		</form>
	</div>
	<div id="dialog-message" title="删除用户">
		<p>
			<span class="ui-icon ui-icon-circle-check"
				style="float: left; margin: 0 7px 50px 0;"></span> 请至少选择一条记录！
		</p>
	</div>

</body>
</html>