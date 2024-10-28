package com.wink.service;

import com.wink.domain.PageBean;
import com.wink.domain.Route;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(旅游线路分页)
 */
public interface IRouteService {

    //根据类别进行分页查询
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    //根据id查询route对象
    Route findOne(String rid);

    //根据cid查询热度top num
    List<Route> findTopRoute(int cid,int num);

    //查询最新旅游
    List<Route> findNewRoute();

    //查询主题旅游，如果都是则按照最新旅游
    List<Route> findThemeRoute();
}
