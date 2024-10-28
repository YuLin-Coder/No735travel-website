package com.wink.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Mr.Ye
 * @Description: TODO(解决全站乱码问题，处理所有的请求)
 */
@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //将父接口转为子接口
        HttpServletRequest  req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        //获取请求方法
        String method = req.getMethod();
        //解决post请求中文数据乱码问题
        if(method.equalsIgnoreCase("post")){
            req.setCharacterEncoding("utf-8");
        }

        //处理响应乱码
        rep.setContentType("text/html;charset=utf-8");
        chain.doFilter(req,rep);
    }

    @Override
    public void destroy() {

    }
}
