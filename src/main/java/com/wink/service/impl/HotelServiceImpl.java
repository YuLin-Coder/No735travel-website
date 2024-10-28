package com.wink.service.impl;

import com.wink.dao.IHotelDao;
import com.wink.dao.IHoteldetailDao;
import com.wink.dao.IHoteltypeDao;
import com.wink.dao.ISellerDao;
import com.wink.dao.impl.HotelDaoImpl;
import com.wink.dao.impl.HoteldetailDaoImpl;
import com.wink.dao.impl.HoteltypeDaoImpl;
import com.wink.dao.impl.SellerDaoImpl;
import com.wink.domain.*;
import com.wink.service.IHotelService;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店业务层接口实现类)
 */
public class HotelServiceImpl implements IHotelService {

    private IHotelDao hotelDao = new HotelDaoImpl();

    private IHoteldetailDao hoteldetailDao = new HoteldetailDaoImpl();

    private IHoteltypeDao hoteltypeDao = new HoteltypeDaoImpl();

    @Override
    public PageBean<Hotel> pageQuery(int currentPage, int pageSize) {
        PageBean<Hotel> pb = new PageBean<>();

        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        int totalCount = hotelDao.findTotalCount();
        pb.setTotalCount(totalCount);
        //总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);

        //设置当前页数据集合
        int start = (currentPage - 1)*pageSize;
        List<Hotel> hotels = hotelDao.findbyPage(start, pageSize);
        pb.setList(hotels);

        return pb;
    }

    @Override
    public Hotel findOne(String hid) {

        Hotel hotel = hotelDao.findOne(Integer.parseInt(hid));

        //根据hid查询房间集合
        List<Hoteldetail> hoteldetails = hoteldetailDao.findByHid(Integer.parseInt(hid));
        hotel.setHoteldetail(hoteldetails);

        //根据tid查询房型
        Hoteltype hoteltype;
        for (Hoteldetail h : hoteldetails) {
            hoteltype = hoteltypeDao.findByTid(h.getTid());
            h.setHoteltype(hoteltype);
        }
        return hotel;
    }
}
