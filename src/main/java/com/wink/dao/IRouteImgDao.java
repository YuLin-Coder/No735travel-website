package com.wink.dao;

import com.wink.domain.RouteImg;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(旅游线路图片)
 */
public interface IRouteImgDao {

    //根据rid封装图片
    List<RouteImg> findByRid(int rid);
}
