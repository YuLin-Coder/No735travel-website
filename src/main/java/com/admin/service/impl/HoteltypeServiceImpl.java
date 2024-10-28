package com.admin.service.impl;

import com.admin.dao.IHoteltypeDao;
import com.admin.service.IHoteltypeService;
import com.github.pagehelper.PageHelper;
import com.wink.domain.Hoteltype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HoteltypeServiceImpl implements IHoteltypeService {

    @Autowired
    private IHoteltypeDao hoteltypeDao;

    @Override
    public List<Hoteltype> findAll(Integer page, Integer size, String search) throws Exception {
        PageHelper.startPage(page,size);
        return hoteltypeDao.findAll(page,size,search);
    }

    @Override
    public void save(Hoteltype hoteltype) throws Exception {
        hoteltypeDao.save(hoteltype);
    }

    @Override
    public void delete(int tid) throws Exception {
        //从tab_hoteldetail表删除
        hoteltypeDao.deletefrom_tab_hoteltype(tid);
        //从tab_hoteltype表删除
        hoteltypeDao.delete(tid);
    }
}
