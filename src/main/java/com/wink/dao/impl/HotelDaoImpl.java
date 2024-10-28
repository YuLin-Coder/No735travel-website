package com.wink.dao.impl;

import com.wink.dao.IHotelDao;
import com.wink.domain.Hotel;
import com.wink.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店持久层实现类)
 */
public class HotelDaoImpl implements IHotelDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount() {
        String sql = "select count(1) from tab_hotel where hflag=1;";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public List<Hotel> findbyPage( int start, int pageSize) {
        String sql = "select * from tab_hotel where hflag = 1 limit ?,?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Hotel.class),start,pageSize);
    }

    @Override
    public Hotel findOne(int hid) {
        String sql = "select * from tab_hotel where hflag = 1 and hid = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Hotel.class),hid);
    }
}
