package com.wink.domain;

import java.io.Serializable;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店订单表)
 */
public class Order implements Serializable {
    private Integer id;//主键
    private String orderId;//订单编号，唯一
    private String ordertime;//下单时间
    private String hname;//酒店名称
    private String tname;//入住房型
    private String uname;//入住姓名
    private String telephone;//客户电话
    private String beginday;//入住时间
    private String endday;//离开时间
    private String totaldays;//居住时间
    private String paymoney;//需要支付金额
    private Integer uid;//用户id
    private Integer hid;//酒店id
    private Integer tid;//房型id

    private Hotel hotel;//酒店信息

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
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

    public String getBeginday() {
        return beginday;
    }

    public void setBeginday(String beginday) {
        this.beginday = beginday;
    }

    public String getEndday() {
        return endday;
    }

    public void setEndday(String endday) {
        this.endday = endday;
    }

    public String getTotaldays() {
        return totaldays;
    }

    public void setTotaldays(String totaldays) {
        this.totaldays = totaldays;
    }

    public String getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(String paymoney) {
        this.paymoney = paymoney;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

}