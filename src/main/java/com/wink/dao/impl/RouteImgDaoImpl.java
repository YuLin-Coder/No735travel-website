package com.wink.dao.impl;

import com.wink.dao.IRouteImgDao;
import com.wink.domain.RouteImg;
import com.wink.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(线路图片持久层实现类)
 */
public class RouteImgDaoImpl implements IRouteImgDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql = "select * from tab_route_img where rid = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(RouteImg.class),rid);
    }
}
