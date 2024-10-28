package com.admin.controller;

import com.admin.service.IHoteltypeService;
import com.github.pagehelper.PageInfo;
import com.wink.domain.Hoteltype;
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
 * @Description: TODO(房型管理)
 */
@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private IHoteltypeService hoteltypeService;

    //新增房型
    @RequestMapping("/save.do")
    @PreAuthorize("hasAnyAuthority('/rooms/save.do')")
    public String save(Hoteltype hoteltype) throws Exception{
        hoteltypeService.save(hoteltype);
        return "redirect:findAll.do";
    }

    //删除分类
    @RequestMapping("/delete.do")
    @PreAuthorize("hasAnyAuthority('/rooms/delete.do')")
    public String delete(Integer tid) throws Exception{
        hoteltypeService.delete(tid);
        return "redirect:findAll.do";
    }

    //查询所有房型
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasAnyAuthority('/rooms/findAll.do')")
    public ModelAndView findAll(
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "10") Integer size,
            @RequestParam(name = "search",defaultValue = "") String search
    ) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Hoteltype> list = hoteltypeService.findAll(page,size,"%"+search+"%");
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("room-list");
        return mv;
    }
}
