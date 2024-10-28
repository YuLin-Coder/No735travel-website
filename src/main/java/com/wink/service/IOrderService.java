package com.wink.service;

import com.wink.domain.Account;
import com.wink.domain.Order;
import com.wink.domain.RouteOrder;

/**
 * @Author: Mr.Ye
 * @Description: TODO(线路、酒店订单业务层接口)
 */
public interface IOrderService {

    //新增入住酒店记录
    void addOrder(Order order);

    //新增线路订单
    void addROrder(RouteOrder routeOrder);

    //查询是否可以入住
    int selectOrder(String uid, String hid,String beginday,String endday, String beginday1,String endday1);

    //根据uid查询账户余额
    Account findAccount(int uid);

    //根据hid查询商家suid
    int findByhid(int hid);

    //根据sid查询商家usid
    int findBySid(int usid);

    //酒店入住转账
    void transfer(Integer sourceId, Integer targetId, Double money);
}
