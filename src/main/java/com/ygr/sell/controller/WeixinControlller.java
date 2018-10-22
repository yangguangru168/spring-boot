package com.ygr.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinControlller {
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
      log.info("努力别放弃,,code:{}",code);

      String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxe976a00133879d85&secret=57e1d8ca55bfcf1682f9efef5d0a93dc&code="+code+"&grant_type=authorization_code";
      RestTemplate restTemplate = new RestTemplate();
      String response = restTemplate.getForObject(url,String.class);
      log.info("message",response);

    }
}
