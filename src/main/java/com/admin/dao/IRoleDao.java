package com.admin.dao;

import com.wink.domain.Permission;
import com.wink.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(角色信息)
 */
@Repository
public interface IRoleDao {
    //增加角色
    @Insert("insert into tab_role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role)throws Exception ;

    //从user_role表中删除
    @Delete("delete from user_role where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(int roleId)throws Exception ;

    //从role_permission表中删除
    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(int roleId)throws Exception ;

    //删除角色
    @Delete("delete from tab_role where id=#{roleId}")
    void deleteRoleById(int roleId)throws Exception ;

    //根据用户id查询出所有对应的角色
    @Select("select * from tab_role where id in (select roleId from user_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.admin.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(int userId) throws Exception;

    //根据角色id查询角色
    @Select("select * from tab_role where id=#{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.admin.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(int roleId)throws Exception ;

    //查询所有角色
    @Select("select * from tab_role where roleName like #{roleName}")
    List<Role> findAll(@Param("page") int page,@Param("size")int size,@Param("roleName")String roleName) throws Exception;

    //查询指定角色未拥有的权限
    @Select("select * from tab_permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(int roleId)throws Exception ;

    //角色新增权限
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId")int roleId, @Param("permissionId")int permissionId)throws Exception ;

    //查询指定角色拥有的权限
    @Select("select * from tab_permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findPermissionByRole(int roleId) throws Exception;

    //移除指定角色拥有的权限
    @Delete("delete from role_permission where roleId = #{roleId} and permissionId = #{permissionId}")
    void removePermission(@Param("roleId")int roleId, @Param("permissionId")int permissionId) throws Exception;
}
