package com.ygr.sell.service;


import com.ygr.sell.dto.OrderDTO;

public interface BuyerService {
    //查询一个订单详情
     OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
