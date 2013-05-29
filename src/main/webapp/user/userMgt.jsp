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
		error_msg = $("#error_msg"), allFields = $([]).add(username).add(password)
				.add(roleId);
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
							//allFields.removeClass( "ui-state-error" );
							//	tips.removeClass("ui-state-highlight");
							tips.html("");
						
							/* var url;
							if(userId==""||userId==0||userId=="0"){
								url='userMgtAction!add';//add
							}else{
								url='userMgtAction!modify';//modify
							} */
							$.ajax({
								type : 'post',
								url : url,
								dataType : 'json',
								data : $("#userForm").serialize(),
								success : function(json) {
									//alert(json.groups.length);
									//alert(json['fieldErrors'].size());
									//	alert(eval(json).fieldErros);
									var flag = true;
									$.each(json.actionMessages, function(index,
											obj) {
										$("#error_msg").html(obj);
										flag = false;
									});
									$.each(json.fieldErrors, function(index,
											obj) {
										$(
												"#error_"
														+ index.replace(".",
																"_").replace(
																".", "_"))
												.html(obj[0]);
										flag = false;

									});
									if (flag) {
										$("#dialog-form").dialog("close");
										$('.right_content').load(
												"userMgtAction!findAll");

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
					//	roleId.val(0);
						tips.html("");
					}
				});
		var url;
		$("#create-user").bind('click', function(e) {
			e.preventDefault();
			url='userMgtAction!add';//add
			$("#dialog-form").dialog("open");
		});
		$("a[name='editLink']").bind('click', function(e) {
			e.preventDefault();
			var thisHref= $(this).attr('href');
			//alert(thisHref);
			 $.getJSON(thisHref,function(data){  
				$("#dialog-form").dialog("open");
				$("#user\\.id").val(data.user.id);
				$("#user\\.username").val(data.user.username);
				$("#user\\.password").val(data.user.password);
				$("#user\\.role\\.id").val(data.user.role.id);
				$("#user\\.password").bind('focus',function(e){
					$("#user\\.password").val("");
				});
				
			 });
			
		});
	});
	$(document).ready(function() {
		$('.ask').jConfirmLoadAction();
		$('a').jLoadAction();
	});

	function batchDelete() {
		var flag = false;
		var userIds = "";
		var checkbox = document.getElementsByTagName("checkbox");
		for (i = 0; i < checkbox.length; i++) {
			if (checkbox[i].checked) {
				userIds += "," + checkbox[i].value;
				flag = true;
			}
		}
		if (flag = false) {
			alert("至少选择一条记录！");
			return false;
		}

	}

	function blurPassword(obj){
		var val = obj.value;
	}
</script>
<style>

/* body { font-size: 62.5%; } */
/* label,input,select,span {
	display: inline;
} */
input.text {
	margin-bottom: 12px;
	/* width: 95%; */
	padding: .4em;
}

option.text {
	margin-bottom: 12px;
	/* width: 95%; */
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

/*div#users-contain {
	width: 350px;
	margin: 20px 0;
}

 div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td,div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
} */
</style>
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
				<!--   <th scope="col" class="rounded">密码</th> -->
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
								<input type="checkbox" name="userId" value="${user.id }"
									disabled="disabled" />
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="userId" value="${user.id }" />
							</c:otherwise>
						</c:choose></td>
						<td>
						${status.index+1 }
					</td>
					<td>${user.username }</td>
					<%-- <td>${user.password }</td> --%>
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
	<a href="#" class="bt_red" onclick="batchDelete()"><span
		class="bt_red_lft"></span><strong>删除</strong><span class="bt_red_r"></span></a>
	<p:pages pageNo="${pageNo }" total="${pages }" />
	<div id="dialog-form" title="用户信息">
		<form id="userForm">
			<fieldset>
				<dl>
					<dd>
						<span style="color: red" id="error_msg"></span>
						<input type="hidden" name="user.id" id="user.id" />
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
							class="text ui-widget-content ui-corner-all"  /> <font
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


</body>
</html>