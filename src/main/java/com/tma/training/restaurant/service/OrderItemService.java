package com.tma.training.restaurant.service;

import com.tma.training.restaurant.dto.OrderItemDto;
import com.tma.training.restaurant.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAllByBillId(String billId);
}
