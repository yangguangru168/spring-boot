package com.ygr.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WecharAccountConfig {
    private String mpAppId;
    private String mpAppSecrect;
}
