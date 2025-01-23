package com.accenture_projeto.buyer.controllers;

import com.accenture_projeto.buyer.dtos.BuyerRecordDto;
import com.accenture_projeto.buyer.dtos.MessageRecordDto;
import com.accenture_projeto.buyer.models.AddressModel;
import com.accenture_projeto.buyer.models.BuyerModel;
import com.accenture_projeto.buyer.services.BuyerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyerController {
    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping("/buyers")
    public ResponseEntity<BuyerModel> saveBuyer(@RequestBody @Valid BuyerRecordDto buyerRecordDto) {
        var buyerModel = createBuyer(buyerRecordDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(buyerService.save(buyerModel));
    }

    @PostMapping("/request-products")
    public ResponseEntity<?> requestProducts(@RequestBody @Valid MessageRecordDto messageRecordDto) {
        buyerService.requestProductList(messageRecordDto.message());
        return ResponseEntity.status(HttpStatus.OK).build();
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
}
