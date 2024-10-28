package com.admin.dao;

import com.wink.domain.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(旅游线路)
 */
@Repository
public interface ICategoryDao {
    //新增线路
    @Insert("insert into tab_category(cname) value(#{cname})")
    void save(Category category) throws Exception;

    //删除线路
    @Delete("delete from tab_category where cid = #{cid}")
    void delete(int cid) throws Exception;

    //修改线路信息
    @Update("update tab_category set cname = #{cname} where cid = #{cid}")
    void update(Category category) throws Exception;

    //根据cid查询线路
    @Select("select * from tab_category where cid = #{cid}")
    Category findByCid(int cid) throws Exception;

    //查询所有线路
    @Select("select * from tab_category where cname like #{cname} order by cid")
    List<Category> findAll(@Param("page") int page, @Param("size")int size, @Param("cname")String cname) throws Exception;

    //查询所有分类名
    @Select("select cname,cid from tab_category order by cid")
    List<Category> findAllCName() throws Exception;
}
