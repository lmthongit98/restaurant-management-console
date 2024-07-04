package com.tma.training.restaurant.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BillCreateDto {
    private List<OrderItemDto> orderItems;
}
