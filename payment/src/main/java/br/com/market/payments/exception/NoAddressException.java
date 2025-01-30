package br.com.market.payments.exception;

public class NoAddressException extends RuntimeException {
    public NoAddressException(String message) {
        super(message);
    }
}