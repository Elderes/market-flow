package com.accenture_projeto.buyer.exceptions;

public class BuyerNotFoundException extends RuntimeException {
    public BuyerNotFoundException(String message) {
        super(message);
    }
}