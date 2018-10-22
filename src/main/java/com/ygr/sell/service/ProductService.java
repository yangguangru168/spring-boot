package com.ygr.sell.service;


import com.ygr.sell.dataobject.ProductInfo;
import com.ygr.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductInfo findOne(String id);

    /**
     * 查询所有在架的商品
     * @return
     */
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //添加库存
    void increateStock(List<CartDTO> cartDTO);
    //扣库存
    void decreaseStock(List<CartDTO> cartDTO);
}
