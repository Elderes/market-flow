package br.com.accenture_project.order.exceptions;

public class NoOrderException extends RuntimeException {
    public NoOrderException(String message) {
        super(message);
    }
}
