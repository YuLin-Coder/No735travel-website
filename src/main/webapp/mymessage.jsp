<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>个人信息-我的留言</title>
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<!-- 我的留言start-->
<section class="hemai_jx">
    <div class="jx_top">
        <div class="jx_tit">
            <img src="images/icon_5.jpg" >
            <span>我的留言</span>
        </div>
    </div>
    <div class="jx_content" >
        <!-- Tab panes -->
        <div id="tab-content" class="tab-content" style="height: 486px;">
            <div role="tabpanel" class="tab-pane active" id="home">
                <div id="favoritediv">
                    <div style="width: 1200px;margin-left: 30px;">
                        <div id="leavefor" style="width: 1000px;margin-top: 20px;">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="footerdiv" style="margin-left: 30px;">
        <div id="page_num" class="page_num_inf" style="margin-bottom: 20px;">
            <i></i> 共
            <span id="totalPage" class="page_num"></span><span class="page_num_inf">页</span><span id="totalCount" class="page_num"></span><span class="page_num_inf">条</span>
        </div>
        <div class="pageNum">
            <ul id="pageNum">
                <%--页码--%>
            </ul>
        </div>
    </div>
</section>
<!-- 我的留言 end-->
<script>
    $(function () {
        $.get("user/findOne",{},function (user) {
            if (user){//用户已登录
                var uid = user.uid;
                load(uid,null);
            } else{//用户未登录

            }
        });
    });
    function del(obj) {
        var mid = $(obj).find("div").text();
        var r = confirm("您确定删除该条留言吗？");
        if (r && mid != "") {
            $.get("personal/deletemessage",{mid:mid},function (object) {
                if (object != null) {
                    location.href = "personal.jsp?varstr=mymessage";
                }else{
                    alert("删除失败！");
                }
            });
        }
    }
    function load(uid,currentPage) {
        $.get("personal/findMessageByPage",{uid:uid,currentPage:currentPage},function (pb) {
            $("#totalPage").html(pb.totalPage);
            $("#totalCount").html(pb.totalCount);

            var lis = "";
            var firstPage = '<li onclick="javascript:load('+uid+',1)"><a href="javascript:void(0)">首页</a></li>';

            //计算上一页的页码
            var beforeNum = pb.currentPage - 1;
            var beforePage;
            if (beforeNum <= 0){
                beforePage = '<li class="threeword">上一页</li>';
            }else{
                beforePage = '<li onclick="javascript:load('+uid+','+beforeNum+')" class="threeword"><a href="javascript:void(0)">上一页</a></li>';
            }
            lis += firstPage;
            lis += beforePage;

            // 定义开始位置begin,结束位置 end
            var begin; // 开始位置
            var end ; //  结束位置
            //1.要显示10个页码
            if(pb.totalPage < 10){
                //总页码不够10页
                begin = 1;
                end = pb.totalPage;
            }else{
                //总页码超过10页
                begin = pb.currentPage - 5 ;
                end = pb.currentPage + 4 ;
                //2.如果前边不够5个，后边补齐10个
                if(begin < 1){
                    begin = 1;
                    end = begin + 9;
                }
                //3.如果后边不足4个，前边补齐10个
                if(end > pb.totalPage){
                    end = pb.totalPage;
                    begin = end - 9 ;
                }
            }
            //是否为当前页面,渲染
            for (var i = begin;i <= end;i++) {
                var li;
                if (pb.currentPage == i) {
                    li = '<li class="curPage" onclick="javascipt:load('+uid+','+i+')"><a href="javascript:void(0)">'+i+'</a></li>';
                } else {
                    li = '<li onclick="javascipt:load('+uid+','+i+')"><a href="javascript:void(0)">'+i+'</a></li>';
                }
                lis += li;
            }

            var nextNum = pb.currentPage + 1;
            var nextPage;
            if (nextNum > pb.totalPage){
                nextPage = '<li class="threeword">下一页</li>';
            }else{
                nextPage = '<li onclick="javascript:load('+uid+','+nextNum+')" class="threeword"><a href="javascript:void(0);">下一页</a></li>';
            }
            var lastPage = '<li onclick="javascript:load('+uid+','+pb.totalPage+')" class="threeword"><a href="javascript:void(0);">末页</a></li>';
            lis += nextPage;
            lis += lastPage;

            //将lis内容设置到 ul
            $("#pageNum").html(lis);

            //2.展示数据
            var message_div = "";
            for (var i = 0;i< pb.list.length;i++){
                var message = pb.list[i];
                message_div += '<div class="div_s"><div class="div_r">' +
                    '<div style="float:left;">标题：</div>' +
                    '<div class="div_lt">'+message.title+'</div></div>' +
                    '<div class="div_r">' +
                    '<div style="float:left;">内容：</div>' +
                    '<div class="div_lt">'+message.content+'</div></div>' +
                    '<div class="div_t">' +'<div class="del" onclick="del(this)">' +
                    '<div style="display:none;">'+message.mid+'</div><a href="javascript:void(0)">删除</a></div>'+
                    '<div class="div_rt" style="margin-right:10px;">'+message.datetime+'</div>' +
                    '<div style="float:right;">时间：</div>' +
                    '<div style="float:left;margin-left:587px;">留言人：</div>' +
                    '<div class="div_lt" style="margin-right:10px;">'+message.user.username+'</div>' +
                    '</div></div>' +
                    '<hr/>';
            }
            $("#leavefor").html(message_div);
            //定位到页面顶部
            window.scrollTo(0,0);
        });
    }
</script>
</body>
</html>
