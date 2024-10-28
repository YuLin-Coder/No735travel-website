<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 头部 start -->
<script src="js/getParameter.js"></script>
<%--js加密--%>
<script src="js/secret.js"></script>
<script>
    $(function() {
        //查询用户信息
        $.get("user/findOne",{},function (data) {
            if (data != null){
                var msg = "欢迎回来，"+data.username;
                $("#span_username").html(msg);
            } else{

            }
        });

        //模糊搜索
        $("#search-button").click(function () {
            var rname = $("#search_input").val();
            // alert(rname);
            var cid = getParameter("cid");
            // location.href = "/route_list.jsp?cid="+cid+"&rname="+rname;
            load(cid,null,rname);
        });
    });
    //给选中的li添加样式
    var url = window.location.href;
    $('.nav li a').each(function () {
        if (returnUrl($(this).attr('href'))== returnUrl(url)){
            $(this).parent("li").addClass("on");
        }
    });
    //以下为截取url的方法
    function returnUrl(href){
        var number=href.lastIndexOf("/");
        return href.substring(number+1);
    }

</script>
<header id="header">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <div class="shortcut">
        <!-- 未登录状态  -->
        <div class="login_out">
            <a href="login.jsp">登录</a>
            <a href="register.jsp">注册</a>
        </div>
        <!-- 登录状态  -->
        <div class="login">
            <span id="span_username" style="margin-right: 10px;"></span>
            <a href="javascript:location.href='user/exit';">退出</a>
        </div>
    </div>
    <div class="header_wrap">
        <div class="topbar">
            <div class="logo">
                <a href="/"><img src="images/logo.jpg" style="width: 207px;height: 56px"></a>
            </div>
            <div class="search">
                <input id="search_input" type="text" placeholder="请输入路线名称" class="search_input" autocomplete="off">
                <a href="javascript:;" id="search-button" class="search-button">搜索</a>
            </div>
            <div class="hottel">
                <div class="hot_pic">
                    <img src="images/hot_tel.jpg" >
                </div>
                <div class="hot_tel">
                    <p class="hot_time">客服热线(9:00-18:00)</p>
                    <p class="hot_num">400-618-8080</p>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- 头部 end -->
<!-- 首页导航 -->
<div class="navitem">
    <ul id="category" class="nav">
        <li class="navli"><a href="/index.jsp">首页</a></li>
        <li class="navli"><a href="/route_list.jsp?cid=1">国内旅游</a></li>
        <li class="navli"><a href="/route_list.jsp?cid=2">出境旅游</a></li>
        <li class="navli"><a href="/favoriterank.jsp">收藏排行榜</a></li>
        <li class="navli"><a href="/hotel_list.jsp">酒店住宿</a></li>
        <li class="navli"><a href="${pageContext.request.contextPath}/findAllMessage">在线留言</a></li>
        <li class="navli"><a href="/personal.jsp">个人信息</a></li>
        <li class="navli"><a href="/pages/main.jsp">后台管理</a></li>
    </ul>
</div>

