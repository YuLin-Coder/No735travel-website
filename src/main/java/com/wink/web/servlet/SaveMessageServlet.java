package com.wink.web.servlet;

import com.wink.domain.Message;
import com.wink.domain.User;
import com.wink.service.IMessageService;
import com.wink.service.impl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @Author: Mr.Ye
 * @Description: TODO(新增留言)
 */
@WebServlet("/saveMessage")
public class SaveMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        Object user = request.getSession().getAttribute("user");
        if (user == null){
            request.setAttribute("login_msg","您尚未登录，请登录后留言");
            request.getRequestDispatcher("/findAllMessage").forward(request,response);

        }else{
            User user1 = (User) user;
            int uid = user1.getUid();
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String dateTime = LocalDateTime.now().format(formatter);

            Message message = new Message();
            message.setTitle(title);
            message.setContent(content);
            message.setUid(uid);
            message.setDatetime(dateTime);

            IMessageService messageService = new MessageServiceImpl();
            messageService.saveMessage(message);

            response.sendRedirect(request.getContextPath()+"/findAllMessage");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
