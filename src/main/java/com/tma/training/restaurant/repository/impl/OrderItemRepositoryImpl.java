package com.tma.training.restaurant.repository.impl;

import com.tma.training.restaurant.entity.OrderItem;
import com.tma.training.restaurant.repository.OrderItemRepository;

public class OrderItemRepositoryImpl extends SimpleCsvRepository<OrderItem> implements OrderItemRepository {
    private static OrderItemRepositoryImpl instance;

    private OrderItemRepositoryImpl() {
    }

    public static OrderItemRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new OrderItemRepositoryImpl();
        }
        return instance;
    }
}
