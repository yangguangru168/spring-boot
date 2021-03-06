package com.ygr.sell.repository;

import com.ygr.sell.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
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
public class ProductInfoResipositoryTest {

    @Autowired
    private ProductInfoResipository resipository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.22));
        productInfo.setProductStock(122);
        productInfo.setProductDescription("非常棒");
        productInfo.setProductIcon(".jpg");
        productInfo.setCategoryType(2);
        productInfo.setProductStatus(0);
        ProductInfo pc = resipository.save(productInfo);
        Assert.assertNotNull(pc);
    }
    @Test
    public void findByProductStatus() {
        List<ProductInfo> pd = resipository.findByProductStatus(0);
        log.info("pd:{}",pd);
    }
}