package com.app.port.input;

import com.app.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderServicePort {
    public Order createOrder(Order order);
    public List<Order> getAllOrders();
    public Optional<Order> getSingleOrder(String id);
    public void deleteOrder(String id);
    public void deleteAllOrders();
}
