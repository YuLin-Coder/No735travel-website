package com.wink.service;

/**
 * @Author: Mr.Ye
 * @Description: TODO(收藏的业务层接口)
 */
public interface IFavoriteService {

    //判断是否收藏
    boolean isFavorite(String rid,int uid);

    //添加收藏
    void add(String rid, int uid);

    //取消收藏
    void delete(String rid, int uid);

    //更新收藏次数
    void updateFavoriteCount(String rid);
}
