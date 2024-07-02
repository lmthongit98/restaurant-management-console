package com.tma.training.restaurant.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BillReport {
    private UUID id;
    private List<OrderInfo> orderInfo;
    private BigDecimal totalPrice;

    public BillReport(UUID id, List<OrderInfo> orderInfo, BigDecimal totalPrice) {
        this.id = id;
        this.orderInfo = orderInfo;
        this.totalPrice = totalPrice;
    }
}
