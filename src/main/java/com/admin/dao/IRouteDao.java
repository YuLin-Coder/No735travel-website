package com.admin.dao;

import com.wink.domain.Category;
import com.wink.domain.Route;
import com.wink.domain.Seller;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(景点线路)
 */
@Repository
public interface IRouteDao {

    //新增线路信息
    @Insert("insert into tab_route(rname,price,routeIntroduce,rimage,rflag,rdate,isThemeTour,cid,sid) " +
            "values(#{rname},#{price},#{routeIntroduce},#{rimage},#{rflag},#{rdate},#{isThemeTour},#{cid},#{sid})")
    void saveRoute(Route route) throws Exception;

    //删除一条线路
    @Delete("delete from tab_route where rid = #{rid}")
    void delete(int rid) throws Exception;

    //修改线路信息
    @Update("update tab_route set rname=#{rname},price=#{price},routeIntroduce=#{routeIntroduce},rimage=#{rimage},rflag=#{rflag},isThemeTour=#{isThemeTour},cid=#{cid},sid=#{sid} where rid = #{rid}")
    void update(Route route) throws Exception;

    //根据rid查询信息
    @Select("select * from tab_route where rid = #{rid}")
    Route findByRid(int rid) throws Exception;

    //根据查询所有景点线路
    @Select("select * from tab_route where rname like #{rname}")
    @Results({
            @Result(id = true,property = "rid",column = "rid"),
            @Result(property = "rname",column = "rname"),
            @Result(property = "price",column = "price"),
            @Result(property = "routeIntroduce",column = "routeIntroduce"),
            @Result(property = "rflag",column = "rflag"),
            @Result(property = "rdate",column = "rdate"),
            @Result(property = "isThemeTour",column = "isThemeTour"),
            @Result(property = "count",column = "count"),
            @Result(property = "rimage",column = "rimage"),
            @Result(property = "category",column = "cid",javaType = Category.class,one= @One(select ="com.admin.dao.ICategoryDao.findByCid" )),
            @Result(property = "seller",column = "sid",javaType = Seller.class,one= @One(select ="com.admin.dao.ISellerDao.findBySid" ))
    })
    List<Route> findAll(@Param("page") int page,@Param("size")int size,@Param("rname")String rname) throws Exception;
}
