package com.ygr.sell.service.impl;

import com.ygr.sell.dto.OrderDTO;
import com.ygr.sell.enums.ResultEnum;
import com.ygr.sell.exception.SellExeption;
import com.ygr.sell.service.BuyerService;
import com.ygr.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("orderId:{},openid:{}",orderDTO.getOrderId(),orderDTO.getBuyerOpenid());
            throw new SellExeption(ResultEnum.USER_ERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("【查询订单】订单的openid不一致，orderId:{},openid:{}",orderDTO.getOrderId(),orderDTO.getBuyerOpenid());
            throw new SellExeption(ResultEnum.USER_ERROR);
        }
        OrderDTO order = orderService.cancel(orderDTO);
        return order;
    }
}
