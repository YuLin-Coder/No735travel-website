package com.admin.service.impl;

import com.admin.dao.ICategoryDao;
import com.admin.service.ICategoryService;
import com.github.pagehelper.PageHelper;
import com.wink.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public void save(Category category) throws Exception {
        categoryDao.save(category);
    }

    @Override
    public void delete(int cid) throws Exception {
        categoryDao.delete(cid);
    }

    @Override
    public void update(Category category) throws Exception {
        categoryDao.update(category);
    }

    @Override
    public Category findByCid(int cid) throws Exception {
        return categoryDao.findByCid(cid);
    }

    @Override
    public List<Category> findAll(int page,int size,String cname) throws Exception {
        PageHelper.startPage(page, size);
        return categoryDao.findAll(page, size, cname);
    }

    @Override
    public List<Category> findAllCName() throws Exception {
        return categoryDao.findAllCName();
    }
}
