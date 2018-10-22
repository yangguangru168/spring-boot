package com.ygr.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatMpConfig {
    @Autowired
    private WecharAccountConfig config;
    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return  wxMpService;
    }
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpIConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpIConfigStorage.setAppId(config.getMpAppId());
        wxMpIConfigStorage.setSecret(config.getMpAppSecrect());
        return wxMpIConfigStorage;

    }
}
