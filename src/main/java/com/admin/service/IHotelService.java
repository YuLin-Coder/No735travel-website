package com.admin.service;


import com.wink.domain.Hotel;
import com.wink.domain.Hoteldetail;
import com.wink.domain.Hoteltype;
import com.wink.domain.User;

import java.util.List;

public interface IHotelService {
    List<Hotel> findAll(Integer page, Integer size, String search) throws Exception;

    void save(Hotel hotel) throws Exception;

    List<User> findUsers() throws Exception;

    void delete(int hid)throws Exception;

    Hotel findByHid(int hid)throws Exception;

    void update(Hotel hotel)throws Exception;

    List<Hoteldetail> findDetail(int hid)throws Exception;

    void addRoom(int hid, int[] ids,List prices)throws Exception;

    List<Hoteltype> findOtherRooms(int hid)throws Exception;

    void removeRoom(int hid, int[] ids)throws Exception;
}
