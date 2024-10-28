package com.admin.service;

import com.wink.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(int page,int size,String search) throws Exception;
}
