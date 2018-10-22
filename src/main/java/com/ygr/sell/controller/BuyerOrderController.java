package com.ygr.sell.controller;

import com.ygr.sell.VO.ResultVO;
import com.ygr.sell.converter.FormconverterOrderDetail;
import com.ygr.sell.dto.OrderDTO;
import com.ygr.sell.enums.ResultEnum;
import com.ygr.sell.exception.SellExeption;
import com.ygr.sell.form.OrderForm;
import com.ygr.sell.service.BuyerService;
import com.ygr.sell.service.OrderService;
import com.ygr.sell.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("buyer/order")
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    //创建订单
    @PostMapping(value = "/create")
    public ResultVO<OrderDTO> create(@Valid OrderForm orderForm, BindingResult result){
        if (result.hasErrors()){
            throw new SellExeption(ResultEnum.ERROR.getCode(),result.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = FormconverterOrderDetail.formConver(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            throw new SellExeption(ResultEnum.CART_EMPTY);
        }
        OrderDTO orderDTO1 = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderDTO1.getOrderId());
        return ResultVOUtil.success(map);
    }
    @GetMapping(value = "/list")
    public ResultVO<List<OrderDTO>> findList(@RequestParam String openid,
                                             @RequestParam Integer page,
                                             @RequestParam Integer pageSize){
        PageRequest pageRequest = new PageRequest(page,pageSize);
        Page<OrderDTO> orderDTOS = orderService.findList(openid,pageRequest);
        return ResultVOUtil.success(orderDTOS.getContent());
    }
    @GetMapping(value = "/detail")
    public ResultVO<OrderDTO> findOne(@RequestParam(value = "orderId") String orderId,
                                      @RequestParam(value = "openId") String openId){
        OrderDTO orderDTO = buyerService.findOrderOne(openId,orderId);
        return ResultVOUtil.success(orderDTO);

    }
    @PostMapping(value = "/cancel")
    public ResultVO<OrderDTO> cancel(@RequestParam(value = "orderId") String orderId,
                                     @RequestParam(value = "openId") String openId){
        OrderDTO orderDTO = buyerService.cancelOrder(openId,orderId);
        if (orderDTO != null) {
            return ResultVOUtil.success();
        }else {
            throw new SellExeption(ResultEnum.CANCEL_ERROR);
        }
    }
}
