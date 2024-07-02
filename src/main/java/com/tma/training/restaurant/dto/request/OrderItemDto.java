package com.tma.training.restaurant.dto.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderItemDto {
    private UUID menuId;
    private int quantity;
}
