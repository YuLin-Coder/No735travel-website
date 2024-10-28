package com.wink.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mr.Ye
 * @Description: TODO(收藏实体类)
 */
public class Favorite implements Serializable {
    private Integer rid;//线路id
    private Date date;//收藏时间
    private Integer uid;//用户id

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Favorite() {
    }
}
