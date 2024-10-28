package com.wink.service.impl;

import com.wink.dao.IFavoriteDao;
import com.wink.dao.IRouteDao;
import com.wink.dao.IRouteImgDao;
import com.wink.dao.ISellerDao;
import com.wink.dao.impl.FavoriteDaoImpl;
import com.wink.dao.impl.RouteDaoImpl;
import com.wink.dao.impl.RouteImgDaoImpl;
import com.wink.dao.impl.SellerDaoImpl;
import com.wink.domain.PageBean;
import com.wink.domain.Route;
import com.wink.domain.RouteImg;
import com.wink.domain.Seller;
import com.wink.service.IRouteService;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(旅游线路商品业务层实现类)
 */
public class RouteServiceImpl implements IRouteService {

    private IRouteDao routeDao = new RouteDaoImpl();

    private IRouteImgDao routeImgDao = new RouteImgDaoImpl();

    private ISellerDao sellerDao = new SellerDaoImpl();

    private IFavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<>();
        //设置当前页码,每页显示条数
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);

        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findOne(String rid) {
        Route route ;
        if (rid == ""){//线路为空
            route = routeDao.findOne(1);
        }else{
            route = routeDao.findOne(Integer.parseInt(rid));
        }

        //根据route的id 查询图片集合信息
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());

        //将集合设置到route对象
        route.setRouteImgList(routeImgList);

        //根据route的sid（商家id）查询商家对象
        Seller seller = sellerDao.findBySid(route.getSid());
        route.setSeller(seller);

        //插叙收藏次数
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);

        return route;
    }

    @Override
    public List<Route> findTopRoute(int cid,int num) {
        return routeDao.findTopRoute(cid,num);
    }

    @Override
    public List<Route> findNewRoute() {
        return routeDao.findNewRoute();
    }

    @Override
    public List<Route> findThemeRoute() {
        return routeDao.findThemeRoute();
    }
}
