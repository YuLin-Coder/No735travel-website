package com.admin.controller;

import com.admin.service.IRoleService;
import com.github.pagehelper.PageInfo;
import com.wink.domain.Permission;
import com.wink.domain.Role;
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
 * @Description: TODO(角色信息)
 */
@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    //添加一个角色
    @RequestMapping("/save.do")
    @PreAuthorize("hasAnyAuthority('/roles/save.do')")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    //给角色添加权限
    @RequestMapping("/addPermissionToRole.do")
    @PreAuthorize("hasAnyAuthority('/roles/addPermissionToRole.do')")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) Integer roleId, @RequestParam(name = "ids", required = true) int[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }

    //查询角色拥有的所有权限
    @RequestMapping("/findPermissionByRoleId.do")
    @PreAuthorize("hasAnyAuthority('/roles/findPermissionByRoleId.do')")
    public ModelAndView findPermissionByRoleId(Integer roleId)throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        List<Permission> list = roleService.findPermissionByRoleId(roleId);
        mv.addObject("role",role);
        mv.addObject("list",list);
        mv.setViewName("role-permission-rmove");
        return mv;
    }

    //移除角色的权限
    @RequestMapping("/removePermission.do")
    @PreAuthorize("hasAnyAuthority('/roles/removePermission.do')")
    public String removePermission( @RequestParam(name="roleId") Integer roleId,@RequestParam(name = "ids") int[] permissionIds) throws Exception {
        roleService.removePermission(roleId,permissionIds);
        return "redirect:findAll.do";
    }

    //根据id删除角色
    @RequestMapping("/deleteRole.do")
    @PreAuthorize("hasAnyAuthority('/roles/deleteRole.do')")
    public String deleteRole(@RequestParam(name="id",required = true) Integer roleId) throws Exception {
        roleService.delete(roleId);
        return "redirect:findAll.do";
    }

    //查询所有角色
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasAnyAuthority('/roles/findAll.do')")
    public ModelAndView findAll(
            @RequestParam(name="page",required = true, defaultValue = "1") Integer page,
            @RequestParam(name="size",required = true, defaultValue = "10") Integer size,
            @RequestParam(name="roleName",required = true, defaultValue = "") String roleName
    ) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll(page,size,"%"+roleName+"%");
        PageInfo pageInfo = new PageInfo(roleList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    //角色详情查询
    @RequestMapping("/findById.do")
    @PreAuthorize("hasAnyAuthority('/roles/findById.do')")
    public ModelAndView findById(@RequestParam(name = "id", required = true) Integer roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role", role);
        mv.setViewName("role-details");
        return mv;
    }

    //根据roleId查询role，并查询出可以添加的权限
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    @PreAuthorize("hasAnyAuthority('/roles/findRoleByIdAndAllPermission.do')")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) Integer roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据roleId查询role
        Role role = roleService.findById(roleId);
        //根据roleId查询可以添加的权限
        List<Permission> otherPermissions = roleService.findOtherPermissions(roleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }
}
