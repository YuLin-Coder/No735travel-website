<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>旅游网站-首页</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!--导入布局js，共享header和footer-->
    <script type="text/javascript" src="js/include.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="js/index.js"></script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<!-- 轮播图 start-->
<section id="banner">
    <!--bootstrap实现轮播图-->
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="3000">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>
        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="images/banner_1.jpg" style="width: 1500px;height: 399px;">
            </div>
            <div class="item">
                <img src="images/banner_2.jpg" style="width: 1500px;height: 399px;">
            </div>
            <div class="item">
                <img src="images/banner_3.jpg" style="width: 1500px;height: 399px;">
            </div>
        </div>
        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</section>
<!-- 轮播图 end-->
<!-- 旅游 start-->
<section id="content">
    <!-- 旅游精选start-->
    <section class="hemai_jx">
        <div class="jx_top">
            <div class="jx_tit">
                <img src="images/icon_5.jpg" alt="">
                <span>旅游精选</span>
            </div>
            <!-- Nav tabs -->
            <ul class="jx_tabs" role="tablist">
                <li role="presentation" class="active">
                    <span><a id="#popularity" onclick="hotfunc()" href="javascript:void(0)" aria-controls="popularity" role="tab" data-toggle="tab">人气旅游</a></span>
                </li>
                <li role="presentation">
                    <span><a id="#newest" onclick="newfunc()" href="javascript:void(0)" aria-controls="newest" role="tab" data-toggle="tab">最新旅游</a></span>
                </li>
                <li role="presentation">
                    <span><a id="#theme" onclick="themefunc()" href="javascript:void(0)" aria-controls="theme" role="tab" data-toggle="tab">主题旅游</a></span>
                </li>
            </ul>
        </div>
        <div class="jx_content">
            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="popularity">
                    <div id="jingxuan" class="row">
                        <%--旅游精选--%>

                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- 旅游精选end-->
    <!-- 国内游 start-->
    <section class="hemai_jx">
        <div class="jx_top">
            <div class="jx_tit">
                <img src="images/icon_6.jpg" alt="">
                <span>国内游</span>
            </div>
        </div>
        <div class="heima_gn">
            <div class="guonei_l">
                <img src="images/guonei_1.jpg" style="height: 488px;width: 360px;">
            </div>
            <div class="guone_r">
                <div id="guonei" class="row">
                    <%--国内游热门--%>
                </div>
            </div>
        </div>
    </section>
    <!-- 国内游 end-->
    <!-- 境外游 start-->
    <section class="hemai_jx">
        <div class="jx_top">
            <div class="jx_tit">
                <img src="images/icon_7.jpg" alt="">
                <span>境外游</span>
            </div>
        </div>
        <div class="heima_gn">
            <div class="guonei_l">
                <img src="images/jiangwai_1.jpg" style="height: 488px;width: 360px;">
            </div>
            <div class="guone_r">
                <div id="guowai" class="row">
                    <%--国外游热门--%>
                </div>
            </div>
        </div>
    </section>
    <!-- 境外游 end-->
</section>
<!-- 旅游 end-->
<!--导入底部-->
<div id="footer"></div>
</body>
</html>
