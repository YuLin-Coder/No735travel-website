package com.wink.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(留言敏感词汇过滤器)
 */
@WebFilter("/saveMessage")
public class WordFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //创建代理对象，增强getParameter方法
        ServletRequest proxy = (ServletRequest)Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("getParameter")){
                    //增强返回值
                    String value = (String) method.invoke(req,args);
                    if (value != null){
                        for (String str : list) {
                            if (value.contains(str)){
                                String re = "";
                                for (int i=0;i< str.length();i++){
                                    re += "*";
                                }
                                value = value.replace(str,re);
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(req,args);
            }
        });
        //放行，传递增强的代理对象
        chain.doFilter(proxy, resp);
    }

    private List<String> list = new ArrayList<>();// 敏感词汇集合

    public void init(FilterConfig config) throws ServletException {
           //1 加载文件,获取文件的真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/key.txt");
        try{

            //2 读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            //3 将文件的每一行添加到list
            String line = null;
            while ((line = br.readLine()) != null){
                list.add(line);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
