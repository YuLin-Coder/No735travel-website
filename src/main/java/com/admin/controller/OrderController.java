package com.admin.controller;

import com.admin.service.IOrderService;
import com.github.pagehelper.PageInfo;
import com.wink.domain.Order;
import com.wink.domain.RouteOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(订单信息)
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    //删除酒店订单
    @RequestMapping("/delete.do")
    @PreAuthorize("hasAnyAuthority('/orders/delete.do')")
    public String delete(Integer id) throws Exception{
        orderService.delete(id);
        return "redirect:findAll.do";
    }

    //删除景点订单
    @RequestMapping("/deleteRoute.do")
    @PreAuthorize("hasAnyAuthority('/orders/deleteRoute.do')")
    public String deleteRoute(Integer id) throws Exception{
        orderService.deleteRoute(id);
        return "redirect:findAllRoute.do";
    }

    //查询所有景点订单
    @RequestMapping("/findAllRoute.do")
    public ModelAndView findAllRoute(
            @RequestParam(name = "page",defaultValue = "1")Integer page,
            @RequestParam(name = "size",defaultValue = "7")Integer size,
            @RequestParam(name = "search",defaultValue = "")String search
    ) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<RouteOrder> list = orderService.findAllRoute(page,size,"%"+search+"%");
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("rorder-list");
        return mv;
    }

    //查询所有酒店订单
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasAnyAuthority('/orders/findAll.do')")
    public ModelAndView findAll(
            @RequestParam(name = "page",defaultValue = "1")Integer page,
            @RequestParam(name = "size",defaultValue = "7")Integer size,
            @RequestParam(name = "search",defaultValue = "")String search
    ) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Order> list = orderService.findAll(page,size,"%"+search+"%");
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("order-list");
        return mv;
    }
}
