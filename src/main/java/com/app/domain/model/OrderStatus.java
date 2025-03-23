package com.app.domain.model;

import com.app.domain.exception.OrderStatusInvalidException;

import java.util.Arrays;

public enum OrderStatus {

    PENDING, PROCESSED, SHIPPED, RECEIVED;

    public static OrderStatus fromString(String status) {
        return Arrays.stream(OrderStatus.values())
                .filter(e -> e.toString().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new OrderStatusInvalidException(status));
    }

    public static void validateStatus(OrderStatus status) {
        boolean isValid = Arrays.stream(OrderStatus.values())
                .anyMatch(e -> e.toString().equalsIgnoreCase(String.valueOf(status)));
        if (!isValid) throw new OrderStatusInvalidException(status.toString());
    }
}
