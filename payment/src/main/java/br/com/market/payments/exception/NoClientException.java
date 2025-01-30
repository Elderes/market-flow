package br.com.market.payments.exception;

public class NoClientException extends RuntimeException {
    public NoClientException(String message) {
        super(message);
    }
}