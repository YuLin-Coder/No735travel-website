package com.admin.controller;

import com.admin.service.ICategoryService;
import com.github.pagehelper.PageInfo;
import com.wink.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(景点分类)
 */
@Controller
@RequestMapping("/cates")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    //新增分类
    @RequestMapping("/save.do")
    @PreAuthorize("hasAnyAuthority('/cates/save.do')")
    public String save(Category category) throws Exception{
        categoryService.save(category);
        return "redirect:findAll.do";
    }

    //删除分类
    @RequestMapping("/delete.do")
    @PreAuthorize("hasAnyAuthority('/cates/delete.do')")
    public String delete(Integer cid) throws Exception{
        categoryService.delete(cid);
        return "redirect:findAll.do";
    }

    //修改分类
    @RequestMapping("/update.do")
    public String update(Category category) throws Exception{
        categoryService.update(category);
        return "redirect:findAll.do";
    }

    //根据cid查询
    @RequestMapping("/findByCid.do")
    public ModelAndView findByCid(Integer cid) throws Exception {
        ModelAndView mv = new ModelAndView();
        Category cate = categoryService.findByCid(cid);
        mv.addObject("cateInfo",cate);
        mv.setViewName("cate-update");
        return mv;
    }

    //查询所有线路分类
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(
            @RequestParam(name="page",required = true, defaultValue = "1") Integer page,
            @RequestParam(name="size",required = true, defaultValue = "10") Integer size,
            @RequestParam(name="cname",required = true, defaultValue = "") String cname
    ) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Category> list = categoryService.findAll(page,size,"%"+cname+"%");
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("cate-list");
        return mv;
    }
}
