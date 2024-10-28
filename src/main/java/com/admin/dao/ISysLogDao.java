package com.admin.dao;

import com.wink.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(日志信息)
 */
@Repository
public interface ISysLogDao {

    @Insert("insert into tab_syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog) throws Exception;

    @Select("select * from tab_sysLog where url like #{search}")
    List<SysLog> findAll(@Param("page") int page, @Param("size")int size, @Param("search")String search) throws Exception;
}
