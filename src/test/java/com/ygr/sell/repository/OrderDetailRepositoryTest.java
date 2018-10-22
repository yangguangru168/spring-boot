package com.ygr.sell.repository;

import com.ygr.sell.dataobject.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;
    @Test
    public void findByOrderId() {
        List<OrderDetail> details = repository.findByOrderId("123");
        log.info("dtaa:{}",details);
    }
    @Test
    public void save(){
        OrderDetail detail = new OrderDetail();
        detail.setDetailId("123");
        detail.setOrderId("123");
        detail.setProductIcon("kkkk.jpg");
        detail.setProductName("泡面");
        detail.setProductQuantity(12);
        detail.setProductId("123");
        detail.setProductPrice(new BigDecimal(23.23));
        repository.save(detail);
    }
}