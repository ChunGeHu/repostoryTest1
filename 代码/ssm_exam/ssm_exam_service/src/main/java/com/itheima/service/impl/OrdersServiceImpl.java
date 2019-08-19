package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrdersMapper;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/13/013
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper ;

    @Override
    public List<Orders> findByPage(Integer pageNo, Integer rows) {
        //开启分页查询
        PageHelper.startPage(pageNo,rows);
        //调用数据访问层查询订单数据
        return ordersMapper.findAll();
    }

    @Override
    public Orders findDetail(String id) {
        //调用数据访问层查询数据
        return ordersMapper.findDetail(id);
    }
}
