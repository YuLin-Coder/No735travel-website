package com.wink.service.impl;

import com.wink.dao.ICategoryDao;
import com.wink.dao.impl.CategoryDaoImpl;
import com.wink.domain.Category;
import com.wink.service.ICategoryService;


import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(分类业务层实现类)
 */
public class CategoryServiceImpl implements ICategoryService {

    private ICategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
