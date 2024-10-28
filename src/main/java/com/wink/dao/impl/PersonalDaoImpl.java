package com.wink.dao.impl;

import com.wink.dao.IPersonalDao;
import com.wink.domain.Message;
import com.wink.domain.Order;
import com.wink.domain.Route;
import com.wink.domain.RouteOrder;
import com.wink.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(我的收藏持久层实现类)
 */
public class PersonalDaoImpl implements IPersonalDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalFavorite(int uid) {
        String sql = "select count(1) from tab_favorite where uid = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,uid);
    }

    @Override
    public List<Route> findFavoriteByPage(int uid, int start, int pageSize) {
        String sql = "select * from tab_route where rid in (select rid from tab_favorite where 1=1 ";

        StringBuilder sb = new StringBuilder(sql);
        List<Integer> params = new ArrayList<>();

        if (uid != 0){
            sb.append(" and uid = ? )");
            params.add(uid);
        }

        sb.append(" limit ?,? ");//分页
        sql = sb.toString();

        params.add(start);
        params.add(pageSize);

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Route.class),params.toArray());
    }

    @Override
    public int findMyMessageCount(int uid) {
        String sql = "select count(1) from tab_message where uid = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,uid);
    }

    @Override
    public List<Message> findMyMessage(int uid, int start, int pageSize) {
        String sql = "select * from tab_message where uid = ? order by mid desc limit ?,?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Message.class),uid,start,pageSize);
    }

    @Override
    public int delmsg(int mid) {
        String sql = "delete from tab_message where mid = ?";
        return jdbcTemplate.update(sql,mid);
    }

    @Override
    public int findTotalOrder(int uid) {
        String sql = "select count(1) from tab_order where uid = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,uid);
    }

    @Override
    public int findTotalROrder(int uid) {
        String sql = "select count(1) from route_order where uid = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,uid);
    }

    @Override
    public List<Order> findOrderByPage(int uid, int start, int pageSize) {
        String sql = "select * from tab_order where uid = ? order by ordertime desc limit ?,?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Order.class),uid,start,pageSize);
    }

    @Override
    public List<RouteOrder> findROrderByPage(int uid, int start, int pageSize) {
        String sql = "select * from route_order where uid = ? order by ordertime desc limit ?,?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(RouteOrder.class),uid,start,pageSize);
    }
}
