package com.app.domain.service;

import com.app.adapter.output.mongo.repository.OrderRepositoryPort;
import com.app.domain.exception.OrderNotFound;
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
        List<Order> orders = orderRepositoryPort.findAll();
        if (orders.isEmpty()) throw new OrderNotFound("[ERROR] Orders Not Found");
        return orders;
    }

    public Optional<Order> getSingleOrder(String id) {
        Optional<Order> order = orderRepositoryPort.findById(id);
        if (order.isEmpty()) throw new OrderNotFound("[ERROR] Order " + id + " Not Found");
        return order;
    }

    public void deleteOrder(String id) {
        Optional<Order> order = orderRepositoryPort.findById(id);
        if (order.isEmpty()) throw new OrderNotFound("[ERROR] Order " + id + " Not Found");
        orderRepositoryPort.deleteById(id);
    }

    public void deleteAllOrders() {
        List<Order> orders = orderRepositoryPort.findAll();
        if (orders.isEmpty()) throw new OrderNotFound("[ERROR] No Orders to Delete");
        orderRepositoryPort.deleteAll();
    }

}
