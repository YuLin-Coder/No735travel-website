package com.wink.service.impl;

import com.wink.dao.ITopDao;
import com.wink.dao.impl.TopDaoImpl;
import com.wink.domain.PageBean;
import com.wink.domain.Route;
import com.wink.service.ITopService;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(收藏排行榜业务层实现类)
 */
public class TopServiceImpl implements ITopService {

    private ITopDao topDao = new TopDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int currentPage, String rname, Double smoney, Double emoney, int pageSize) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<>();
        //设置当前页码,每页显示条数
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = topDao.findTotalCount(rname,smoney,emoney);
        pb.setTotalCount(totalCount);

        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;
        List<Route> list = topDao.findByPage(start,rname,smoney,emoney,pageSize);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }
}
