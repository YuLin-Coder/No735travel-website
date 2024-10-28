package com.admin.service.impl;

import com.admin.dao.IRouteDao;
import com.admin.service.IRouteService;
import com.github.pagehelper.PageHelper;
import com.wink.domain.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RouteServiceImpl implements IRouteService {

    @Autowired
    private IRouteDao routeDao;

    @Override
    public void saveRoute(Route route) throws Exception {
        routeDao.saveRoute(route);
    }

    @Override
    public void delete(int rid) throws Exception {
        routeDao.delete(rid);
    }

    @Override
    public void update(Route route) throws Exception {
        routeDao.update(route);
    }

    @Override
    public List<Route> findAll(int page,int size,String rname) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page, size);
        return routeDao.findAll(page,size,rname);
    }

    @Override
    public Route findByRid(int rid) throws Exception {
        return routeDao.findByRid(rid);
    }
}
