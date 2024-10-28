package com.wink.dao;

import com.wink.domain.Message;
import com.wink.domain.Order;
import com.wink.domain.Route;
import com.wink.domain.RouteOrder;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(我的收藏持久层接口)
 */
public interface IPersonalDao {

    //根据uid查询收藏总记录数
    int findTotalFavorite(int uid);

    //根据uid，start查询当前页的收藏数据集合
    List<Route> findFavoriteByPage(int uid,int start,int pageSize);

    //查询我的留言总数
    int findMyMessageCount(int uid);

    //查询留言信息
    List<Message> findMyMessage(int uid,int start,int pageSize);

    //删除留言
    int delmsg(int mid);

    //根据uid查询订单总记录数
    int findTotalOrder(int uid);

    //根据uid查询景点订单总记录数
    int findTotalROrder(int uid);

    //根据uid，start查询当前页的订单数据集合
    List<Order> findOrderByPage(int uid, int start, int pageSize);

    //根据uid，start查询当前页的景点订单数据集合
    List<RouteOrder> findROrderByPage(int uid, int start, int pageSize);
}
