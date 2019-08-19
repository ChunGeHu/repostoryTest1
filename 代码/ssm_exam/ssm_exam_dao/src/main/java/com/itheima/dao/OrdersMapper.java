package com.itheima.dao;

import com.itheima.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/13/013
 */
public interface OrdersMapper {

    /**
     * 查询所有订单数据
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true , property = "id" , column = "ID"),
            @Result(property = "orderNum" , column = "ORDERNUM"),
            @Result(property = "orderTime" , column = "ORDERTIME"),
            @Result(property = "orderStatus" , column = "ORDERSTATUS"),
            @Result(property = "peopleCount" , column = "PEOPLECOUNT"),
            @Result(property = "payType" , column = "PAYTYPE"),
            @Result(property = "orderDesc" , column = "ORDERDESC"),
            @Result(property = "product" , column = "PRODUCTID",
                    one = @One(select = "com.itheima.dao.ProductMapper.findById")),
    })
    List<Orders> findAll();

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    @Select("select * from ORDERS where  ID = #{id}")
    @Results({
            @Result(id = true , property = "id" , column = "ID"),
            @Result(property = "orderNum" , column = "ORDERNUM"),
            @Result(property = "orderTime" , column = "ORDERTIME"),
            @Result(property = "orderStatus" , column = "ORDERSTATUS"),
            @Result(property = "peopleCount" , column = "PEOPLECOUNT"),
            @Result(property = "payType" , column = "PAYTYPE"),
            @Result(property = "orderDesc" , column = "ORDERDESC"),
            @Result(property = "product" , column = "PRODUCTID",
                    one = @One(select = "com.itheima.dao.ProductMapper.findById")),
            @Result(property = "member" , column = "MEMBERID",
                    one = @One(select = "com.itheima.dao.MemberMapper.findById")),
            @Result(property = "travellers" , column = "ID",
                    many = @Many(select = "com.itheima.dao.TravellerMapper.findByOrderId"))
    })
    Orders findDetail(String id);
}
