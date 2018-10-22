package com.ygr.sell.converter;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ygr.sell.dataobject.OrderDetail;
import com.ygr.sell.dto.OrderDTO;
import com.ygr.sell.enums.ResultEnum;
import com.ygr.sell.exception.SellExeption;
import com.ygr.sell.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

public class FormconverterOrderDetail {
    public static OrderDTO formConver(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> list = new ArrayList<>();
        try {
            list = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            throw new SellExeption(ResultEnum.ERROR);
        }
        orderDTO.setOrderDetailList(list);
        return orderDTO;
    }
}
