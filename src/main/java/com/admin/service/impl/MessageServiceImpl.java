package com.admin.service.impl;

import com.admin.dao.IMessageDao;
import com.admin.service.IMessageService;
import com.admin.util.DateUtils;
import com.github.pagehelper.PageHelper;
import com.wink.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private IMessageDao messageDao;

    @Override
    public List<Message> findAll(Integer page, Integer size, String search) throws Exception {
        PageHelper.startPage(page,size);
        return messageDao.findAll(page,size,search);
    }

    @Override
    public void delete(int mid) throws Exception {
        messageDao.delete(mid);
    }

    @Override
    public void save(Message message) throws Exception {
        message.setDatetime(DateUtils.date2String(new Date(),"yyyy-MM-dd HH:mm"));
        messageDao.save(message);
    }
}
