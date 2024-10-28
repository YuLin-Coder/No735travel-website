<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>个人信息-线路订单</title>
    <script src="js/jquery-3.3.1.js"></script>
</head>
<body>
<section class="hemai_jx">
    <div class="jx_top">
        <div class="jx_tit">
            <img src="images/icon_5.jpg" >
            <span>景点订单</span>
        </div>
    </div>
    <div class="jx_content" >
        <!-- Tab panes -->
        <div id="tab-content" class="tab-content" style="height: 496px;">
            <div role="tabpanel" class="tab-pane active" id="home">
                <div id="favoritediv" class="row">

                </div>
            </div>
        </div>
    </div>
    <div id="footerdiv">
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
<!-- 排行榜 end-->
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
    function load(uid,currentPage) {
        $.get("personal/findROrderByPage",{uid:uid,currentPage:currentPage},function (pb) {
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
            var order_div = "";
            for (var i = 0;i< pb.list.length;i++){
                var order = pb.list[i];
                var orderd_div =
                    '<div class="col-md-4" style="height: 250px;">'+
                    '<div style="width:50%;float: left;"><a href="route_detail.jsp?cid='+order.route.cid+'&rid='+order.rid+'"><img src="'+order.route.rimage+'" ></a></div>'+
                    '<div style="width: 50%;float: left;padding: 10px 0 0 10px;"><span style="float: left;">'+order.rname+'</span></div>'+
                    '<div class="eldiv"><div class="dleft">出行时间</div><div class="dright">'+order.starttime+'</div>' +
                    '<div class="eldiv"><div class="dleft">购&nbsp;票&nbsp;人</div><div class="dright">'+order.uname+'</div></div>' +
                    '<div class="eldiv"><div class="dleft">联系电话</div><div class="dright">'+order.telephone+'</div></div>' +
                    '<div class="elbdiv"><div class="dleft">总&emsp;&emsp;价</div><div class="dright">'+order.paymoney+'元</div></div>' +
                    '</div></div>';
                order_div += orderd_div;
            }
            $("#favoritediv").html(order_div);
            //定位到页面顶部
            window.scrollTo(0,0);
        });
    }
</script>
</body>
</html>
