package com.admin.dao;

import com.wink.domain.RouteImg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(景点图片信息)
 */
@Repository
public interface IImgDao {

    //查询所有图片
    @Select("select * from tab_route_img where rid = #{rid}")
    List<RouteImg> findImgByRid(int rid);

    //删除线路详情对应的图片
    @Delete("delete from tab_route_img where rgid = #{rgid}")
    void delete(int rgid) throws Exception;

    @Insert("insert into tab_route_img (rid,bigPic,smallPic) values(#{rid},#{bigPic},#{smallPic})")
    void save(RouteImg routeImg)throws Exception;
}
