package com.tma.training.restaurant.service;

import com.tma.training.restaurant.dto.request.CustomerOrder;
import com.tma.training.restaurant.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAllByBillId(String billId);

    void addMenuItems(String billId, List<CustomerOrder> customerOrders);
}
