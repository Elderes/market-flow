package com.accenture_projeto.seller.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture_projeto.seller.repository.ProductRepository;
import com.accenture_projeto.seller.repository.SellerRepository;

@SpringBootTest
public class ProductTest {
    @Autowired SellerRepository sellerRepository;
    @Autowired ProductRepository productRepository;
    

    @Test
    void saveProduct() {
        // SellerModel seller = new Sellermodel();
        // seller.setName("Abraão");

        // sellerRepository.save(seller);

        ProductModel product_02 = new ProductModel();
        product_02.setName("Notebook");
        product_02.setDescription("Useful aggregate of metal and silicon.");
        product_02.setQuantity(5);
        product_02.setSeller(sellerRepository.getReferenceById(1));

        productRepository.save(product_02);
    }
}
