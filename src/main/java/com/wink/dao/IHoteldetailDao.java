package com.wink.dao;

import com.wink.domain.Hoteldetail;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店房型持久层接口)
 */
public interface IHoteldetailDao {

    //根据hid查询所有房型和价格
    List<Hoteldetail> findByHid(int hid);

}
