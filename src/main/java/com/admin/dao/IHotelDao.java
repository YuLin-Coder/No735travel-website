package com.admin.dao;

import com.wink.domain.Hotel;
import com.wink.domain.Hoteldetail;
import com.wink.domain.Hoteltype;
import com.wink.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店信息)
 */
@Repository
public interface IHotelDao {

    //查询所有酒店
    @Select("select * from tab_hotel where hname like #{search}")
    @Results({
            @Result(id = true,property = "hid",column = "hid"),
            @Result(property = "hname",column = "hname"),
            @Result(property = "hintroduce",column = "hintroduce"),
            @Result(property = "himage",column = "himage"),
            @Result(property = "hflag",column = "hflag"),
            @Result(property = "address",column = "address"),
            @Result(property = "telphone",column = "telphone"),
            @Result(property = "user",column = "usid",javaType = User.class,one= @One(select ="com.admin.dao.IUserDao.findByUid" ))
    })
    List<Hotel> findAll(@Param("page") int page, @Param("size")int size, @Param("search")String search) throws Exception;

    //新增酒店
    @Insert("insert into tab_hotel(hname,hintroduce,himage,hflag,address,telphone,usid) values(#{hname},#{hintroduce},#{himage},#{hflag},#{address},#{telphone},#{usid})")
    void save(Hotel hotel)throws Exception;

    //查询所有用户
    @Select("select * from tab_user ")
    List<User> findUsers() throws Exception;

    //删除酒店
    @Delete("delete from tab_hotel where hid = #{hid}")
    void delete(int hid)throws Exception;

    //从tab_hoteldetail删除酒店
    @Delete("delete from tab_hoteldetail where hid = #{hid}")
    void deletefrom_tab_hoteldetail(int hid)throws Exception;

    //根据hid查找酒店信息
    @Select("select * from tab_hotel where hid = #{hid}")
    Hotel findByHid(int hid)throws Exception;

    //修改酒店信息
    @Update("update tab_hotel set hname=#{hname},hintroduce=#{hintroduce},himage=#{himage},hflag=#{hflag},address=#{address},telphone=#{telphone},usid=#{usid} where hid = #{hid}")
    void update(Hotel hotel)throws Exception;

    //查看选择的酒店拥有的房型
    @Select("select * from tab_hoteldetail where hid = #{hid}")
    @Results({
            @Result(id = true,property = "hid",column = "hid"),
            @Result(property = "tid",column = "tid"),
            @Result(property = "price",column = "price"),
            @Result(property = "hoteltype",column = "tid",javaType = Hoteltype.class,one = @One(select = "com.admin.dao.IHoteltypeDao.findByTid"))
    })
    List<Hoteldetail> findDetail(int hid)throws Exception;

    //移除已拥有的房型
    @Delete("delete from tab_hoteldetail where hid=#{hid} and tid=#{tid}")
    void removeRoom(@Param("hid") int hid,@Param("tid") int tid)throws Exception;

    //根据hid查看未拥有的房型
    @Select("select * from tab_hoteltype where tid not in (select tid from tab_hoteldetail where hid = #{hid})")
    List<Hoteltype> findOtherRooms(int hid)throws Exception;

    //添加未拥有的房型
    @Insert("insert into tab_hoteldetail(hid,tid,price) values(#{hid},#{tid},#{price})")
    void addRoom(@Param("hid") int hid,@Param("tid") int tid,@Param("price") Double price)throws Exception;

}
