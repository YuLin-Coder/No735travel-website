<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>修改景点线路</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
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
                    景点管理 <small>景点表单</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/index.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/routes/findAll.do">景点管理</a></li>
                    <li class="active">景点表单</li>
                </ol>
            </section>
            <!-- 内容头部 /-->
            <form action="${pageContext.request.contextPath}/routes/update.do?rid=${routeInfo.rid}" method="post" enctype="multipart/form-data">
                <!-- 正文区域 -->
                <section class="content">
                    <div class="panel panel-default">
                        <div class="panel-heading">景点信息</div>

                        <div class="row data-type">
                            <div class="col-md-2 title">编号</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" name="rid" value="${routeInfo.rid}" disabled="disabled">
                            </div>
                            <div class="col-md-2 title">名称</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" name="rname" placeholder="名称" autocomplete="off" value="${routeInfo.rname}">
                            </div>
                            <div class="col-md-2 title">价格</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" name="price" placeholder="价格" autocomplete="off" value="${routeInfo.price}">
                            </div>
                            <div class="col-md-2 title">简介</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" name="routeIntroduce" placeholder="简介" autocomplete="off" value="${routeInfo.routeIntroduce}">
                            </div>
                            <div class="col-md-2 title" style="height: 250px;padding: 100px 0;">缩略图</div>
                            <div class="col-md-4 data" style="height:250px;">
                                <div style="width: 400px;height: 100%;float:left;">
                                    <div id="big" style="width: 276px;height: 164px;margin: 10px 0 0 50px;border: 1px solid #fcc;"><img src="${pageContext.request.contextPath}/${routeInfo.rimage}" style="width:100%;height:100%;"></div>
                                    <input id="rimage" type="file" name="rimageStr" style="margin: 20px 0 0 50px;" onchange="change(this)">
                                    <input type="hidden" class="form-control" name="rimage" value="${routeInfo.rimage}">
                                </div>
                            </div>
<%--                            <div class="col-md-2 title">缩略图</div>--%>
<%--                            <div class="col-md-4 data">--%>
<%--                                <input type="text" class="form-control" name="rimage" placeholder="缩略图" autocomplete="off" value="${routeInfo.rimage}">--%>
<%--                            </div>--%>
                            <div class="col-md-2 title">是否上架</div>
                            <div class="col-md-4 data">
                                <select class="form-control select2" style="width: 100%" name="rflag">
                                    <c:if test="${routeInfo.rflag == 0}">
                                        <option value="0" selected="selected">否</option>
                                        <option value="1">是</option>
                                    </c:if>
                                    <c:if test="${routeInfo.rflag == 1}">
                                        <option value="0">否</option>
                                        <option value="1" selected="selected">是</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="col-md-2 title">创建时间</div>
                            <div class="col-md-4 data">
                                <div class="input-group date">
                                    <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    <input type="text" class="form-control" id="datepicker-a3" name="rdate" value="${routeInfo.rdateStr}" disabled="disabled">
                                </div>
                            </div>
                            <div class="col-md-2 title">主题旅游</div>
                            <div class="col-md-4 data">
                                <select class="form-control select2" style="width: 100%" name="isThemeTour">
                                    <c:if test="${routeInfo.isThemeTour == '0'}">
                                        <option value="0" selected="selected">否</option>
                                        <option value="1">是</option>
                                    </c:if>
                                    <c:if test="${routeInfo.isThemeTour == '1'}">
                                        <option value="0">否</option>
                                        <option value="1" selected="selected">是</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="col-md-2 title">收藏数量</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" name="count" value="${routeInfo.count}" disabled="disabled">
                            </div>
                            <div class="col-md-2 title">所属分类</div>
                            <div class="col-md-4 data">
                                <select class="form-control select2" style="width: 100%" name="cid">
                                    <c:forEach items="${CList}" var="coute">
                                        <c:if test="${coute.cid == routeInfo.cid}">
                                            <option value="${coute.cid}" selected="selected">${coute.cname}</option>
                                        </c:if>
                                        <c:if test="${coute.cid != routeInfo.cid}">
                                            <option value="${coute.cid}">${coute.cname}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-2 title">所属商家</div>
                            <div class="col-md-4 data">
                                <select class="form-control select2" style="width: 100%" name="sid">
                                    <c:forEach items="${SList}" var="soute">
                                        <c:if test="${soute.sid == routeInfo.sid}">
                                            <option value="${soute.sid}" selected="selected">${soute.sname}</option>
                                        </c:if>
                                        <c:if test="${soute.sid != routeInfo.sid}">
                                            <option value="${soute.sid}">${soute.sname}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!--工具栏-->
                    <div class="box-tools text-center">
                        <button type="submit" class="btn bg-maroon">保存</button>
                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                    </div>
                    <!--工具栏/--> </section>
                <!-- 正文区域 /-->
            </form>
        </div>
        <!-- 内容区域 /-->
        <!-- 底部导航 -->
        <footer class="main-footer">
            <div class="pull-right hidden-xs">
                <b>Version</b> 1.0
            </div>
            <strong>Copyright &copy; 2019-2020 <a href="#">研究院研发部</a>.
            </strong> All rights reserved. </footer>
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
<script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

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
        setSidebarActive("routes");
    });
    function change(obj) {
        var file = document.getElementById("rimage").files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function () {
            document.getElementById("big").innerHTML = '<img src="'+this.result+'" style="width:100%;height:100%;"/>';
        }
    }
</script>
</body>

</html>