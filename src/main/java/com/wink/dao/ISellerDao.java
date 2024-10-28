package com.wink.dao;

import com.wink.domain.Seller;

/**
 * @Author: Mr.Ye
 * @Description: TODO(商家)
 */
public interface ISellerDao {

    //根据id查询商家信息
    Seller findBySid(int sid);
}
