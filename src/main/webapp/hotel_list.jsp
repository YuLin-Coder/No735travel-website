<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>旅游网站-酒店住宿</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/search.css">
    <style>
        .tab-content .row>div {
            margin-top: 16px;
        }
        .tab-content {
            margin-bottom: 36px;
        }
    </style>
    <script src="js/jquery-3.3.1.js"></script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<!-- 排行榜 start-->
<section id="content">
    <section class="hemai_jx">
        <div class="jx_top">
            <div class="jx_tit">
                <img src="images/icon_5.jpg" >
                <span>精选酒店</span>
            </div>
        </div>
        <div class="jx_content" >
            <!-- Tab panes -->
            <div id="tab-content" class="tab-content" style="height: 556px;">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <div id="favoritediv" class="row">

                    </div>
                </div>
            </div>
        </div>
        <div id="page_num" class="page_num_inf" style="margin-bottom: 20px;">
            <i></i> 共
            <span id="totalPage" class="page_num"></span><span class="page_num_inf">页</span><span id="totalCount" class="page_num"></span><span class="page_num_inf">条</span>
        </div>
        <div class="pageNum">
            <ul id="pageNum">
                <%--页码--%>
            </ul>
        </div>
    </section>
</section>
<!-- 排行榜 end-->

<!--引入尾部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
<script>
    $(function () {
        load(null);
    });
    function load(currentPage) {
        $.get("hotel/pageQuery",{currentPage:currentPage},function (pb) {
            $("#totalPage").html(pb.totalPage);
            $("#totalCount").html(pb.totalCount);

            var lis = "";
            var firstPage = '<li onclick="javascript:load(1)"><a href="javascript:void(0)">首页</a></li>';

            //计算上一页的页码
            var beforeNum = pb.currentPage - 1;
            var beforePage;
            if (beforeNum <= 0){
                beforePage = '<li class="threeword">上一页</li>';
            }else{
                beforePage = '<li onclick="javascript:load('+beforeNum+')" class="threeword"><a href="javascript:void(0)">上一页</a></li>';
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
                    li = '<li class="curPage" onclick="javascipt:load('+i+')"><a href="javascript:void(0)">'+i+'</a></li>';
                } else {
                    li = '<li onclick="javascipt:load('+i+')"><a href="javascript:void(0)">'+i+'</a></li>';
                }
                lis += li;
            }

            var nextNum = pb.currentPage + 1;
            var nextPage;
            if (nextNum > pb.totalPage){
                nextPage = '<li class="threeword">下一页</li>';
            }else{
                nextPage = '<li onclick="javascript:load('+nextNum+')" class="threeword"><a href="javascript:void(0);">下一页</a></li>';
            }
            var lastPage = '<li onclick="javascript:load('+pb.totalPage+')" class="threeword"><a href="javascript:void(0);">末页</a></li>';
            lis += nextPage;
            lis += lastPage;

            //将lis内容设置到 ul
            $("#pageNum").html(lis);

            //2.展示数据
            var hotel_div = "";
            for (var i = 0;i< pb.list.length;i++){
                var hotel = pb.list[i];
                var hoteld_div =
                    '<div class="col-md-3">'+
                    '<a href="hotel_detail.jsp?hid='+hotel.hid+'">\n' +
                    '<img src="'+hotel.himage+'" style="height: 162px;width: 275px;" >\n' +
                    '<div class="has_border">\n' +
                    '<h3>'+hotel.hname+'</h3>\n' +
                    '<div><h4>'+hotel.hintroduce+'<h4></div>\n' +
                    '</div></a></div>';
                hotel_div += hoteld_div;
            }
            $("#favoritediv").html(hotel_div);
            //定位到页面顶部
            window.scrollTo(0,0);
        });
    }
</script>
</body>
</html>
