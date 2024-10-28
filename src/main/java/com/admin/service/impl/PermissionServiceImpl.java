package com.admin.service.impl;

import com.admin.dao.IPermissionDao;
import com.admin.service.IPermissionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wink.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public void deleteById(int id) throws Exception {
        permissionDao.deleteFromRole_Permission(id);
        permissionDao.deleteById(id);
    }

    @Override
    public Permission findById(int id) throws Exception {
        return permissionDao.findById(id);
    }

    @Override
    public void update(Permission permission) throws Exception {
        permissionDao.update(permission);
    }

    @Override
    public List<Permission> findAll(int page,int size,String permissionName) throws Exception {
        PageHelper.startPage(page,size);
        return permissionDao.findAll(page, size, permissionName);
    }

}
