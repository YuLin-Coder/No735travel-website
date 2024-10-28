package com.wink.dao.impl;

import com.wink.dao.IHoteltypeDao;
import com.wink.domain.Hoteltype;
import com.wink.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: Mr.Ye
 * @Description: TODO(房型持久层实现类)
 */
public class HoteltypeDaoImpl implements IHoteltypeDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Hoteltype findByTid(int tid) {
        String sql = "select * from tab_hoteltype where tid = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Hoteltype.class),tid);
    }
}
