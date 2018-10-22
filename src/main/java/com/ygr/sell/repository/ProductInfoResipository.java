package com.ygr.sell.repository;

import com.ygr.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoResipository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
