package com.admin.service.impl;

import com.admin.dao.IOrderDao;
import com.admin.service.IOrderService;
import com.github.pagehelper.PageHelper;
import com.wink.domain.Order;
import com.wink.domain.RouteOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Override
    public List<Order> findAll(Integer page, Integer size, String search) throws Exception {
        PageHelper.startPage(page,size);
        return orderDao.findAll(page,size,search);
    }

    @Override
    public void delete(int id) throws Exception {
        orderDao.delete(id);
    }

    @Override
    public List<RouteOrder> findAllRoute(Integer page, Integer size, String search) throws Exception {
        PageHelper.startPage(page,size);
        return orderDao.findAllRoute(page,size,search);
    }

    @Override
    public void deleteRoute(Integer id) throws Exception {
        orderDao.deleteRoute(id);
    }
}
