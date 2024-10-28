package com.admin.controller;

import com.admin.service.IPermissionService;
import com.github.pagehelper.PageInfo;
import com.wink.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(权限信息)
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    //新增一个权限
    @RequestMapping("/save.do")
    @PreAuthorize("hasAnyAuthority('/permission/save.do')")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    //根据id删除权限
    @RequestMapping("/deletePermission.do")
    @PreAuthorize("hasAnyAuthority('/permission/deletePermission.do')")
    public String deletePermission(Integer id) throws Exception {
        permissionService.deleteById(id);
        return "redirect:findAll.do";
    }

    //根据id修改权限
    @RequestMapping("/update.do")
    @PreAuthorize("hasAnyAuthority('/permission/update.do')")
    public String update(Permission permission) throws Exception {
        permissionService.update(permission);
        return "redirect:findAll.do";
    }

    //根据id查找权限详情
    @RequestMapping("/findById.do")
    @PreAuthorize("hasAnyAuthority('/permission/findById.do')")
    public ModelAndView findById(Integer id) throws Exception {
        Permission permission=  permissionService.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("permission-update");
        mv.addObject("permission",permission);
        return mv;
    }

    //查询所有资源权限
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasAnyAuthority('/permission/findAll.do')")
    public ModelAndView findAll(
            @RequestParam(name="page",required = true, defaultValue = "1") Integer page,
            @RequestParam(name="size",required = true, defaultValue = "10") Integer size,
            @RequestParam(name="permissionName",required = true, defaultValue = "") String permissionName
    ) throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Permission> permissionList = permissionService.findAll(page,size,"%"+permissionName+"%");
        PageInfo pageInfo = new PageInfo(permissionList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }
}
