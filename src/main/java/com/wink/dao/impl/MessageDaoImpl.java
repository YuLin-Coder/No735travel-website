package com.wink.dao.impl;

import com.wink.dao.IMessageDao;
import com.wink.domain.Message;
import com.wink.domain.User;
import com.wink.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO()
 */
public class MessageDaoImpl implements IMessageDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void saveMessage(Message message) {
        String sql = "insert into tab_message(uid,title,content,datetime) values(?,?,?,?)";
        jdbcTemplate.update(sql,message.getUid(),message.getTitle(),message.getContent(),message.getDatetime());
    }

    @Override
    public int findTotalCount() {
        String sql = "select count(1) from tab_message";
        int count = jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }

    @Override
    public List<Message> findByPage(int start, int pageSize) {
        String sql = "select * from tab_message order by mid desc limit ?,?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Message.class),start,pageSize);
    }

    @Override
    public User findByUid(int uid) {
        String sql = "select * from tab_user where uid = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),uid);
    }
}
