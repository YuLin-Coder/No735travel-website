package com.wink.service;

import com.wink.domain.*;

/**
 * @Author: Mr.Ye
 * @Description: TODO(我的收藏业务层接口)
 */
public interface IPersonalService {

    //根据用户进行分页查询
    PageBean<Route> pageQuery(int uid,int currentPage,int pageSize);

    //根据用户查询留言
    PageBean<Message> QueryMessage(int uid,int currentPage,int pageSize);

    //删除留言
    int delmsg(String mid);

    //查询用户酒店订单
    PageBean<Order> QueryOrder(int uid,int currentPage,int pageSize);

    //查询用户景点订单
    PageBean<RouteOrder> QueryROrder(int uid, int currentPage, int pageSize);
}
