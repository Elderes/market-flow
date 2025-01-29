package com.accenture_project.order.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductModel implements Serializable {
    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;
    private OrderModel order;
}
