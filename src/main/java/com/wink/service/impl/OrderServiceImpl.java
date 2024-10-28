package com.wink.service.impl;

import com.wink.dao.IOrderDao;
import com.wink.dao.impl.OrderDaoImpl;
import com.wink.domain.Account;
import com.wink.domain.Order;
import com.wink.domain.RouteOrder;
import com.wink.service.IOrderService;
import com.wink.util.TransactionConfig;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店订单业务层实现类)
 */
public class OrderServiceImpl implements IOrderService {

    IOrderDao orderDao = new OrderDaoImpl();

    @Override
    public void addOrder(Order order){
        orderDao.addOrder(order);
    }

    @Override
    public void addROrder(RouteOrder routeOrder) {
        orderDao.addROrder(routeOrder);
    }

    @Override
    public int selectOrder(String uid, String hid, String beginday, String endday, String beginday1,String endday1) {
        return orderDao.selectOrder(Integer.parseInt(uid),Integer.parseInt(hid),beginday,endday,beginday1,endday1);
    }

    @Override
    public Account findAccount(int uid) {
        return orderDao.findAccount(uid);
    }

    @Override
    public int findByhid(int hid) {
        return orderDao.findByhid(hid);
    }

    @Override
    public int findBySid(int usid) {
        return orderDao.findBySid(usid);
    }

    @Override
    public void transfer(Integer sourceId, Integer targetId, Double money) {
        try {
            TransactionConfig.startTransaction();//开启事务
            Account source = orderDao.findAccount(sourceId);
            Account target = orderDao.findAccount(targetId);
            if (source != null && target != null) {
                source.setMoney(source.getMoney() - money);
                target.setMoney(target.getMoney() + money);
                orderDao.updateAccount(source);
//                int i = 1 / 0;
                orderDao.updateAccount(target);
                TransactionConfig.commit();//提交事务
            }
        }catch (Exception e){
            TransactionConfig.rollback();//事务回滚
        }finally {
            TransactionConfig.close();//关闭事务
        }
    }
}
