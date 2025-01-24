package com.accenture_projeto.seller.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderModelDTO {
    private BuyerModelDTO buyer;
    private List<ProductModelDTO> products;
}
