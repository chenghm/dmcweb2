<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IN ADMIN PANEL | Powered by INDEZINER</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ddaccordion.js"></script>
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
				"<img src='resources/img/plus.gif' class='statusicon' />",
				"<img src='resources/img/minus.gif' class='statusicon' />" ], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
		animatespeed : "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
		oninit : function(headers, expandedindices) { //custom code to run when headers have initalized
			//do nothing
		},
		onopenclose : function(header, index, state, isuseractivated) { //custom code to run whenever a header is opened or closed
			//do nothing
		}
	})
</script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.jclock-1.2.0.js.txt"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jconfirmaction.jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.ask').jConfirmAction();

		//${pageContext.request.contextPath}/logout

		/* $('.ask').click(function(e) {
			alert();
			
			e.preventDefault();
			thisHref	= $(this).attr('href');
			
			if($(this).next('div.question').length <= 0)
				$(this).after('<div class="question">Are you sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
			
			$('.question').animate({opacity: 1}, 300);
			
			$('.yes').live('click', function(){
				window.location = thisHref;
			});
			
			$('.cancel').live('click', function(){
				$(this).parents('div.question').fadeOut(300, function() {
					$(this).remove();
				});
			});
			
		}); */
		
	});



	
</script>
<script type="text/javascript">
	$(function($) {
		$('.jclock').jclock();
	});
</script>

<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/niceforms.js"></script>
<link rel="stylesheet" type="text/css" media="all"
	href="${pageContext.request.contextPath}/resources/css/niceforms-default.css" />

