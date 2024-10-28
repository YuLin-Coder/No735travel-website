/*设置日期控件*/
var beginTimeStore = '';
var endTimeStore = '';
$('#config-demo').daterangepicker({
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
}, function(start, end,label) {
    beginTimeStore = start;
    endTimeStore = end;
    // console.log(this.startDate.format(this.locale.format));
    // console.log(this.endDate.format(this.locale.format));
    if(!this.startDate){
        this.element.val('');
    }else{
        this.element.val(this.startDate.format(this.locale.format) + this.locale.separator + this.endDate.format(this.locale.format));
    }
});
/*预定房间*/
$(function () {
    var hid = getParameter("hid");
    $.get("hotel/findOne",{hid:hid},function (hotel) {
        $("#big_img").prop("src",hotel.himage);
        $("#detail_ename").html(hotel.hname);
        $("#Introduce").html("酒店简介："+hotel.hintroduce);
        $("#address").html("酒店地址："+hotel.address);
        $("#telphone").html("酒店电话："+hotel.telphone);
        if (hotel.hoteldetail.length > 0){
            var li = "";
            var btns = "";
            for(var i=0; i < hotel.hoteldetail.length;i++){
                li += '<div style="overflow: hidden;"><div class="room_type">'+hotel.hoteldetail[i].hoteltype.tname+'</div>\n' +
                    '<div class="room_price">'+hotel.hoteldetail[i].price+'</div><div class="room_price">元</div>' +
                    '<div onclick="yuding(this)" class="room_btn" id="'+hotel.hoteldetail[i].hoteltype.tid+'">' +
                    '<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#myModal">预定</button></div></div>';
                btns += '';
            }
            $("#roomtype").html(li);
            $("#r_btns").html(btns);
        }
    });
});
//字符串转日期函数
function getDate(strDate){
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
        function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
    return date;
}
/*预定*/
function yuding(obj){
    var tid = obj.id;
    $.get("user/findOne",{},function (data) {
        if (data != null){
            var uid = data.uid;
            var live = $("#config-demo").val();
            if (live == ""){
                $(".modal-body").html("请输入您要入住的时间");
            }else {
                var beginday = live.substring(0,10);
                var endday = live.substring(13,23);
                //居住时间
                var totaldays = (getDate(endday) - getDate(beginday))/(24*60*60*1000);
                var price = $(obj).prev("div").prev("div").text();//单日价格
                //总价格
                var totalprice = totaldays * price ;

                var uname = data.name;
                var tel = data.telephone;
                var hid = getParameter("hid");
                var tname = $(obj).prev("div").prev("div").prev("div").text();
                $.get("hotel/findOne",{hid:hid},function (hotel) {
                    $("#hname").val(hotel.hname);
                    $("#uname").val(uname);
                    $("#telephone").val(tel);
                    $("#beginday").val(beginday+" 14:00");
                    $("#endday").val(endday+" 12:00");
                    $("#totaldays").val(totaldays+"天");
                    $("#tname").val(tname);
                    $("#paymoney").val(totalprice);
                    $("#uid").val(uid);
                    $("#hid").val(hid);
                    $("#tid").val(tid);
                });
            }
        } else{
            $(".modal-body").html("您尚未登录，请登录");
        }
    });
}
/*预定-确定*/
function addorder() {
    var hid = getParameter("hid");
    $.get("user/findOne",{},function (data) {
        if (data){
            var live = $("#config-demo").val();
            if (live == ""){
                location.href = "hotel_detail.jsp?hid="+hid ;
            } else{
                $("#myModal").hide();
                $.post("order/addOrder",$("#orderform").serialize(),function(data){
                    if(data.flag){
                        confirm("预定成功");
                        location.href = "hotel_detail.jsp?hid="+hid ;
                    }else{
                        confirm(data.errorMsg);
                        location.href = "hotel_detail.jsp?hid="+hid ;
                    }
                });
            }
        }else{
            location.href = "hotel_detail.jsp?hid="+hid ;
        }
    });
}
