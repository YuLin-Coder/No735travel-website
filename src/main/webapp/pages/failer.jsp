<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>登录失败</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">

    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">


    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/morris/morris.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/select2/select2.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
</head>

<body class="hold-transition skin-purple sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
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
                            <a href="${pageContext.request.contextPath}/login.do" class="btn btn-default btn-flat"
                               style="background-color: #3c8dbc;border:0;padding: 15px 20px 0 0;color: white;">未登录</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- 页面头部 /-->
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
                         class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>
                        未登录
                    </p>
                    <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
                </div>
            </div>

            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">菜单</li>
                <li id="admin-index"><a><i class="fa fa-dashboard"></i> <span>首页</span></a></li>

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
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>登录失败 页面</h1>

            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/pages/index.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">登录失败</li>
            </ol>

        </section>

        <!-- Main content -->
        <section class="content">
            <div class="error-page">

                <div class="error-content">
                    <p>
                        登录失败 , 你可以 <a href="${pageContext.request.contextPath}/pages/login.jsp">返回到登录页面</a>
                        重新登录
                    </p>

                </div>
                <!-- /.error-content -->
            </div>
            <!-- /.error-page -->
        </section>
        <!-- /.content -->
    </div>
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0
        </div>
        <strong>Copyright &copy; 2019-2020 <a href="#">研究院研发部</a>.
        </strong> All rights reserved.
    </footer>
    <!-- 底部导航 /-->

</div>


<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
<script>$.widget.bridge('uibutton', $.ui.button);</script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script>
    $(document).ready(function() {
        // 选择框
        $(".select2").select2();

        // WYSIHTML5编辑器
        $(".textarea").wysihtml5({
            locale : 'zh-CN'
        });
    });

    // 设置激活菜单
    function setSidebarActive(tagUri) {
        var liObj = $("#" + tagUri);
        if (liObj.length > 0) {
            liObj.parent().parent().addClass("active");
            liObj.addClass("active");
        }
    }

    $(document).ready(function() {
        setSidebarActive("admin-500");
    });
</script>
</body>

</html>