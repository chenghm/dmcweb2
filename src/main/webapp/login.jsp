<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IN ADMIN PANEL | Powered by INDEZINER</title>

<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/ddaccordion.js"></script>
<script type="text/javascript">
	ddaccordion.init({
		headerclass : "submenuheader", //Shared CSS class name of headers group
		contentclass : "submenu", //Shared CSS class name of contents group
		revealtype : "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
		mouseoverdelay : 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
		collapseprev : true, //Collapse previous content (so only one open at any time)? true/false 
		defaultexpanded : [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
		onemustopen : false, //Specify whether at least one header should be open always (so never all headers closed)
		animatedefault : false, //Should contents open by default be animated into view?
		persiststate : true, //persist state of opened contents within browser session?
		toggleclass : [ "", "" ], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
		togglehtml : [ "suffix",
				"<img src='images/plus.gif' class='statusicon' />",
				"<img src='images/minus.gif' class='statusicon' />" ], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
		animatespeed : "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
		oninit : function(headers, expandedindices) { //custom code to run when headers have initalized
			//do nothing
		},
		onopenclose : function(header, index, state, isuseractivated) { //custom code to run whenever a header is opened or closed
			//do nothing
		}
	});
	/* if(self.location.href != top.location.href){ top.location.href = self.location.href; } */
</script>

<script type="text/javascript"
	src="resources/js/jconfirmaction.jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.ask').jConfirmAction();
	});
</script>

<script language="javascript" type="text/javascript"
	src="resources/js/niceforms.js"></script>
<link rel="stylesheet" type="text/css" media="all"
	href="resources/css/niceforms-default.css" />

</head>
<body>
	<div id="main_container">

		<div class="header_login">
			<div class="logo">
				<a href="#"><img src="resources/img/logo.gif" alt="" title=""
					border="0" /></a>
			</div>

		</div>


		<div class="login_form">

			<h3>Admin Panel Login</h3>
			<form action="${pageContext.request.contextPath}/login" method="post"
				class="niceform">

				<c:if test="${param.error}">
					<div class="error_box" style="height: 5px">
						Failed to login.
						<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">Reason: <c:out
								value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
						</c:if>
					</div>
				</c:if>
				<c:if test="${param.expired}">
					<div class="warning_box">You have been forcibly logged out
						due to multiple sessions on the same account (only one active
						session per user is allowed).</div>
				</c:if>
				<c:if test="${param.logout}">
					<div class="valid_box">You have been logged out.</div>
				</c:if>
				<fieldset>
					<dl>
						<dt>
							<label for="username">Username:</label>
						</dt>
						<dd>
							<input type="text" id="username" name="username" size="54" />
						</dd>
					</dl>
					<dl>
						<dt>
							<label for="password">Password:</label>
						</dt>
						<dd>
							<input type="password" name="password" id="password" size="54" />
						</dd>
					</dl>

					<dl class="submit">
						<input type="submit" name="submit" id="submit" value="Enter" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" name="reset" id="reset" value="Reset" />
					</dl>

				</fieldset>

			</form>
		</div>



		<div class="footer_login">

			<div class="left_footer_login">
				IN ADMIN PANEL | Powered by <a href="http://indeziner.com">INDEZINER</a>
			</div>
			<div class="right_footer_login">
				<a href="http://indeziner.com"><img
					src="resources/img/indeziner_logo.gif" alt="" title="" border="0" /></a>
			</div>

		</div>

	</div>
	<input type="hidden" id="elementOnLoginPage" value="1" />
</body>
</html>