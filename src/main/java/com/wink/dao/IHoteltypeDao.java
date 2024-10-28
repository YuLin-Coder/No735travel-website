package com.wink.dao;

import com.wink.domain.Hoteltype;

/**
 * @Author: Mr.Ye
 * @Description: TODO(房型持久层接口)
 */
public interface IHoteltypeDao {

    //查询房型分类
    Hoteltype findByTid(int tid);
}
