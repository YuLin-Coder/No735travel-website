<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>路线详情</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/daterangepicker.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/route-detail.css">
    <link rel="stylesheet" type="text/css" href="css/hotel-detail.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/moment.min.js"></script>
    <script src="js/daterangepicker.js"></script>
    <script>
        //加载线路详情页面
        $(function () {
            //1.获取rid
            var rid = getParameter("rid");
            var cid = getParameter("cid");
            var cname;
            if (cid==1){
                cname = "国内旅游";
            }
            if(cid == 2){
                cname = "出境旅游";
            }
            var box = '<a href="/">首页</a><span> &gt;</span>\n' +
                '<a href="route_list.jsp?cid='+cid+'">'+cname+'</a>';
            $("#bread_box").html(box);

            //2.请求route/findOne
            $.get("route/findOne",{rid:rid},function (route) {
                $("#detail_ename").html(route.rname);
                $("#routeIntroduce").html(route.routeIntroduce);
                $("#price").html("¥"+route.price);
                $("#sname").html(route.seller.sname);
                $("#consphone").html(route.seller.consphone);
                $("#address").html(route.seller.address);
                //设置收藏次数
                $("#favoriteCount").html("已收藏"+route.count+"次");

                //展示图片
                var ddstr = '<a class="up_img up_img_disable"></a>';
                //遍历routeImgList
                if (route.routeImgList.length > 0){
                    $("#big_img").prop("src",route.routeImgList[0].bigPic);
                }
                for (var i = 0; i < route.routeImgList.length; i++) {
                    var astr;
                    if(i >= 4){
                        astr = '<a class="little_img" data-bigpic="'+route.routeImgList[i].bigPic+'" style="display:none;">\n' +
                            '<img src="'+route.routeImgList[i].smallPic+'"></a>';
                    }else{
                        if (i == 0){
                            astr = '<a class="little_img cur_img" data-bigpic="'+route.routeImgList[i].bigPic+'">\n' +
                                '<img src="'+route.routeImgList[i].smallPic+'"></a>';
                        }else{
                            astr =  '<a class="little_img" data-bigpic="'+route.routeImgList[i].bigPic+'" >\n' +
                                '<img src="'+route.routeImgList[i].smallPic+'"></a>';
                        }
                    }
                    ddstr += astr;
                }
                ddstr+='<a class="down_img down_img_disable" style="margin-bottom: 0;"></a>';
                $("#routeImgDd").html(ddstr);
                //图片展示和切换代码调用
                goImg();
                setInterval("auto_play()", 5000);
            });
        });

        //点击收藏按钮触发的方法
        function choseFavorite(){
            var rid = getParameter("rid");
            //判断用户是否登录
            $.get("user/findOne",{},function (user) {
                if (user){//用户已登录
                    //添加收藏
                    $.get("route/choseFavorite",{rid:rid},function () {
                        location.reload();
                    })
                } else{//用户未登录
                    alert("您尚未登录，请登录");
                }
            });
        }

        // 发送请求，判断用户是否收藏过该线路
        $(function () {
            var rid = getParameter("rid");
            $.get("route/isFavorite",{rid:rid},function (flag) {
                if (flag){ // 用户已经收藏过
                    $("#favorite").removeAttr("class");
                } else{ // 用户未收藏过
                    $("#favorite").addClass("btn already");
                }
            });
        });
    </script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<!-- 详情 start -->
<div class="wrap">
    <div id="bread_box" class="bread_box">
