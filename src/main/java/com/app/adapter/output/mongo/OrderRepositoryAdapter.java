package com.app.adapter.output.mongo;

import com.app.adapter.input.mapper.OrderMapper;
import com.app.adapter.output.mongo.repository.OrderRepository;
import com.app.adapter.output.mongo.repository.OrderRepositoryPort;
import com.app.adapter.output.mongo.repository.entity.OrderEntity;
import com.app.domain.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderRepository orderRepository;

    public OrderRepositoryAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        OrderEntity entity = OrderMapper.toEntity(order);
        OrderEntity savedEntity = orderRepository.save(entity);
        return OrderMapper.toModel(savedEntity);
    }

    public List<Order> findAll() {
        return orderRepository.findAll().stream().map(OrderMapper::toModel).toList();
    }

    public Optional<Order> findById(String id) {
        return orderRepository.findById(id).map(OrderMapper::toModel);
    }

    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }
}
