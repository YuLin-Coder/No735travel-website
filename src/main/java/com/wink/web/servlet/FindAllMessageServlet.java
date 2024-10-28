package com.wink.web.servlet;

import com.wink.domain.Message;
import com.wink.domain.PageBean;
import com.wink.service.IMessageService;
import com.wink.service.impl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Mr.Ye
 * @Description: TODO(分页查询所有留言)
 */
@WebServlet("/findAllMessage")
public class FindAllMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage = 1;
        if (currentPageStr != null && !"".equals(currentPageStr)){
            currentPage = Integer.parseInt(currentPageStr);
        }
        int pageSize = 5;
        if (pageSizeStr != null && !"".equals(pageSizeStr)){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        IMessageService messageService = new MessageServiceImpl();
        PageBean<Message> pb = messageService.findByPage(currentPage,pageSize);

        //将pageBean存入request
        request.setAttribute("pb",pb);
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
