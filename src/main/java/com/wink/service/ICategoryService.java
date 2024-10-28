package com.wink.service;

import com.wink.domain.Category;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(分类业务层接口)
 */
public interface ICategoryService {

    //查询所有
    List<Category> findAll();
}
