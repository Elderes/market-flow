package com.accenture_projeto.buyer.models;

import java.util.List;

public class OrderModel {
    private BuyerModel buyer;
    private List<ProductModel> products;

    public OrderModel(BuyerModel buyer, List<ProductModel> products) {
        this.buyer = buyer;
        this.products = products;
    }

    public BuyerModel getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerModel buyer) {
        this.buyer = buyer;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
