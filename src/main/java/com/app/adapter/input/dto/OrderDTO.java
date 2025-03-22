package com.app.adapter.input.dto;

import com.app.domain.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private String orderId;
    private String orderExternalId;
    private List<Item> Items;
    private float totalPrice;
    private LocalDateTime createdAt;

    public OrderDTO(String orderId, String orderExternalId, List<Item> items, float totalPrice,
                    LocalDateTime createdAt) {
        this.orderId = orderId;
        this.orderExternalId = orderExternalId;
        Items = items;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

    public OrderDTO() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderExternalId() {
        return orderExternalId;
    }

    public void setOrderExternalId(String orderExternalId) {
        this.orderExternalId = orderExternalId;
    }

    public List<Item> getItems() {
        return Items;
    }

    public void setItems(List<Item> items) {
        Items = items;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
