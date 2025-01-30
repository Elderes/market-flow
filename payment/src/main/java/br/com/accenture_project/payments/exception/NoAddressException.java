package br.com.accenture_project.payments.exception;

public class NoAddressException extends RuntimeException {
    public NoAddressException(String message) {
        super(message);
    }
}