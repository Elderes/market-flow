package com.accenture_projeto.seller.model;

import java.util.List;

import lombok.Data;

@Data
public class OrderModel {
    private BuyerModel buyer;
    private List<ProductModel> products;

    public OrderModel(BuyerModel buyer, List<ProductModel> products) {
        this.buyer = buyer;
        this.products = products;
    }
}
