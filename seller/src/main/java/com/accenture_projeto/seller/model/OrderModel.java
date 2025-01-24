package com.accenture_projeto.seller.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class OrderModel implements Serializable {
    private BuyerModel buyer;
    private List<ProductModel> products;

    public OrderModel() {};

    public OrderModel(BuyerModel buyer, List<ProductModel> products) {
        this.buyer = buyer;
        this.products = products;
    }
}
