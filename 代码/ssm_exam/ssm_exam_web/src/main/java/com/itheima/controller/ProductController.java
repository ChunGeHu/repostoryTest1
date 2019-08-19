package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/13/013
 */
@Controller
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService ;

    /**
     * 查询所有产品信息
     * @return
     */
    @RequestMapping(path = "/findAll")
    public String findAll(Model model){
        //查询商品数据
        List<Product> products = productService.findAll();
        //存储数据到model
        model.addAttribute("products",products);
        return "product-list" ;
    }

    /**
     * 添加商品
     * @return
     */
    @RequestMapping(path = "/add")
    public String add(Product product){
        //调用业务层完成添加操作
        productService.add(product);
        //页面跳转
        return "redirect:/product/findAll" ;
    }

}
