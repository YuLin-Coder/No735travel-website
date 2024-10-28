package com.admin.service;

import com.wink.domain.Category;

import java.util.List;

public interface ICategoryService {

    void save(Category category) throws Exception;

    void delete(int cid) throws Exception;

    void update(Category category) throws Exception;

    Category findByCid(int cid) throws Exception;

    List<Category> findAll(int page,int size,String cname) throws Exception;

    List<Category> findAllCName() throws Exception;
}
