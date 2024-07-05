package com.tma.training.restaurant.domain.models;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class BillModel extends BaseModel {

    private final List<OrderItemModel> orderItems = new ArrayList<>();

    private BillModel(UUID id, LocalDateTime createdDate, LocalDateTime updatedDate, List<OrderItemModel> orderItems) {
        super(id, createdDate, updatedDate);
        if (orderItems != null) {
            this.orderItems.addAll(orderItems);
        }
    }

    public void removeOrderItem(UUID menuId) {
        orderItems.removeIf(item -> item.getMenu().getId().equals(menuId));
    }

    public void addOrderItem(OrderItemModel orderItem) {
        orderItem.validate();
        this.orderItems.add(orderItem);
    }

    public void validate() {
        // entity business rules validation to protect it's invariant before saving entity to a database
        if (orderItems.isEmpty()) {
            throw new IllegalArgumentException("Order items could not be empty");
        }
    }

    public BigDecimal getTotalAmount() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderItemModel orderItem : orderItems) {
            totalAmount = totalAmount.add(orderItem.getPrice());
        }
        return totalAmount;
    }

    public static class Builder {
        private UUID id;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private List<OrderItemModel> orderItems = new ArrayList<>();

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder updatedDate(LocalDateTime updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public Builder orderItems(List<OrderItemModel> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public BillModel build() {
            return new BillModel(id, createdDate, updatedDate, orderItems);
        }

    }

}
