package com.admin.service;

import com.wink.domain.RouteImg;

import java.util.List;

public interface IImgService {

    void save(RouteImg routeImg) throws Exception;

    List<RouteImg> findImgByRid(int rid)throws Exception;

    void delete(int[] rgids) throws Exception;
}
