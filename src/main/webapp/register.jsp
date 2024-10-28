<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>用户注册页面</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/register.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script>
        //1.校验用户名

        //2.校验密码

        //3.校验邮箱
        function checkEmail(){
            var email = $("#email").val();
            var reg_email = /^\w+@\w+\.\w+$/;

            var flag = reg_email.test(email);
            if(flag){
                $("#email").css("border","");
            }else{
                $("#email").css("border","1px solid red");
            }
            return flag;
        }

        //4.校验姓名

        //5.校验手机号
        function checkTelephone(){
            var telephone = $("#telephone").val();
            var reg_telephone = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;

            var flag = reg_telephone.test(telephone);
            if(flag){
                $("#telephone").css("border","");
            }else{
                $("#telephone").css("border","1px solid red");
            }
            return flag;
        }

        //6.校验出生日期
        function checkBirthday(){
            var birthday = $("#birthday").val();
            var flag = birthday ? true : false;
            if(flag){
                $("#birthday").css("border","");
            }else{
                $("#birthday").css("border","1px solid red");
            }
            return flag;
        }

        $(function () {
            //当表单提交时，调用所有的校验方法
            $("#registerForm").submit(function(){
                //1.发送数据到服务器
                 //校验通过,发送ajax请求，提交表单的数据   username=zhangsan&password=123
                    $.post("user/regist",$("#registerForm").serialize(),function(data){
                        //处理服务器响应的数据data  {flag:false,errorMsg:"注册失败"}
                        if(data.flag){
                            //注册成功，跳转成功页面
                            location.href="login.jsp";
                        }else{
                            //注册失败,给errorMsg添加提示信息
                            $("#errorMsg").html(data.errorMsg);
                            $("#img").click();
                        }
                    });
                //2.不让页面跳转
                return false;
                //如果这个方法没有返回值，或者返回为true，则表单提交，如果返回为false，则表单不提交
            });

            //当某一个组件失去焦点是，调用对应的校验方法
            $("#email").blur(checkEmail);
            $("#telephone").blur(checkTelephone);
        });
    </script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<!-- 头部 end -->
<div class="rg_layout">
    <div class="rg_form clearfix">
        <div class="rg_form_left">
            <p>新用户注册</p>
            <p>USER REGISTER</p>
        </div>
        <div class="rg_form_center">
            <div id="errorMsg" style="color:red;text-align: center"></div>
            <!--注册表单-->
            <form id="registerForm" action="user">
                <!--提交处理请求的标识符-->
                <input type="hidden" name="action" value="register">
                <table style="margin-top: 25px;">
                    <tr>
                        <td class="td_left">
                            <label for="username">用户名</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="username" name="username" autocomplete="off" placeholder="请输入账号">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="password">密码</label>
                        </td>
                        <td class="td_right">
                            <input type="password" id="password" name="password" autocomplete="off" placeholder="请输入密码" >
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="email">邮箱</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="email" name="email" autocomplete="off" placeholder="请输入邮箱">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="name">姓名</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="name" name="name" autocomplete="off" placeholder="请输入真实姓名">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="telephone">手机号</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="telephone" name="telephone" autocomplete="off" placeholder="请输入您的手机号">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="sex">性别</label>
                        </td>
                        <td class="td_right gender">
                            <input type="radio" id="sex" name="sex" value="男" checked> 男
                            <input type="radio" name="sex" value="女"> 女
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="birthday">出生日期</label>
                        </td>
                        <td class="td_right">
                            <input type="date" id="birthday" name="birthday" placeholder="年/月/日">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="check">验证码</label>
                        </td>
                        <td class="td_right check">
                            <input type="text" id="check" name="check" class="check">
                            <img id="img" src="checkCode" height="32px"  onclick="changeCheckCode(this)">
                            <script type="text/javascript">
                                //图片点击事件
                                function changeCheckCode(img) {
                                    img.src="checkCode?"+new Date().getTime();
                                }
                            </script>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                        </td>
                        <td class="td_right check">
                            <input type="submit" class="submit" value="注册">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="rg_form_right">
            <p>
                已有账号？
                <a href="login.jsp">立即登录</a>
            </p>
        </div>
    </div>
</div>
<!--引入尾部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
</body>
</html>
