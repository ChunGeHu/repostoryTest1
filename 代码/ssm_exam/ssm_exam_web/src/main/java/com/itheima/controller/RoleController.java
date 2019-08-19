package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/16/016
 */
@Controller
@RequestMapping(path = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色信息
     * @return
     */
    @RequestMapping(path = "/findAll")
    public String findAll(Model model){
        //调用业务层查询数据
        List<Role> roles = roleService.findAll();
        //将查询到的数据存入到Model中
        model.addAttribute("roles",roles);
        //跳转视图
        return "role-list";
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping(path = "/add")
    public String add(Role role){
        //调用Service添加数据
        roleService.add(role);
        //跳转视图
        return "redirect:/role/findAll";
    }

    /**
     * 查询所有用户可以添加的角色信息
     * @return
     */
    @RequestMapping(path = "/findAdditableRoles")
    public String findAdditableRoles(Model model , String userId){
        //调用业务层查询数据
        List<Role> roles =  roleService.findAdditableRoles(userId);
        //将查询到的数据存入到Model中
        model.addAttribute("roles",roles);
        model.addAttribute("userId",userId);
        //跳转视图
        return "user-role-add" ;
    }

    /**
     * 添加角色信息
     * @return
     */
    @RequestMapping(path = "/addRoles2User")
    public String addRoles2User(String userId,String[] ids){
        //调用业务层添加数据
        roleService.addRoles2User(userId,ids);
        //跳转视图
        return "redirect:/user/findByPage" ;
    }
}
