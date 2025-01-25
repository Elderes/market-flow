package br.summer_academy.stock.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import br.summer_academy.stock.dto.ProductRecordDTO;
import br.summer_academy.stock.model.Product;
import br.summer_academy.stock.repository.StockRepository;

public class StockService {
    @Autowired
    StockRepository repository;

    public boolean checkIfAvailable(List<Product> products) {
        for (Product p : products) {
            Product stockProduct = repository.findByName(p.getName()); // Search by name
            if (stockProduct == null || stockProduct.getQuantity() < p.getQuantity()) { // Need exists in stock and have necessary amount
                return false;
            }
        }
        return true;
    }

    // Mapping DTO to Entity
    public List<Product> mapProducts(List<ProductRecordDTO> productDTOs) {
        return productDTOs.stream().map(dto -> {
            Product product = new Product();
            product.setName(dto.name());
            product.setQuantity(dto.quantity());
            product.setPrice(dto.price());
            return product;
        }).collect(Collectors.toList());
    }

}
