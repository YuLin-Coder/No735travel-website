package com.wink.dao;

import com.wink.domain.Account;
import com.wink.domain.Hotel;
import com.wink.domain.Order;
import com.wink.domain.RouteOrder;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店订单持久层接口)
 */
public interface IOrderDao {

    //新增入住酒店
    void addOrder(Order order);

    //新增线路订单
    void addROrder(RouteOrder routeOrder);

    //查询是否在某时间段内入住
    int selectOrder(int uid, int hid, String beginday,String endday, String beginday1,String endday1);

    //根据uid查询账户余额
    Account findAccount(int uid);

    //根据hid查询商家suid
    int findByhid(int hid);

    //根据sid查询商家usid
    int findBySid(int usid);

    //更新账户
    void updateAccount(Account account);

    //根据订单查询酒店信息
    Hotel findHotelByhid(Integer hid);
}
