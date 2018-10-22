package com.ygr.sell.service.impl;

import com.ygr.sell.converter.OrderMasterConverterOrderDTO;
import com.ygr.sell.dataobject.OrderDetail;
import com.ygr.sell.dataobject.OrderMaster;
import com.ygr.sell.dataobject.ProductInfo;
import com.ygr.sell.dto.CartDTO;
import com.ygr.sell.dto.OrderDTO;
import com.ygr.sell.enums.ResultEnum;
import com.ygr.sell.enums.OrderStatusEnum;
import com.ygr.sell.enums.PayEnum;
import com.ygr.sell.exception.SellExeption;
import com.ygr.sell.repository.OrderDetailRepository;
import com.ygr.sell.repository.OrderMasteRepository;
import com.ygr.sell.repository.ProductInfoResipository;
import com.ygr.sell.service.OrderService;
import com.ygr.sell.service.ProductService;
import com.ygr.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductInfoResipository productInfoResipository;
    @Autowired
    private OrderDetailRepository detailRepository;
    @Autowired
    private OrderMasteRepository masteRepository;
    @Autowired
    private ProductService productService;
    BigDecimal allAmount = new BigDecimal(BigInteger.ZERO);
    String orderId = KeyUtil.randomKey();
    //List<CartDTO> cartDTOS = new ArrayList<>();
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        for (OrderDetail orderDetailList: orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoResipository.findOne(orderDetailList.getProductId());
            if (productInfo == null) {
                throw new SellExeption(ResultEnum.PRODUCT_NOT_EXIT);
            }
            //计算总价
            allAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetailList.getProductQuantity()))
                    .add(allAmount);
            //写入订单详情
            orderDetailList.setOrderId(orderId);
            orderDetailList.setDetailId(KeyUtil.randomKey());
            BeanUtils.copyProperties(productInfo,orderDetailList);
            detailRepository.save(orderDetailList);
           /* CartDTO c = new CartDTO(orderDetailList.getProductId(),orderDetailList.getProductQuantity());
            cartDTOS.add(c);*/
        }
        //写入订单表
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayEnum.WAIT.getCode());
        orderMaster.setOrderAmount(allAmount);
        masteRepository.save(orderMaster);
        //扣库存,每次创建订单是库存减少
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        //根据id查订单
        OrderMaster orderMaster = masteRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellExeption(ResultEnum.ORDER_NOT_EXIT);
        }
        //根据订单id查订单详情
        List<OrderDetail> orderDetails = detailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails)) {
            throw new SellExeption(ResultEnum.ORDERDETAIL_NOT_EXIT);
        }
        //把订单详情放到OrderDTO展示出来
        OrderDTO orderDTO =new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasters = masteRepository.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOS = OrderMasterConverterOrderDTO.converter(orderMasters.getContent());
        return new PageImpl<>(orderDTOS,pageable,orderMasters.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellExeption(ResultEnum.ORDER_STARUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        //把修改的状态写入数据库
        OrderMaster orderMaster1 = masteRepository.save(orderMaster);
        if (orderMaster1 == null) {
            throw new SellExeption(ResultEnum.ORDER_STARUS_FAIL);
        }
        //返回库存,根据详情表的productId、productQuantity字段
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            throw new SellExeption(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (OrderDetail order: orderDTO.getOrderDetailList()) {
            cartDTOList.add(new CartDTO(order.getProductId(),order.getProductQuantity()));
        }
        /*List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());*/
        productService.increateStock(cartDTOList);
        //如果已支付，则返回
        if(orderDTO.getPayStatus().equals(PayEnum.SUCCESS.getCode())){
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellExeption(ResultEnum.ORDER_STARUS_ERROR);
        }
        //修改状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster orderMaster1 = masteRepository.save(orderMaster);
        if (orderMaster1 == null) {
            throw new SellExeption(ResultEnum.ORDER_STATUSFINISH_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellExeption(ResultEnum.ORDER_STARUS_ERROR);
        }
        //判断支付状态
        if (orderDTO.getPayStatus().equals(PayEnum.WAIT.getCode())) {
            orderDTO.setPayStatus(PayEnum.SUCCESS.getCode());
            BeanUtils.copyProperties(orderDTO,orderMaster);
            OrderMaster orderMaster1 = masteRepository.save(orderMaster);
            if (orderMaster1 == null) {
                throw new SellExeption(ResultEnum.ORDER_PAY_FAIL);
            }
        }else {
            throw new SellExeption(ResultEnum.ORDER_PAYSTATUS_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        Page<OrderMaster> orderMasters = masteRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMasterConverterOrderDTO.converter(orderMasters.getContent());
        return new PageImpl<>(orderDTOList,pageable,orderMasters.getTotalElements());
    }
}
