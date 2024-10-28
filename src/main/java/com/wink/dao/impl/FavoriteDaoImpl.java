package com.wink.dao.impl;

import com.wink.dao.IFavoriteDao;
import com.wink.domain.Favorite;
import com.wink.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * @Author: Mr.Ye
 * @Description: TODO(收藏持久层实现类)
 */
public class FavoriteDaoImpl implements IFavoriteDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid = ? and uid = ?";
            favorite = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), rid, uid);
        }catch (DataAccessException e){

        }
        return favorite;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(1) from tab_favorite where rid = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public void addFavorite(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        jdbcTemplate.update(sql,rid,new Date(),uid);
    }

    @Override
    public void deleteFavorite(int rid, int uid) {
        String sql = "delete from tab_favorite where uid = ? and rid = ?";
        jdbcTemplate.update(sql,uid,rid);
    }

    @Override
    public void updateFavorite(int rid) {
        String sql = "update tab_route set count = ? where rid = ?";
        jdbcTemplate.update(sql,findCountByRid(rid),rid);
    }
}
