package com.itheima.dao;

import com.itheima.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 王磊
 * @Date 2019/8/13/013
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-dao.xml")
public class ProductMapperTest {


    @Autowired
    private ProductMapper productMapper ;

    @Test
    public void findAll() {

        List<Product> products = productMapper.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }
}