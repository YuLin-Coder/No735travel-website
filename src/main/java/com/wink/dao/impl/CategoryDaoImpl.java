package com.wink.dao.impl;

import com.wink.dao.ICategoryDao;
import com.wink.domain.Category;
import com.wink.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(分类持久层接口实现类)
 */
public class CategoryDaoImpl implements ICategoryDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category order by cid";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Category.class));
    }
}
