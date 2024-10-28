package com.wink.domain;

import java.io.Serializable;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店房间类型)
 */
public class Hoteltype implements Serializable {
    private Integer tid;//房型id
    private String tname;//房间类型

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Hoteltype(Integer tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    public Hoteltype() {
    }
}
