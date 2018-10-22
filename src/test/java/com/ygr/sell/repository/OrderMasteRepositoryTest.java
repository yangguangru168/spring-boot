package com.ygr.sell.repository;

import com.ygr.sell.dataobject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasteRepositoryTest {
    @Autowired
    private OrderMasteRepository repository;
    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0,1);
        Page<OrderMaster> page = repository.findByBuyerOpenid("99",request);
        Assert.assertNotEquals(0,page.getTotalElements());
    }
    @Test
    public void save(){
        OrderMaster om = new OrderMaster();
        om.setOrderId("124");
        om.setBuyerAddress("成都");
        om.setBuyerName("杨珂");
        om.setBuyerPhone("987654321");
        om.setBuyerOpenid("99");
        om.setOrderAmount(new BigDecimal(43.33));
        log.info("oderMaster:{}",repository.save(om));
    }
}