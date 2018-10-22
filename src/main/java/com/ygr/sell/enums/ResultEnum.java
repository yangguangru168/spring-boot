package com.ygr.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),
    ERROR(6,"参数错误"),
    PRODUCT_NOT_EXIT(10,"商品不存在"),
    STOCK_INSUFFICIENT(11,"库存不足"),
    ORDER_NOT_EXIT(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIT(13,"订单详情不存在"),
    ORDER_STARUS_ERROR(14,"订单状态不允许被修改"),
    ORDER_STARUS_FAIL(15,"订单状态修失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_STATUSFINISH_FAIL(17,"订单完成下单失败"),
    ORDER_PAY_FAIL(18,"支付失败"),
    ORDER_PAYSTATUS_FAIL(19,"不允许操作支付状态"),
    CART_EMPTY(20,"购物车不能为空"),
    CANCEL_ERROR(21,"取消不成功"),
    USER_ERROR(22,"该订单不属于该用户")
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
