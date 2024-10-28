<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>个人信息-我的信息</title>
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $.get("/updateUser",{},function () {

            });
        });
    </script>
</head>
<body>
<section class="hemai_jx">
    <div class="jx_top">
        <div class="jx_tit">
            <img src="images/icon_5.jpg" >
            <span>我的信息</span>
        </div>
    </div>
    <div class="jx_content" >
        <!-- Tab panes -->
        <div id="tab-content" class="tab-content" style="height: 426px;">
            <div role="tabpanel" class="tab-pane active" id="home">
                <div id="favoritediv" class="row">
                    <div style="margin-left: 100px;">
                        <div id="errorMsg" class="errormsg"></div>
                    <form action="${pageContext.request.contextPath}/updateUser" method="post">
                        <input type="hidden" name="uid" value="${user.uid}">
                        <div class="ediv">
                            <div class="ldiv">
                                <label for="username">用户名</label>
                            </div>
                            <div class="rdiv">
                                <input type="text" class="form-control" id="username" name="username" value="${user.username}" readonly="readonly" placeholder="请输入姓名" autocomplete="off" />
                            </div>
                        </div>
                        <div class="ediv">
                            <div class="ldiv">
                                <label for="password">密码</label>
                            </div>
                            <div class="rdiv">
                                <input type="password" class="form-control" id="password" name="password" value="${user.password}" placeholder="请输入密码" autocomplete="off" />
                            </div>
                        </div>
                        <div class="ediv">
                            <div class="ldiv">
                                <label for="email">邮箱</label>
                            </div>
                            <div class="rdiv">
                                <input type="text" class="form-control" id="email" name="email" value="${user.email}" placeholder="请输入邮箱" autocomplete="off"/>
                            </div>
                        </div>
                        <div class="ediv">
                            <div class="ldiv">
                                <label for="name">姓名</label>
                            </div>
                            <div class="rdiv">
                                <input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="请输入姓名" autocomplete="off"/>
                            </div>
                        </div>
                        <div class="ediv">
                            <div class="ldiv">
                                <label for="telephone">手机号</label>
                            </div>
                            <div class="rdiv">
                                <input type="text" class="form-control" id="telephone" name="telephone" value="${user.telephone}" placeholder="请输入手机号" autocomplete="off"/>
                            </div>
                        </div>
                        <div class="ediv">
                            <div class="ldiv">
                                <label for="sex">性别</label>
                            </div>
                            <div class="rdiv">
                                <c:if test="${user.sex == '男'}">
                                    <input id="sex" type="radio" name="sex" value="男"  checked/>男
                                    <input type="radio" name="sex" value="女"  />女
                                </c:if>
                                <c:if test="${user.sex == '女'}">
                                    <input id="sex" type="radio" name="sex" value="男"  />男
                                    <input type="radio" name="sex" value="女"  checked/>女
                                </c:if>
                            </div>
                        </div>
                        <div class="ediv">
                            <div class="ldiv">
                                <label for="birthday">出生日期</label>
                            </div>
                            <div class="rdiv">
                                <input type="date" class="form-control" id="birthday" name="birthday"  value="${user.birthday}" placeholder="年/月/日" />
                            </div>
                        </div>
                        <div class="ediv" style="margin-left: 250px;">
                            <input class="btn btn-primary" type="submit" value="保存" />
                        </div>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
