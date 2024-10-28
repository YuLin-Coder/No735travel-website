package com.wink.dao;

import com.wink.domain.Favorite;

/**
 * @Author: Mr.Ye
 * @Description: TODO(收藏的持久层接口)
 */
public interface IFavoriteDao {

    //根据rid和uid查询收藏信息
    Favorite findByRidAndUid(int rid,int uid);

    //根据rid 查询收藏次数
    int findCountByRid(int rid);

    //添加收藏
    void addFavorite(int rid,int uid);

    //删除收藏
    void deleteFavorite(int rid,int uid);

    //修改收藏次数
    void updateFavorite(int rid);

}
