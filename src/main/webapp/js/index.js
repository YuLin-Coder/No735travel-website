$(function () {
    //加载旅游精选
    hotfunc();

    load(1,6);//加载国内游
    load(2,6);//加载境外游
});
function hotfunc(){//人气旅游
    $.get("route/findTopRoute",{num:4},function (toproute) {
        var li= "";
        for (var i=0;i<toproute.length;i++){
            li += '<div class="col-md-3">' +
                '<a href="route_detail.jsp?cid='+toproute[i].cid+'&rid='+toproute[i].rid+'">\n' +
                '<img src="'+toproute[i].rimage+'" style="width: 275px;height: 160;">\n' +
                '<div class="has_border">\n' +
                '<h3>'+toproute[i].rname+'</h3>\n' +
                '<div class="price">网付价<em>￥</em><strong>'+toproute[i].price+'</strong><em>起</em></div>\n' +
                '</div></a></div>';
        }
        $("#jingxuan").html(li);
    });
}

function newfunc() {//最新旅游
    $.get("route/findNewRoute",function (toproute) {
        var li= "";
        for (var i=0;i<toproute.length;i++){
            li += '<div class="col-md-3">' +
                '<a href="route_detail.jsp?rid='+toproute[i].rid+'">\n' +
                '<img src="'+toproute[i].rimage+'" style="width: 275px;height: 160;">\n' +
                '<div class="has_border">\n' +
                '<h3>'+toproute[i].rname+'</h3>\n' +
                '<div class="price">网付价<em>￥</em><strong>'+toproute[i].price+'</strong><em>起</em></div>\n' +
                '</div></a></div>';
        }
        $("#jingxuan").html(li);
    });
}

function themefunc() {//主题旅游
    $.get("route/findThemeRoute",function (toproute) {
        var li= "";
        for (var i=0;i<toproute.length;i++){
            li += '<div class="col-md-3">' +
                '<a href="route_detail.jsp?rid='+toproute[i].rid+'">\n' +
                '<img src="'+toproute[i].rimage+'" style="width: 275px;height: 160;">\n' +
                '<div class="has_border">\n' +
                '<h3>'+toproute[i].rname+'</h3>\n' +
                '<div class="price">网付价<em>￥</em><strong>'+toproute[i].price+'</strong><em>起</em></div>\n' +
                '</div></a></div>';
        }
        $("#jingxuan").html(li);
    });
}

function load(cid,num){//国内游，境外游
    $.get("route/findTopRoute",{cid:cid,num:num},function (toproute) {
        var li= "";
        for (var i=0;i<toproute.length;i++){
            li += '<div class="col-md-4"><a href="route_detail.jsp?cid='+toproute[i].cid+'&rid='+toproute[i].rid+'">\n' +
                '<img src="'+toproute[i].rimage+'" style="height: 140px;width: 241px;">\n' +
                '<div class="has_border">\n' +
                '<h3>'+toproute[i].rname+'</h3>\n' +
                '<div class="price">网付价<em>￥</em><strong>'+toproute[i].price+'</strong><em>起</em></div>\n' +
                '</div></a></div>';
        }
        if (cid == 1){
            $("#guonei").html(li);//加载国内游
        }
        if(cid == 2){
            $("#guowai").html(li);//加载境外游
        }
    });
}