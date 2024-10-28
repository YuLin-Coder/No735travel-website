package com.wink.web.servlet;

import com.wink.domain.Category;
import com.wink.service.ICategoryService;
import com.wink.service.impl.CategoryServiceImpl;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @Author: Mr.Ye
 * @Description: TODO(分类查询)
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private ICategoryService service = new CategoryServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cid = request.getParameter("cid");
        //1.调用service查询所有
        List<Category> cs = service.findAll();
        //2.序列化json返回
        writeValue(cs,response);
    }
}
