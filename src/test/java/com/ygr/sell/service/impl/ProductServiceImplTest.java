package com.ygr.sell.service.impl;

import com.ygr.sell.dataobject.ProductInfo;
import com.ygr.sell.enums.ProductEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl serviceImpl;
    @Test
    public void findOne() {
        ProductInfo pc = serviceImpl.findOne("123");
        Assert.assertEquals("123",pc.getProductId());
    }
    @Test
    public void findUpAll() {
        List<ProductInfo> pc = serviceImpl.findUpAll();
        Assert.assertNotEquals(0, pc.size());
    }
    @Test
    public void findAll() {
        PageRequest pr = new PageRequest(0,2);
        Page<ProductInfo> page = serviceImpl.findAll(pr);
        System.out.println(page.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("哈哈哈");
        productInfo.setProductPrice(new BigDecimal(3.22));
        productInfo.setProductStock(122);
        productInfo.setProductDescription("非常棒");
        productInfo.setProductIcon(".jpg");
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(ProductEnum.UP.getCode());
        ProductInfo pc = serviceImpl.save(productInfo);
        Assert.assertNotNull(pc);
    }
}