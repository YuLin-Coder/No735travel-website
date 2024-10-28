package com.wink.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店相关信息实体类)
 */
public class Hotel implements Serializable {
    private Integer hid;//酒店id
    private String hname;//酒店名称
    private String hintroduce;//酒店介绍
    private String himage;//酒店图片
    private MultipartFile himageFile;
    private Integer hflag;//是否上架(0:未上架，1：上架)
    private String hflagStr;
    private String address;//酒店地址
    private String telphone;//电话
    private User user;
    private Integer usid;
    private List<Hoteldetail> hoteldetail;//详细房型

    public Integer getUsid() {
        return usid;
    }

    public void setUsid(Integer usid) {
        this.usid = usid;
    }

    public MultipartFile getHimageFile() {
        return himageFile;
    }

    public void setHimageFile(MultipartFile himageFile) {
        this.himageFile = himageFile;
    }

    public String getHflagStr() {
        if (hflag==0){
            hflagStr = "未上架";
        }
        if (hflag==1){
            hflagStr = "上架";
        }
        return hflagStr;
    }

    public void setHflagStr(String hflagStr) {
        this.hflagStr = hflagStr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getHintroduce() {
        return hintroduce;
    }

    public void setHintroduce(String hintroduce) {
        this.hintroduce = hintroduce;
    }

    public String getHimage() {
        return himage;
    }

    public void setHimage(String himage) {
        this.himage = himage;
    }

    public Integer getHflag() {
        return hflag;
    }

    public void setHflag(Integer hflag) {
        this.hflag = hflag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public List<Hoteldetail> getHoteldetail() {
        return hoteldetail;
    }

    public void setHoteldetail(List<Hoteldetail> hoteldetail) {
        this.hoteldetail = hoteldetail;
    }

    public Hotel(Integer hid, String hname, String hintroduce, String himage, Integer hflag, String address, String telphone) {
        this.hid = hid;
        this.hname = hname;
        this.hintroduce = hintroduce;
        this.himage = himage;
        this.hflag = hflag;
        this.address = address;
        this.telphone = telphone;
    }

    public Hotel() {
    }
}
