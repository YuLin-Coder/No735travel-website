package com.wink.web.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wink.domain.PageBean;
import com.wink.domain.Route;
import com.wink.service.ITopService;
import com.wink.service.impl.TopServiceImpl;

/**
 * @Author: Mr.Ye
 * @Description: TODO(收藏排行榜)
 */
@WebServlet("/favoriteTop/*")
public class TopServlet extends BaseServlet {

    private ITopService topService = new TopServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取参数
        String currentPageStr = request.getParameter("currentPage");
        String rname = request.getParameter("rname");
        String smoneyStr = request.getParameter("smoney");
        String emoneyStr = request.getParameter("emoney");
        String pageSizeStr = request.getParameter("pageSize");

        //处理参数
        int currentPage = 1;
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }

        if (rname != null && rname != ""&& !"null".equals(rname)){
            rname = new String(rname.getBytes("iso-8859-1"),"utf-8");
        }else{
            rname = null;
        }

        Double smoney;
        if (smoneyStr != null && smoneyStr.length() > 0){
            smoney = Double.parseDouble(smoneyStr);
        }else{
            smoney = null;
        }
        Double emoney;
        if (emoneyStr != null && emoneyStr.length() > 0){
            emoney = Double.parseDouble(emoneyStr);
        }else{
            emoney = null;
        }

        int pageSize = 8;
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        //调用service方法查询pagebean对象
        PageBean<Route> pb = topService.pageQuery(currentPage,rname,smoney,emoney,pageSize);

        //返回数据到客户端
        writeValue(pb,response);
    }

}
