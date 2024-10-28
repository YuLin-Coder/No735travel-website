<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>酒店管理</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,hotel-scalable=no" name="viewport">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/morris/morris.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
    <style>
        .search_form{
            width:602px;
            height:42px;
        }
        .input_sub{
            width:40px;
            height: 30px;
            background: #3d9970;
            /*去掉submit按钮默认边框*/
            border:0px;
            float:right;
            color:white;/*搜索的字体颜色为白色*/
            cursor:pointer;/*鼠标变为小手*/
        }
    </style>
</head>

<body class="hold-transition skin-purple sidebar-mini">
<div class="wrapper">
    <!-- 页面头部 -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- 页面头部 /-->
    <!-- 导航侧栏 -->
    <jsp:include page="aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->
    <!-- 内容区域 -->
    <div class="content-wrapper">
        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                酒店管理 <small>酒店列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/pages/main.jsp"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active"><a href="">酒店管理</a></li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content" style="padding: 15px 0 0 0;">
            <div class="box box-primary" style="margin: 0px;">
                <div class="box-body">
                    <!-- 数据表格 -->
                    <div class="table-box" style="min-height: 501px;">
                        <!--工具栏-->
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" title="新建"
                                            onclick="location.href='${pageContext.request.contextPath}/hotels/findUsers.do'">
                                        <i class="fa fa-file-o"></i> 新建
                                    </button>
                                    <button type="button" class="btn btn-default" title="刷新" onclick="location.reload()">
                                        <i class="fa fa-refresh"></i> 刷新
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="box-tools pull-right">
                            <div class="has-feedback" >
                                <form class="search_form" action="${pageContext.request.contextPath}/hotels/findAll.do" method="post">
                                    <input type="submit" value="搜索" class="input_sub">
                                    <input type="text" name="search" class="form-control input-sm" style="width:190px;height: 30px;float:right;" placeholder="请输入搜索内容" autocomplete="off">
                                </form>
                            </div>
                        </div>
                        <!--工具栏/-->

                        <!--数据列表-->
                        <div style="width: 1270px;height:452px;overflow-x: scroll;">
                            <table id="dataList" style="table-layout:fixed;width: 1500px;" class="table table-bordered table-striped table-hover dataTable">
                            <tr style="background: lightsteelblue;">
                                <th style="text-align: center;width: 20px;">ID</th>
                                <th style="text-align: center;width: 100px;">名称</th>
                                <th style="text-align: center;width: 500px;">简介</th>
                                <th style="text-align: center;width: 140px;">图片</th>
                                <th style="text-align: center;width: 60px;">是否上架</th>
                                <th style="text-align: center;width: 150px;">地址</th>
                                <th style="text-align: center;width: 100px;">联系电话</th>
                                <th style="text-align: center;width: 60px;">所属商家</th>
                                <th style="text-align: center;width: 130px;">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${pageInfo.list}" var="hotel">
                                <tr>
                                    <td>${hotel.hid }</td>
                                    <td>${hotel.hname }</td>
                                    <td>${hotel.hintroduce }</td>
                                    <td style="text-align: center;"><img src="${pageContext.request.contextPath}/${hotel.himage }" style="width: 138px;height: 82px;"></td>
                                    <td style="text-align: center;">${hotel.hflagStr }</td>
                                    <td style="text-align: center;">${hotel.address }</td>
                                    <td style="text-align: center;">${hotel.telphone }</td>
                                    <td style="text-align: center;">${hotel.user.name }</td>
                                    <td style="padding-left: 20px;">
                                        <a href="${pageContext.request.contextPath}/hotels/findByUid.do?hid=${hotel.hid}" class="btn btn-info btn-xs">详情</a>
                                        <a href="${pageContext.request.contextPath}/hotels/findU.do?hid=${hotel.hid}" class="btn btn-primary btn-xs">修改</a>
                                        <button type="button" class="btn btn-danger btn-xs"
                                                onclick="if(confirm('您确定删除吗？')){location.href='${pageContext.request.contextPath}/hotels/delete.do?hid=${hotel.hid}'}">删除</button>
                                        <a href="${pageContext.request.contextPath}/hotels/findOtherRooms.do?hid=${hotel.hid}" class="btn btn-success btn-xs" style="margin-top:5px;">添加房型</a>
                                        <a href="${pageContext.request.contextPath}/hotels/findRooms.do?hid=${hotel.hid}" class="btn btn-warning btn-xs" style="margin-top:5px;">移除房型</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!--数据列表/-->
                    </div>
                    <!-- 数据表格 /-->
                </div>
                <!-- .box-footer-->
                <div class="box-footer" style="padding: 0 30px 0 20px">
                    <div class="pull-left">
                        <div style="margin-top: 5px;">
                            总共<span style="color: #3c8dbc;margin: 0 10px;">${pageInfo.pages}</span>页，共<span style="color: #3c8dbc;margin: 0 10px;">${pageInfo.total}</span>条数据，第<span style="color: #3c8dbc;margin: 0 10px;">${pageInfo.pageNum}</span>页
                        </div>
                    </div>

                    <div class="box-tools pull-right">
                        <ul class="pagination" style="margin: 0px">
                            <li>
                                <a href="${pageContext.request.contextPath}/hotels/findAll.do?page=1" aria-label="Previous">首页</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/hotels/findAll.do?page=${pageInfo.pageNum-1}">上一页</a></li>
                            <!--中间页码开始-->
                            <c:if test="${pageInfo.pages < 10}">
                                <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                                    <li><a href="${pageContext.request.contextPath}/hotels/findAll.do?page=${pageNum}">${pageNum}</a></li>
                                </c:forEach>
                            </c:if>
                            <c:if test="${pageInfo.pages >= 10}">
                                <c:if test="${pageInfo.pageNum <= 6}">
                                    <c:forEach begin="1" end="10" var="pageNum">
                                        <li><a href="${pageContext.request.contextPath}/hotels/findAll.do?page=${pageNum}">${pageNum}</a></li>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${pageInfo.pageNum > 6}">
                                    <c:if test="${pageInfo.pages - pageInfo.pageNum < 9}">
                                        <c:forEach begin="${pageInfo.pages - 9 > 0?pageInfo.pages - 9:1}" end="${pageInfo.pages}" var="pageNum">
                                            <li><a href="${pageContext.request.contextPath}/hotels/findAll.do?page=${pageNum}">${pageNum}</a></li>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${pageInfo.pages - pageInfo.pageNum >= 9}">
                                        <c:forEach begin="${pageInfo.pageNum - 5 < 1 ? 1:pageInfo.pageNum - 5}" end="${pageInfo.pageNum + 4 > pageInfo.pages ? pageInfo.pages:pageInfo.pageNum + 4}" var="pageNum">
                                            <li><a href="${pageContext.request.contextPath}/hotels/findAll.do?page=${pageNum}">${pageNum}</a></li>
                                        </c:forEach>
                                    </c:if>
                                </c:if>
                            </c:if>
                            <!--中间页码结束-->
                            <li><a href="${pageContext.request.contextPath}/hotels/findAll.do?page=${pageInfo.pageNum+1}">下一页</a></li>
                            <li>
                                <a href="${pageContext.request.contextPath}/hotels/findAll.do?page=${pageInfo.pages}" aria-label="Next">尾页</a>
                            </li>
                        </ul>
                    </div>

                </div>
                <!-- /.box-footer-->
            </div>

        </section>
        <!-- 正文区域 /-->

    </div>
    <!-- @@close -->
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
        // 激活导航位置
        setSidebarActive("hotels");
    });
</script>
</body>

</html>