package com.admin.service;

import com.wink.domain.Role;
import com.wink.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    void save(User user) throws Exception;

    User findByUid(int uid) throws Exception;

    List<User> findAll(int page,int size,String username) throws Exception;

    List<Role> findOtherRoles(int userId) throws Exception;

    void addRoleToUser(int userId, int[] roleIds) throws Exception;

    void delete(int uid) throws Exception;

    void update(User user) throws Exception;

    List<Role> findRoleByUserId(int userId) throws Exception;

    void removeRole(int userId,int[] roleIds) throws Exception;

    Integer findUidByUsername(String username) throws Exception;
}
