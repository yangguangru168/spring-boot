package com.ygr.sell.service.impl;

import com.ygr.sell.dataobject.OrderDetail;
import com.ygr.sell.dataobject.OrderMaster;
import com.ygr.sell.dto.OrderDTO;
import com.ygr.sell.enums.OrderStatusEnum;
import com.ygr.sell.enums.PayEnum;
import com.ygr.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderService service;
    private final String openid = "99";
    private final String orderId = "15396734533621020089";
    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("阿三");
        orderDTO.setBuyerAddress("上海");
        orderDTO.setBuyerPhone("7777777777");
        orderDTO.setBuyerOpenid(openid);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("2");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO re = service.create(orderDTO);
        log.info("data:{}",re);
        Assert.assertNotNull(re);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = service.findOne(orderId);
        Assert.assertNotNull(orderDTO);
        log.info("orderDTO:{}",orderDTO);
    }
    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> orderDTOS = service.findList(openid,pageRequest);
        log.info("result:{}",orderDTOS);
        Assert.assertNotNull(orderDTOS);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = service.findOne(orderId);
        OrderDTO o = service.cancel(orderDTO);
        System.out.println(o.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = service.findOne(orderId);
        OrderDTO orderDTO1 = service.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),orderDTO1.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = service.findOne(orderId);
        OrderDTO orderDTO1 = service.paid(orderDTO);
        Assert.assertEquals(PayEnum.SUCCESS.getCode(),orderDTO1.getPayStatus());
    }
    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> orderDTOS = service.findAll(pageRequest);
        log.info("result:{}",orderDTOS);
        Assert.assertNotNull(orderDTOS);
    }
}