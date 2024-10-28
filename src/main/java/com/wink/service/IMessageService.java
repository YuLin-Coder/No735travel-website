package com.wink.service;

import com.wink.domain.Message;
import com.wink.domain.PageBean;


/**
 * @Author: Mr.Ye
 * @Description: TODO(留言业务层接口)
 */
public interface IMessageService {

    //保存留言
    void saveMessage(Message message);

    //分页查询留言
    PageBean<Message> findByPage(int currentPage, int pageSize);
}
