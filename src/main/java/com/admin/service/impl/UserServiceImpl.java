package com.admin.service.impl;

import com.admin.dao.IUserDao;
import com.admin.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.wink.domain.Permission;
import com.wink.domain.Role;
import com.wink.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.wink.util.Md5Util.encodeByMd5;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;


    @Override
    public List<Role> findOtherRoles(int userId) throws Exception {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(int userId, int[] roleIds) throws Exception {
        for(int roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public void delete(int uid) throws Exception {
        userDao.delete(uid);
    }

    @Override
    public void update(User user) throws Exception {
        user.setPassword(encodeByMd5(user.getPassword()));
        userDao.update(user);
    }

    @Override
    public List<Role> findRoleByUserId(int userId) throws Exception {
        return userDao.findRoleByUserId(userId);
    }

    @Override
    public void removeRole(int userId,int[] roleIds) throws Exception {
        for (int roleId : roleIds) {
            userDao.removeRole(userId,roleId);
        }
    }

    @Override
    public Integer findUidByUsername(String username) throws Exception {
        return userDao.findUidByUsername(username);
    }

    @Override
    public void save(User user) throws Exception {
        //对密码进行加密处理
        user.setPassword(encodeByMd5(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public List<User> findAll(int page,int size,String username) throws Exception {
        PageHelper.startPage(page, size);
        return userDao.findAll(page,size,username);
    }

    @Override
    public User findByUid(int uid) throws Exception {
        return userDao.findByUid(uid);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        String password = null;
        try {
            user = userDao.findByUsername(username);
             password = user.getPassword();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
       return new org.springframework.security.core.userdetails.User(
                user.getUsername(), password, user.getStatus() == 0 ? false : true,
               true, true, true,
               getAuthority(user.getRoles()));
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<GrantedAuthority> getAuthority(List<Role> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            for (Permission permission : role.getPermissions()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getUrl()));
            }
        }
        return grantedAuthorities;
    }

    }