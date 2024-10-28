package com.wink.service.impl;

import com.wink.dao.IMessageDao;
import com.wink.dao.impl.MessageDaoImpl;
import com.wink.domain.Message;
import com.wink.domain.PageBean;
import com.wink.domain.User;
import com.wink.service.IMessageService;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(留言业务层实现类)
 */
public class MessageServiceImpl implements IMessageService {

    private IMessageDao messageDao = new MessageDaoImpl();

    @Override
    public void saveMessage(Message message) {
        messageDao.saveMessage(message);
    }

    @Override
    public PageBean<Message> findByPage(int currentPage, int pageSize) {

        PageBean<Message> pb = new PageBean<>();

        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = messageDao.findTotalCount();
        pb.setTotalCount(totalCount);
        //开始
        int start = (currentPage-1)*pageSize;
        List<Message> list = messageDao.findByPage(start,pageSize);
        User user;
        for (Message message : list) {
            user = messageDao.findByUid(message.getUid());
            message.setUser(user);
        }
        pb.setList(list);
        //计算页数
        int totalPage = totalCount % pageSize ==0 ? (totalCount/pageSize):(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);

        return pb;
    }
}
