package com.admin.controller;

import com.admin.service.ICategoryService;
import com.admin.service.IRouteService;
import com.admin.service.ISellerService;
import com.admin.util.Upload;
import com.github.pagehelper.PageInfo;
import com.wink.domain.Category;
import com.wink.domain.Route;
import com.wink.domain.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.admin.util.Upload.RIMAGE;

/**
 * @Author: Mr.Ye
 * @Description: TODO(景点线路)
 */
@Controller
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private IRouteService routeService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISellerService sellerService;

    //新增景点线路,查询商家、分类选项
    @RequestMapping("/findSC.do")
    @PreAuthorize("hasAnyAuthority('/routes/findSC.do')")
    public ModelAndView find() throws Exception{
        List<Category> clist = categoryService.findAllCName();
        List<Seller> slist = sellerService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("CList",clist);
        mv.addObject("SList",slist);
        mv.setViewName("route-add");
        return mv;
    }
    //新增景点线路
    @RequestMapping("/save.do")
    @PreAuthorize("hasAnyAuthority('/routes/save.do')")
    public String save(Route route) throws Exception{
        if (route.getRimageStr()!=null){
            String filename = Upload.uploadImg(RIMAGE,route.getRimageStr());
            route.setRimage("img/product/small/"+filename);
            routeService.saveRoute(route);
        }
        return "redirect:findAll.do";
    }

    //删除景点线路
    @RequestMapping("/delete.do")
    @PreAuthorize("hasAnyAuthority('/routes/delete.do')")
    public String delete(Integer rid) throws Exception{
        routeService.delete(rid);
        return "redirect:findAll.do";
    }

    //修改景点线路
    @RequestMapping("/update.do")
    @PreAuthorize("hasAnyAuthority('/routes/update.do')")
    public String update(Route route) throws Exception{
        if (route.getRimageStr().getSize()!=0&&route.getRimageStr()!=null){//修改过图片
            String filename = Upload.uploadImg(RIMAGE,route.getRimageStr());
            route.setRimage("img/product/small/"+filename);
            routeService.update(route);
        }else{//未修改图片
            routeService.update(route);
        }
        return "redirect:findAll.do";
    }

    //根据rid查询线路信息
    @RequestMapping("/findByRid.do")
    @PreAuthorize("hasAnyAuthority('/routes/findByRid.do')")
    public ModelAndView findByRid(Integer rid) throws Exception{
        ModelAndView mv = new ModelAndView();
        Route route = routeService.findByRid(rid);
        mv.addObject("routeInfo",route);
        List<Category> clist = categoryService.findAllCName();
        List<Seller> slist = sellerService.findAll();
        mv.addObject("CList",clist);
        mv.addObject("SList",slist);
        mv.setViewName("route-update");
        return mv;
    }

    //查询所有线路
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasAnyAuthority('/routes/findAll.do')")
    public ModelAndView findAll(
            @RequestParam(name="page",required = true, defaultValue = "1") Integer page,
            @RequestParam(name="size",required = true, defaultValue = "10") Integer size,
            @RequestParam(name="rname",required = true, defaultValue = "") String rname
    ) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Route> list = routeService.findAll(page,size,"%"+rname+"%");
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("route-list");
        return mv;
    }
}
