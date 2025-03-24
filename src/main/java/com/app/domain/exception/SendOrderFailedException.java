package com.app.domain.exception;

public class SendOrderFailedException extends RuntimeException {
    public SendOrderFailedException(String message) {
        super("[ERROR] " + message);
    }
}
