package com.app.domain.exception;

public class OrderStatusInvalidException extends RuntimeException {
    public OrderStatusInvalidException(String status) {
        super("[ERROR] Invalid Status " + status);
    }
}
