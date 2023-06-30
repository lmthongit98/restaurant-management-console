package com.tma.training.restaurant.dto;

import lombok.Data;

import java.util.List;

@Data
public class BillDto {
    private List<CustomerOrder> customerOrders;

    @Data
    static class CustomerOrder {
        private String menuId;
        private int quantity;
    }
}
