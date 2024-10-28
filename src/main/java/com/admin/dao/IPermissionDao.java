package com.admin.dao;

import com.wink.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(资源权限)
 */
@Repository
public interface IPermissionDao {

    //查询与role关联的所有的权限
    @Select("select * from tab_permission where id in (select permissionId from role_permission where roleId=#{id} )")
    List<Permission> findPermissionByRoleId(int id) throws Exception;

    @Select("select * from tab_permission where id=#{id}")
    Permission findById(int id) throws Exception ;

    @Insert("insert into tab_permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception ;

    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteFromRole_Permission(int id) throws Exception ;

    @Delete("delete from tab_permission where id=#{id}")
    void deleteById(int id) throws Exception ;

    @Select("select * from tab_permission where permissionName like #{permissionName}")
    List<Permission> findAll(@Param("page") int page, @Param("size")int size, @Param("permissionName")String permissionName) throws Exception ;

    @Update("update tab_permission set permissionName=#{permissionName},url=#{url} where id=#{id}")
    void update(Permission permission) throws Exception;
}
