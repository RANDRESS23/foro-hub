package com.alura.foro_hub.exception;

public class InvalidCredentials extends RuntimeException {
    public InvalidCredentials(String message) {
        super(message);
    }
}
