package com.admin.controller;

import com.admin.service.IUserService;
import com.github.pagehelper.PageInfo;
import com.wink.domain.Role;
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
 * @Description: TODO(用户操作)
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    //给用户添加角色
    @RequestMapping("/addRoleToUser.do")
    @PreAuthorize("hasAnyAuthority('/users/addRoleToUser.do')")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) Integer userId, @RequestParam(name = "ids", required = true) int[] roleIds) throws Exception {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }

    //查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    @PreAuthorize("hasAnyAuthority('/users/findUserByIdAndAllRole.do')")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) Integer uid) throws Exception {
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        User user = userService.findByUid(uid);
        //2.根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(uid);
        mv.addObject("user", user);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    //查询用户拥有的角色
    @RequestMapping("/findRoleByUserId.do")
    @PreAuthorize("hasAnyAuthority('/users/findRoleByUserId.do')")
    public ModelAndView findRoleByUserId(Integer userId) throws Exception{
        ModelAndView mv = new ModelAndView();
        User user = userService.findByUid(userId);
        List<Role> roleList = userService.findRoleByUserId(userId);
        mv.addObject("user", user);
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-rmove");
        return mv;
    }

    //移除用户指定的角色
    @RequestMapping("/removeRole.do")
    @PreAuthorize("hasAnyAuthority('/users/removeRole.do')")
    public String removeRole( @RequestParam(name = "userId") Integer userId, @RequestParam(name = "ids") int[] roleIds) throws Exception{
        userService.removeRole(userId,roleIds);
        return "redirect:findAll.do";
    }

    //添加用户
    @RequestMapping("/save.do")
    @PreAuthorize("hasAnyAuthority('/users/save.do')")
    public String save(User user) throws Exception {
        userService.save(user);
        return "redirect:findAll.do";
    }

    //删除用户，设置status=0
    @RequestMapping("/delete.do")
    @PreAuthorize("hasAnyAuthority('/users/delete.do')")
    public String delete(Integer uid) throws Exception{
        userService.delete(uid);
        return "redirect:findAll.do";
    }

    //修改用户
    @RequestMapping("/findU.do")
    @PreAuthorize("hasAnyAuthority('/users/findU.do')")
    public ModelAndView findU(Integer uid) throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = userService.findByUid(uid);
        mv.addObject("user",user);
        mv.setViewName("user-update");
        return mv;
    }
    @RequestMapping("/update.do")
    @PreAuthorize("hasAnyAuthority('/users/update.do')")
    public String update(User user) throws Exception{
        userService.update(user);
        return "redirect:findAll.do";
    }

    //根据uid查询用户
    @RequestMapping("/findByUid.do")
    @PreAuthorize("hasAnyAuthority('/users/findByUid.do')")
    public ModelAndView findByUid(Integer uid) throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = userService.findByUid(uid);
        mv.addObject("user", user);
        mv.setViewName("user-details");
        return mv;
    }

    //查询用户相关信息
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasAnyAuthority('/users/findAll.do')")
    public ModelAndView findAll(
            @RequestParam(name="page",required = true, defaultValue = "1") Integer page,
            @RequestParam(name="size",required = true, defaultValue = "6") Integer size,
            @RequestParam(name="username",required = true, defaultValue = "") String username
    ) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.findAll(page,size,"%"+username+"%");
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }
}
