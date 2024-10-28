package com.admin.service.impl;

import com.admin.dao.ISellerDao;
import com.admin.service.ISellerService;
import com.github.pagehelper.PageHelper;
import com.wink.domain.Seller;
import com.wink.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SellerServiceImpl implements ISellerService {

    @Autowired
    private ISellerDao sellerDao;

    @Override
    public Seller findBySid(int sid) throws Exception{
        return sellerDao.findBySid(sid);
    }

    @Override
    public List<Seller> findAll() throws Exception {
        return sellerDao.findAll();
    }

    @Override
    public List<Seller> findAlls(int page,int size,String search) throws Exception {
        PageHelper.startPage(page,size);
        return sellerDao.findAlls(page,size,search);
    }

    @Override
    public void update(Seller seller) throws Exception {
        sellerDao.update(seller);
    }

    @Override
    public void delete(int sid) throws Exception {
        sellerDao.delete(sid);
    }

    @Override
    public void save(Seller seller) throws Exception {
        sellerDao.save(seller);
    }

    @Override
    public List<User> findUsers() throws Exception {
        return sellerDao.findUsers();
    }
}
