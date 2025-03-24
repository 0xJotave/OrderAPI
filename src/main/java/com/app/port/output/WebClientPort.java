package com.app.port.output;

import com.app.domain.model.Order;

public interface WebClientPort {
    void sendOrder(Order order);
}
