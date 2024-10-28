package com.wink.web.servlet;

import com.wink.domain.User;
import com.wink.service.IUserService;
import com.wink.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author: Mr.Ye
 * @Description: TODO(个人信息修改用户信息)
 */
@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");

        Map<String,String[]> map = request.getParameterMap();
        if (map.size()!=0){
            User user = new User();
            try {
                BeanUtils.populate(user,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            IUserService userService = new UserServiceImpl();
            //获取session域中登录的user与提交修改的user进行密码判断
            User suser = (User)request.getSession().getAttribute("user");
            User u;
            if (user.getPassword().equals(suser.getPassword())){
                u = userService.updateNopwd(user);
            }else{
                u = userService.update(user);
            }
            request.getSession().setAttribute("user",u);
        }
        response.sendRedirect(request.getContextPath()+"/personal.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
