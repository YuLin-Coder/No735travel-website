package com.wink.dao;

import com.wink.domain.Message;
import com.wink.domain.User;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(留言持久层接口)
 */
public interface IMessageDao {

    //保存留言
    void saveMessage(Message message);

    //查询留言数量
    int findTotalCount();

    //分页查询留言
    List<Message> findByPage(int start,int pageSize);

    //根据uid查询用户
    User findByUid(int uid);
}
