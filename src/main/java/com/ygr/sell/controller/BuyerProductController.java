package com.ygr.sell.controller;

import com.ygr.sell.VO.ProductInfoVO;
import com.ygr.sell.VO.ProductVo;
import com.ygr.sell.VO.ResultVO;
import com.ygr.sell.dataobject.ProductCategory;
import com.ygr.sell.dataobject.ProductInfo;
import com.ygr.sell.service.CategoryService;
import com.ygr.sell.service.ProductService;
import com.ygr.sell.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * @author person.test@hand-china.com
 * @date 2018/10/15
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @GetMapping("/list")
    public ResultVO<ProductInfo> list(){
        //查询上架的商品
        List<ProductInfo> productList= productService.findUpAll();
        //查询类目商品
        /*List<Integer> categoryInt= new ArrayList<>();
        for (ProductInfo list : productList) {
            categoryInt.add(list.getCategoryType());
        }*/
        //java8写法
        List<Integer> categoryInt = productList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategory = categoryService.findByCategoryTypeIn(categoryInt);
        //数据拼装
        List<ProductVo> productVOList = new ArrayList<>();
        for (ProductCategory categoryList: productCategory) {
            ProductVo pvo = new ProductVo();
            pvo.setCategoryType(categoryList.getCategoryType());
            pvo.setCategoryName(categoryList.getCategoryName());
            List<ProductInfoVO> productInList = new ArrayList<>();
            for (ProductInfo product : productList) {
                if ((categoryList.getCategoryType()).equals(product.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(product, productInfoVO);
                    productInList.add(productInfoVO);
                }
            }
            pvo.setProductInfoVOS(productInList);
            productVOList.add(pvo);
        }
        return ResultVOUtil.success(productVOList);
    }
}
