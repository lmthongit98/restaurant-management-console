package com.tma.training.restaurant.infra.csv.repository;

import com.tma.training.restaurant.infra.csv.entity.OrderItem;

import java.util.List;

public interface CsvOrderItemRepository extends CrudRepository<OrderItem, String> {
    List<OrderItem> findAllByBillId(String billId);
}
