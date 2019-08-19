package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/16/016
 */
@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService ;



    /**
     * 分页查询用户信息
     * @return
     */
    @RequestMapping(path = "/findByPage")
    public String findByPage(HttpServletRequest request , Model model , @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(value = "rows",defaultValue = "3") Integer rows){
        //调用业务层查询数据
        List<UserInfo> userInfos =  userService.findByPage(pageNo,rows);
        //封装数据到分页对象
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfos);
        //封装数据到Model
        model.addAttribute("pageInfo",pageInfo);
        //跳转视图
        return "user-list" ;
    }

    /**
     * 添加用户
     * @return
     */
    @RequestMapping(path = "/add")
    public String add(UserInfo userInfo){
        //调用业务层保存数据
        userService.add(userInfo);
        //跳转视图
        return "redirect:/user/findByPage";
    }

    /**
     * 查询用户详情
     * @return
     */
    @RequestMapping(path = "/findDetail")
    public String findDetail(Model model , String id){
        //调用业务层查询数据
        UserInfo userInfo = userService.findDetail(id);
        //将查询到的数据存入到Model中
        model.addAttribute("userInfo",userInfo);
        //跳转视图
        return "user-show";
    }
}
