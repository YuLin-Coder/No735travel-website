package com.wink.dao;

import com.wink.domain.Category;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(分类持久层接口)
 */
public interface ICategoryDao {

    //查询所有
    List<Category> findAll();
}
