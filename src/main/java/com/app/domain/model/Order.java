package com.app.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {
    private String orderId;
    private String orderExternalId;
    private List<Item> Items;
    private float totalPrice;
    private LocalDateTime createdAt;

    public Order(String orderId, String orderExternalId, List<Item> items, float totalPrice,
                 LocalDateTime createdAt) {
        this.orderId = orderId;
        this.orderExternalId = orderExternalId;
        Items = items;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderExternalId='" + orderExternalId + '\'' +
                ", Items=" + Items +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Float.compare(totalPrice, order.totalPrice) == 0 && Objects.equals(orderId, order.orderId) && Objects.equals(orderExternalId, order.orderExternalId) && Objects.equals(Items, order.Items) && Objects.equals(createdAt, order.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderExternalId, Items, totalPrice, createdAt);
    }
}
