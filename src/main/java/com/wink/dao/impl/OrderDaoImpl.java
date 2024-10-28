package com.wink.dao.impl;

import com.wink.dao.IOrderDao;
import com.wink.domain.Account;
import com.wink.domain.Hotel;
import com.wink.domain.Order;
import com.wink.domain.RouteOrder;
import com.wink.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店订单持久层实现类)
 */
public class OrderDaoImpl implements IOrderDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void addOrder(Order order){
        String sql = "insert into tab_order values(null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,order.getOrderId(),order.getOrdertime(),order.getHname(),order.getTname(),order.getUname(),order.getTelephone(),order.getBeginday(),order.getEndday(),order.getTotaldays(),order.getPaymoney(),order.getUid(),order.getHid(),order.getTid());
    }

    @Override
    public void addROrder(RouteOrder routeOrder) {
        String sql = "insert into route_order(orderId,ordertime,rname,starttime,totalp,paymoney,uname,telephone,uid,rid) values(?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,routeOrder.getOrderId(),routeOrder.getOrdertime(),routeOrder.getRname(),routeOrder.getStarttime(),routeOrder.getTotalp(),routeOrder.getPaymoney(),routeOrder.getUname(),routeOrder.getTelephone(),routeOrder.getUid(),routeOrder.getRid());
    }

    @Override
    public int selectOrder(int uid, int hid, String beginday,String endday, String beginday1,String endday1) {
        String sql = "select count(1) from tab_order where uid = ? and hid = ? and (beginday between ? and ? or endday between ? and ?);";
        return jdbcTemplate.queryForObject(sql,Integer.class,uid,hid,beginday,endday,beginday1,endday1);
    }

    @Override
    public Account findAccount(int uid) {
        String sql = "select * from tab_account where uid = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Account.class),uid);
    }

    @Override
    public int findByhid(int hid) {
        String sql = "select usid from tab_hotel where hid = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,hid);
    }

    @Override
    public int findBySid(int usid) {
        String sql = "select usid from tab_seller where sid = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,usid);
    }

    @Override
    public void updateAccount(Account account) {
        String sql = "update tab_account set money = ? where aid = ?";
        jdbcTemplate.update(sql,account.getMoney(),account.getAid());
    }

    @Override
    public Hotel findHotelByhid(Integer hid) {
        String sql = "select * from tab_hotel where hid = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Hotel.class),hid);
    }
}
