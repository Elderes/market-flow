package com.accenture_project.order.exceptions;

public class InvalidPayException extends RuntimeException {
    public InvalidPayException(String message) {
        super(message);
    }
}
