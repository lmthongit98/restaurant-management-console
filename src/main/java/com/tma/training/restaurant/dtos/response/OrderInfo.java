package com.tma.training.restaurant.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class OrderInfo {
    private UUID menuId;
    private String menuName;
    private int quantity;
    private BigDecimal price;

    public OrderInfo(UUID menuId, String menuName, int quantity, BigDecimal price) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.quantity = quantity;
        this.price = price;
    }
}
