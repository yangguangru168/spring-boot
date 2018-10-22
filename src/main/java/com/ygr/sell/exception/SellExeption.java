package com.ygr.sell.exception;


import com.ygr.sell.enums.ResultEnum;

public class SellExeption extends RuntimeException{
    private Integer code;

    public SellExeption(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
    public SellExeption(Integer code, String massge) {
        super(massge);
        this.code = code;
    }
}
