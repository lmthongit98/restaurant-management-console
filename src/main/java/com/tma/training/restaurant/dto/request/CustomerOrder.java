package com.tma.training.restaurant.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOrder {
    private String menuId;
    private int quantity;
}
