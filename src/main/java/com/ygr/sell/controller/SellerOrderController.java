package com.ygr.sell.controller;

import com.ygr.sell.dataobject.OrderDetail;
import com.ygr.sell.dto.OrderDTO;
import com.ygr.sell.enums.ResultEnum;
import com.ygr.sell.exception.SellExeption;
import com.ygr.sell.repository.OrderDetailRepository;
import com.ygr.sell.repository.ProductInfoResipository;
import com.ygr.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("seller/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page -1,pageSize);
        Page<OrderDTO> orderDTOPage = orderService.findAll(request);
        map.put("orderDTOpage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",pageSize);
        return new ModelAndView("order/list",map);
    }
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam(value = "orderId") String orderId,
                               Map<String,Object> map){
            try {
                OrderDTO orderDTO = orderService.findOne(orderId);
                orderService.cancel(orderDTO);
            }catch (SellExeption e){
                map.put("msg",e.getMessage());
                map.put("url","/sell/seller/order/list");
                return new ModelAndView("common/error",map);
            }
        map.put("msg", ResultEnum.SUCCESS);
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/succes",map);
    }
    @GetMapping("/findOne")
    public ModelAndView findOne(@RequestParam(value = "orderId") String orderId,
                                Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
            map.put("orderDetails",orderDetails);
            map.put("orderDTO",orderDTO);
        }catch (SellExeption e){
            System.out.println(e.getMessage());
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.SUCCESS);
        return new ModelAndView("common/detatil",map);
    }
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam(value = "orderId") String orderId,
                               Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellExeption e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.SUCCESS);
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/succes",map);
    }
}
