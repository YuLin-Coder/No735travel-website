<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 页面头部 -->
<header class="main-header">
	<!-- Logo -->
	<a href="" class="logo" style="background-color: #3c8dbc;"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>数据</b></span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg">后台管理</span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top" style="background-color: #3c8dbc;">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button" style="background-color: #3c8dbc;border-left: 1px solid #fff;"> <span class="sr-only">Toggle navigation</span>
		</a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown user user-menu">
					<div class="">
						<a href="${pageContext.request.contextPath}/logout.do" class="btn btn-default btn-flat"
						   style="background-color: #3c8dbc;border:0;padding: 15px 20px 0 0;color: white;">退出</a>
					</div>
				</li>
			</ul>
		</div>
	</nav>
</header>
<!-- 页面头部 /-->