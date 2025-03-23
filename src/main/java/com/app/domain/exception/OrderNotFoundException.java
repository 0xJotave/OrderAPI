package com.app.domain.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super("[ERROR] " + message);
    }
}
