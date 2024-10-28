<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>旅游网站-在线留言</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/daterangepicker.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/search.css">
    <link rel="stylesheet" href="css/route-detail.css">
    <link rel="stylesheet" href="css/personal.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        function save() {
            var title = $("#title").val().length;
            var content = $("#content").val().length;
            $.get("user/findOne",{},function (user) {
                if (user){//用户已登录
                    if (title < 1){
                        $("#title").val("请输入标题!");
                        $("#title").css("border","1px solid #DC143C");
                    }
                    if (title > 30){
                        $("#title").val("标题字数最多为30字!");
                        $("#title").css("border","1px solid #DC143C");
                    }
                    if (content < 20){
                        $("#content").val("评论字数至少20字!");
                        $("#content").css("border","1px solid #DC143C");
                    }
                    if (content > 500){
                        $("#content").val("评论字数最多500字!");
                        $("#content").css("border","1px solid #DC143C");
                    }
                    if (title > 1 && title < 30 && content >20 && content < 500){
                        $("form").submit();
                    }
                }else{
                    $("form").submit();
                }
            });
        }
    </script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<!-- 详情 start -->
<div class="wrap">
    <div class="prosum_box">
        <!--填写留言-->
        <div class="topmsg">
            <div class="online">在线留言</div>
            <div role="alert" class="toperror">
                <button type="button" class="close" data-dismiss="alert" >
                    <span>&times;</span>
                </button>
                <strong>${login_msg}</strong>
            </div>
            <div class="onlinecontent">
                <form action="${pageContext.request.contextPath}/saveMessage" method="post">
                    <div class="form-group">
                        <label for="title">标题</label>
                        <input id="title" name="title" type="text" class="form-control" placeholder="请输入留言标题" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label for="content">内容</label>
                        <textarea id="content" name="content" class="form-control" rows="5" placeholder="请输入留言内容" autocomplete="off"></textarea>
                    </div>
                    <button type="button" onclick="save()" class="btn btn-default">留言</button>
                    <button type="reset" class="btn btn-default">清除</button>
                </form>
            </div>
        </div>
        <!--留言列表-->
        <div class="leave">
            <div class="leave_list">留言列表</div>
            <div class="leave_for">
                <!--单条留言-->
                <c:forEach items="${pb.list}" var="message" varStatus="s">
                    <div class="div_s">
                        <div class="div_r">
                            <div style="float: left;">标题：</div>
                            <div class="div_lt">${message.title}</div>
                        </div>
                        <div class="div_r">
                            <div style="float: left;">内容：</div>
                            <div class="div_lt">${message.content}</div>
                        </div>
                        <div class="div_t">
                            <div class="div_rt" style="margin-right:10px;">${message.datetime}</div>
                            <div style="float: right;">时间：</div>
                            <div style="float: left;margin-left:587px;">留言人：</div>
                            <div class="div_lt" style="margin-right:10px;">${message.user.username}</div>
                        </div>
                    </div>
                    <hr/>
                </c:forEach>
            </div>
            <div class="page_num_inf" style="margin:0 0 20px 50px;">
                <i></i> 共
                <span id="totalPage" class="page_num">${pb.totalPage}</span>页<span id="totalCount" class="page_num">${pb.totalCount}</span>条
            </div>
            <div class="pageNum" style="margin:0 0 10px 50px;">
                <ul id="pageNum">
                    <li><a href="${pageContext.request.contextPath}/findAllMessage?currentPage=1" >首页</a></li>
                    <c:if test="${pb.currentPage == 1}">
                        <li class="threeword" >上一页</li>
                    </c:if>
                    <c:if test="${pb.currentPage != 1}">
                        <li class="threeword"><a href="${pageContext.request.contextPath}/findAllMessage?currentPage=${pb.currentPage-1}">上一页</a></li>
                    </c:if>
                    <!--中间页码开始-->
                    <c:if test="${pb.totalPage < 10}">
                        <c:forEach begin="1" end="${pb.totalPage}" var="i">
                            <c:if test="${pb.currentPage == i}">
                                <li class="curPage">
                                    <a href="${pageContext.request.contextPath}/findAllMessage?currentPage=${i}">${i}</a>
                                </li>
                            </c:if>
                            <c:if test="${pb.currentPage != i}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/findAllMessage?currentPage=${i}">${i}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${pb.totalPage >= 10}">
                        <c:if test="${pb.currentPage - 5 < 1 }">
                            <c:forEach begin="1" end="10" var="i">
                                <c:if test="${pb.currentPage == i}">
                                    <li class="curPage">
                                        <a href="${pageContext.request.contextPath}/findAllMessage?currentPage=${i}">${i}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pb.currentPage != i}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/findAllMessage?currentPage=${i}">${i}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${pb.currentPage + 4 > pb.totalPage }">
                            <c:forEach begin="${pb.totalPage - 9}" end="${pb.totalPage}" var="i">
                                <c:if test="${pb.currentPage == i}">
                                    <li class="curPage">
                                        <a href="${pageContext.request.contextPath}/findAllMessage?currentPage=${i}">${i}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pb.currentPage != i}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/findAllMessage?currentPage=${i}">${i}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:if>
                    <!--中间页码结束-->
                    <c:if test="${pb.currentPage >= pb.totalPage}">
                        <li class="threeword" >下一页</li>
                    </c:if>
                    <c:if test="${pb.currentPage < pb.totalPage}">
                        <li class="threeword" ><a href="${pageContext.request.contextPath}/findAllMessage?currentPage=${pb.currentPage+1}">下一页</a></li>
                    </c:if>
                    <li><a href="${pageContext.request.contextPath}/findAllMessage?currentPage=${pb.totalPage}" >末页</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- 详情 end -->
</body>
<!--引入底部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
<script src="js/getParameter.js"></script>
</body>
</html>


