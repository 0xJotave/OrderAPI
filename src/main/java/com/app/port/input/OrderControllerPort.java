package com.app.port.input;

import com.app.adapter.input.dto.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderControllerPort {
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO);
    public ResponseEntity<List<OrderDTO>> getAllOrders();
    public ResponseEntity<OrderDTO> getById(@PathVariable String id);
    public ResponseEntity<String> deleteById(@PathVariable String id);
    public ResponseEntity<String> deleteAllOrders();
}
