package com.ygr.sell.service.impl;

import com.ygr.sell.dataobject.ProductCategory;
import com.ygr.sell.repository.ProductCateRepository;
import com.ygr.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCateRepository repository;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryId) {
        return repository.findByCategoryTypeIn(categoryId);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
