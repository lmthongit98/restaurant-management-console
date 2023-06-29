package com.tma.training.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private String billId;
    private String menuId;
    private Integer quantities;
    private Double unitPrice;
}
