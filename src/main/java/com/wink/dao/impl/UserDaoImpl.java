package com.wink.dao.impl;

import com.wink.dao.IUserDao;
import com.wink.domain.User;
import com.wink.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.wink.util.Md5Util.encodeByMd5;

/**
 * @Author: Mr.Ye
 * @Description: TODO(用户持久层实现类)
 */
public class UserDaoImpl implements IUserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try{
            String sql = "select * from tab_user where username = ?";
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        }catch (Exception e){}
        return user;
    }

    @Override
    public void save(User user){
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email) values(?,?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql,user.getUsername(),encodeByMd5(user.getPassword()),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try{
            String sql = "select * from tab_user where username = ? and password = ? and status = 1";
            user  = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),username,password);
        }catch (Exception e){}
        return user;
    }

    @Override
    public User update(User user) {
        String sql = "update tab_user set password = ?,name = ?,birthday = ?,sex = ?,telephone = ?,email = ? where uid = ?";
        try {
            jdbcTemplate.update(sql,encodeByMd5(user.getPassword()),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getUid());
            return findByUsername(user.getUsername());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User updateNopwd(User user) {
        String sql = "update tab_user set name = ?,birthday = ?,sex = ?,telephone = ?,email = ? where uid = ?";
        try {
            jdbcTemplate.update(sql,user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getUid());
            return findByUsername(user.getUsername());
        } catch (Exception e) {
            return null;
        }
    }
}
