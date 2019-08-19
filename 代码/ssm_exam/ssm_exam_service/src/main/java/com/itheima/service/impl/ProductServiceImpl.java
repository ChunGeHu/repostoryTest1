package com.itheima.service.impl;

import com.itheima.dao.ProductMapper;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/13/013
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper ;

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public void add(Product product) {
        productMapper.add(product);
    }
}
