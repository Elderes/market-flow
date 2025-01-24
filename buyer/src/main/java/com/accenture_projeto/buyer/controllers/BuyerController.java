package com.accenture_projeto.buyer.controllers;

import com.accenture_projeto.buyer.dtos.BuyerRecordDto;
import com.accenture_projeto.buyer.dtos.MessageRecordDto;
import com.accenture_projeto.buyer.dtos.OrderRecordDto;
import com.accenture_projeto.buyer.dtos.ProductsListRecordDto;
import com.accenture_projeto.buyer.exceptions.BuyerNotFoundException;
import com.accenture_projeto.buyer.exceptions.ProductNotFoundException;
import com.accenture_projeto.buyer.models.AddressModel;
import com.accenture_projeto.buyer.models.BuyerModel;
import com.accenture_projeto.buyer.models.ProductModel;
import com.accenture_projeto.buyer.services.BuyerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BuyerController {
    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping("/buyers")
    public ResponseEntity<BuyerModel> saveBuyer(@RequestBody @Valid BuyerRecordDto buyerRecordDto) {
        var buyerModel = createBuyer(buyerRecordDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(buyerService.saveBuyer(buyerModel));
    }

    @PostMapping("/request-products")
    public ResponseEntity<?> requestProducts(@RequestBody @Valid MessageRecordDto messageRecordDto) {
        buyerService.requestProductList(messageRecordDto.message());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getProducts() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(buyerService.getAllProducts());
    }

    @PostMapping("/send-products")
    public ResponseEntity<?> sendProducts(@RequestBody @Valid OrderRecordDto orderRecordDto) {
        try {
            var buyer = buyerService.getBuyerByCellphone(orderRecordDto.cellphone());
            var products = createProducts(orderRecordDto);

            buyerService.sendProducts(buyer, products);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (BuyerNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } catch (ProductNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    private AddressModel createAddress(BuyerRecordDto buyerRecordDto) {
        var addressModel = new AddressModel();

        addressModel.setCountry(buyerRecordDto.address().country());
        addressModel.setState(buyerRecordDto.address().state());
        addressModel.setCity(buyerRecordDto.address().city());
        addressModel.setNeighborhood(buyerRecordDto.address().neighborhood());
        addressModel.setStreet(buyerRecordDto.address().street());
        addressModel.setNumber(buyerRecordDto.address().number());

        return addressModel;
    }

    private BuyerModel createBuyer(BuyerRecordDto buyerRecordDto) {
        var addressModel = createAddress(buyerRecordDto);
        var buyerModel = new BuyerModel();

        buyerModel.setName(buyerRecordDto.name());
        buyerModel.setEmail(buyerRecordDto.email());
        buyerModel.setCellphone(buyerRecordDto.cellphone());
        buyerModel.setAddress(addressModel);

        return buyerModel;
    }

    private List<ProductModel> createProducts(OrderRecordDto orderRecordDto) {
        ProductModel productModel = new ProductModel();
        List<ProductModel> productList = new ArrayList<>();

        for (ProductsListRecordDto products : orderRecordDto.products()) {
            var product = buyerService.getProductByName(products.nameProduct());

            if (product == null) {
                throw new ProductNotFoundException("Product not found with name: " + products.nameProduct());
            }

            productModel.setId(product.getId());
            productModel.setName(product.getName());
            productModel.setDescription(product.getDescription());
            productModel.setQuantity(products.quantity());
            productModel.setPrice(product.getPrice());

            productList.add(productModel);
        }

        return productList;
    }
}
