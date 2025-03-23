package com.app.domain.exception;

public class OrderDuplicatedException extends RuntimeException {
    public OrderDuplicatedException(String message) {
        super("[ERROR] " + message);
    }
}
