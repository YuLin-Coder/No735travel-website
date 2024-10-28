<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>旅游网站-个人中心</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" >
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/search.css">
    <link rel="stylesheet" href="css/personal.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $.get("user/findOne",{},function (user) {
                if (user){//用户已登录

                } else{//用户未登录
                    $("#left").hide();
                    $(".page_one").height(340);
                    $(".page_one").html("<div style='text-align:center;font-size:30px;margin-top:100px;'>您尚未登录，请点击<a href='/login.jsp' style='color: #6495ED;'>登录</a>查看</div>");
                }
            });
        });
        //给选中的li添加样式
        $(function () {
            var url = window.location.href;
            $(".la a").each(function () {
                if (returnUrl($(this)[0].href)== returnUrl(url)){
                    $(this).parent("div").addClass("on");
                }
                if ("personal.jsp"== returnUrl(url)) {
                    $("#userInfo").addClass("on");
                }
            });
        });
        //以下为截取url的方法
        function returnUrl(href){
            var number=href.lastIndexOf("/");
            return href.substring(number+1);
        }
    </script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<div class="page_one">
    <div class="pediv">
        <div id="left" class="pldiv">
            <div id="userInfo"  class="la" ><a href="/personal.jsp?varstr=userInfo">我的信息</a></div>
            <div id="myfavorite" class="la"><a href="/personal.jsp?varstr=myfavorite">我的收藏</a></div>
            <div id="mymessage" class="la"><a href="/personal.jsp?varstr=mymessage">我的留言</a></div>
            <div id="rorder" class="la"><a href="/personal.jsp?varstr=rorder">景点订单</a></div>
            <div id="order" class="la"><a href="/personal.jsp?varstr=order">酒店订单</a></div>
        </div>
       <div id="right" class="prdiv">
           <%
               String varstr = request.getParameter("varstr");
               if ("userInfo".equals(varstr)||varstr == null){//个人信息
               %>
                    <%@ include file="userInfo.jsp" %>
               <%}
               if ("myfavorite".equals(varstr)){//我的收藏
                %>
                    <%@ include file="myfavorite.jsp" %>
               <%}
               if ("mymessage".equals(varstr)){//我的留言
               %>
                    <%@ include file="mymessage.jsp" %>
               <%}
                   if ("rorder".equals(varstr)){//景点订单
               %>
                    <%@ include file="myrorder.jsp" %>
               <%}
                   if ("order".equals(varstr)){//酒店订单
               %>
               <%@ include file="myorder.jsp" %>
           <%}
           %>
       </div>
    </div>
</div>
<!--引入底部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
</body>
</html>
