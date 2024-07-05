package com.tma.training.restaurant.infrastructure.persistences.csv.repositories.impl;

import com.tma.training.restaurant.infrastructure.persistences.csv.entities.OrderItem;
import com.tma.training.restaurant.infrastructure.persistences.csv.repositories.CsvOrderItemRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CsvOrderItemRepositoryImpl extends SimpleCsvRepository<OrderItem> implements CsvOrderItemRepository {
    private static CsvOrderItemRepositoryImpl instance;

    private CsvOrderItemRepositoryImpl() {
    }

    public static CsvOrderItemRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CsvOrderItemRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<OrderItem> findAllByBillId(String billId) {
        return findAll().stream().filter(orderItem -> billId.equals(orderItem.getBillId())).collect(Collectors.toList());
    }
}
