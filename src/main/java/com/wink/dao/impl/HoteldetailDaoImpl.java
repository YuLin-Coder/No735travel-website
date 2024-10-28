package com.wink.dao.impl;

import com.wink.dao.IHoteldetailDao;
import com.wink.domain.Hoteldetail;
import com.wink.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店房型持久层实现类)
 */
public class HoteldetailDaoImpl implements IHoteldetailDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Hoteldetail> findByHid(int hid) {
        String sql = "select * from tab_hoteldetail where hid = ?";

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Hoteldetail.class),hid);
    }
}
