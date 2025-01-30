package br.com.accenture_project.payments.exception;

public class NoProductException extends RuntimeException {
    public NoProductException(String message) {
        super(message);
    }
}