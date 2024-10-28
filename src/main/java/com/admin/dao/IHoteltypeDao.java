package com.admin.dao;

import com.wink.domain.Hoteltype;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店房型)
 */
@Repository
public interface IHoteltypeDao {
    //查询所有房型
    @Select("select * from tab_hoteltype where tname like #{search} order by tid")
    List<Hoteltype> findAll(@Param("page") int page, @Param("size")int size, @Param("search")String search) throws Exception;

    //新增房型
    @Insert("insert into tab_hoteltype(tname) values(#{tname})")
    void save(Hoteltype hoteltype)throws Exception;

    //从tab_hoteldetail表删除
    @Delete("delete from tab_hoteldetail where tid = #{tid}")
    void deletefrom_tab_hoteltype(int tid)throws Exception;

    //从tab_hoteltype表删除
    @Delete("delete from tab_hoteltype where tid = #{tid}")
    void delete(int tid)throws Exception;

    //根据tid查询
    @Select("select * from tab_hoteltype where tid=#{tid}")
    Hoteltype findByTid(int tid) throws Exception;
}
