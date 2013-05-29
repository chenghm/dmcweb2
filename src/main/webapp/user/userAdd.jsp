<?xml version="1.0" encoding="utf-8" ?>
<%@ taglib prefix="p" uri="/WEB-INF/page.tld"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/niceforms.js"></script>
<link rel="stylesheet" type="text/css" media="all"
	href="${pageContext.request.contextPath}/resources/css/niceforms-default2.css" />
<script type="text/javascript">
/* window.onbeforeunload = confirmExit;
function confirmExit(){
    $(".right_content", window.parent.document).load("/userMgtAction!findAll");
   window.close();
} */
	$(document).ready(function() {

		$("form").submit(function(e) {
			//$("window").unbind("beforeunload");
			//alert($(".right_content", window.parent.document));
			$(".right_content", window.parent.parent.document).load("/userMgtAction!findAll");
			
		});

		$("#cancelBtn").click(function() {
			window.close();
		});

	});
</script>
<title>用戶添加</title>
</head>
<body>
	<div id="container">
		<form action="userMgtAction!add" method="post" class="niceform"
			id="userForm">
			<fieldset>
				<legend>用户信息</legend>
				<dl>
					<font color="red">${actionMessages[0] }</font>

				</dl>
				<dl>

					<dt>
						<label for="user.username">用户名:</label>
					</dt>
					<dd>
						<input type="text" name="user.username" id="user.username"
							size="32" maxlength="128" value="${user.username }" /> <font
							color="red">*${errors['user.username'][0] }</font>


					</dd>
				</dl>
				<dl>
					<dt>
						<label for="user.password">密码:</label>
					</dt>
					<dd>
						<input type="password" name="user.password" id="user.password"
							size="32" maxlength="32" /> <font color="red">*${errors['user.password'][0]}</font>
					</dd>
				</dl>
				<dl>
					<dt>
						<label for="user.role.id">角色:</label>
					</dt>
					<dd>

						<select size="1" name="user.role.id" id="user.role.id">
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
						</select> <font color="red">*${errors['user.role.id'][0]}</font>
					</dd>
				</dl>

			</fieldset>
			<fieldset class="action">
				<input type="submit" value="保存" id="saveBtn" /> <input type="button"
					name="cancel" id="cancelBtn" value="取消" />
			</fieldset>
		</form>
	</div>
</body>
</html>