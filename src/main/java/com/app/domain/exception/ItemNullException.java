package com.app.domain.exception;

public class ItemNullException extends RuntimeException {
    public ItemNullException(String message) {
        super("[ERROR] " + message);
    }
}
