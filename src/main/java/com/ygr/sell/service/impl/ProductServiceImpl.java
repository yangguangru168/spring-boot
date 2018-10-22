package com.ygr.sell.service.impl;

import com.ygr.sell.dataobject.ProductInfo;
import com.ygr.sell.dto.CartDTO;
import com.ygr.sell.enums.ResultEnum;
import com.ygr.sell.enums.ProductEnum;
import com.ygr.sell.exception.SellExeption;
import com.ygr.sell.repository.ProductInfoResipository;
import com.ygr.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoResipository resipository;
    @Override
    public ProductInfo findOne(String id) {
        return resipository.findOne(id);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return resipository.findByProductStatus(ProductEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return resipository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return resipository.save(productInfo);
    }

    @Override
    public void increateStock(List<CartDTO> cartDTO) {
        for (CartDTO cartDTOList: cartDTO) {
            ProductInfo productInfo = resipository.findOne(cartDTOList.getProductId());
            if (productInfo == null) {
                throw new SellExeption(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer stock = productInfo.getProductStock()+ cartDTOList.getProductQuantity();
            productInfo.setProductStock(stock);
            resipository.save(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTO) {
        for (CartDTO cartDTOList: cartDTO) {
            ProductInfo productInfo = resipository.findOne(cartDTOList.getProductId());
            if (productInfo == null) {
                throw new SellExeption(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock()-cartDTOList.getProductQuantity();
            if (result <0) {
                throw new SellExeption(ResultEnum.STOCK_INSUFFICIENT);
            }
            productInfo.setProductStock(result);
            resipository.save(productInfo);
        }

    }
}
