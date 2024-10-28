package com.admin.service.impl;

import com.admin.dao.IHotelDao;
import com.admin.service.IHotelService;
import com.github.pagehelper.PageHelper;
import com.wink.domain.Hotel;
import com.wink.domain.Hoteldetail;
import com.wink.domain.Hoteltype;
import com.wink.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HotelServiceImpl implements IHotelService {

    @Autowired
    private IHotelDao hotelDao;

    @Override
    public List<Hotel> findAll(Integer page, Integer size, String search) throws Exception {
        PageHelper.startPage(page,size);
        return hotelDao.findAll(page,size,search);
    }

    @Override
    public void save(Hotel hotel) throws Exception {
        hotelDao.save(hotel);
    }

    @Override
    public List<User> findUsers() throws Exception {
        return hotelDao.findUsers();
    }

    @Override
    public void delete(int hid) throws Exception {
        //从tab_hoteldetail删除酒店
        hotelDao.deletefrom_tab_hoteldetail(hid);
        //从tab_hotel删除酒店
        hotelDao.delete(hid);
    }

    @Override
    public Hotel findByHid(int hid) throws Exception {
        return hotelDao.findByHid(hid);
    }

    @Override
    public void update(Hotel hotel) throws Exception {
        hotelDao.update(hotel);
    }

    @Override
    public List<Hoteldetail> findDetail(int hid) throws Exception {
        return hotelDao.findDetail(hid);
    }

    @Override
    public void addRoom(int hid, int[] ids,List prices) throws Exception {
        for (int i=0;i<ids.length;i++){
            hotelDao.addRoom(hid,ids[i], (Double) prices.get(i));
        }
    }

    @Override
    public List<Hoteltype> findOtherRooms(int hid) throws Exception {
        return hotelDao.findOtherRooms(hid);
    }

    @Override
    public void removeRoom(int hid, int[] ids) throws Exception {
        for (int tid : ids) {
            hotelDao.removeRoom(hid,tid);
        }
    }

}
