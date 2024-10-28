package com.wink.service.impl;

import com.wink.dao.IUserDao;
import com.wink.dao.impl.UserDaoImpl;
import com.wink.domain.User;
import com.wink.service.IUserService;

import static com.wink.util.Md5Util.encodeByMd5;

/**
 * @Author: Mr.Ye
 * @Description: TODO(注册用户业务层实现类)
 */
public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    //注册
    @Override
    public boolean regist(User user){
        User u = userDao.findByUsername(user.getUsername());
        if(u != null){
            return false;
        }
        userDao.save(user);
        return true;
    }

    //登录
    @Override
    public User login(User user){
        try {
            return userDao.findByUsernameAndPassword(user.getUsername(),encodeByMd5(user.getPassword()));
        } catch (Exception e) {
            return null;
        }
    }

    //修改
    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User updateNopwd(User user) {
        return userDao.updateNopwd(user);
    }

}
