package com.tma.training.restaurant.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BillCreateDto {
    private Set<OrderItemDto> orderItems;
}
