package br.com.market.payments.exception;

public class NoProductException extends RuntimeException {
    public NoProductException(String message) {
        super(message);
    }
}
