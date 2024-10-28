package com.admin.dao;

import com.wink.domain.Message;
import com.wink.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(留言信息)
 */
@Repository
public interface IMessageDao {

    //查询所有留言信息
    @Select("select * from tab_message where title like #{search}")
    @Results({
            @Result(id = true,property = "mid",column = "mid"),
            @Result(property = "content",column = "content"),
            @Result(property = "datetime",column = "datetime"),
            @Result(property = "title",column = "title"),
            @Result(property = "user",column = "uid",javaType = User.class,one= @One(select ="com.admin.dao.IUserDao.findByUid" ))
    })
    List<Message> findAll(@Param("page") int page, @Param("size")int size, @Param("search")String search) throws Exception;

    //删除留言
    @Delete("delete from tab_message where mid=#{mid}")
    void delete(int mid) throws Exception;

    //新增留言
    @Insert("insert into tab_message(uid,content,datetime,title) values(#{uid},#{content},#{datetime},#{title})")
    void save(Message message)throws Exception;
}
