package com.ygr.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    @Test
    public void logger(){
        String name = "杨光儒";
        Integer password = 123456;
        log.info("name:{},password: {}",name,password);
        log.info("姓名"+name+"密码:"+password);
        log.error("error错误我");
    }
}
