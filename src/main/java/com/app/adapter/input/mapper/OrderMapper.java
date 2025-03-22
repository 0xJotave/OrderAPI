package com.app.adapter.input.mapper;

import com.app.adapter.input.dto.OrderDTO;
import com.app.adapter.output.mongo.repository.entity.OrderEntity;
import com.app.domain.model.Order;

public interface OrderMapper {
    public static Order toModel(OrderDTO orderDTO) {
        return new Order(orderDTO.getOrderId(), orderDTO.getOrderExternalId(),
                orderDTO.getItems(), orderDTO.getTotalPrice(), orderDTO.getCreatedAt());
    }

    public static Order toModel(OrderEntity orderEntity) {
        return new Order(orderEntity.getOrderId(), orderEntity.getOrderExternalId(),
                orderEntity.getItems(), orderEntity.getTotalPrice(), orderEntity.getCreatedAt());
    }

    public static OrderDTO toDTO(Order order) {
        return new OrderDTO(order.getOrderId(), order.getOrderExternalId(),
                order.getItems(), order.getTotalPrice(), order.getCreatedAt());
    }

    public static OrderEntity toEntity(Order order) {
        return new OrderEntity(order.getOrderId(), order.getOrderExternalId(),
                order.getItems(), order.getTotalPrice(), order.getCreatedAt());
    }
}
