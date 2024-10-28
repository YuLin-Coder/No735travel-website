package com.admin.service.impl;

import com.admin.dao.ISysLogDao;
import com.admin.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import com.wink.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public List<SysLog> findAll(int page,int size,String search) throws Exception {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll(page,size,search);
    }

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }
}
