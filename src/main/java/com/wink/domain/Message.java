package com.wink.domain;

import java.io.Serializable;

/**
 * @Author: Mr.Ye
 * @Description: TODO(留言实体类)
 */
public class Message implements Serializable {
    private Integer mid;//留言id
    private Integer uid;//用户id
    private String content;//留言内容
    private String datetime;//留言时间
    private String title;//留言标题

    private User user;//用户

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
