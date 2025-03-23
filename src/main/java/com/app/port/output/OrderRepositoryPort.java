package com.app.port.output;

import com.app.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);
    List<Order> findAll();
    Optional<Order> findById(String id);
    void deleteById(String id);
    void deleteAll();
}
