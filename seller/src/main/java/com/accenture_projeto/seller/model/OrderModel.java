package com.accenture_projeto.seller.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "tb_order")
public class OrderModel implements Serializable {
    private BuyerModel buyer;
    private List<ProductModel> products;

    public OrderModel(BuyerModel buyer, List<ProductModel> products) {
        this.buyer = buyer;
        this.products = products;
    }
}
