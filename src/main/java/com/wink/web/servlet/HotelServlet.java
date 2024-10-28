package com.wink.web.servlet;

import com.wink.domain.Hotel;
import com.wink.domain.PageBean;
import com.wink.domain.Route;
import com.wink.service.IHotelService;
import com.wink.service.impl.HotelServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店相关操作)
 */
@WebServlet("/hotel/*")
public class HotelServlet extends BaseServlet {

    private IHotelService hotelService = new HotelServiceImpl();

    /**
     * 分页查询酒店
     * @param request
     * @param response
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage = 0;//当前页面，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示5条数记录
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 8;
        }

        //3.调用service查询pagebean对象
        PageBean<Hotel> pb = hotelService.pageQuery(currentPage,pageSize);

        //4. 将pageBean对象序列化为json，返回
        writeValue(pb,response);
    }

    /**
     * 查询酒店详细信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String hid = request.getParameter("hid");

        Hotel hotel = hotelService.findOne(hid);

        writeValue(hotel,response);
    }
}
