package com.tma.training.restaurant.domain.models;

import com.tma.training.restaurant.dto.request.OrderItemDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class OrderItemModel extends BaseModel {

    private UUID billId;

    private UUID menuId;

    private int quantity;

    private BigDecimal price;

    public static OrderItemModel create(UUID billId, OrderItemDto orderItemDto) {
        if (orderItemDto.getMenuId() == null || orderItemDto.getQuantity() <= 0) {
            throw new IllegalArgumentException("OrderItemDto is not valid");
        }
        return OrderItemModel.builder()
                .id(UUID.randomUUID())
                .billId(billId)
                .menuId(orderItemDto.getMenuId())
                .quantity(orderItemDto.getQuantity())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
