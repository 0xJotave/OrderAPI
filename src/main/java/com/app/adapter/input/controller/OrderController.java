package com.app.adapter.input.controller;

import com.app.adapter.input.dto.OrderDTO;
import com.app.adapter.input.mapper.OrderMapper;
import com.app.domain.model.Order;
import com.app.domain.service.OrderService;
import com.app.port.input.OrderControllerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController implements OrderControllerPort {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = OrderMapper.toModel(orderDTO);
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(OrderMapper.toDTO(createdOrder));
    }

    @Override
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders().stream().map(OrderMapper::toDTO).toList();
        return ResponseEntity.ok(orders);
    }

    @Override
    @GetMapping("/orders/{id}")
     public ResponseEntity<OrderDTO> getById(@PathVariable String id) {
        return orderService.getSingleOrder(id).map(order -> ResponseEntity.ok(OrderMapper.toDTO(order)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order " + id + " Deleted");
    }

    @Override
    @DeleteMapping("/orders")
    public ResponseEntity<String> deleteAllOrders() {
        orderService.deleteAllOrders();
        return ResponseEntity.ok("All Orders Deleted");
    }
}
