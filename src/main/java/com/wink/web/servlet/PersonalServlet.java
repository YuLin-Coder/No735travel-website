package com.wink.web.servlet;

import com.wink.domain.*;
import com.wink.service.IPersonalService;
import com.wink.service.impl.PersonalServiceImpl;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Mr.Ye
 * @Description: TODO(我的收藏)
 */
@WebServlet("/personal/*")
public class PersonalServlet extends BaseServlet {

    private IPersonalService personalService = new PersonalServiceImpl();

    /**
     * 根据分页查询我的收藏
     * @param request
     * @param response
     * @throws IOException
     */
    public void findFavoriteByPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //1.获取参数
        String uidStr = request.getParameter("uid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        //2.处理参数
        int uid = 0;
        if (uidStr != null && uidStr.length() > 0 && !"null".equals(uidStr)){
            uid = Integer.parseInt(uidStr);
        }
        int currentPage = 1;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }

        int pageSize = 8;//每页显示条数，如果不传递，默认每页显示5条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        //3.调用service查询PageBean对象
        PageBean<Route> pb = personalService.pageQuery(uid, currentPage, pageSize);
        //4. 将pageBean对象序列化为json，返回
        writeValue(pb,response);
    }

    /**
     * 查询我的留言
     * @param request
     * @param response
     * @throws IOException
     */
    public void findMessageByPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //1.获取参数
        String uidStr = request.getParameter("uid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        //2.处理参数
        int uid = 0;
        if (uidStr != null && uidStr.length() > 0 && !"null".equals(uidStr)){
            uid = Integer.parseInt(uidStr);
        }
        int currentPage = 1;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }

        int pageSize = 3;//每页显示条数，如果不传递，默认每页显示3条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        //3.调用service查询PageBean对象
        PageBean<Message> pb = personalService.QueryMessage(uid, currentPage, pageSize);
        //4. 将pageBean对象序列化为json，返回
        writeValue(pb,response);
    }

    /**
     * 删除我的留言
     * @param request
     * @param response
     * @throws IOException
     */
    public void deletemessage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String mid = request.getParameter("mid");
        Object object = null;
        if (mid != null){
            object = personalService.delmsg(mid);
        }
        writeValue(object,response);
    }

    /**
     * 根据分页查询我的酒店订单
     * @param request
     * @param response
     * @throws IOException
     */
    public void findOrderByPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //1.获取参数
        String uidStr = request.getParameter("uid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        //2.处理参数
        int uid = 0;
        if (uidStr != null && uidStr.length() > 0 && !"null".equals(uidStr)){
            uid = Integer.parseInt(uidStr);
        }
        int currentPage = 1;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }

        int pageSize = 8;//每页显示条数，如果不传递，默认每页显示5条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        //3.调用service查询PageBean对象
        PageBean<Order> pb = personalService.QueryOrder(uid, currentPage, pageSize);

        //4. 将pageBean对象序列化为json，返回
        writeValue(pb,response);
    }

    /**
     * 查询景点订单
     * @param request
     * @param response
     * @throws IOException
     */
    public void findROrderByPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //1.获取参数
        String uidStr = request.getParameter("uid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        //2.处理参数
        int uid = 0;
        if (uidStr != null && uidStr.length() > 0 && !"null".equals(uidStr)){
            uid = Integer.parseInt(uidStr);
        }
        int currentPage = 1;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }

        int pageSize = 6;//每页显示条数，如果不传递，默认每页显示6条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        //3.调用service查询PageBean对象
        PageBean<RouteOrder> pb = personalService.QueryROrder(uid, currentPage, pageSize);

        //4. 将pageBean对象序列化为json，返回
        writeValue(pb,response);
    }
}