<%--        <a href="/">首页</a>--%>
<%--        <span> &gt;</span>--%>
<%--        <a href="#">国内游</a><span> &gt;</span>--%>
<%--        <a href="#">全国-曼谷6-7天自由行 泰国出境旅游 特价往返机票自由行二次确认</a>--%>
    </div>
    <div class="prosum_box">
        <dl class="prosum_left">
            <dt>
                <img id="big_img" class="big_img">
            </dt>
            <dd id="routeImgDd">

            </dd>
        </dl>
        <div class="prosum_right">
            <p id="detail_ename" class="pros_title"></p>
            <p id="routeIntroduce" class="hot"></p>
            <div class="pros_other">
                <p>经营商家 ：<span id="sname"></span></p>
                <p>咨询电话 : <span id="consphone"></span></p>
                <p>地址 ：<span id="address"></span></p>
            </div>
            <div class="pros_price" style="height:200px;">
                <div class="detail_p" style="float:left;width: 100%;">
                    <div style="float: left;margin: 5px 0 0 20px;">出行日期：</div>
                    <div class="col-md-4 demo" style="margin-right: 80px;" >
                        <input type="text" id="config-demo" class="form-control">
                        <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                    </div>
                </div>
                <div class="price" style="float: left;width: 100%;margin: 10px 15px;">
                    <div style="float: left;"><strong id="price" ></strong><span>起</span></div>
                    <div style="float: left;margin: 10px 0 0 40px;">
                        购买票数：
                        <button type="button" class="btn btn-warning btn-sm "
                                onclick="dec()">-</button>
                        <button type="button" class="btn btn-sm"
                                id="count">1</button>
                        <button type="button" class="btn btn-warning btn-sm "
                                onclick="insc()">+</button>
                    </div>
                    <div style="float: right;margin: 8px 30px 0 0;">
                        <div onclick="yuding(this)" style="">
                            <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#myModal">预定</button>
                        </div>

                        <!-- Modal -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <form id="orderform" >
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                            <h4 class="modal-title" id="myModalLabel">线路订单</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="eleft">
                                                <div class="left_div">线路名称</div>
                                                <textarea id="rname" name="rname" style="float:left;width: 77%;margin-left: 10px;" class="form-control" rows="2" readonly="readonly"></textarea>
                                            </div>
                                            <div class="eleft">
                                                <div class="left_div">出行日期</div>
                                                <input id="starttime" name="starttime" style="float:left;width: 77%;margin-left: 10px;"  class="form-control" readonly="readonly" type="text" autocomplete="off" >
                                            </div>
                                            <div class="eleft">
                                                <div class="left_div">购买票数</div>
                                                <input id="totalp" name="totalp" style="float:left;width: 77%;margin-left: 10px;" readonly="readonly"  class="form-control" type="text" autocomplete="off" >
                                            </div>
                                            <div class="eleft">
                                                <div class="left_div">支付金额</div>
                                                <input id="paymoney" name="paymoney" style="float:left;width: 77%;margin-left: 10px;" readonly="readonly" class="form-control" type="text" autocomplete="off" >
                                            </div>
                                            <div class="eleft">
                                                <div class="left_div">购票人</div>
                                                <input id="uname" name="uname" style="float:left;width: 77%;margin-left: 10px;" readonly="readonly"  class="form-control" type="text" autocomplete="off">
                                            </div>
                                            <div class="eleft">
                                                <div class="left_div">联系电话</div>
                                                <input id="telephone" name="telephone" style="float:left;width: 77%;margin-left: 10px;" readonly="readonly"  class="form-control" type="text" autocomplete="off">
                                            </div>
                                            <input id="uid" name="uid" type="hidden" >
                                            <input id="rid" name="rid" type="hidden" >
                                            <input id="sid" name="sid" type="hidden" >
                                        </div>
                                        <div class="modal-footer" style="text-align: center;">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                            <button type="button" class="btn btn-primary" onclick="addorder()">确定</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="collect" style="float: left; margin: 10px 15px;">
                    <a id="favorite" onclick="choseFavorite();" class="btn already"><i class="glyphicon glyphicon-heart-empty"></i>点击收藏</a>

                    <span id="favoriteCount"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="you_need_konw">
        <span>旅游须知</span>
        <div class="notice">
            <p>1、旅行社已投保旅行社责任险。建议游客购买旅游意外保险 <br>

            <p>2、旅游者参加打猎、潜水、海边游泳、漂流、滑水、滑雪、滑草、蹦极、跳伞、滑翔、乘热气球、骑马、赛车、攀岩、水疗、水上飞机等属于高风险性游乐项目的，敬请旅游者务必在参加前充分了解项目的安全须知并确保身体状况能适应此类活动；如旅游者不具备较好的身体条件及技能，可能会造成身体伤害。</p>

            <p>3、参加出海活动时，请务必穿着救生设备。参加水上活动应注意自己的身体状况，有心脏病、冠心病、高血压、感冒、发烧和饮酒及餐后不可以参加水上活动及潜水。在海里活动时，严禁触摸海洋中各种鱼类，水母，海胆，珊瑚等海洋生物，避免被其蛰伤。老人和小孩必须有成年人陪同才能参加合适的水上活动。在海边游玩时，注意保管好随身携带的贵重物品。</p>

            <p>4、根据中国海关总署的规定，旅客在境外购买的物品，在进入中国海关时可能需要征收关税。详细内容见《中华人民共和国海关总署公告2010年第54号文件》。</p>

            <p>5、建议出发时行李托运，贵重物品、常用物品、常用药品、御寒衣物等请随身携带，尽量不要托运。行李延误属于不可抗力因素，我司将全力协助客人跟进后续工作，但我司对此不承担任何责任。</p>
            <p>1、旅行社已投保旅行社责任险。建议游客购买旅游意外保险 <br>

            <p>2、旅游者参加打猎、潜水、海边游泳、漂流、滑水、滑雪、滑草、蹦极、跳伞、滑翔、乘热气球、骑马、赛车、攀岩、水疗、水上飞机等属于高风险性游乐项目的，敬请旅游者务必在参加前充分了解项目的安全须知并确保身体状况能适应此类活动；如旅游者不具备较好的身体条件及技能，可能会造成身体伤害。</p>

            <p>3、参加出海活动时，请务必穿着救生设备。参加水上活动应注意自己的身体状况，有心脏病、冠心病、高血压、感冒、发烧和饮酒及餐后不可以参加水上活动及潜水。在海里活动时，严禁触摸海洋中各种鱼类，水母，海胆，珊瑚等海洋生物，避免被其蛰伤。老人和小孩必须有成年人陪同才能参加合适的水上活动。在海边游玩时，注意保管好随身携带的贵重物品。</p>

            <p>4、根据中国海关总署的规定，旅客在境外购买的物品，在进入中国海关时可能需要征收关税。详细内容见《中华人民共和国海关总署公告2010年第54号文件》。</p>

            <p>5、建议出发时行李托运，贵重物品、常用物品、常用药品、御寒衣物等请随身携带，尽量不要托运。行李延误属于不可抗力因素，我司将全力协助客人跟进后续工作，但我司对此不承担任何责任。</p>
        </div>
    </div>
