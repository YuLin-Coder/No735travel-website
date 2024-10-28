package com.admin.service.impl;

import com.admin.dao.IImgDao;
import com.admin.service.IImgService;
import com.wink.domain.RouteImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ImgServiceImpl implements IImgService {

    @Autowired
    private IImgDao imgDao;

    @Override
    public void save(RouteImg routeImg) throws Exception {
        imgDao.save(routeImg);
    }

    @Override
    public List<RouteImg> findImgByRid(int rid) throws Exception {
        return imgDao.findImgByRid(rid);
    }

    @Override
    public void delete(int[] rgids) throws Exception {
        if (rgids.length>0){
            for (int rgid : rgids) {
                imgDao.delete(rgid);
            }
        }
    }
}
