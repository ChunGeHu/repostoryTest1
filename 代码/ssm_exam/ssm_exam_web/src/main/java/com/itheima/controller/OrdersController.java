package com.itheima.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/13/013
 */
@Controller
@RequestMapping(path = "/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService ;

    /**
     * 分页查询订单数据
     * @return
     */
    @RequestMapping(path = "/findByPage")
    public String findByPage(Model model ,  @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo , @RequestParam(name = "rows",defaultValue = "3") Integer rows){
        //调用业务层查询数据
        List<Orders> ordersList = ordersService.findByPage(pageNo,rows);
        //构造分页对象
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersList);
        //将查询的数据保存到Model中
        model.addAttribute("pageInfo",pageInfo);

        return "orders-list";
    }

    /**
     * 查询订单详情
     * @return
     */
    @RequestMapping(path = "/findDetail")
    public String findDetail(Model model , String id){
        //调用业务层查询数据
        Orders order = ordersService.findDetail(id);
        //将查询的数据保存到Model中
        model.addAttribute("order",order);
        //跳转视图
        return "orders-show" ;
    }
}
