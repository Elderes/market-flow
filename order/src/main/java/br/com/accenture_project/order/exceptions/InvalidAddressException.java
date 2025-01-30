package br.com.accenture_project.order.exceptions;

public class InvalidAddressException extends RuntimeException {
    public InvalidAddressException(String message) {
        super(message);
    }
}
