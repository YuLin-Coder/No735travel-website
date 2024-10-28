<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>旅游网站-收藏排行榜</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/ranking-list.css">
    <link rel="stylesheet" href="css/search.css">
    <script src="js/jquery-3.3.1.js"></script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<div class="contant">
    <div id="shaixuan" class="shaixuan">
        <span>线路名称</span>
        <input id="in_rname" type="text">
        <span>金额</span>
        <input id="start_money" type="text">~<input id="end_money" type="text">
        <button id="search">搜索</button>
    </div>
    <div class="list clearfix" style="width: 1200px;">
        <ul id="route">
<%--            <li></li>--%>
        </ul>
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
</div>

<!--导入底部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
<script>
    $("#search").click(function () {
        var rname = $("#in_rname").val();
        var smoney = $("#start_money").val();
        var emoney = $("#end_money").val();
        // alert("rname="+rname+",smoney="+smoney+",emoney="+emoney);
        if (rname == "" && smoney == "" && emoney == ""){//无条件
            alert("请输入搜索搜索内容!");
        }
        if (rname != "" && smoney == "" && emoney == ""){//根据rname
            load(null,rname,null,null);
        }
        if (rname =="" && smoney != "" && emoney == ""){//根据price>=
            load(null,null,smoney,null);
        }
        if (rname =="" && smoney == "" && emoney != ""){//根据price<=
            load(null,null,null,emoney)
        }
        if (rname == "" && smoney != "" && emoney != ""){//根据price
            load(null,null,smoney,emoney)
        }
        if (rname !="" && smoney != "" && emoney == ""){//根据rname和price>=
            load(null,rname,smoney,null);
        }
        if (rname !="" && smoney == "" && emoney != ""){//根据rname和price<=
            load(null,rname,null,emoney);
        }
        if (rname != "" && smoney != "" && emoney != ""){//根据rname和price
            load(null,rname,smoney,emoney);
        }
    });
    $(function () {
        // var rname = getParameter("rname");//获取rname的参数值
        load(null,null,null,null);
    });

    function load(currentPage,rname,smoney,emoney) {
        $.get("favoriteTop/pageQuery",{currentPage:currentPage,rname:rname,smoney:smoney,emoney:emoney},function (pb) {
            $("#totalPage").html(pb.totalPage);
            $("#totalCount").html(pb.totalCount);

            var lis = "";
            var firstPage = '<li onclick="javascript:load(1,\''+rname+'\','+smoney+','+emoney+')"><a href="javascript:void(0)">首页</a></li>';

            //计算上一页的页码
            var beforeNum = pb.currentPage - 1;
            var beforePage;
            if (beforeNum <= 0){
                beforePage = '<li class="threeword">上一页</li>';
            }else{
                beforePage = '<li onclick="javascript:load('+beforeNum+',\''+rname+'\','+smoney+','+emoney+')" class="threeword"><a href="javascript:void(0)">上一页</a></li>';
            }

            lis += firstPage;
            lis += beforePage;

            //1.2 展示分页页码

            var begin; // 开始位置
            var end ; //  结束位置

            if (pb.totalPage < 10){//不足10页
                begin = 1;
                end = pb.totalPage;
            } else {
                begin = pb.currentPage - 5;
                end = pb.currentPage + 4;

                //如果前边不够5个，后边补齐10个
                if(begin < 1){
                    begin = 1;
                    end = begin + 9;
                }

                //如果后边不足4个，前边补齐10个
                if (end > pb.totalPage){
                    end = pb.totalPage;
                    begin = end - 9;
                }
            }
            //是否为当前页面,渲染
            for (var i = begin;i <= end;i++) {
                var li;
                if (pb.currentPage == i) {
                    li = '<li class="curPage" onclick="javascipt:load('+i+',\''+rname+'\','+smoney+','+emoney+')"><a href="javascript:void(0)">'+i+'</a></li>';
                } else {
                    li = '<li onclick="javascipt:load('+i+',\''+rname+'\','+smoney+','+emoney+')"><a href="javascript:void(0)">'+i+'</a></li>';
                }
                lis += li;
            }

            var nextNum = pb.currentPage + 1;
            var nextPage;
            if (nextNum > pb.totalPage){
                nextPage = '<li class="threeword">下一页</li>';
            }else{
                nextPage = '<li onclick="javascript:load('+nextNum+',\''+rname+'\','+smoney+','+emoney+')" class="threeword"><a href="javascript:void(0);">下一页</a></li>';
            }
            var lastPage = '<li onclick="javascript:load('+pb.totalPage+',\''+rname+'\','+smoney+','+emoney+')" class="threeword"><a href="javascript:void(0);">末页</a></li>';

            lis += nextPage;
            lis += lastPage;
            //将lis内容设置到 ul
            $("#pageNum").html(lis);

            //2.列表数据展示
            var route_lis = "";
            if (pb.list.length == 0){
                route_lis="<div style='text-align: center;font-size: 30px;margin:100px 0 80px 0;'>暂无符合搜索范围的内容</div>";
            }
            for (var i=0;i<pb.list.length;i++){
                //获取{rid:1,rname:"xxx"}
                var route = pb.list[i];
                var li = '';
                if ( (i+(pb.currentPage-1)*8) == 0){
                    li = '<li><span class="num one">'+ ((1+i)+(pb.currentPage-1)*8) +'</span>\n' +
                        '<a href="route_detail.jsp?cid='+route.cid+'&rid='+route.rid+'"><img src="'+route.rimage+'"></a>\n' +
                        '<h4><a href="route_detail.jsp?cid='+route.cid+'&rid='+route.rid+'">'+route.rname+'</a></h4>\n' +
                        '<p><b class="price">&yen;<span>'+route.price+'</span>起</b>\n' +
                        '<span class="shouchang">已收藏 '+route.count+' 次</span>\n' +
                        '</p></li>';
                }else if((i+(pb.currentPage-1)*8) == 1){
                    li = '<li><span class="num two">'+ ((1+i)+(pb.currentPage-1)*8) +'</span>\n' +
                        '<a href="route_detail.jsp?cid='+route.cid+'&rid='+route.rid+'"><img src="'+route.rimage+'"></a>\n' +
                        '<h4><a href="route_detail.jsp?cid='+route.cid+'&rid='+route.rid+'">'+route.rname+'</a></h4>\n' +
                        '<p><b class="price">&yen;<span>'+route.price+'</span>起</b>\n' +
                        '<span class="shouchang">已收藏 '+route.count+' 次</span>\n' +
                        '</p></li>';
                }else{
                    li = '<li><span class="num">'+ ((1+i)+(pb.currentPage-1)*8) +'</span>\n' +
                        '<a href="route_detail.jsp?cid='+route.cid+'&rid='+route.rid+'"><img src="'+route.rimage+'"></a>\n' +
                        '<h4><a href="route_detail.jsp?cid='+route.cid+'&rid='+route.rid+'">'+route.rname+'</a></h4>\n' +
                        '<p><b class="price">&yen;<span>'+route.price+'</span>起</b>\n' +
                        '<span class="shouchang">已收藏 '+route.count+' 次</span>\n' +
                        '</p></li>';
                }
                route_lis += li;
            }
            $("#route").html(route_lis);
            //定位到页面顶部
            window.scrollTo(0,0);
        });
    }
</script>
</body>
</html>
