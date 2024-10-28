package com.admin.controller;

import com.admin.service.IImgService;
import com.admin.util.Upload;
import com.wink.domain.RouteImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.admin.util.Upload.*;

/**
 * @Author: Mr.Ye
 * @Description: TODO(景点图片信息)
 */
@Controller
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private IImgService imgService;

    //新增图片
    @RequestMapping("/save.do")
    @PreAuthorize("hasAnyAuthority('/images/save.do')")
    public String upload(RouteImg routeImg)throws Exception{
        if (routeImg.getBigFile()!=null&& routeImg.getBigFile()!=null){
            String filename1 = Upload.uploadImg(BIGPATH, routeImg.getBigFile());
            String filename2 = Upload.uploadImg(SMALLPATH, routeImg.getSmallFile());
            routeImg.setBigPic("img/product/size4/"+filename1);
            routeImg.setSmallPic("img/product/size2/"+filename2);
            imgService.save(routeImg);
        }
        return "redirect:findImgByRid.do?rid="+routeImg.getRid();
    }

    //删除图片
    @RequestMapping("/delete.do")
    @PreAuthorize("hasAnyAuthority('/images/delete.do')")
    public String delete(@RequestParam(name = "rid") Integer rid, @RequestParam(name = "ids") int[] rgids) throws Exception{
        imgService.delete(rgids);
        return "redirect:findImgByRid.do?rid="+rid;
    }

    //根据rid查看图片列表
    @RequestMapping("/findImgByRid.do")
    @PreAuthorize("hasAnyAuthority('/images/findImgByRid.do')")
    public ModelAndView findImgByRid(Integer rid)throws Exception{
        ModelAndView mv = new ModelAndView();
        List<RouteImg> list = imgService.findImgByRid(rid);
        if (list.size()==0){
            mv.addObject("img",new Integer(rid));
        }
        mv.addObject("imglist",list);
        mv.setViewName("img-list");
        return mv;
    }

}
