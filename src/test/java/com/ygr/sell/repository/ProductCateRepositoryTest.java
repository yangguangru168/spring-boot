package com.ygr.sell.repository;

import com.ygr.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class )
@SpringBootTest
@Slf4j
public class ProductCateRepositoryTest {
    @Autowired
    private ProductCateRepository repository;

    @Test
    public void findOne(){
        ProductCategory p = repository.findOne(1);
        log.info("p:{}",p);
    }
    @Test
    public void insert(){
       ProductCategory prc = new ProductCategory();
       prc.setCategoryName("谢霆锋");
       prc.setCategoryType(6);
       ProductCategory pc = repository.save(prc);
        Assert.assertNotNull(pc);
    }
    @Test
    public void update(){
        ProductCategory prc = repository.findOne(1);
        prc.setCategoryType(23);
       repository.save(prc);
    }
    @Test
    @Transactional
    public void delete(){
        repository.delete(7);
    }
    @Test
    public void findAll(){
        List<Integer> list = Arrays.asList(3,4,5);
       List<ProductCategory> prc = repository.findByCategoryTypeIn(list);
       Assert.assertNotEquals(0,prc.size());
    }
}