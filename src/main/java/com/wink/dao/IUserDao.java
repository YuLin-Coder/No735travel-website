package com.wink.dao;

import com.wink.domain.User;

/**
 * @Author: Mr.Ye
 * @Description: TODO(用户持久层接口)
 */
public interface IUserDao {

    //根据用户名查询用户信息
    User findByUsername(String username);

    //用户保存
    void save(User user);

    //查询用户密码
    User findByUsernameAndPassword(String username, String password);

    //修改用户信息
    User update(User user);

    //修改用户信息,不修改密码
    User updateNopwd(User user);
}
