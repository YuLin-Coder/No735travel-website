package com.admin.service;

import com.wink.domain.Permission;
import java.util.List;

public interface IPermissionService {

    void save(Permission permission) throws Exception;

    void deleteById(int id) throws Exception;

    List<Permission> findAll(int page,int size,String permissionName) throws Exception;

    Permission findById(int id) throws Exception;

    void update(Permission permission) throws Exception;
}
