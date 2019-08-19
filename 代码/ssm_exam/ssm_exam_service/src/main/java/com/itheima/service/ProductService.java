package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/13/013
 */
public interface ProductService {

    /**
     * 查询所有产品信息
     * @return
     */
    public List<Product> findAll();

    /**
     * 添加商品数据
     * @param product
     */
    void add(Product product);
}
