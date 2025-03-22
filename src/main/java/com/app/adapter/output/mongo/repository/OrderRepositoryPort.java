package com.app.adapter.output.mongo.repository;

import com.app.adapter.output.mongo.repository.entity.OrderEntity;
import com.app.domain.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);
    List<Order> findAll();
    Optional<Order> findById(String id);
    void deleteById(String id);
    void deleteAll();
}
