package com.wink.web.servlet;

import com.wink.domain.PageBean;
import com.wink.domain.Route;
import com.wink.domain.User;
import com.wink.service.IFavoriteService;
import com.wink.service.IRouteService;
import com.wink.service.impl.FavoriteServiceImpl;
import com.wink.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(线路及分页查询)
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private IRouteService routeService = new RouteServiceImpl();

    private IFavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        if (rname != null && rname != ""){
            rname = new String(rname.getBytes("iso-8859-1"),"utf-8");
        }

        int cid = 0;//类别id
        //2.处理参数
        if (cidStr !=null && !"null".equals(cidStr) && cidStr.length() > 0){
            cid = Integer.parseInt(cidStr);
        }

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
            pageSize = 5;
        }

        //3.调用service查询pagebean对象
        PageBean<Route> pb = routeService.pageQuery(cid,currentPage,pageSize,rname);

        //4. 将pageBean对象序列化为json，返回
        writeValue(pb,response);
    }

    /**
     * 查询热门线路
     * @param request
     * @param response
     * @throws IOException
     */
    public void findTopRoute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cidStr = request.getParameter("cid");
        String numStr = request.getParameter("num");

        int cid = 0;//类别id
        //处理参数
        if (cidStr !=null && !"null".equals(cidStr) && cidStr.length() > 0){
            cid = Integer.parseInt(cidStr);
        }
        int num = 0;
        if (numStr != null && !"null".equals(numStr) && numStr != ""){
            num = Integer.parseInt(numStr);
        }

        List<Route> toproute = routeService.findTopRoute(cid,num);

        writeValue(toproute,response);
    }

    /**
     * 查询最近旅游
     * @param request
     * @param response
     * @throws IOException
     */
    public void findNewRoute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Route> toproute = routeService.findNewRoute();

        writeValue(toproute,response);
    }

    /**
     * 查询主题旅游，如果都是则按照最新旅游
     * @param request
     * @param response
     * @throws IOException
     */
    public void findThemeRoute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Route> toproute = routeService.findThemeRoute();

        writeValue(toproute,response);
    }

    /**
     * 根据id查询一个旅游线路的详细信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String rid = request.getParameter("rid");

        Route route = routeService.findOne(rid);

        writeValue(route,response);
    }

    /**
     * 判断当前登录用户是否收藏过该线路
     * @param request
     * @param response
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取线路id
        String rid = request.getParameter("rid");

        //获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null){
            return;
        }else{
            uid = user.getUid();
        }
        //调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid,uid);
        //写回客户端
        writeValue(flag,response);
    }

    /**
     * 添加/取消收藏
     * @param request
     * @param response
     * @throws IOException
     */
    public void choseFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1. 获取线路rid
        String rid = request.getParameter("rid");
        //2. 获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user==null){//用户尚未登录
            return;
        }else{
            uid = user.getUid();
        }
        //3.判断是否收藏
        boolean flag = favoriteService.isFavorite(rid,uid);
        if (flag){//收藏过，再点击，取消
            favoriteService.delete(rid,uid);
            favoriteService.updateFavoriteCount(rid);
        }else {//没有收藏过，点击添加收藏
            favoriteService.add(rid,uid);
            favoriteService.updateFavoriteCount(rid);
        }
        writeValue(flag,response);
    }
}
