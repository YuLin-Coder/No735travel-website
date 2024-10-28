package com.admin.service;

import com.wink.domain.Route;

import java.util.List;

public interface IRouteService {

    void saveRoute(Route route) throws Exception;

    void delete(int rid) throws Exception;

    void update(Route route)throws Exception;

    List<Route> findAll(int page,int size,String rname) throws Exception;

    Route findByRid(int rid) throws Exception;
}
