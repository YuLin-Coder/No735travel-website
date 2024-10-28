<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>旅游网站-旅游景点</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/search.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/getParameter.js"></script>
    <script src="js/route_list.js"></script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<div class="page_one">
    <div class="contant">
        <div class="crumbs">
            <img src="images/search.png" alt="">
            <p>畅快旅行><span>搜索结果</span></p>
        </div>
        <div class="xinxi clearfix">
            <div class="left">
                <div id="tophead" class="header">
<%--                    <span>景点及线路信息</span>--%>
<%--                    <span class="jg">价格</span>--%>
                </div>
                <ul id="route">
                <%--线路--%>
                </ul>
                <div class="page_num_inf" >
                    <i></i> 共
                    <span id="totalPage"></span>页<span id="totalCount"></span>条
                </div>
                <div class="pageNum">
                    <ul id="pageNum">
                    <%--页码--%>
                    </ul>
                </div>
            </div>
            <div class="right">
                <div class="top">
                    <div class="hot">HOT</div>
                    <span>热门推荐</span>
                </div>
                <ul id="topul">
<%--                    <li>--%>
<%--                        <div class="left"><img src="images/04-search_09.jpg"></div>--%>
<%--                        <div class="right">--%>
<%--                            <p>清远新银盏温泉度假村酒店/自由行套...</p>--%>
<%--                            <p>网付价<span>&yen;<span>899</span>起</span></p>--%>
<%--                        </div>--%>
<%--                    </li>--%>
                </ul>
            </div>
        </div>
    </div>
</div>
<!--引入底部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>

</body>
</html>
