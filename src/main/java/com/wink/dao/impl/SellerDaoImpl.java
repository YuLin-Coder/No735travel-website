package com.wink.dao.impl;

import com.wink.dao.ISellerDao;
import com.wink.domain.Seller;
import com.wink.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: Mr.Ye
 * @Description: TODO(商家持久层实现类)
 */
public class SellerDaoImpl implements ISellerDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findBySid(int sid) {
        String sql = "select * from tab_seller where sid = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Seller.class),sid);
    }
}
