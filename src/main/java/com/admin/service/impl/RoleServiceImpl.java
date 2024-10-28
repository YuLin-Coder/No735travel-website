package com.admin.service.impl;

import com.admin.dao.IRoleDao;
import com.admin.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.wink.domain.Permission;
import com.wink.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public void delete(int roleId) throws Exception {
        //从user_role表中删除
        roleDao.deleteFromUser_RoleByRoleId(roleId);
        //从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //从tab_role表中删除
        roleDao.deleteRoleById(roleId);
    }

    @Override
    public Role findById(int roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Role> findAll(int page,int size,String roleName) throws Exception {
        PageHelper.startPage(page, size);
        return roleDao.findAll(page,size,roleName);
    }

    @Override
    public List<Permission> findOtherPermissions(int roleId) throws Exception {
        return roleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(int roleId, int[] permissionIds) throws Exception {
        for (int permissionId: permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public List<Permission> findPermissionByRoleId(int roleId) throws Exception {
        return roleDao.findPermissionByRole(roleId);
    }

    @Override
    public void removePermission(int roleId,int[] permissionIds) throws Exception {
        for (int permissionId : permissionIds) {
            roleDao.removePermission(roleId,permissionId);
        }
    }
}