</div>
<!-- 详情 end -->

<!--引入头部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
<script src="js/getParameter.js"></script>
<script type="text/javascript" src="js/route_detail.js"></script>
<script>
    function insc() {
        var count=document.getElementById("count").innerHTML;
        if (parseInt(count)<10){
            document.getElementById("count").innerHTML=parseInt(count)+1;
        } else{
            confirm("单次最多预定10张！");
        }
    }
    function dec() {
        var count=document.getElementById("count").innerHTML;
        if(parseInt(count)>1){
            document.getElementById("count").innerHTML=parseInt(count)-1;
        }
    }
    /*设置日期控件*/
    var beginTimeStore = '';
    var endTimeStore = '';
    $('#config-demo').daterangepicker({
        "singleDatePicker":true,
        "timePicker": false,
        "linkedCalendars": false,
        "autoUpdateInput": false,
        "minDate":new Date(),
        "maxDate":moment().add(30,'day'),
        "locale": {
            format: 'YYYY-MM-DD',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            customRangeLabel : '自定义',
            daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
            monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月','七月', '八月', '九月', '十月', '十一月', '十二月']
        }
    }, function (start,end,label) {
        beginTimeStore = start;
        if(!this.startDate){
            this.element.val('');
        }else{
            this.element.val(this.startDate.format(this.locale.format));
        }
    });
    /*预定*/
    function yuding(obj){
        $.get("user/findOne",{},function (data) {
            if (data != null){
                var uid = data.uid;//用户id
                var starttime = $("#config-demo").val();//出行时间
                if (starttime == ""){
                    $(".modal-body").html("请输入您要出行的时间");
                }else {
                    var totalp = $("#count").text();//票数
                    var uname = data.name;//用户名
                    var tel = data.telephone;//联系电话
                    var rid = getParameter("rid");//线路id
                    $.get("route/findOne",{rid:rid},function (route) {
                        var price = route.price;//单张价格
                        var totalprice = totalp * price ;//总价格
                        $("#rname").val(route.rname);
                        $("#starttime").val(starttime);
                        $("#totalp").val(totalp+"张");
                        $("#paymoney").val(totalprice);
                        $("#uname").val(uname);
                        $("#telephone").val(tel);
                        $("#uid").val(uid);
                        $("#rid").val(rid);
                        $("#sid").val(route.seller.sid);
                    });
                }
            } else{
                $(".modal-body").html("您尚未登录，请登录");
            }
        });
    }
    /*预定-确定*/
    function addorder() {
        var rid = getParameter("rid");
        var cid = getParameter("cid");
        $.get("user/findOne",{},function (data) {
            if (data){
                var live = $("#config-demo").val();
                if (live == ""){
                    location.href = "route_detail.jsp?cid="+cid+"&rid="+rid ;
                } else{
                    $("#myModal").hide();
                    $.post("order/OrderRoute",$("#orderform").serialize(),function(data){
                        if(data.flag){
                            confirm("预定成功");
                            location.href = "route_detail.jsp?cid="+cid+"&rid="+rid ;
                        }else{
                            confirm(data.errorMsg);
                            location.href = "route_detail.jsp?cid="+cid+"&rid="+rid ;
                        }
                    });
                }
            }else{
                location.href = "/login.jsp";
            }
        });
    }
</script>
</body>
</html>
