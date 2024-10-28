<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>旅游网站-登录</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--导入jquery-->
    <script src="js/jquery-3.3.1.js"></script>
</head>
<body>
<%
    String username = "";
    String password = "";
    String remember = "";

    //从Cookie中取数据
    Cookie[] cookies = request.getCookies();
    if(cookies!=null&&cookies.length>0){
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("username")){
                username = cookie.getValue();
            }else if(cookie.getName().equals("password")){
                password = cookie.getValue();
            }else if(cookie.getName().equals("remember")){
                remember = cookie.getValue();
            }
            request.setAttribute("username", URLDecoder.decode(username, "UTF-8"));
            request.setAttribute("password", password);
            request.setAttribute("remember", remember);
        }
//        System.out.println("username="+username+"password="+password+"remember="+remember);
    }
%>
<!--引入头部-->
<div id="header"></div>
<!-- 头部 end -->
<section id="login_wrap">
    <div class="fullscreen-bg" style="background-image: url(images/login_bg.png);height: 532px;"></div>
    <div class="login-box">
        <div class="title" style="text-align: center;">
            <span>欢迎登录旅游网站</span>
        </div>
        <div class="login_inner">
            <!--登录错误提示消息-->
            <div id="errorMsg" class="alert alert-danger" ></div>
            <form id="loginForm" action="" method="post" accept-charset="utf-8">
                <input type="hidden" name="action" value="login"/>
                <input name="username" value="${username}" type="text" placeholder="请输入账号" autocomplete="off">
                <input name="password" value="${password}" type="password" placeholder="请输入密码" autocomplete="off">
                <div class="verify">
                    <input name="check" id="mycode" type="text" placeholder="请输入验证码" autocomplete="off">
                    <span><img id="img" src="checkCode" alt="" onclick="changeCheckCode(this)"></span>
                </div>
                <div class="submit_btn">
                    <button type="button"  id="btn_sub">登录</button>
                    <div class="auto_login">
                        <input type="checkbox" name="remember" ${remember eq "1" ? 'checked="checked"':'' } value="1" class="checkbox">
                        <span>记住密码</span>
                    </div>
                </div>
            </form>
            <div class="reg">没有账户？<a href="register.jsp">立即注册</a></div>
        </div>
    </div>
</section>
<!--引入尾部-->
<div id="footer"></div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
<script type="text/javascript">
    //验证码点击事件
    function changeCheckCode(img) {
        img.src="checkCode?"+new Date().getTime();
    }
    $(function () {
        //1.给登录按钮绑定单击事件
        $("#btn_sub").click(function () {
            //2.发送ajax请求，提交表单数据
            $.post("user/login",$("#loginForm").serialize(),function (data) {
                if(data.flag){
                    //登录成功
                    location.href="index.jsp";
                }else{
                    //登录失败
                    $("#errorMsg").html(data.errorMsg);
                    $("#img").click();
                }
            });
        });
    });
</script>
</body>
</html>
