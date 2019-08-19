package com.itheima.dao;

import com.itheima.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/15/015
 */
public interface TravellerMapper {

    /**
     * 根据订单id查询旅客信息
     * @param orderId
     * @return
     */
    @Select("select t.* from TRAVELLER t , ORDER_TRAVELLER ot where  t.ID = ot.TRAVELLERID and ot.ORDERID = #{orderId}")
    public List<Traveller> findByOrderId(String orderId);
}
