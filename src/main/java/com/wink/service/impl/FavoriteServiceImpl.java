package com.wink.service.impl;

import com.wink.dao.IFavoriteDao;
import com.wink.dao.impl.FavoriteDaoImpl;
import com.wink.domain.Favorite;
import com.wink.service.IFavoriteService;

/**
 * @Author: Mr.Ye
 * @Description: TODO(判断是否收藏)
 */
public class FavoriteServiceImpl implements IFavoriteService {

    private IFavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {

        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid),uid);
        return favorite != null ? true : false;
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.addFavorite(Integer.parseInt(rid),uid);
    }

    @Override
    public void delete(String rid, int uid) {
        favoriteDao.deleteFavorite(Integer.parseInt(rid),uid);
    }

    @Override
    public void updateFavoriteCount(String rid) {
        favoriteDao.updateFavorite(Integer.parseInt(rid));
    }
}
