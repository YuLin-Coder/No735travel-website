package com.admin.service;

import com.wink.domain.Permission;
import com.wink.domain.Role;

import java.util.List;

public interface IRoleService {

    void save(Role role) throws Exception;

    void delete(int roleId) throws Exception;

    Role findById(int roleId) throws  Exception;

    List<Role> findAll(int page,int size,String roleName) throws Exception;

    List<Permission> findOtherPermissions(int roleId) throws Exception;

    void addPermissionToRole(int roleId, int[] permissionIds) throws Exception;

    List<Permission> findPermissionByRoleId(int roleId) throws Exception;

    void removePermission(int roleId,int[] permissionIds) throws Exception;
}