</head>
<body>
	<div id="main_container">

		<div class="header">
			<div class="logo">
				<a href="#"><img src="${pageContext.request.contextPath}/resources/img/logo.gif" alt="" title=""
					border="0" /></a>
			</div>

			<div class="right_header">
				Welcome <sec:authentication property="principal.username" />  | <a
					href="${pageContext.request.contextPath}/logout" class="ask">退出<img src="${pageContext.request.contextPath}/resources/img/user_logout.png" border="0" align="absbottom" /></a>
			</div>
			<div class="jclock"></div>
		</div>

		<div class="main_content">

			<div class="menu">
				<ul>
					<li><a class="current" href="index.html">Admin Home</a></li>
					<li><a href="list.html">Manage Categories<!--[if IE 7]><!--></a>
						<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
						<ul>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
						</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
					<li><a href="login.html">Manage Users<!--[if IE 7]><!--></a> <!--<![endif]-->
						<!--[if lte IE 6]><table><tr><td><![endif]-->
						<ul>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a class="sub1" href="" title="">sublevel2<!--[if IE 7]><!--></a>
								<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
								<ul>
									<li><a href="" title="">sublevel link</a></li>
									<li><a href="" title="">sulevel link</a></li>
									<li><a class="sub2" href="#nogo">sublevel3<!--[if IE 7]><!--></a>
										<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
										<ul>
											<li><a href="#nogo">Third level-1</a></li>
											<li><a href="#nogo">Third level-2</a></li>
											<li><a href="#nogo">Third level-3</a></li>
											<li><a href="#nogo">Third level-4</a></li>
										</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
									<li><a href="" title="">sulevel link</a></li>
								</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>

							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
						</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
					<li><a href="login.html">Manage Orders<!--[if IE 7]><!--></a>
						<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
						<ul>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a class="sub1" href="" title="">sublevel2<!--[if IE 7]><!--></a>
								<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
								<ul>
									<li><a href="" title="">sublevel link</a></li>
									<li><a href="" title="">sulevel link</a></li>
									<li><a class="sub2" href="#nogo">sublevel3<!--[if IE 7]><!--></a>
										<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
										<ul>
											<li><a href="#nogo">Third level-1</a></li>
											<li><a href="#nogo">Third level-2</a></li>
											<li><a href="#nogo">Third level-3</a></li>
											<li><a href="#nogo">Third level-4</a></li>
										</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
									<li><a href="" title="">sulevel link</a></li>
								</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>

							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
						</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
					<li><a href="login.html">Settings<!--[if IE 7]><!--></a> <!--<![endif]-->
						<!--[if lte IE 6]><table><tr><td><![endif]-->
						<ul>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a class="sub1" href="" title="">sublevel2<!--[if IE 7]><!--></a>
								<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
								<ul>
									<li><a href="" title="">sublevel link</a></li>
									<li><a href="" title="">sulevel link</a></li>
									<li><a class="sub2" href="#nogo">sublevel3<!--[if IE 7]><!--></a>
										<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
										<ul>
											<li><a href="#nogo">Third level-1</a></li>
											<li><a href="#nogo">Third level-2</a></li>
											<li><a href="#nogo">Third level-3</a></li>
											<li><a href="#nogo">Third level-4</a></li>
										</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
									<li><a href="" title="">sulevel link</a></li>
								</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>

							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
						</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
					<li><a href="">Templates</a></li>
					<li><a href="">Custom details</a></li>
					<li><a href="">Contact</a></li>
				</ul>
			</div>




			<div class="center_content">



				<div class="left_content">

					<div class="sidebar_search">
						<form>
							<input type="text" name="" class="search_input"
								value="search keyword" onclick="this.value=''" /> <input
								type="image" class="search_submit"
								src="resources/img/search.png" />
						</form>
					</div>

					<div class="sidebarmenu">

						<a class="menuitem submenuheader" href="">Subcategories</a>
						<div class="submenu">
							<ul>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
							</ul>
						</div>
						<a class="menuitem submenuheader" href="">Sidebar Settings</a>
						<div class="submenu">
							<ul>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
							</ul>
						</div>
						<a class="menuitem submenuheader" href="">Add new products</a>
						<div class="submenu">
							<ul>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
							</ul>
						</div>
						<a class="menuitem" href="${pageContext.request.contextPath}/userMgtAction!findAll"  target="rightContent">用戶管理</a> <a class="menuitem"
							href="">Blue button</a> <a class="menuitem_green" href="">Green
							button</a> <a class="menuitem_red" href="">Red button</a>

					</div>


					<div class="sidebar_box">
						<div class="sidebar_box_top"></div>
						<div class="sidebar_box_content">
							<h3>User help desk</h3>
							<img src="resources/img/info.png" alt="" title=""
								class="sidebar_icon_right" />
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna
								aliqua.</p>
						</div>
						<div class="sidebar_box_bottom"></div>
					</div>

					<div class="sidebar_box">
						<div class="sidebar_box_top"></div>
						<div class="sidebar_box_content">
							<h4>Important notice</h4>
							<img src="resources/img/notice.png" alt="" title=""
								class="sidebar_icon_right" />
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna
								aliqua.</p>
						</div>
						<div class="sidebar_box_bottom"></div>
					</div>

					<div class="sidebar_box">
						<div class="sidebar_box_top"></div>
						<div class="sidebar_box_content">
							<h5>Download photos</h5>
							<img src="resources/img/photo.png" alt="" title=""
								class="sidebar_icon_right" />
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna
								aliqua.</p>
						</div>
						<div class="sidebar_box_bottom"></div>
					</div>

					<div class="sidebar_box">
						<div class="sidebar_box_top"></div>
						<div class="sidebar_box_content">
							<h3>To do List</h3>
							<img src="resources/img/info.png" alt="" title=""
								class="sidebar_icon_right" />
							<ul>
								<li>Lorem ipsum dolor sit amet, consectetur adipisicing
									elit.</li>
								<li>Lorem ipsum dolor sit ametconsectetur <strong>adipisicing</strong>
									elit, sed do eiusmod tempor incididunt ut labore et dolore
									magna aliqua.
								</li>
								<li>Lorem ipsum dolor sit amet, consectetur <a href="#">adipisicing</a>
									elit.
								</li>
								<li>Lorem ipsum dolor sit amet, consectetur adipisicing
									elit.</li>
								<li>Lorem ipsum dolor sit amet, consectetur adipisicing
									elit.</li>
								<li>Lorem ipsum dolor sit amet, consectetur adipisicing
									elit.</li>
							</ul>
						</div>
						<div class="sidebar_box_bottom"></div>
					</div>


				</div>

				<div class="right_content">

					<%-- <decorator:body /> --%>
					<iframe name="rightContent" />


				</div>
				<!-- end of right content-->


			</div>
			<!--end of center content -->




			<div class="clear"></div>
		</div>
		<!--end of main content-->


		<div class="footer">

			<div class="left_footer">
				IN ADMIN PANEL | Powered by <a href="http://indeziner.com">INDEZINER</a>
			</div>
			<div class="right_footer">
				<a href="http://indeziner.com"><img
					src="${pageContext.request.contextPath}/resources/img/indeziner_logo.gif" alt="" title="" border="0" /></a>
			</div>

		</div>

	</div>
</body>
</html>