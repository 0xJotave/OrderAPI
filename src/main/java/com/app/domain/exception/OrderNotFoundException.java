package com.app.domain.exception;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(String message) {
        super(message);
    }
}
