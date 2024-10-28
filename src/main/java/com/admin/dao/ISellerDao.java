package com.admin.dao;

import com.wink.domain.Seller;
import com.wink.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(商家信息)
 */
@Repository
public interface ISellerDao {

    //根据sid查询
    @Select("select * from tab_seller where sid = #{sid}")
    Seller findBySid(int sid) throws Exception;

    //查询所有商家
    @Select("select * from tab_seller")
    List<Seller> findAll() throws Exception;

    //分页查询
    @Select("select * from tab_seller where sname like #{search}")
    @Results({
            @Result(id = true,property = "sid",column = "sid"),
            @Result(property = "sname",column = "sname"),
            @Result(property = "consphone",column = "consphone"),
            @Result(property = "address",column = "address"),
            @Result(property = "user",column = "usid",javaType = User.class,one= @One(select ="com.admin.dao.IUserDao.findByUid" ))
    })
    List<Seller> findAlls(@Param("page") int page, @Param("size")int size, @Param("search")String search) throws Exception;

    //修改商家
    @Update("update tab_seller set consphone = #{consphone},address = #{address},usid=#{usid} where sid = #{sid}")
    void update(Seller seller)throws Exception;

    //删除商家
    @Delete("delete from tab_seller where sid=#{sid}")
    void delete(int sid) throws Exception;

    //新增商家
    @Insert("insert into tab_seller(sname,consphone,address,usid) values(#{sname},#{consphone},#{address},#{usid})")
    void save(Seller seller) throws Exception;

    //查询所有用户
    @Select("select * from tab_user ")
    List<User> findUsers() throws Exception;;
}
