package com.tma.training.restaurant.dtos.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderItemDto {
    private UUID menuId;
    private int quantity;
}
