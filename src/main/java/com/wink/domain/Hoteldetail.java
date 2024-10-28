package com.wink.domain;

import java.io.Serializable;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店房型及价格)
 */
public class Hoteldetail implements Serializable {
    private Integer tid;//房型id
    private Integer hid;//酒店id
    private Double price;//单日房价

    private Hoteltype hoteltype;//房型

    public Hoteltype getHoteltype() {
        return hoteltype;
    }

    public void setHoteltype(Hoteltype hoteltype) {
        this.hoteltype = hoteltype;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Hoteldetail(Integer tid, Integer hid, Double price) {
        this.tid = tid;
        this.hid = hid;
        this.price = price;
    }

    public Hoteldetail() {
    }
}
