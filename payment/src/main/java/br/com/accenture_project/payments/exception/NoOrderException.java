package br.com.accenture_project.payments.exception;

public class NoOrderException extends RuntimeException {
    public NoOrderException(String message) {
        super(message);
    }
}