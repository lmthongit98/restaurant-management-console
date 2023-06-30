package com.tma.training.restaurant.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BillRequestDto {
    private List<CustomerOrder> customerOrders;
}
