package br.com.accenture_project.payments.exception;

public class NoClientException extends RuntimeException {
    public NoClientException(String message) {
        super(message);
    }
}