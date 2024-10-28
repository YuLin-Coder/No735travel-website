package com.wink.dao;

import com.wink.domain.Route;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(旅游线路商品分页)
 */
public interface IRouteDao {

    //根据cid查询总记录数
    int findTotalCount(int cid,String rname);

    //根据cid，start,rname查询当前页的数据集合
    List<Route> findByPage(int cid,int start,int pageSize,String rname);

    //根据id查询route对象
    Route findOne(int rid);

    //查询前num条热门景点线路
    List<Route> findTopRoute(int cid,int num);

    //查询最新旅游
    List<Route> findNewRoute();

    //查询主题旅游
    List<Route> findThemeRoute();
}

