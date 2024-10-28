package com.wink.dao.impl;

import com.wink.dao.ITopDao;
import com.wink.domain.Route;
import com.wink.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(收藏排行榜持久层实现类)
 */
public class TopDaoImpl implements ITopDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(String rname, Double smoney, Double emoney) {
        String sql = "select count(1) from tab_route where rflag = 1 and 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();

        if (rname != null && rname.length() > 0 ){
            sb.append("and rname like ? ");
            params.add("%"+rname+"%");
        }

        if (smoney != null){
            sb.append("and price >= ? ");
            params.add(smoney);
        }
        if (emoney != null){
            sb.append("and price<= ?");
            params.add(emoney);
        }
        sql = sb.toString();
        int count = jdbcTemplate.queryForObject(sql,Integer.class,params.toArray());
        return count;
    }

    @Override
    public List<Route> findByPage(int start, String rname, Double smoney, Double emoney,int pageSize) {
        String sql = "select * from tab_route where rflag = 1 and 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();

        if (rname != null && rname.length() > 0){
            sb.append("and rname like ? ");
            params.add("%"+rname+"%");
        }

        if (smoney != null){
            sb.append("and price >= ? ");
            params.add(smoney);
        }
        if (emoney != null){
            sb.append("and price<= ? ");
            params.add(emoney);
        }
        sb.append(" order by count desc limit ? , ? ");
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Route.class),params.toArray());
    }
}
