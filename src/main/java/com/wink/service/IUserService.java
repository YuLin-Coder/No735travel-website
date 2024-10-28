package com.wink.service;

import com.wink.domain.User;

/**
 * @Author: Mr.Ye
 * @Description: TODO(注册用户业务层接口)
 */
public interface IUserService {

    //用户注册
    boolean regist(User user);

    //用户登录
    User login(User user);

    //修改用户信息
    User update(User user);

    //修改用户信息,不修改密码
    User updateNopwd(User user);
}
