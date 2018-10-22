package com.ygr.sell.VO;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;
    private String status;
    private T data;
}
