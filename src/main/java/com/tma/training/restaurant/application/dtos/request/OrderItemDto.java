package com.tma.training.restaurant.application.dtos.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "menuId")
public class OrderItemDto {
    private UUID menuId;
    private int quantity;
}
