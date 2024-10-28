package com.wink.service.impl;

import java.util.List;

import com.wink.dao.IMessageDao;
import com.wink.dao.IOrderDao;
import com.wink.dao.IPersonalDao;
import com.wink.dao.IRouteDao;
import com.wink.dao.impl.MessageDaoImpl;
import com.wink.dao.impl.OrderDaoImpl;
import com.wink.dao.impl.PersonalDaoImpl;
import com.wink.dao.impl.RouteDaoImpl;
import com.wink.domain.Hotel;
import com.wink.domain.Message;
import com.wink.domain.Order;
import com.wink.domain.PageBean;
import com.wink.domain.Route;
import com.wink.domain.RouteOrder;
import com.wink.domain.User;
import com.wink.service.IPersonalService;

/**
 * @Author: Mr.Ye
 * @Description: TODO(我的收藏业务层实现类)
 */
public class PersonalServiceImpl implements IPersonalService {

    private IPersonalDao personalDao = new PersonalDaoImpl();

    private IMessageDao messageDao = new MessageDaoImpl();

    private IOrderDao orderDao = new OrderDaoImpl();

    private IRouteDao routeDao = new RouteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int uid, int currentPage, int pageSize) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = personalDao.findTotalFavorite(uid);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Route> list = personalDao.findFavoriteByPage(uid,start,pageSize);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public PageBean<Message> QueryMessage(int uid, int currentPage, int pageSize) {
        PageBean<Message> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = personalDao.findMyMessageCount(uid);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1)*pageSize;
        List<Message> list = personalDao.findMyMessage(uid,start,pageSize);
        User user;
        for (Message message : list) {
            user = messageDao.findByUid(message.getUid());
            message.setUser(user);
        }
        pb.setList(list);

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public int delmsg(String mid) {
        return personalDao.delmsg(Integer.parseInt(mid));
    }

    @Override
    public PageBean<Order> QueryOrder(int uid, int currentPage, int pageSize) {
        //封装PageBean
        PageBean<Order> pb = new PageBean<>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = personalDao.findTotalOrder(uid);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Order> list = personalDao.findOrderByPage(uid,start,pageSize);

        Hotel hotel;
        for (Order order : list){
            hotel = orderDao.findHotelByhid(order.getHid());
            order.setHotel(hotel);
        }
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public PageBean<RouteOrder> QueryROrder(int uid, int currentPage, int pageSize) {
        //封装PageBean
        PageBean<RouteOrder> pb = new PageBean<>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = personalDao.findTotalROrder(uid);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<RouteOrder> list = personalDao.findROrderByPage(uid,start,pageSize);

        Route route;
        for (RouteOrder order: list){
            route = routeDao.findOne(order.getRid());
            order.setRoute(route);
        }
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }
}
