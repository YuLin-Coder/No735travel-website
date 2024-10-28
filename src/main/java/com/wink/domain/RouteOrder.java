package com.wink.domain;

import java.io.Serializable;

/**
 * @author: Mr.Ye
 * @Description:线路订单
 * @date: 2020.05.13
 */
public class RouteOrder implements Serializable {
    private Integer id;//主键
    private String orderId;//订单编号，唯一
    private String ordertime;//下单时间
    private String rname;//线路名称
    private String starttime;//出发时间
    private String totalp;//购票数量
    private String paymoney;//需要支付金额
    private String uname;//下单人姓名
    private String telephone;//联系电话
    private Integer uid;//用户id
    private Integer rid;//商家id

    private Route route;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getTotalp() {
        return totalp;
    }

    public void setTotalp(String totalp) {
        this.totalp = totalp;
    }

    public String getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(String paymoney) {
        this.paymoney = paymoney;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

}
