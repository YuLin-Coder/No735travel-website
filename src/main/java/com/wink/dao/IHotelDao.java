package com.wink.dao;

import com.wink.domain.Hotel;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店持久层接口)
 */
public interface IHotelDao {
    //查询酒店总数
    int findTotalCount();

    //分页查询酒店信息
    List<Hotel> findbyPage(int start, int pageSize);

    //查询一个酒店信息
    Hotel findOne(int hid);
}
