package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/13/013
 */
public interface ProductMapper {

    /**
     * 查询所有产品信息
     * @return
     */
    @Select("select * from product")
    public List<Product> findAll();

    /**
     * 添加商品数据
     * @param product
     */
    @Insert("insert into product values (sys_guid(),#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void add(Product product);


    /**
     * 根据产品ID查询产品信息
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    public Product findById(String id);
}
