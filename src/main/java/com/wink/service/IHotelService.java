package com.wink.service;

import com.wink.domain.Hotel;
import com.wink.domain.PageBean;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店业务层接口)
 */
public interface IHotelService {
    //分页查询所有酒店记录
    PageBean<Hotel> pageQuery( int currentPage, int pageSize);

    //查询一个酒店信息
    Hotel findOne(String hid);
}
