package com.app.domain.service;

import com.app.adapter.output.mongo.repository.OrderRepositoryPort;
import com.app.domain.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepositoryPort orderRepositoryPort;

    public OrderService(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    public Order createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        return orderRepositoryPort.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepositoryPort.findAll();
    }

    public Optional<Order> getSingleOrder(String id) {
        return orderRepositoryPort.findById(id);
    }

    public void deleteOrder(String id) {
        orderRepositoryPort.deleteById(id);
    }

    public void deleteAllOrders() {
        orderRepositoryPort.deleteAll();
    }

}
