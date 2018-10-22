package com.ygr.sell.enums;

import lombok.Getter;

@Getter
public enum PayEnum implements CodeEnum{
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;
    private Integer code;
    private String message;

    PayEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
