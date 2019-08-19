package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/13/013
 */
public interface OrdersService {
    /**
     * 分页查询订单信息
     * @param pageNo
     * @param rows
     * @return
     */
    List<Orders> findByPage(Integer pageNo, Integer rows);

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    Orders findDetail(String id);
}
