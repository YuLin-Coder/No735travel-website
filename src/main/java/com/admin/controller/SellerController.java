package com.admin.controller;

import com.admin.service.ISellerService;
import com.admin.service.IUserService;
import com.github.pagehelper.PageInfo;
import com.wink.domain.Seller;
import com.wink.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(景点商家信息)
 */
@Controller
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private ISellerService iSellerService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/findBySid.do")
    @PreAuthorize("hasAnyAuthority('/sellers/findBySid.do')")
    public ModelAndView findBySid(Integer sid) throws Exception {
        ModelAndView mv = new ModelAndView();
        Seller seller = iSellerService.findBySid(sid);
        mv.addObject("sellerInfo",seller);
        List<User> list = iUserService.findAll(1,1000,"%%");
        mv.addObject("UList",list);
        mv.setViewName("seller-update");
        return mv;
    }

    @RequestMapping("/findUsers.do")
    @PreAuthorize("hasAnyAuthority('/sellers/findUsers.do')")
    public ModelAndView findUsers() throws Exception{
        List<User> list = iSellerService.findUsers();
        ModelAndView mv = new ModelAndView();
        mv.addObject("users",list);
        mv.setViewName("seller-add");
        return mv;
    }
    //新增商家
    @RequestMapping("/save.do")
    @PreAuthorize("hasAnyAuthority('/sellers/save.do')")
    public String save(Seller seller) throws Exception{
        iSellerService.save(seller);
        return "redirect:findAlls.do";
    }

    //查询所有商家
    @RequestMapping("/findAlls.do")
    @PreAuthorize("hasAnyAuthority('/sellers/findAlls.do')")
    public ModelAndView findAlls(
        @RequestParam(name="page",required = true, defaultValue = "1") Integer page,
        @RequestParam(name="size",required = true, defaultValue = "10") Integer size,
        @RequestParam(name="search",required = true, defaultValue = "") String search
    )throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Seller> list = iSellerService.findAlls(page,size,"%"+search+"%");
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("seller-list");
        return mv;
    }

    //删除商家
    @RequestMapping("/delete.do")
    @PreAuthorize("hasAnyAuthority('/sellers/delete.do')")
    public String delete(Integer sid) throws Exception{
        iSellerService.delete(sid);
        return "redirect:findAlls.do";
    }

    //修改商家
    @RequestMapping("/update.do")
    @PreAuthorize("hasAnyAuthority('/sellers/update.do')")
    public String update(Seller seller) throws Exception{
        iSellerService.update(seller);
        return "redirect:findAlls.do";
    }
}
