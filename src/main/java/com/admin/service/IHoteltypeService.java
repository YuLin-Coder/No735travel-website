package com.admin.service;

import com.wink.domain.Hoteltype;

import java.util.List;

public interface IHoteltypeService {

    List<Hoteltype> findAll(Integer page, Integer size, String search) throws Exception;

    void save(Hoteltype hoteltype)throws Exception;

    void delete(int tid)throws Exception;
}
