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
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<!-- 详情 start -->
<div class="wrap">
    <div class="prosum_box">
        <dl class="prosum_left" style="width: 604px;">
            <dt>
                <img id="big_img" src="" class="big_img">
            </dt>

        </dl>
        <div class="prosum_right" style="width: 512px;">
            <p id="detail_ename" class="pros_title"></p>
            <p id="Introduce" class="detail_p"></p>
            <p id="address" class="detail_p"></p>
            <p id="telphone" class="detail_p"></p>
<%--            <p class="detail_p">房型：</p>--%>
            <div>
                <div class="detail_p" style="float: left;width: 10%;margin-top: 15px;">房型：</div>
                <div class="detail_p" style="float:left;width: 90%;">
                    <div class="col-md-4 demo" >
                        <input type="text" id="config-demo" class="form-control">
                        <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                    </div>
                    <span style="float: right;margin-top: 5px;">入住日期：</span>
                </div>
                <!-- Button trigger modal -->


                <!-- Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form id="orderform" action="order">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">酒店订单</h4>
                            </div>
                            <div class="modal-body">
                                <div class="eleft">
                                    <div class="left_div"><strong>酒店名称</strong></div>
                                    <input id="hname" name="hname" style="float:left;width: 77%;margin-left: 10px;" readonly="readonly"  class="form-control" type="text" autocomplete="off" >
                                </div>
                                <div class="eleft">
                                    <div class="left_div"><strong>入住房型</strong></div>
                                    <input id="tname" name="tname" style="float:left;width: 77%;margin-left: 10px;"  class="form-control" readonly="readonly" type="text" autocomplete="off" >
                                </div>
                                <div class="eleft">
                                    <div class="left_div"><strong>入&nbsp;住&nbsp;人</strong></div>
                                    <input id="uname" name="uname" style="float:left;width: 77%;margin-left: 10px;" readonly="readonly"  class="form-control" type="text" autocomplete="off" >
                                </div>
                                <div class="eleft">
                                    <div class="left_div"><strong>联系电话</strong></div>
                                    <input id="telephone" name="telephone" style="float:left;width: 77%;margin-left: 10px;" readonly="readonly"  class="form-control" type="text" autocomplete="off">
                                </div>
                                <div class="eleft">
                                    <div class="left_div"><strong>入住时间</strong></div>
                                    <input id="beginday" name="beginday" style="float:left;width: 77%;margin-left: 10px;"  class="form-control" readonly="readonly" type="text" autocomplete="off" >
                                </div>
                                <div class="eleft">
                                    <div class="left_div"><strong>离开时间</strong></div>
                                    <input id="endday" name="endday" style="float:left;width: 77%;margin-left: 10px;"  class="form-control" readonly="readonly" type="text" autocomplete="off" >
                                </div>
                                <div class="eleft">
                                    <div class="left_div"><strong>入住天数</strong></div>
                                    <input id="totaldays" name="totaldays" style="float:left;width: 77%;margin-left: 10px;"  class="form-control" readonly="readonly" type="text" autocomplete="off" >
                                </div>
                                <div class="eleft">
                                    <div class="left_div"><strong>价&emsp;&emsp;格</strong></div>
                                    <input id="paymoney" name="paymoney" style="float:left;width: 77%;margin-left: 10px;" readonly="readonly" class="form-control" type="text" autocomplete="off" >
                                </div>
                                <input id="uid" name="uid" type="hidden" >
                                <input id="hid" name="hid" type="hidden" >
                                <input id="tid" name="tid" type="hidden" >
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
            <div id="room" style="margin-top: 20px;">
                <div id="roomtype" class="room_tp"></div>
                <div id="r_btns" class="room_btns"></div>
            </div>
        </div>
    </div>
    <div class="you_need_konw">
        <span style="color: #383838;font-size: 18px;">入住须知</span>
        <div class="notice" style="height: 230px;">
            <div class="botoom_div">
                <div class="div_top">入离时间</div>
                <div class="div_bottom">入住时间：14:00以后      离店时间：12:00以前</div>
            </div>
            <div class="botoom_div">
                <div class="div_top">餐食政策</div>
                <div class="div_bottom">酒店不提供早餐</div>
            </div>
            <div class="botoom_div">
                <div class="div_top">宠物政策</div>
                <div class="div_bottom">不可携带宠物。</div>
            </div>
            <div class="botoom_div">
                <div class="div_top">儿童政策</div>
                <div class="div_bottom">不接受18岁以下客人单独入住。 不接受18岁以下客人在无监护人陪同的情况下入住。床位不接待18岁以下儿童。 加床政策、儿童人数请参见您所选定的客房政策，若超过房型限定人数，可能需收取额外费用。提出的任何要求均需获得酒店的确认，所有服务详情以酒店告知为准。</div>
            </div>
        </div>
    </div>
    <div class="you_need_konw">
        <span style="color: #383838;font-size: 18px;">服务设施</span>
        <div class="notice" style="height: 230px;">
            <div class="botoom_div">
                <div class="div_top">通用设施</div>
                <div class="div_box">
                    <div class="itembox">吸烟区</div>
                    <div class="itembox">有可无线上网的公共区域</div>
                    <div class="itembox">公共音响系统</div>
                    <div class="itembox">公共区域闭路电视监控系统</div>
                </div>
            </div>
            <div class="botoom_div">
                <div class="div_top">房间设施</div>
                <div class="div_box">
                    <div class="itembox">Wi-Fi</div><div class="itembox">有线网络</div>
                    <div class="itembox">空调</div><div class="itembox">独卫</div>
                    <div class="itembox">热水淋浴</div><div class="itembox">牙具</div>
                    <div class="itembox">拖鞋</div><div class="itembox">电热水壶</div>
                    <div class="itembox">吹风机</div><div class="itembox">电话</div>
                </div>
            </div>
            <div class="botoom_div">
                <div class="div_top">服务项目</div>
                <div class="div_box">
                    <div class="itembox">24小时前台服务</div>
                    <div class="itembox">快速办理入住/退房手续</div>
                    <div class="itembox">行李寄存</div>
                    <div class="itembox">办理私人登记入住/退房手续</div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 详情 end -->

<!--引入头部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
<script src="js/getParameter.js"></script>
<script type="text/javascript" src="js/hotel_detail.js"></script>
</body>
</html>
