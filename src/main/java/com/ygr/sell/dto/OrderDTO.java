package com.ygr.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ygr.sell.dataobject.OrderDetail;
import com.ygr.sell.enums.OrderStatusEnum;
import com.ygr.sell.enums.PayEnum;
import com.ygr.sell.util.EnumUtil;
import com.ygr.sell.util.serializer.DataSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据传输对象，在各层之间传输
 */
@Data
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus ;
    @JsonSerialize(using = DataSerializer.class)
    private Date createTime;
    @JsonSerialize(using = DataSerializer.class)
    private Date updateTime;
    private List<OrderDetail> orderDetailList = new ArrayList<>();
    @JsonIgnore
    public OrderStatusEnum getorderStatusEnum(){
        return EnumUtil.getCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayEnum getpayEnum(){
        return EnumUtil.getCode(payStatus,PayEnum.class);
    }
}
