package com.tma.training.restaurant.repository.impl;

import com.tma.training.restaurant.entity.OrderItem;
import com.tma.training.restaurant.repository.OrderItemRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<OrderItem> findAllByBillId(String billId) {
        return findAll().stream().filter(orderItem -> billId.equals(orderItem.getBillId())).collect(Collectors.toList());
    }
}
