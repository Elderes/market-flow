package br.com.accenture_project.send.exceptions;

public class SendNotFoundException extends RuntimeException {
    public SendNotFoundException(String message) {
        super(message);
    }
}
