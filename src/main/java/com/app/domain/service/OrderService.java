package com.app.domain.service;

import com.app.adapter.input.dto.OrderDTO;
import com.app.domain.exception.ItemNullException;
import com.app.domain.model.Item;
import com.app.port.output.OrderRepositoryPort;
import com.app.domain.exception.OrderNotFoundException;
import com.app.domain.model.Order;
import com.app.port.input.OrderServicePort;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServicePort {
    private final OrderRepositoryPort orderRepositoryPort;

    public OrderService(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Order createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalPrice(calculatePrice(order));
        return orderRepositoryPort.save(order);
    }

    public BigDecimal calculatePrice(Order order) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Item item : order.getItems()) {
            if (item.getPrice() != null && item.getQuantity() != null) {
                totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            } else {
                throw new ItemNullException("Price or Quantity cannot be Null");
            }
        }
        return totalPrice;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepositoryPort.findAll();
        if (orders.isEmpty()) throw new OrderNotFoundException("Orders Not Found");
        return orders;
    }

    @Override
    @Cacheable("orders")
    public Optional<Order> getSingleOrder(String id) {
        Optional<Order> order = orderRepositoryPort.findById(id);
        if (order.isEmpty()) throw new OrderNotFoundException("Order " + id + " Not Found");
        return order;
    }

    @Override
    @CacheEvict("orders")
    public void deleteOrder(String id) {
        Optional<Order> order = orderRepositoryPort.findById(id);
        if (order.isEmpty()) throw new OrderNotFoundException("Order " + id + " Not Found");
        orderRepositoryPort.deleteById(id);
    }

    @Override
    @CacheEvict(value = "orders", allEntries = true)
    public void deleteAllOrders() {
        List<Order> orders = orderRepositoryPort.findAll();
        if (orders.isEmpty()) throw new OrderNotFoundException("[ERROR] No Orders to Delete");
        orderRepositoryPort.deleteAll();
    }
}
