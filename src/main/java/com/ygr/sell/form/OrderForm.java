package com.ygr.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderForm {
    @NotEmpty(message = "用户名必填")
    private String name;
    @NotEmpty(message = "手机号码必填")
    private String phone;

    private String address;
    @NotEmpty(message = "用户微信号")
    private String openid;
    @NotEmpty(message = "不能为空")
    private String items;
}
