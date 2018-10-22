package com.ygr.sell.service;

import com.ygr.sell.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();

    /**
     * 查询所有类目
     * @param categoryId
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryId);
    ProductCategory save(ProductCategory productCategory);

}
