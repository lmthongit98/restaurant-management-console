package com.tma.training.restaurant.domain.models;

import com.tma.training.restaurant.dto.request.BillCreateDto;
import com.tma.training.restaurant.dto.request.OrderItemDto;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class BillModel extends BaseModel {

    private final List<OrderItemModel> orderItems = new ArrayList<>();

    private BillModel(UUID id, LocalDateTime createdDate, LocalDateTime updatedDate, List<OrderItemModel> orderItems) {
        super(id, createdDate, updatedDate);
        if (orderItems != null) {
            this.orderItems.addAll(orderItems);
        }
    }

    public static BillModel create(BillCreateDto billCreateDto) {
        BillModel billModel = new BillModel.Builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        for (OrderItemDto orderItemDto : billCreateDto.getOrderItems()) {
            billModel.orderItems.add(OrderItemModel.create(billModel.id, orderItemDto));
        }
        return billModel;
    }

    public void removeOrderItem(UUID orderItemId) {
        orderItems.removeIf(item -> item.getId().equals(orderItemId));
    }

    public void updateOrderItems(List<OrderItemDto> orderItemDtos) {
        Map<UUID, OrderItemModel> menuIdOrderItemMap = orderItems.stream().collect(Collectors.toMap(OrderItemModel::getMenuId, Function.identity()));
        for (OrderItemDto orderItemDto : orderItemDtos) {
            UUID menuId = orderItemDto.getMenuId();
            OrderItemModel orderItem = menuIdOrderItemMap.get(menuId);
            if (orderItem == null) { // new item
                orderItem = OrderItemModel.create(id, orderItemDto);
                orderItems.add(orderItem);
            } else { // existing item
                orderItem.setQuantity(orderItemDto.getQuantity());
            }
        }
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
