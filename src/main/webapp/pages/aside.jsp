<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
<%--			<div class="pull-left info" style="    padding: 15px;">--%>
<%--				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>--%>
<%--			</div>--%>
			<div class="pull-left info">
				<p>
					<security:authentication property="principal.username"></security:authentication>
				</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<security:authorize access="hasRole('ADMIN')">
			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>系统管理</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
			</a>
				<ul class="treeview-menu">
					<li id="user">
						<a href="${pageContext.request.contextPath}/users/findAll.do"> <i class="fa fa-circle-o"></i> 用户管理</a>
					</li>
					<li id="role">
						<a href="${pageContext.request.contextPath}/roles/findAll.do"> <i class="fa fa-circle-o"></i> 角色管理</a>
					</li>
					<li id="permission">
						<a href="${pageContext.request.contextPath}/permission/findAll.do"> <i class="fa fa-circle-o"></i> 权限管理</a>
					</li>
					<li id="log">
						<a href="${pageContext.request.contextPath}/sysLog/findAll.do"> <i class="fa fa-circle-o"></i> 访问日志</a>
					</li>
				</ul>
			</li>
			</security:authorize>
			<security:authorize access="hasAnyRole('TOUR','ADMIN')">
			<li class="treeview">
				<a href="#"> <i class="fa fa-camera-retro"></i>
					<span>景区管理</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
				</a>
				<ul class="treeview-menu">
					<li id="cates">
						<a href="${pageContext.request.contextPath}/cates/findAll.do"> <i class="fa fa-circle-o"></i> 分类管理</a>
					</li>
					<li id="routes">
						<a href="${pageContext.request.contextPath}/routes/findAll.do"><i class="fa fa-circle-o"></i> 景点管理</a>
					</li>
					<li id="rorders">
						<a href="${pageContext.request.contextPath}/orders/findAllRoute.do"><i class="fa fa-circle-o"></i> 订单管理</a>
					</li>
				</ul>
			</li>
			<li class="treeview" id="sellers">
				<a href="${pageContext.request.contextPath}/sellers/findAlls.do"> <i class="fa fa-map-o"></i>
					<span>商家管理</span> <span class="pull-right-container"></span>
				</a>
			</li>
			</security:authorize>
			<security:authorize access="hasAnyRole('HOTEL','ADMIN')">
			<li class="treeview">
				<a href="#"> <i class="fa fa-moon-o"></i>
					<span>酒店管理</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
				</a>
				<ul class="treeview-menu">
					<li id="hotels">
						<a href="${pageContext.request.contextPath}/hotels/findAll.do"> <i class="fa fa-circle-o"></i> 酒店管理</a>
					</li>
					<li id="rooms">
						<a href="${pageContext.request.contextPath}/rooms/findAll.do"><i class="fa fa-circle-o"></i> 房型管理</a>
					</li>
					<li id="orders">
						<a href="${pageContext.request.contextPath}/orders/findAll.do"><i class="fa fa-circle-o"></i> 订单管理</a>
					</li>
				</ul>
			</li>
			</security:authorize>
			<security:authorize access="hasAnyRole('MSG','ADMIN')">
			<li class="treeview" id="mesgs">
				<a href="${pageContext.request.contextPath}/messages/findAll.do"> <i class="fa fa-edit"></i>
					<span>留言管理</span> <span class="pull-right-container"></span>
				</a>
			</li>
			</security:authorize>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>