package com.admin.dao;

import com.wink.domain.Role;
import com.wink.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(用户信息)
 */
@Repository
public interface IUserDao {
    //新增用户
    @Insert("insert into tab_user(username,password,name,birthday,sex,telephone,email,status) values(#{username},#{password},#{name},#{birthday},#{sex},#{telephone},#{email},#{status})")
    void save(User user) throws Exception;

    //根据用户名查询信息
    @Select("select * from tab_user where username=#{username}")
    @Results({
            @Result(id=true,property = "uid",column = "uid"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "name",column = "name"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "telephone",column = "telephone"),
            @Result(property = "email",column = "email"),
            @Result(property = "roles",column = "uid",javaType = java.util.List.class,many = @Many(select = "com.admin.dao.IRoleDao.findRoleByUserId")),
            @Result(property = "permissions",column = "roleId",javaType = java.util.List.class,many = @Many(select = "com.admin.dao.IPermissionDao.findPermissionByRoleId"))
    })
    User findByUsername(String username) throws Exception;

    //查询所有用户
    @Select("select * from tab_user where username like #{username} order by uid")
    List<User> findAll(@Param("page") int page, @Param("size")int size, @Param("username")String username) throws Exception;

    //

    //根据uid查询用户及角色
    @Select("select * from tab_user where uid=#{uid}")
    @Results({
            @Result(id=true,property = "uid",column = "uid"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "name",column = "name"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "telephone",column = "telephone"),
            @Result(property = "email",column = "email"),
            @Result(property = "roles",column = "uid",javaType = java.util.List.class,many = @Many(select = "com.admin.dao.IRoleDao.findRoleByUserId"))
    })
    User findByUid(int uid) throws Exception;

    //查询用户未拥有的角色
    @Select("select * from tab_role where id not in (select roleId from user_role where userId=#{userId})")
    List<Role> findOtherRoles(int userId)throws Exception ;

    //给用户添加角色
    @Insert("insert into user_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") int userId, @Param("roleId") int roleId)throws Exception ;

    //删除用户
    @Update("update tab_user set status = 0 where uid = #{uid}")
    void delete(int uid) throws Exception;

    //修改用户信息
    @Update("update tab_user set password=#{password},name=#{name},birthday=#{birthday},sex=#{sex},telephone=#{telephone},email=#{email},status=#{status} where uid = #{uid}")
    void update(User user) throws Exception;

    //查询用户已拥有的角色
    @Select("select * from tab_role where id in (select roleId from user_role where userId = #{userId})")
    List<Role> findRoleByUserId(int userId) throws Exception;

    //移除用户拥有的角色
    @Delete("delete from user_role where userId = #{userId} and roleId = #{roleId}")
    void removeRole(@Param("userId") int userId,@Param("roleId") int roleId) throws Exception;

    //查询根据username查询uid
    @Select("select uid from tab_user where username=#{username}")
    Integer findUidByUsername(String username) throws Exception;
}
