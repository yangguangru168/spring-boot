package com.ygr.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
@Entity
@Data
/**
 * 商品
 */
public class ProductInfo {
    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer productStatus;
    private Integer categoryType;
}
