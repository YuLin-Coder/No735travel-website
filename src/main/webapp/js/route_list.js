/*线路分页及列表数据*/
$(function () {
    var cid = getParameter("cid");//获取cid的参数值
    var rname = getParameter("rname");//获取rname的参数值
    if (cid == 1){
        $("#tophead").html("<span>国内景点及线路信息</span><span class=\"jg\">价格</span>");
    }else if (cid == 2) {
        $("#tophead").html("<span>境外景点及线路信息</span><span class=\"jg\">价格</span>");
    }

    if (rname){//判断rname如果不为null或者""
        rname = window.decodeURIComponent(rname);//解码
    }else{
        rname = "";
    }
    load(cid,null,rname);
    $.get("route/findTopRoute",{cid:cid,num:5},function (toproute) {
        if(toproute.length <1 ){
        }else{
            var li= "";
            for (var i=0;i<toproute.length;i++){
                li += '<li><div class="left"><a href="route_detail.jsp?cid='+toproute[i].cid+'&rid='+toproute[i].rid+'"><img style="height: 71px;width: 124px;" src="'+toproute[i].rimage+'"></a></div>\n' +
                    '<div class="right">\n' +
                    '<p>'+toproute[i].rname+'...</p>\n' +
                    '<p>网付价<span>&yen;<span>'+toproute[i].price+'</span>起</span></p>\n' +
                    '</div></li>';
            }
            $("#topul").html(li);
        }
    });
});

function load(cid,currentPage,rname) {
    //发送ajax请求，请求route/pageQuery
    $.get("route/pageQuery",{cid:cid,currentPage:currentPage,rname:rname},function (pb) {
        //解析pagebean数据。展示到页面上
        //1.分页工具条数据展示
        //1.1 展示总页码和总记录数
        $("#totalPage").html(pb.totalPage);
        $("#totalCount").html(pb.totalCount);

        var lis = "";
        var firstPage = '<li onclick="javascript:load(1,\''+rname+'\')"><a href="javascript:void(0)">首页</a></li>';

        //计算上一页的页码
        var beforeNum = pb.currentPage - 1;
        var beforePage;
        if (beforeNum <= 0){
            beforePage = '<li class="threeword">上一页</li>';
        }else{
            beforePage = '<li onclick="javascript:load('+beforeNum+',\''+rname+'\')" class="threeword"><a href="javascript:void(0)">上一页</a></li>';
        }

        lis += firstPage;
        lis += beforePage;

        //1.2 展示分页页码
        /*
            1.一共展示10个页码，能够达到前5后4的效果
            2.如果前边不够5个，后边补齐10个
            3.如果后边不足4个，前边补齐10个
        */
        // 定义开始位置begin,结束位置 end
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
                li = '<li class="curPage" onclick="javascipt:load('+cid+','+i+',\''+rname+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            } else {
                li = '<li onclick="javascipt:load('+cid+','+i+',\''+rname+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            }
            lis += li;
        }

        var nextNum = pb.currentPage + 1;
        var nextPage;
        if (nextNum > pb.totalPage){
            nextPage = '<li class="threeword">下一页</li>';
        }else{
            nextPage = '<li onclick="javascript:load('+cid+','+nextNum+',\''+rname+'\')" class="threeword"><a href="javascript:void(0);">下一页</a></li>';
        }
        var lastPage = '<li onclick="javascript:load('+cid+','+pb.totalPage+',\''+rname+'\')" class="threeword"><a href="javascript:void(0);">末页</a></li>';

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
            var li = '<li>\n' +
                '<div class="img"><img src="'+route.rimage+'" style="width: 299px;height: 169px;"></div>\n' +
                '<div class="text1"><p>'+route.rname+'</p><br/>' +
                '<p>'+route.routeIntroduce+'</p></div>' +
                '<div class="price"><p class="price_num">\n' +
                '    <span>&yen;</span>\n' +
                '    <span>'+route.price+'</span>\n' +
                '    <span>起</span></p>\n' +
                '  <p><a href="route_detail.jsp?cid='+cid+'&rid='+route.rid+'">查看详情</a></p>\n' +
                '</div></li>';
            route_lis += li;
        }
        $("#route").html(route_lis);
        //定位到页面顶部
        window.scrollTo(0,0);
    });
}