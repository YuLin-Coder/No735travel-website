package com.admin.service;

import com.wink.domain.Seller;
import com.wink.domain.User;

import java.util.List;

public interface ISellerService {

    Seller findBySid(int sid) throws Exception;

    List<Seller> findAll() throws Exception;

    List<Seller> findAlls(int page,int size,String search) throws Exception;

    void update(Seller seller)throws Exception;

    void delete(int sid)throws Exception;

    void save(Seller seller) throws Exception;

    List<User> findUsers() throws Exception;
}
