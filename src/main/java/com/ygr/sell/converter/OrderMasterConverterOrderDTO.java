package com.ygr.sell.converter;

import com.ygr.sell.dataobject.OrderMaster;
import com.ygr.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMasterConverterOrderDTO {
    public static OrderDTO converter(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }
    public static List<OrderDTO> converter(List<OrderMaster> orderMasters){
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (OrderMaster orderMaster: orderMasters) {
           orderDTOS.add(converter(orderMaster));
        }
        return orderDTOS;
        /*return orderMasters.stream()
                .map(e -> converter(e))
                .collect(Collectors.toList());*/
    }
}
