package com.wink.web.servlet;

import com.wink.domain.ResultInfo;
import com.wink.domain.User;
import com.wink.service.IUserService;
import com.wink.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @Author: Mr.Ye
 * @Description: TODO(注册、登录、退出)
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    //声明UserService业务对象
    private IUserService service = new UserServiceImpl();

    /**
     * 校验验证码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public boolean checkCodeMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次

        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            ResultInfo info = new ResultInfo();
            //验证码错误
            if ("".equals(check)){
                info.setErrorMsg("验证码不能为空");
            }else{
                info.setErrorMsg("验证码错误");
            }
            info.setFlag(false);
            //将info对象序列化为json,响应数据
            response.setContentType("application/json;charset=utf-8");
            String json = writeValueAsString(info);
            response.getWriter().write(json);
            return false;
        }
        return true;
    }
    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //验证码
        if(!checkCodeMethod(request,response)){
            return;
        }

        //1.获取数据,封装对象
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2.调用service完成注册
        boolean flag = false;
        if (user.getUsername() != null){
            flag = service.regist(user);
        }
        ResultInfo info = new ResultInfo();
        if(flag){//注册成功
            info.setFlag(true);
        }else {//注册失败
            info.setFlag(false);
            info.setErrorMsg("用户名已存在！");
        }

        //3.将info对象序列化为json,响应数据
        String json = writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取用户名和密码数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        username = username != null ? username : "";
        password = password != null ? password : "";
        remember = remember != null ? remember : "";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //3.调用Service查询
        User u  = service.login(user);
        ResultInfo info = new ResultInfo();

        //4.判断用户对象是否为null
        if(u == null){
            //用户名密码或错误
            info.setFlag(false);
            info.setErrorMsg("用户名密码或错误");
        }
        //5.判断登录成功
        if(u != null){
            Cookie user_cookie = new Cookie("username",  URLEncoder.encode(username, "UTF-8")); //解决cookie乱码
            Cookie pswd_cookie = new Cookie("password", password);
            Cookie reme_cookie = new Cookie("remember", remember);
            if(remember.equals("1")) {
                user_cookie.setMaxAge(24*60*60);
                user_cookie.setPath(request.getContextPath()+"/");
                response.addCookie(user_cookie);

                pswd_cookie.setMaxAge(24*60*60);
                pswd_cookie.setPath(request.getContextPath()+"/");
                response.addCookie(pswd_cookie);

                reme_cookie.setMaxAge(24*60*60);
                reme_cookie.setPath(request.getContextPath()+"/");
                response.addCookie(reme_cookie);
            }else {
                user_cookie.setMaxAge(0);
                user_cookie.setPath("/");
                response.addCookie(user_cookie);
                pswd_cookie.setMaxAge(0);
                pswd_cookie.setPath("/");
                response.addCookie(pswd_cookie);
                reme_cookie.setMaxAge(0);
                reme_cookie.setPath("/");
                response.addCookie(reme_cookie);
            }
            if (!checkCodeMethod(request,response)){
                return;
            }
            request.getSession().setAttribute("user",u);//登录成功标记
            //登录成功
            info.setFlag(true);
        }
        //响应数据
       writeValue(info,response);
    }

    /**
     * 查询一个
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端
        writeValue(user,response);
    }

    /**
     * 退出功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.销毁session
        request.getSession().invalidate();
        //2.跳转登录页面
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
}
