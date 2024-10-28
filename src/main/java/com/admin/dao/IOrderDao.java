package com.admin.dao;

import com.wink.domain.Order;
import com.wink.domain.RouteOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(订单信息)
 */
@Repository
public interface IOrderDao {

    //查询所有酒店订单
    @Select("select * from tab_order where uname like #{search}")
    List<Order> findAll(@Param("page") int page, @Param("size")int size, @Param("search")String search) throws Exception;

    //删除酒店订单
    @Delete("delete from tab_order where id=#{id}")
    void delete(int id)throws Exception;

    //查询景点订单
    @Select("select * from route_order where uname like #{search}")
    List<RouteOrder> findAllRoute(@Param("page") int page, @Param("size")int size, @Param("search")String search)throws Exception;

    //删除景点订单
    @Delete("delete from route_order where id=#{id}")
    void deleteRoute(Integer id)throws Exception;
}
