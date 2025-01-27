package com.accenture_project.order.exceptions;

public class NoAddressException extends RuntimeException {
    public NoAddressException(String message) {
        super(message);
    }
}
