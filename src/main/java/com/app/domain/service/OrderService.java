package com.app.domain.service;

import com.app.domain.exception.ItemNullException;
import com.app.domain.exception.SendOrderFailedException;
import com.app.domain.model.Item;
import com.app.port.output.OrderRepositoryPort;
import com.app.domain.exception.OrderNotFoundException;
import com.app.domain.model.Order;
import com.app.port.input.OrderServicePort;
import com.app.port.output.WebClientPort;
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
    private final WebClientPort webClientPort;

    public OrderService(OrderRepositoryPort orderRepositoryPort, WebClientPort webClientPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.webClientPort = webClientPort;
    }

    @Override
    public Order createOrder(Order order) {
        System.out.println("Starting Order Creation...");
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalPrice(calculatePrice(order));
        Order savedOrder = orderRepositoryPort.save(order);
        try {
            webClientPort.sendOrder(savedOrder);
        } catch (Exception e) {
            throw new SendOrderFailedException("Error sending order to external service: " + e.getMessage());
        }
        return savedOrder;
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
        System.out.println("Featching All Orders...");
        List<Order> orders = orderRepositoryPort.findAll();
        if (orders.isEmpty()) throw new OrderNotFoundException("Orders Not Found");
        System.out.println("Total Orders Found: " + orders.size());
        return orders;
    }

    @Override
    @Cacheable("orders")
    public Optional<Order> getSingleOrder(String id) {
        System.out.printf("Featching Order %s %n", id);
        Optional<Order> order = orderRepositoryPort.findById(id);
        if (order.isEmpty()) throw new OrderNotFoundException("Order " + id + " Not Found");
        System.out.printf("Order %s Founded %n", id);
        return order;
    }

    @Override
    @CacheEvict(value="orders", key="#id")
    public void deleteOrder(String id) {
        System.out.printf("Deleting Order %s %n",id);
        Optional<Order> order = orderRepositoryPort.findById(id);

        if (order.isEmpty()) throw new OrderNotFoundException("Order " + id + " Not Found");
        orderRepositoryPort.deleteById(id);

        System.out.printf("Order %s Deleted %n", id);
    }

    @Override
    @CacheEvict(value = "orders", allEntries = true)
    public void deleteAllOrders() {
        System.out.println("Deleting All Orders...");
        List<Order> orders = orderRepositoryPort.findAll();
        if (orders.isEmpty()) throw new OrderNotFoundException("No Orders to Delete");
        orderRepositoryPort.deleteAll();
        System.out.println("All Orders Deleted");
    }
}