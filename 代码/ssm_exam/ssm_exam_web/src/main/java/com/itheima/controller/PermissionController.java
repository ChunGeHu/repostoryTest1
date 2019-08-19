package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/16/016
 */
@Controller
@RequestMapping(path = "/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService ;

    /**
     * 查询所有权限信息
     * @return
     */
    @RequestMapping(path = "/findAll")
    public String findAll(Model model){
        //调用业务层查询数据
        List<Permission> permissions = permissionService.findAll();
        //将查询结果存入到Model中
        model.addAttribute("permissions",permissions);
        //跳转视图
        return "permission-list";
    }

    /**
     * 添加权限信息
     * @return
     */
    @RequestMapping(path = "/add")
    public String add(Permission permission){
        //调用业务层添加数据
        permissionService.add(permission);
        //跳转视图
        return "redirect:/permission/findAll" ;
    }

    /**
     * 查询所有的可以添加的权限信息
     * @return
     */
    @RequestMapping(path = "/findAdditablePermission")
    public String findAdditablePermission(Model model , String roleId){
        //调用业务层查询数据
        List<Permission> permissions =  permissionService.findAdditablePermission(roleId);
        //将查询到的数据保存到Model中
        model.addAttribute("permissions",permissions);
        model.addAttribute("roleId",roleId);
        //跳转视图
        return "role-permission-add" ;
    }

    /**
     * 给角色添加权限
     * @return
     */
    @RequestMapping(path = "/addPermissions2Role")
    public String addPermissions2Role(String roleId,String[] ids){
        //调用业务层添加数据
        permissionService.addPermissions2Role(roleId,ids);
        //跳转视图
        return "redirect:/role/findAll";
    }

    /**
     * 查询用户菜单
     * @return
     */
    @RequestMapping(path = "/findMenus")
    public String findMenus(HttpSession session){
        //从SpringSecurity中获取登录用户信息
        //SecurityContext securityContext = SecurityContextHolder.getContext();//获取spring security的上下文对象
        //Authentication authentication = securityContext.getAuthentication(); //获取认证对象
        //获取当前登录用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //调用Service菜单数据
        List<Permission> permissions =  permissionService.findMenus(username);
        //将数据保存session中
        session.setAttribute("menus",permissions);
        //跳转视图
        return "redirect:/main.jsp";
    }
}
