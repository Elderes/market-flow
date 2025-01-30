package br.com.market.payments.exception;

public class NoOrderException extends RuntimeException {
    public NoOrderException(String message) {
        super(message);
    }
}